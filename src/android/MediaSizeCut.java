package com.phonegap.plugins.mediasizecut;


import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MediaSizeCut extends CordovaPlugin {
		
	private static final String VIDEO_3GPP = "video/3gpp";
	private static final String VIDEO_MP4 = "video/mp4";

 	private static final int CAPTURE_VIDEO = 2;     // Constant for capture video
 	private static final String LOG_TAG = "VideoCapturePlus";
 	private static final int CAPTURE_NO_MEDIA_FILES = 3;

 	private CallbackContext callbackContext;        // The callback context from which we were invoked.
 	private long limit;                             // the number of pics/vids/clips to take
 	private int duration;                           // optional max duration of video recording in seconds
 	private boolean highquality;                    // optional setting for controlling the video quality
 	private boolean frontcamera;                    // optional setting for starting video capture with the frontcamera
 	private JSONArray results;   
	
 	@Override
 	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
 	    this.callbackContext = callbackContext;
 	    this.limit = 1;
 	    this.duration = 0;
 	    this.highquality = false;
 	    this.results = new JSONArray();
 	    
 	   //JSONObject options = args.optJSONObject(0);
 	   
 	  long fileSizeInBytes = Long.parseLong(args.getString(0));
 	   
 	  
 	   if (action.equals("onClick1")) {
 	      this.captureVideo(fileSizeInBytes);
 	   }else {
 	      return false;
 	    }
 	   
 	    
 	    return true;
 	}// End of class execute
 	
 	private void captureVideo(long fileSizeInBytes) {
 	    Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
 	    String videoUri = "d:testpath";
 	    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
 	    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, fileSizeInBytes);

 	 
 	   cordova.setActivityResultCallback (this);
 	    this.cordova.startActivityForResult(this, intent, CAPTURE_VIDEO);
 	    PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
 	    r.setKeepCallback(true);
 	    callbackContext.sendPluginResult(r);
 	  }
 	
 	 public void onActivityResult(int requestCode, int resultCode, Intent intent) {
 		  callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, resultCode));
 	    if (resultCode == Activity.RESULT_OK) {
 	      if (requestCode == CAPTURE_VIDEO) {
 	        Uri data = null;
 	        if (intent != null) {
 	          // Get the uri of the video clip
 	          data = intent.getData();
 	        }

 	        if (data == null) {
 	        //  File movie = new File(getTempDirectoryPath(), "VideoCapturePlus.avi");
 	          //data = data.getPath();
 	        	 callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 1));
 	        }

 	        // create a file object from the uri
 	        if (data == null) {
 	        	 callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 2));
 	        } else {
 	          //results.put(createMediaFile(data));
 	          if (5 >= 2) {
 	            // Send Uri back to JavaScript for viewing video
 	        	 callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 3));
 	          } else {
 	            // still need to capture more video clips
 	           // captureVideo(duration, highquality, frontcamera);
 	        	 callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 5));
 	          }
 	        }
 	      }
 	    } else if (resultCode == Activity.RESULT_CANCELED) {
 	      // If we have partial results send them back to the user
 	    	 if (5 >= 2) {
 	    	  callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 4));
 	      } else {
 	    	  callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 8));
 	      }
 	    } else {
 	      // If we have partial results send them back to the user
 	    	 if (5 >= 2) {
 	         callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 6));
 	      } else {
 	    	 callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 7));
 	      }
 	    }
 	  }
 	
}