package com.csi.sanketbhimani.csi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Android login screen Activity
 */
public class LoginActivity extends Activity{

    private static final String DUMMY_CREDENTIALS = "user@test.com:hello";

    private UserLoginTask userLoginTask = null;
    private View loginFormView;
    private View progressView;
    private AutoCompleteTextView emailTextView;
    private EditText passwordTextView;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
        String reg = sharedpreferences.getString("user_id","not_registered");

        if(!reg.equals("not_registered")){
            Intent myIntent = new Intent(this, HomeActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            this.startActivity(myIntent);
        }
        else {
            setContentView(R.layout.activity_login);


            emailTextView = (AutoCompleteTextView) findViewById(R.id.email);

            passwordTextView = (EditText) findViewById(R.id.password);
            passwordTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == EditorInfo.IME_NULL) {
                        initLogin();
                        return true;
                    }
                    return false;
                }
            });

            Button loginButton = (Button) findViewById(R.id.email_sign_in_button);
            loginButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    initLogin();
                }
            });

            loginFormView = findViewById(R.id.login_form);
            progressView = findViewById(R.id.login_progress);

            //adding underline and link to signup textview
            signUpTextView = (TextView) findViewById(R.id.signUpTextView);
            signUpTextView.setPaintFlags(signUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            Linkify.addLinks(signUpTextView, Linkify.ALL);

            signUpTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("LoginActivity", "Sign Up Activity activated.");
                    Intent myIntent = new Intent(LoginActivity.this, SignupActivity.class);
                    //myIntent.putExtra("key", value); //Optional parameters
                    LoginActivity.this.startActivity(myIntent);
                }
            });
        }
    }




    /**
     * Validate Login form and authenticate.
     */
    public void initLogin() {
        if (userLoginTask != null) {
            return;
        }

        emailTextView.setError(null);
        passwordTextView.setError(null);

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        boolean cancelLogin = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordTextView.setError(getString(R.string.invalid_password));
            focusView = passwordTextView;
            cancelLogin = true;
        }

        if (TextUtils.isEmpty(email)) {
            emailTextView.setError(getString(R.string.field_required));
            focusView = emailTextView;
            cancelLogin = true;
        } else if (!isEmailValid(email)) {
            emailTextView.setError(getString(R.string.invalid_email));
            focusView = emailTextView;
            cancelLogin = true;
        }

        if (cancelLogin) {

        focusView.requestFocus();
    } else {
        // show progress spinner, and start background task to login
        showProgress(true);
        userLoginTask = new UserLoginTask(email, password, this);
        userLoginTask.execute((Void) null);
    }
}

    private boolean isEmailValid(String email) {
        //add your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //add your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }






    /**
     * Async Login Task to authenticate
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String emailStr;
        private final String passwordStr;
        public LoginActivity laclass;

        UserLoginTask(String email, String password, LoginActivity la) {
            emailStr = email;
            passwordStr = password;
            laclass = la;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            String server_response = "false";
            //this is where you should write your authentication code
            // or call external service
            // following try-catch just simulates network access
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = null;
            try {

                Log.e("###","http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/authenticateUser.php?email="+ URLEncoder.encode(emailStr, "UTF-8")+"&password="+URLEncoder.encode(passwordStr, "UTF-8"));

            httpget= new HttpGet("http://192.168.43.190/csi_data/CSI_ANDROID_APP_SERVER/authenticateUser.php?email="+ URLEncoder.encode(emailStr, "UTF-8")+"&password="+URLEncoder.encode(passwordStr, "UTF-8"));
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

                    JSONObject obj = new JSONObject(server_response);
                    //Log.e("res ", obj.getJSONObject("user_info").getString("valid"));
                    String rrr = obj.getJSONObject("user_info").getString("valid");
                    Log.e("res ", rrr.toString());
                    if (rrr.trim().equals("true")) {
                        SharedPreferences sharedpreferences = getSharedPreferences("CSI_OFFLINE_DATA", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        Log.e("@@@", obj.getJSONObject("event").getString("validEvent") + " " + obj.getJSONObject("event").getString("name") + " " + obj.getJSONObject("event").getString("fee") + " " + obj.getJSONObject("event").getString("date"));
                        editor.putString("user_name", obj.getJSONObject("user_info").getString("user_name"));
                        editor.putString("user_email", obj.getJSONObject("user_info").getString("user_email"));
                        editor.putString("user_year", obj.getJSONObject("user_info").getString("user_year"));
                        editor.putString("user_branch", obj.getJSONObject("user_info").getString("user_branch"));
                        editor.putString("user_password", obj.getJSONObject("user_info").getString("user_password"));
                        editor.putString("user_id", obj.getJSONObject("user_info").getString("user_id"));
                        editor.commit();
                        Log.e("res jhhjhghg", obj.getJSONObject("user_info").getString("valid"));
                        return true;
                    }
                    else{
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Server response", server_response );
            } else {
                Log.e("Server response", "Failed to get server response" );
            }
            return false;
            //using a local dummy credentials store to authenticate
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            userLoginTask = null;
            //stop the progress spinner
            showProgress(false);

            if (success) {
                Intent myIntent = new Intent(laclass, HomeActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                laclass.startActivity(myIntent);
                //  login success and move to main Activity here.
            } else {
                // login failure
                passwordTextView.setError(getString(R.string.incorrect_password));
                passwordTextView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            userLoginTask = null;
            showProgress(false);
        }
    }
}