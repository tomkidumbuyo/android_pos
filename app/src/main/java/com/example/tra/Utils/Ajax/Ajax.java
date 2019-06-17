package com.example.tra.Utils.Ajax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;


import com.example.tra.Utils.Config;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Ajax extends AsyncTask<String, Void, Void> {

    // This is the JSON body of the post
    JSONObject postData;
    JSONObject resultData;
    String resultString;
    String errorString;
    String requestType = "GET";
    String Url = Config.apiUrl;
    String AccessToken;
    Boolean loginRequired;
    String cancelError;
    String TAG = "Homeactivity";
    Context context;
    AjaxResponse callback;

    int statusCode;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    // This is a constructor that allows you to pass in the JSON body
    public Ajax(Context context, Map<String, String> postData, String url, AjaxResponse callback) {
        this.callback = callback;
        this.context = context;
        this.Url = Config.apiUrl + url;
            this.postData = new JSONObject(postData);
            this.requestType = "POST";

    }

    public Ajax(Context context, String url, AjaxResponse callback) {
        this.callback = callback;
        this.context = context;
        this.Url = Config.apiUrl + url;
        this.requestType = "GET";

    }

    public Ajax(Context context, String url, boolean loginRequired, AjaxResponse callback) {
        this.callback = callback;
        this.context = context;
        this.Url = Config.apiUrl + url;
        this.loginRequired = loginRequired;
        this.requestType = "GET";

    }

    public Ajax(Context context, JSONObject postData, String url, AjaxResponse callback) {
        this.callback = callback;
        this.context = context;
        this.Url = Config.apiUrl + url;
            this.postData = postData;
            this.requestType = "POST";

    }

    public Ajax(Context context, JSONObject postData, String url, boolean loginRequired, AjaxResponse callback) {
        this.callback = callback;
        this.context = context;
        Url = Config.apiUrl + url;
        this.loginRequired = loginRequired;
            this.postData = postData;
            this.requestType = "POST";

    }

    public Ajax(Context context, Map<String, String> postData, String url, boolean loginRequired, AjaxResponse callback) {
        this.callback = callback;
        this.context = context;
        this.Url = Config.apiUrl + url;
        this.loginRequired = loginRequired;
                this.postData = new JSONObject(postData);
                this.requestType = "POST";

    }

    private String getAccessToken(){
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("token", null);
        if (restoredText != null) {
            AccessToken = restoredText;
            return restoredText;
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }


    // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(String... params) {

        try {
            // This is getting the url from the string we passed in
            URL url = new URL(Url);

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(requestType);

            if(requestType == "POST"){
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setDoOutput(true);
            }
            urlConnection.setDoInput(true);


            getAccessToken();

            if(loginRequired == null){
                if(getAccessToken() != null){
                    urlConnection.setRequestProperty("Authorization", "Token "+AccessToken);
                }
            } else if(loginRequired == true && getAccessToken() != null){
                urlConnection.setRequestProperty("Authorization", "Token "+AccessToken);
            }else if(loginRequired == true && getAccessToken() == null) {
                cancelError = "Login is required";
                cancel(true);
            }else if(loginRequired == false && getAccessToken() != null) {
                urlConnection.setRequestProperty("Authorization", "Token "+AccessToken);
            }
            // OPTIONAL - Sets an authorization header
            // Send the post body
            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            statusCode = urlConnection.getResponseCode();


            if (statusCode ==  200) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                StringBuilder textBuilder = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader
                        (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                    int c = 0;
                    while ((c = reader.read()) != -1) {
                        textBuilder.append((char) c);
                    }
                }
                new InputStreamReader(inputStream, "UTF-8");
                resultString = textBuilder.toString();
                resultData = new JSONObject(resultString);


                // From here you can convert the string to JSON with whatever JSON parser you like to use
                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
            } else {
                InputStream inputStream = new BufferedInputStream(urlConnection.getErrorStream());
                StringBuilder textBuilder = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader
                        (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                    int c = 0;
                    while ((c = reader.read()) != -1) {
                        textBuilder.append((char) c);
                    }
                }
                // Status code is not 200
                // Do something to handle the error
                errorString = textBuilder.toString();
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            // Log.e(TAG, e.getLocalizedMessage());
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        if (statusCode ==  200) {
            callback.success(resultData);
            callback.success(resultString);
        } else {
            callback.error(statusCode,"Ajax error: "+errorString);
        }

    }
}