package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.VideoView;

public class Splash_Screen extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView)findViewById(R.id.videoView);
        String videoPath = new StringBuilder("android.resource://")
            .append(getPackageName())
            .append("/raw/pokemonvideo")
            .toString();
        videoView.setVideoPath(videoPath);
        //event
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },500);
            }
        });
        videoView.start();
    }
}