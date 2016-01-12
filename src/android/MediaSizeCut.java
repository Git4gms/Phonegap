package com.phonegap.plugins.mediasizecut;

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
import android.os.Build;
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
import android.content.pm.Signature;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.provider.MediaStore;



public class MediaSizeCut extends CordovaPlugin{
	public CallbackContext callbackContext;
	private static final int CAPTURE_VIDEO = 1;  
	private long limit;                             // the number of pics/vids/clips to take
	private int duration; 
	private int quality;
    private JSONArray results;  
    private static final int CAPTURE_NO_MEDIA_FILES = 3;
    Uri uriVideo = null;
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       // this.callbackContext = callbackContext;
        PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
        r.setKeepCallback(true);
       this.callbackContext.sendPluginResult(r);
        
        
       
        duration = 0;
        quality = 1;
        if (action.equals("onClick1")) {
        	 Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);


	        if(Build.VERSION.SDK_INT > 7){
	            intent.putExtra("android.intent.extra.durationLimit", duration);
	            intent.putExtra("android.intent.extra.videoQuality", quality);
	        }
	        cordova.startActivityForResult(this,intent,90);
        }
		
		
		
		return true;
	}
	 public void onActivityResult(int requestCode, int resultCode, final Intent data) {
		 final MediaSizeCut that = this;
		 Runnable captureVideo = new Runnable() {

	             @Override
	             public void run() {
	            	 uriVideo = data.getData();
	 				Toast.makeText(cordova.getActivity().getApplicationContext(),
	 						uriVideo.getPath(), Toast.LENGTH_LONG).show();
	            	 that.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
	            	 
	             }
	         };
	         this.cordova.getThreadPool().execute(captureVideo);
	     } 
	 
	 
}