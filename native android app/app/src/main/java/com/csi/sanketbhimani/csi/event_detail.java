package com.csi.sanketbhimani.csi;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class event_detail extends AppCompatActivity {

    private TextView stickyView;
    private ListView listView;
    private View heroImageView;

    private View stickyViewSpacer;

    private int MAX_ROWS = 20;
    String id;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Intent intent = getIntent();
        id = intent.getStringExtra("eventid");
        Log.e("@#$",id);
        /* Initialise list view, hero image, and sticky view */

        listView = (ListView) findViewById(R.id.listView);
        heroImageView = findViewById(R.id.heroImageView);
        stickyView = (TextView) findViewById(R.id.stickyView);

        /* Inflate list header layout */
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listHeader = inflater.inflate(R.layout.activity_list_header, null);
        stickyViewSpacer = listHeader.findViewById(R.id.stickyViewPlaceholder);

        /* Add list view header */
        listView.addHeaderView(listHeader);

        /* Handle list View scroll events */
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                /* Check if the first item is already reached to top.*/
                if (listView.getFirstVisiblePosition() == 0) {
                    View firstChild = listView.getChildAt(0);
                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }

                    int heroTopY = stickyViewSpacer.getTop();
                    stickyView.setY(Math.max(0, heroTopY + topY));

                    /* Set the image to scroll half of the amount that of ListView */
                    heroImageView.setY(topY * 1f);
                }
            }
        });

        try {
            heroImageView.setBackground(new BitmapDrawable(loadImageFromStorage(id)));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);

        stickyView.setText(sharedpreferences.getString("name"+id,"CSI Event"));

        /* Populate the ListView with sample data */

        List<String> title = new ArrayList<>();
        List<String> value = new ArrayList<>();
        title.add("Name:");
        title.add("Details:");
        title.add("Date:");
        title.add("Time:");
        title.add("Venue:");
        title.add("Contact:");
        title.add("Fee:");
        title.add("ragister");
        value.add(sharedpreferences.getString("name"+id,"CSI Event"));
        value.add(sharedpreferences.getString("text"+id,"Details"));
        value.add(sharedpreferences.getString("date"+id,"Date"));
        value.add(sharedpreferences.getString("time"+id,"Time"));
        value.add(sharedpreferences.getString("venue"+id,"Venue"));
        value.add(sharedpreferences.getString("contact"+id,"Contact"));
        value.add(sharedpreferences.getString("fee"+id,"Fee"));
        value.add("ragister");

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, title, value, id);
        listView.setAdapter(adapter);





        //modelList.add(tv[0]);
        //listView.addView(tv[0]);
        //listView.addView(tv[1]);
        //listView.addView(tv[2]);
        //listView.addView(tv[3]);



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


}
class CustomArrayAdapter extends BaseAdapter {

    List<String> title;
    List<String> value;
    Context context;
    String event_id;
    private static LayoutInflater inflater=null;

    public CustomArrayAdapter(event_detail ed, List<String> t, List<String> v, String id){
        title = t;
        value = v;
        context=ed;
        event_id = id;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        if(title.get(position) == "ragister"){
            SharedPreferences sharedpreferences;
            sharedpreferences = context.getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
            if(sharedpreferences.getString("registered"+event_id,"CSI Event").equals("false")) {
                view = inflater.inflate(R.layout.detail_button, null);
                Button b = (Button) view.findViewById(R.id.reg_btn);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(context, Ragister_Event.class);
                        myIntent.putExtra("eventid", event_id + "");
                        context.startActivity(myIntent);
                    }
                });
            }
            else{
                view = inflater.inflate(R.layout.activity_list_item_detail,null);
                TextView tv1 = (TextView) view.findViewById(R.id.title);
                TextView tv2 = (TextView) view.findViewById(R.id.value);
                tv1.setText("You are already registered");
                tv2.setText("");
            }
        }
        else{
            view = inflater.inflate(R.layout.activity_list_item_detail,null);
            TextView tv1 = (TextView) view.findViewById(R.id.title);
            TextView tv2 = (TextView) view.findViewById(R.id.value);
            tv1.setText(title.get(position));
            tv2.setText(value.get(position));
        }


        return view;
    }
}
