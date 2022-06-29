package com.example.gamesuite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Game extends Activity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();    //get the intent extras
        Maze maze = (Maze)extras.get("maze");  //retrieve the maze from intent extras
        GameView view = new GameView(this,maze);
        setContentView(view);
    }
}