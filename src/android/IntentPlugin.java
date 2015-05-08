package com.openexchange.cordova.intentplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class IntentPlugin extends CordovaPlugin{
    private final String TAG = "INTENT_PLUGIN";

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
            if (intentData != null) {
                result.put("data", intentData);
            }
            callbackContext.sendPluginResult(new PluginResult(status, result));
            return true;
        }
        return false;
    }

}
