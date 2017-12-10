package com.sagar.flatmates.com.sagar.flatmates.com.sagar.flatmates.fcm;

import android.os.Build;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sagar.flatmates.com.sagar.flatmates.com.sagar.flatmates.domain.FCM;
import com.sagar.flatmates.com.sagar.flatmates.com.sagar.flatmates.domain.User;
import com.sagar.flatmates.com.sagar.flatmates.constants.URLConstants;
import com.sagar.flatmates.com.sagar.flatmates.service.GeneralUtils;
import com.sagar.flatmates.com.sagar.flatmates.service.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by santosh sagar on 10-12-2017.
 */

public class CustomFirebaseInstanceIdService extends FirebaseInstanceIdService{

    private static  final String          TAG         = CustomFirebaseInstanceIdService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = getFcmToken();
        Log.d(TAG,"Refreshed Token : "+refreshedToken);
        sendRegistrationTokenToServer();
    }

    public static void sendRegistrationTokenToServer() {
        String fcmToken  = getFcmToken();
        Log.d(TAG,"Request to send Token to server : "+fcmToken);

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;

        FCM  fcm  = new FCM()
                .setUserName("admin")
                .setDeviceManufacturer(manufacturer)
                .setDeviceModel(model)
                .setDeviceVersion(String.valueOf(version))
                .setDeviceVersionRelease(versionRelease)
                .setFcmToken(fcmToken);

        Log.d(TAG, "FCM OBJECT :: "+fcm);

       try{

           Log.d(TAG, "BEFORE CREATING REQUEST PARAM ");
           RequestParams requestParams = new RequestParams(GeneralUtils.convertValue(fcm));
           requestParams.setUseJsonStreamer(true);
           Log.d(TAG, "AFTER CREATING REQUEST PARAM "+requestParams.toString());

           HttpUtils.postAuthorized(URLConstants.API_SAVE_TOKEN,requestParams,new JsonHttpResponseHandler(){

               @Override
               public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                   Log.e(TAG, "FAILURE RESPONSE FROM SERVER : statusCode ="+statusCode+", responseString ="+responseString);
               }

               @Override
               public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                   Log.e(TAG, "FAILURE RESPONSE FROM SERVER : statusCode ="+statusCode+", JSON ARRAY ="+errorResponse);
               }

               @Override
               public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                   Log.e(TAG, "FAILURE RESPONSE FROM SERVER : statusCode ="+statusCode+", JSON OBJECT ="+errorResponse);
               }
           });

       }catch (IllegalArgumentException e){
           Log.e(TAG, "Error occured while updating fcm token to server : "+e.getMessage() );
       }




    }

    public static String getFcmToken(){
        return FirebaseInstanceId.getInstance().getToken();
    }
}
