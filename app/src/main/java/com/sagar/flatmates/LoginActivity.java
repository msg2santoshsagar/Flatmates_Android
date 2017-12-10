package com.sagar.flatmates;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sagar.flatmates.com.sagar.flatmates.com.sagar.flatmates.fcm.CustomFirebaseInstanceIdService;
import com.sagar.flatmates.com.sagar.flatmates.constants.URLConstants;
import com.sagar.flatmates.com.sagar.flatmates.service.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    private static  final String          TAG         = LoginActivity.class.getSimpleName();
    private         final LoginActivity   CONTEXT     = LoginActivity.this;

    private EditText        userNameText, passwordText;
    private Switch          rememberMeSwitch;
    private TextView        errorMessageText;
    private ProgressBar     progressBar;
    private RelativeLayout  parentLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton                  =  (Button)         findViewById(R.id.loginButton);
        userNameText                        =  (EditText)       findViewById(R.id.username);
        passwordText                        =  (EditText)       findViewById(R.id.password);
        rememberMeSwitch                    =  (Switch)         findViewById(R.id.rememberMeSwitch);
        errorMessageText                    =  (TextView)       findViewById(R.id.errorMessage);
        progressBar                         =  (ProgressBar)    findViewById(R.id.progressBar);
        parentLayout                        =  (RelativeLayout) findViewById(R.id.parentLayout);

        initializeWidgetValues();
        CustomFirebaseInstanceIdService.sendRegistrationTokenToServer();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Login Button clicked");
                Log.d(TAG, "FCM TOKEN VALUE :: "+ CustomFirebaseInstanceIdService.getFcmToken());

                errorMessageText.setVisibility(View.INVISIBLE);

                String userName         = userNameText.getText().toString();
                String password         = passwordText.getText().toString();
                boolean rememberMeFlaag = rememberMeSwitch.isChecked();

                Log.d(TAG, "USER NAME : "+userName);
                Log.d(TAG, "PASSWORD  : "+password);
                Log.d(TAG, "REMEMBER ME FLAG : "+rememberMeFlaag);

                if( isEmptyString(userName) ){
                    userNameText.setError("User Name can't be blank.");
                }

                if( isEmptyString(password) ){
                    passwordText.setError("Password can't be blank.");
                    return;
                }

                RequestParams requestParams = new RequestParams();
                requestParams.add("username",userName);
                requestParams.add("password",password);

                showSpinner();

                HttpUtils.post(URLConstants.API_LOGIN, requestParams, new TextHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d(TAG, "SUCCESS RESPONSE FROM SERVER : " + responseString);
                        Intent intent = new Intent( CONTEXT , Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        hideSpinner();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e(TAG, "FAILURE RESPONSE FROM SERVER :  statusCode="+statusCode+", Response =" + responseString);
                        JSONObject jsonObject;
                        try{
                            jsonObject = new JSONObject(responseString);
                            Log.d(TAG,"Json parsed : "+jsonObject);
                            String errMessage = jsonObject.getString("message");
                            errorMessageText.setText(errMessage);
                            errorMessageText.setVisibility(View.VISIBLE);
                        }catch(JSONException | NullPointerException e){
                            Log.e(TAG,"Error Occured : "+e);
                            errorMessageText.setText("Unknown error occured while login!!");
                            errorMessageText.setVisibility(View.VISIBLE);
                        }
                        hideSpinner();
                    }

                });
       }
        });
    }

    private void initializeWidgetValues() {
        userNameText.setText("admin");
        passwordText.setText("admin");
        rememberMeSwitch.setChecked(true);
        errorMessageText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private boolean isEmptyString(String str){
        return  "".equals(str.trim());
    }


    public void showSpinner() {
        progressBar.setVisibility(View.VISIBLE);
       // parentLayout.setAlpha(Color.CYAN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideSpinner() {
        progressBar.setVisibility(View.GONE);
        //parentLayout.setBackgroundColor(Color.WHITE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}