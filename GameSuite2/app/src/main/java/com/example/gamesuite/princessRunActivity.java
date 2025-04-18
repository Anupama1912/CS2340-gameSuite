package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class princessRunActivity extends AppCompatActivity {
    static tileMap currentMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //tileMap map = new tileMap(tileSize);
//        canvas = new myCanvas(this);
//        canvas.setBackgroundColor(Color.parseColor("#3f3851"));
        setContentView(R.layout.activity_princessrun);
//        ImageButton easyMode = findViewById(R.id.easyMode);
//        easyMode.setOnClickListener(v -> {
//            currentMap.setTileNum(1);
//            View curr = findViewById(R.id.maze);
//            curr.invalidate();
//        });
//
//        ImageButton hardMode = findViewById(R.id.hardMode);
//        hardMode.setOnClickListener(v -> {
//            currentMap.setTileNum(2);
//            View curr = findViewById(R.id.maze);
//            curr.invalidate();
//        });
        TextView score = findViewById(R.id.score);
        Thread t = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                score.setText("Score: " + PrincessChar.points);
                                if(PrincessChar.gameWon() == 1) {
                                    score.setTextSize(8);
                                    score.setText("You WON! Best score: " + MainActivity2.prBestScore + " Current score: " + PrincessChar.points);
                                } else if (PrincessChar.gameWon() == 2) {
                                    score.setTextSize(8);
                                    score.setText("You LOST! Best score: " + MainActivity2.prBestScore + " Current score: " + PrincessChar.points);
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            if (princessRunActivity.currentMap.getLivesCount() != 0) {
                myCanvas.timer.cancel();
            }
            PrincessChar.points = 0;
            Intent intent = new Intent(princessRunActivity.this, princessRunWelcome.class);

            startActivity(intent);
        });

        ImageButton info = findViewById(R.id.infoP);
        info.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Info Button works!");
            showInstructions();
        });
    }


    void showInstructions() {
        Dialog instrP = new Dialog(princessRunActivity.this);
        //Have already added custom title in layout. So disable the default title
        instrP.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Can cancel the dialog by clicking anywhere outside the dialog
        instrP.setCancelable(true);
        //Set layout of dialog
        instrP.setContentView(R.layout.instr_p);

        ImageButton close = instrP.findViewById(R.id.close);
        close.setOnClickListener(v -> instrP.dismiss());

        instrP.show();
    }

}




