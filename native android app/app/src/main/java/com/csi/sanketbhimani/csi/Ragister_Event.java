package com.csi.sanketbhimani.csi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Ragister_Event extends AppCompatActivity implements View.OnClickListener{
    private View signupFormView;
    private View progressView;
    String id;
    EditText code;
    Button b;
    String user_id;
    String code_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister__event);
        Intent intent = getIntent();
        id = intent.getStringExtra("eventid");
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
        user_id =  sharedpreferences.getString("user_id","ERROR");
        code = (EditText) findViewById(R.id.code);
        b = (Button) findViewById(R.id.ragister_event);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ragister_event){
            showProgress(true);
            code_str = code.getText().toString();


            event_reg er = new event_reg(code_str,user_id,id,this);
            er.execute((Void) null);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {

        signupFormView = findViewById(R.id.signup_form);
        progressView = findViewById(R.id.signup_progress);
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            signupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            signupFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    signupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            signupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
class event_reg extends AsyncTask<Void, Void, String> {

    Ragister_Event sa_class;
    String code;
    String userid;
    String eventid;

    event_reg(String cd, String ui, String i, Ragister_Event sa){

        sa_class = sa;
        code = cd;
        userid = ui;
        eventid = i;
    }

    @Override
    protected String doInBackground(Void... params) {
        String server_response = "false";
        //this is where you should write your authentication code
        // or call external service
        // following try-catch just simulates network access
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = null;
        try {
            Log.e("###","http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/reg_event.php?code="+ URLEncoder.encode(code, "UTF-8")+"&userid="+URLEncoder.encode(userid, "UTF-8")+"&eventid="+URLEncoder.encode(eventid, "UTF-8"));

        httpget= new HttpGet("http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/reg_event.php?code="+ URLEncoder.encode(code, "UTF-8")+"&userid="+URLEncoder.encode(userid, "UTF-8")+"&eventid="+URLEncoder.encode(eventid, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response.getStatusLine().getStatusCode()==200){
            try {
                server_response = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("***",server_response);
            return server_response;
        } else {
            return "false";
        }
    }


    @Override
    protected void onPostExecute(final String success) {

        //stop the progress spinner
        sa_class.showProgress(false);

        if (!success.equals("false")) {
            SharedPreferences sharedpreferences;
            sharedpreferences = sa_class.getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("registered"+eventid,"true");
            editor.commit();
            Intent myIntent = new Intent(sa_class, HomeActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            sa_class.startActivity(myIntent);//  login success and move to main Activity here.
        } else {
            // login failure
        }
    }

}