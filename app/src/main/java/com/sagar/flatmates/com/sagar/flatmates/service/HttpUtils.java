package com.sagar.flatmates.com.sagar.flatmates.service;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sagar.flatmates.com.sagar.flatmates.constants.URLConstants;

/**
 * Created by santosh sagar on 08-12-2017.
 */

public class HttpUtils {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postAuthorized(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setBasicAuth("admin","admin");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return URLConstants.REST_END_POINT + relativeUrl;
    }

    private static String getAbsoluteUrlAuthorized(String url) {
        return getAbsoluteUrl(url).replace("http://","http://admin:admin@");
    }


}
