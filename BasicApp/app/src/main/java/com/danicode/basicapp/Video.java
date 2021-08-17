package com.danicode.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    VideoView videoView;
    // Mostrar pausa, next, provious
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        this.videoView = findViewById(R.id.videoView);
        this.mediaController = new MediaController(this);

        //Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_example);
        // Necesita permisos de internet (manifest/AndroidManifest.xml)
        Uri uri = Uri.parse("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4");

        this.videoView.setVideoURI(uri);
        this.videoView.setMediaController(this.mediaController);
        this.videoView.start();
    }
}