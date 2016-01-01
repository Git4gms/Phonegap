package com.example.sampleapp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class Capture extends Activity {

	final static int REQUEST_VIDEO_CAPTURED = 1;
	Uri uriVideo = null;
	VideoView videoviewPlay;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button buttonRecording = (Button) findViewById(R.id.recording);
		Button buttonPlayback = (Button) findViewById(R.id.playback);
		videoviewPlay = (VideoView) findViewById(R.id.videoview);

		buttonRecording.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				// Get length of file in bytes
				long fileSizeInMB = 10;
				// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
				long fileSizeInKB = fileSizeInMB * 1024;
				// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
				long fileSizeInBytes = fileSizeInKB * 1024;

				captureVideo(Long.valueOf(fileSizeInBytes));
			}

			public void captureVideo(Long limit) {

				Toast.makeText(getApplicationContext(), "Button clicked",
						Toast.LENGTH_LONG).show();

				/*
				 * Intent intent = new Intent(
				 * android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
				 * intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,limit);//
				 * 20*1024*1024 = 20MB startActivityForResult(intent,
				 * REQUEST_VIDEO_CAPTURED);
				 */
			}
		});

		buttonPlayback.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (uriVideo == null) {
					Toast.makeText(Capture.this, "No Video", Toast.LENGTH_LONG)
							.show();
				} else {
					Toast.makeText(Capture.this,
							"Playback: " + uriVideo.getPath(),
							Toast.LENGTH_LONG).show();
					videoviewPlay.setVideoURI(uriVideo);
					videoviewPlay.start();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_VIDEO_CAPTURED) {
				uriVideo = data.getData();
				Toast.makeText(Capture.this, uriVideo.getPath(),
						Toast.LENGTH_LONG).show();
			}
		} else if (resultCode == RESULT_CANCELED) {
			uriVideo = null;
			Toast.makeText(Capture.this, "Cancelled!", Toast.LENGTH_LONG)
					.show();
		}
	}
}