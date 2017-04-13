package com.csi.sanketbhimani.csi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    LinearLayout ll;
    private View progressView;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ll = (LinearLayout) findViewById(R.id.ll_home);
        progressView = findViewById(R.id.home_progress);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        showProgress(true);
        GetEventDetails getEventDetails= new GetEventDetails(this);
        getEventDetails.execute((Void) null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {
            SharedPreferences preferences = getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
            preferences.edit().remove("user_name").commit();
            preferences.edit().remove("user_email").commit();
            preferences.edit().remove("user_year").commit();
            preferences.edit().remove("user_branch").commit();
            preferences.edit().remove("user_password").commit();
            preferences.edit().remove("user_id").commit();

            Intent myIntent = new Intent(this, LoginActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            this.startActivity(myIntent);

        }
        else if(id == R.id.clear_app_data){
            SharedPreferences preferences = getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
            preferences.edit().clear().commit();
            Intent myIntent = new Intent(this, LoginActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            this.startActivity(myIntent);
        }
        else if(id == R.id.show_registered_events){
            Intent myIntent = new Intent(this, Ragistered_events.class);
            //myIntent.putExtra("key", value); //Optional parameters
            this.startActivity(myIntent);
        }
        else if(id == R.id.edit_details){
            Intent myIntent = new Intent(this, Edit_details.class);
            //myIntent.putExtra("key", value); //Optional parameters
            this.startActivity(myIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            ll.setVisibility(show ? View.GONE : View.VISIBLE);
            ll.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ll.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            ll.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    class GetEventDetails extends AsyncTask <Void, Void, Boolean> implements View.OnClickListener{

        HomeActivity ha;

        GetEventDetails(HomeActivity h){
            ha = h;
        }

        //List<String> eventName = new ArrayList<String>();
        //List<String> fee = new ArrayList<String>();
        //List<String> date = new ArrayList<String>();
        //List<Drawable> image = new ArrayList<Drawable>();
        String server_response;
        Bitmap b = null;
        SharedPreferences sharedpreferences;
        @Override
        protected Boolean doInBackground(Void... params) {
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            boolean flg = true;

            sharedpreferences = getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
//           int id = sharedpreferences.getInt("total_count",0)+1;
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            if(cm.getActiveNetworkInfo() == null){
                flg = false;
            }
            if(flg) {


                List<String> ids = new ArrayList();

                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = null;
                //Log.e("###", "http://192.168.2.17/CSI_ANDROID_APP_SERVER/getbyid.php?id=" + id);
                HttpGet httpget = new HttpGet("http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/gettotalid.php");
                try {
                    response = httpclient.execute(httpget);
                    server_response = EntityUtils.toString(response.getEntity());
                    JSONObject obj = new JSONObject(server_response);

                    int total_no = Integer.parseInt(obj.getJSONObject("event").getString("total_event"));
                    for(int i = 1 ; i<= total_no ; i++){
                        ids.add(obj.getJSONObject("event").getString(i+""));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                for (String i : ids) {
                     if(sharedpreferences.getString("name"+i,"HELLLLLLO").equals("HELLLLLLO")){
                    Log.e("!@#$%^", i);
                    httpget = new HttpGet("http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/getbyid.php?id=" + i);
                    try {
                        response = httpclient.execute(httpget);
                        server_response = EntityUtils.toString(response.getEntity());
                        JSONObject obj = new JSONObject(server_response);

                        if (obj.getJSONObject("event").getString("validEvent").equals("yes")) {
                            Log.e("@@@", obj.getJSONObject("event").getString("validEvent") + " " + obj.getJSONObject("event").getString("name") + " " + obj.getJSONObject("event").getString("fee") + " " + obj.getJSONObject("event").getString("date"));
                            editor.putString("name" + obj.getJSONObject("event").getString("eventid"), obj.getJSONObject("event").getString("name"));
                            editor.putString("fee" + obj.getJSONObject("event").getString("eventid"), obj.getJSONObject("event").getString("fee"));
                            editor.putString("date" + obj.getJSONObject("event").getString("eventid"), obj.getJSONObject("event").getString("date"));
                            editor.putString("text" + obj.getJSONObject("event").getString("eventid"), obj.getJSONObject("event").getString("text"));
                            editor.putString("time" + obj.getJSONObject("event").getString("eventid"), obj.getJSONObject("event").getString("time"));
                            editor.putString("venue" + obj.getJSONObject("event").getString("eventid"), obj.getJSONObject("event").getString("venue"));
                            editor.putString("contact" + obj.getJSONObject("event").getString("eventid"), obj.getJSONObject("event").getString("contact"));
                            editor.putString("registered" + obj.getJSONObject("event").getString("eventid"), "false");
                            editor.putString("total_count", sharedpreferences.getString("total_count", "0") + "," + obj.getJSONObject("event").getString("eventid"));
                            editor.commit();
                            //eventName.add(obj.getJSONObject("event").getString("name"));
                            //fee.add(obj.getJSONObject("event").getString("fee"));
                            //date.add(obj.getJSONObject("event").getString("date"));
                            String urlimage = "http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/images/" + obj.getJSONObject("event").getString("eventid") + ".jpeg";

                            try {
                                b = drawable_from_url(urlimage);
                                Log.e("image path", saveToInternalStorage(b, obj.getJSONObject("event").getString("eventid")));
                                //image.add(new BitmapDrawable(getResources(),b));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                }
            }
            return true;
        }
        String i;
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        protected void onPostExecute(final Boolean success) {
            if(success == true){
                CardView[] cv = new CardView[100];
                LinearLayout ll = (LinearLayout) findViewById(R.id.ll_home);
                TableLayout[] tl = new TableLayout[100];
                View[] v = new View[100];
                View[] v1 = new View[100];
                TableRow[] tr1 = new TableRow[100];
                TableRow[] tr2 = new TableRow[100];
                TableRow[] tr3 = new TableRow[100];
                ImageButton[] ib = new ImageButton[100];
                TextView[] tv1 = new TextView[100];
                TextView[] tv2 = new TextView[100];
                TextView[] tv3 = new TextView[100];

                //Iterator<String> n = eventName.iterator();
                //Iterator<String> f = fee.iterator();
                //Iterator<String> d = date.iterator();
                //Iterator<Drawable> img = image.iterator();
                i = sharedpreferences.getString("total_count","0");
                Log.e("%%%",i+"");
                List<String> item_no = Arrays.asList(i.split(","));
                for (String i : item_no ) {

                        int j = Integer.parseInt(i);
                    if(j!=0) {
                        cv[j] = new CardView(ha);
                        ib[j] = new ImageButton(ha);
                        tv1[j] = new TextView(ha);
                        tv2[j] = new TextView(ha);
                        tv3[j] = new TextView(ha);
                        tl[j] = new TableLayout(ha);
                        tr1[j] = new TableRow(ha);
                        tr2[j] = new TableRow(ha);
                        tr3[j] = new TableRow(ha);
                        v[j] = new View(ha);
                        v1[j] = new View(ha);

                        cv[j].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
                        cv[j].setRadius(12);
                        //cv[i].setPadding(0,6,0,6);


                        //Drawable image = img.next();
                        ib[j].setBackgroundColor(Color.BLUE);
                        try {
                            ib[j].setBackground(new BitmapDrawable(loadImageFromStorage(new Integer(i).toString())));
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        ib[j].setMinimumHeight(300);
                        ib[j].setMaxHeight(300);
                        ib[j].setAdjustViewBounds(true);
                        ib[j].setScaleType(ImageView.ScaleType.CENTER_CROP);
                        ib[j].setCropToPadding(true);
                        ib[j].setOnClickListener(this);
                        ib[j].setId(j);

                        tv1[j].setText(sharedpreferences.getString("name" + i, "CSI Event"));
                        tv1[j].setPadding(12, 0, 12, 12);
                        tv1[j].setTextColor(Color.WHITE);
                        tv1[j].setTextSize(48);
                        tv1[j].setGravity(Gravity.CENTER);
                        tv1[j].setMinWidth(cv[j].getWidth() - 100);
                        tv1[j].setShadowLayer(3, 6, 6, Color.BLACK);

                        tv2[j].setText(sharedpreferences.getString("fee" + i, "Fee"));
                        tv2[j].setPadding(12, 0, 12, 12);
                        tv2[j].setTextColor(Color.WHITE);
                        tv2[j].setTextSize(30);
                        tv2[j].setShadowLayer(3, 6, 6, Color.BLACK);
                        //tv2[i].setGravity(Gravity.RIGHT);
                        //tv2[i].setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                        tv3[j].setText(sharedpreferences.getString("date" + i, "Date"));
                        tv3[j].setPadding(12, 0, 12, 12);
                        tv3[j].setTextColor(Color.WHITE);
                        tv3[j].setTextSize(30);
                        tv3[j].setShadowLayer(3, 3, 3, Color.BLACK);
                        //tv3[i].setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                        tr1[j].addView(tv1[j]);
                        //tr1[i].setMinimumWidth(cv[i].getWidth());
                        tr1[j].setMinimumHeight(100);

                        //tr2[i].addView(tv3[i]);
                        tr2[j].addView(tv2[j]);
                        tr2[j].setMinimumHeight(90);

                        tr3[j].addView(tv3[j]);

                        v[j].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30));

                        //tl[i].setColumnStretchable(2,true);
                        tl[j].setMinimumWidth(cv[j].getWidth());
                        tl[j].setMinimumHeight(cv[j].getHeight());
                        tl[j].addView(tr1[j]);
                        tl[j].addView(v[j]);
                        tl[j].addView(tr2[j]);
                        tl[j].addView(tr3[j]);

                        cv[j].addView(ib[j]);
                        cv[j].addView(tl[j]);

                        ll.addView(cv[j]);

                        v1[j].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 12));

                        ll.addView(v1[j]);
                        //   i--;
                    }
                }
                showProgress(false);
            }
        }

        Bitmap drawable_from_url(String url) throws java.net.MalformedURLException, java.io.IOException {
            Bitmap x;

            HttpURLConnection connection = (HttpURLConnection)new URL(url) .openConnection();
            connection.setRequestProperty("User-agent","Chrome/4.0");

            connection.connect();
            InputStream input = connection.getInputStream();

            x = BitmapFactory.decodeStream(input);
            return x;
        }
        private String saveToInternalStorage(Bitmap bitmapImage,String name) throws IOException {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("images", Context.MODE_PRIVATE);
            // Create imageDir
            File mypath=new File(directory,name+".jpeg");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fos.close();
            }
            return directory.getAbsolutePath();
        }
        private Bitmap loadImageFromStorage(String filename) throws PackageManager.NameNotFoundException {

            PackageManager m = getPackageManager();
            PackageInfo p = m.getPackageInfo(getPackageName(), 0);
            String path = p.applicationInfo.dataDir + "/app_images";

            try {
                File f=new File(path, filename+".jpeg");
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                return b;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            Log.i("LoginActivity", "Sign Up Activity activated.");
            Intent myIntent = new Intent(ha, event_detail.class);
            myIntent.putExtra("eventid",id+"");
            ha.startActivity(myIntent);
        }
    }
}
