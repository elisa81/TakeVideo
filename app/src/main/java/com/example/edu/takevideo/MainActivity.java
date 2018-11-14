package com.example.edu.takevideo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final int REQUEST_CODE = 201;
    VideoView videoView;
    Button buttonTakeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        buttonTakeVideo = findViewById(R.id.buttonTakeVideo);

        buttonTakeVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            Toast.makeText(this, "" + videoUri, Toast.LENGTH_SHORT).show();
            videoView.setVideoURI(videoUri);
            videoView.start();
        }

    }
}
