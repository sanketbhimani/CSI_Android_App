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
import android.widget.RadioGroup;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameTextView;
    private EditText emailTextView;
    private EditText passwordTextView;
    private EditText yearTextView;
    private EditText branchTextView;
    private Button signUpButton;
    private View signupFormView;
    private View progressView;
    private RadioGroup mem;
    private EditText csi_id;
    String a = "no";

    String password;
    String name;
    String year;
    String branch;
    String email;

    UserSignUp userSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailTextView = (EditText) findViewById(R.id.email_signup);
        passwordTextView = (EditText) findViewById(R.id.password_signup);
        yearTextView = (EditText) findViewById(R.id.year_signup);
        branchTextView = (EditText) findViewById(R.id.branch_signup);
        nameTextView = (EditText) findViewById(R.id.name_signup);
        signUpButton = (Button) findViewById(R.id.email_sign_up_button);

        mem = (RadioGroup) findViewById(R.id.mem);
        mem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.mem_yes){
                    findViewById(R.id.mem_id).setVisibility(View.VISIBLE);
                    a="yes";
                }
                else if(checkedId == R.id.mem_no){
                    findViewById(R.id.mem_id).setVisibility(View.GONE);
                    a="no";
                }
            }
        });
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View V){
        if(V.getId() == R.id.email_sign_up_button){
            showProgress(true);
            email = emailTextView.getText().toString();
            name = nameTextView.getText().toString();
            password = passwordTextView.getText().toString();
            year = yearTextView.getText().toString();
            branch = branchTextView.getText().toString();

            csi_id = (EditText)findViewById(R.id.mem_id);
            String a = csi_id.getText().toString();
            userSignup = new UserSignUp(name,email,year,branch,password,a,this);
            userSignup.execute((Void) null);
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

class UserSignUp extends AsyncTask<Void, Void, String>{

    SignupActivity sa_class;
    String name;
    String email;
    String password;
    String year;
    String branch;
    String csi_id;
    UserSignUp(String n, String e, String y, String b, String p, String id, SignupActivity sa){

        sa_class = sa;
        name = n;
        email = e;
        year = y;
        branch = b;
        password = p;
        csi_id = id;

    }

    @Override
    protected String doInBackground(Void... params) {
        String server_response = "false";
        //this is where you should write your authentication code
        // or call external service
        // following try-catch just simulates network access
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget= null;
        try {
            if(sa_class.a.equals("yes")) {
                Log.e("###", "http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/addUser.php?is_csi_member=" + "yes" + "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8") + "&name=" + URLEncoder.encode(name, "UTF-8") + "&year=" + URLEncoder.encode(year, "UTF-8") + "&branch=" + URLEncoder.encode(branch, "UTF-8"));


                httpget = new HttpGet("http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/addUser.php?is_csi_member=" + "yes" + "&csi_id="+csi_id+"&email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8") + "&name=" + URLEncoder.encode(name, "UTF-8") + "&year=" + URLEncoder.encode(year, "UTF-8") + "&branch=" + URLEncoder.encode(branch, "UTF-8"));
            }else if(sa_class.a.equals("no")){
                httpget = new HttpGet("http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/addUser.php?is_csi_member=" + "no" + "&email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8") + "&name=" + URLEncoder.encode(name, "UTF-8") + "&year=" + URLEncoder.encode(year, "UTF-8") + "&branch=" + URLEncoder.encode(branch, "UTF-8"));

            }
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
            return server_response;
        } else {
            return "false";
        }
    }


    @Override
    protected void onPostExecute(final String success) {
        sa_class.userSignup = null;
        //stop the progress spinner
        sa_class.showProgress(false);

        if (!success.equals("false")) {
            SharedPreferences sharedpreferences;
            sharedpreferences = sa_class.getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("user_name",name);
            editor.putString("user_email",email);
            editor.putString("user_year",year);
            editor.putString("user_branch",branch);
            editor.putString("user_password",password);
            editor.putString("user_id",success);
            editor.commit();
            Intent myIntent = new Intent(sa_class, HomeActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            sa_class.startActivity(myIntent);//  login success and move to main Activity here.
        } else {
            // login failure
        }
    }

}