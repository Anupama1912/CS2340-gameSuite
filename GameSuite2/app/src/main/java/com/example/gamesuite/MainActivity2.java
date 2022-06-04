package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FrameLayout layout2 = (FrameLayout) findViewById(R.id.layout);

        ToggleButton toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton1.setOnClickListener(v -> {
            if (toggleButton1.isChecked()) {
                layout2.setBackgroundResource(R.drawable.nightplaceholder);
            } else {
                layout2.setBackgroundResource(R.drawable.dayplaceholder);
            }
        });
    }
}