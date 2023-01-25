package com.example.newsphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent iHome= new Intent(SplashScreen.this, login_activity.class);
        Intent main = new Intent(getApplicationContext(),MainActivity.class);
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getBoolean("isLoggedIn",false)) {
                    startActivity(main);
                    finish();
                }
                else {
                    startActivity(iHome);
                    finish();
                }
            }
        }, 3000);
    }
}