package com.openexchange.cordova.intentplugin;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

public class IntentPlugin extends CordovaPlugin{
    private final String TAG = "INTENT_PLUGIN";
    private final String PACKAGE_NAME = "com.openexchange.mobile.mailapp.enterprise";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getIntentData")) {
            PluginResult.Status status = PluginResult.Status.OK;

            Intent i = this.cordova.getActivity().getIntent();
            Log.d(TAG, "Got intent " + i.getAction());

            String intentAction = i.getAction();
            String intentData = i.getDataString();

            JSONObject result = new JSONObject();
            Log.d(TAG, "Got intent data" + intentData);

            if (intentAction != null) {
                result.put("action", intentAction);
            }


            if (intentAction == "android.intent.action.SEND") {

                Uri fileUri = (Uri) i.getParcelableExtra(Intent.EXTRA_STREAM);
                if (fileUri != null) {
                    Log.d(TAG, "Image URI" + fileUri.toString());
                    if (fileUri.toString().contains(PACKAGE_NAME)) {
                        Log.d(TAG, "Prevented sending file from own package");
                        return false;
                    }
                    result.put("uri", fileUri);
                }
            }
            if (intentAction == "android.intent.action.SEND_MULTIPLE") {
                ArrayList<Uri> imageUris = i.getParcelableArrayListExtra(Intent.EXTRA_STREAM);

                if (imageUris != null) {
                    JSONArray a = new JSONArray();

                    for (Uri uri : imageUris) {
                        a.put(uri.toString());

                        if (uri.toString().contains(PACKAGE_NAME)) {
                            Log.d(TAG, "Prevented sending file from own package");
                            return false;
                        }
                        Log.d(TAG, "put URI to result: " + uri.toString());
                    }
                    result.put("uri", a);
                }
            }
            if (intentData != null) {
                result.put("data", intentData);
            }

            callbackContext.sendPluginResult(new PluginResult(status, result));
            // clear the intent to avoid double use of the same intent
            clearIntent(i);

            return true;
        }
        return false;
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "new Intent: " + intent.getAction());
        if (intent != null) this.cordova.getActivity().setIntent(intent);

    }
    public void clearIntent(Intent i) {
        i.setAction("");
        i.setData(null);
    }

}
