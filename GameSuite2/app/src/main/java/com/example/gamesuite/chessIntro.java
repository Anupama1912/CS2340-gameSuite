package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class chessIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_intro);
        VideoView videoView = findViewById(R.id.chessVid);  //casting to VideoView is not Strictly required above API level 26
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.videoclip); //set the path of the video that we need to use in our VideoView
        videoView.start();  //start() method of the VideoView class will start the video to play
        Button start = findViewById(R.id.startGame);
        start.setOnClickListener(v -> {
            princessRunActivity.currentMap = new tileMap(1);
            Intent intent = new Intent(chessIntro.this, chessActivity.class);
            startActivity(intent);
        });
    }
}