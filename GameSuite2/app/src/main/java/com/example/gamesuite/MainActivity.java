package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button login, signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout layout1 = findViewById(R.id.layout);

//        ToggleButton toggleButton1 = findViewById(R.id.toggleButton);
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

        username = findViewById(R.id.username);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                //String pass = password.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(MainActivity.this, "All fields REQUIRED", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = DB.checkusername(user);
                    if (checkuserpass == true) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signupActivity.class);
                startActivity(intent);
            }
        });
    }
}