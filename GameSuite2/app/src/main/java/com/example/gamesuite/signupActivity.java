package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signupActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        signup = findViewById(R.id.signup);
        DB = new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
//                String pass = password.getText().toString();
//                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(signupActivity.this, "All fields REQUIRED", Toast.LENGTH_SHORT).show();
                } else {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user);
                            if (insert == true) {
                                Toast.makeText(signupActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signupActivity.this, MainActivity2.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signupActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });

    }
}