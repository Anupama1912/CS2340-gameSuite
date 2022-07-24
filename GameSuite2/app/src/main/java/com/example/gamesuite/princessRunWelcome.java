package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class princessRunWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princess_run_welcome);
        Button easyMode = findViewById(R.id.easy);
        Button hardMode = findViewById(R.id.hard);
        ImageButton close = findViewById(R.id.close);
        easyMode.setOnClickListener(v -> {
            princessRunActivity.currentMap = new tileMap(1);
            Intent intent = new Intent(princessRunWelcome.this, princessRunActivity.class);
            startActivity(intent);
        });
        hardMode.setOnClickListener(v -> {
            princessRunActivity.currentMap = new tileMap(2);
            Intent intent = new Intent(princessRunWelcome.this, princessRunActivity.class);
            startActivity(intent);
        });
        close.setOnClickListener(v -> {
            Intent intent = new Intent(princessRunWelcome.this, MainActivity2.class);
            startActivity(intent);
        });
    }
}