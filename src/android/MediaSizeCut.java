package com.phonegap.plugins.mediasizecut;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.graphics.drawable.Drawable.Callback;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.webkit.WebSettings.PluginState;
import android.widget.Toast;
import android.widget.VideoView;
import android.os.Environment;



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
        this.callbackContext = callbackContext;
        duration = 0;
        quality = 1;
        if (action.equals("onClick1")) {
        	this.captureVideo(duration, quality);
        }
		
		
		
		return true;
	}
	 private void captureVideo(int duration, int quality) {
	        Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);

	        if(Build.VERSION.SDK_INT > 7){
	            intent.putExtra("android.intent.extra.durationLimit", duration);
	            intent.putExtra("android.intent.extra.videoQuality", quality);
	        }
	        this.cordova.startActivityForResult((CordovaPlugin) this, intent, CAPTURE_VIDEO);
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