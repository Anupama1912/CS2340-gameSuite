package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout layout1 = findViewById(R.id.layout);

        //Wire up exit button for application
        Button btn = findViewById(R.id.Exit);
        //Functionality for exit button
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Exit Button Works!");
            Toast.makeText(getApplicationContext(), "Exit Application", Toast.LENGTH_SHORT)
                    .show();
            finishAffinity();
            System.exit(0);

        });

//        ToggleButton toggleButton1 = findViewById(R.id.toggleButton);
        Button enter = findViewById(R.id.button);
//        TextView title = findViewById(R.id.title);

//        toggleButton1.setOnClickListener(v -> {
//            if (toggleButton1.isChecked()) {
//                layout1.setBackgroundResource(R.drawable.night);
//                title.setTextColor(getResources().getColor(R.color.nTitle));
//                enter.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
//                enter.setTextColor(getResources().getColor(R.color.nButtonTxt));
//                btn.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
//            } else {
//                layout1.setBackgroundResource(R.drawable.day);
//                title.setTextColor(getResources().getColor(R.color.dTitle));
//                enter.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
//                enter.setTextColor(getResources().getColor(R.color.dButtonTxt));
//                btn.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
//            }
//        });

        enter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

            startActivity(intent);
        });

    }
}