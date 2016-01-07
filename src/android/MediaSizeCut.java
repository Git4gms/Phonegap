package org.apache.cordova.facebook;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class MediaSizeCut extends CordovaPlugin {
	public static final String TAG = "Media Plugin";
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		final int duration = Toast.LENGTH_SHORT;
		// Shows a toast
		Log.v(TAG,"CoolPlugin received:"+ action);
	 
			 
		cordova.getActivity().runOnUiThread(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), action, duration);
				toast.show();
			}
		});
		return true;
	}
}