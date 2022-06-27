  package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

  public class loginActivity extends AppCompatActivity {

    EditText username, password;
    Button login, signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    Toast.makeText(loginActivity.this, "All fields REQUIRED", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = DB.checkusername(user);
                    if (checkuserpass == true) {
                        Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginActivity.this, MainActivity2.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(loginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, signupActivity.class);
                startActivity(intent);
            }
        });
    }
}