package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

public class chessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);

        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(chessActivity.this, MainActivity2.class);

            startActivity(intent);
        });
    }
}