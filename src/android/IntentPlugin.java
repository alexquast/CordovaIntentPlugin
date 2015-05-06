package com.openexchange.cordova.IntentPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class IntentPlugin extends CordovaPlugin{
	private final String TAG = "INTENTPLUGIN";

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getIntentData")) {
        	Intent i = this.cordova.getActivity().getIntent();
        	Log.d(TAG, "Got intent " + i.getAction());
            return true;
        }
        return false;
    }

}
