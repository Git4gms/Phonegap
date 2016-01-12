package com.example.geepas_dev;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.WebSettings.PluginState;
import android.widget.Toast;
import android.widget.VideoView;

public class MediaSizeCut extends CordovaPlugin{
	public static final String TAG = "Media Plugin";
	final static int REQUEST_VIDEO_CAPTURED = 1;
	Uri uriVideo = null;
	public int testVar = 0;
	VideoView videoviewPlay;
	private CallbackContext callbackContext;
	
	@Override
	public boolean execute(final String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		this.testVar = 2;
		this.callbackContext = callbackContext;
		final int duration = Toast.LENGTH_SHORT;

		long fileSizeInBytes = Long.parseLong(args.getString(0));
		captureVideo(Long.valueOf(fileSizeInBytes));
		
		return true;

	}

	private void captureVideo(Long limit) {
		this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, limit);
		cordova.setActivityResultCallback (this);
		this.cordova.startActivityForResult((CordovaPlugin) this, intent, REQUEST_VIDEO_CAPTURED);
		this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, testVar));

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		final MediaSizeCut that = this;
		// TODO Auto-generated method stub
		if (resultCode == cordova.getActivity().RESULT_OK) {
			if (requestCode == REQUEST_VIDEO_CAPTURED) {
				uriVideo = data.getData();
				Toast.makeText(cordova.getActivity().getApplicationContext(),
						uriVideo.getPath(), Toast.LENGTH_LONG).show();
				 //that.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
				  
				
			    Runnable captureAudio = new Runnable() {

	                    @Override
	                    public void run() {
	                          that.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
	                        
	                    }
	              };
		
			}
		} else if (resultCode == cordova.getActivity().RESULT_CANCELED) {
			uriVideo = null;
			Toast.makeText(cordova.getActivity().getApplicationContext(),
					"Cancelled!", Toast.LENGTH_LONG).show();
		}
	}
}
