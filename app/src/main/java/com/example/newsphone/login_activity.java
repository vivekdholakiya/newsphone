package com.example.newsphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login_activity extends AppCompatActivity {

    EditText mobile,password;
    MaterialButton log_btn;
    TextView reg_btn;
    String s_pass,s_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobile = findViewById(R.id.log_mob);
        password= findViewById(R.id.log_password);
        log_btn=findViewById(R.id.btn2);
        reg_btn=findViewById(R.id.btn1);

        //reg

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =  new Intent(login_activity.this,regiater_activity.class);
                startActivity(intent2);
                finish();
            }
        });
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });
    }

    private void Validation() {

        s_mobile=mobile.getText().toString();
        s_pass=password.getText().toString();

        if (!checkMobile(s_mobile)){
            mobile.setError("Please fill Field");
            mobile.requestFocus();
            return;
        }
        if (s_pass.isEmpty()){
            password.setError("Please fill Field");
            password.requestFocus();
            return;
        }
        chackfromdb();


    }

    private boolean checkMobile(String mobile) {
        Pattern p = Pattern.compile("[0-9]{10}");
        Matcher m = p.matcher(mobile);
        return  m.matches();
    }

    private void chackfromdb() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://newsphone-465ed-default-rtdb.firebaseio.com");
        reference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(s_mobile)){
                            final String db_pass = snapshot.child(s_mobile).child("password").getValue(String.class);
                            final String name = snapshot.child(s_mobile).child("username").getValue(String.class);
                            if (db_pass.equals(s_pass)){
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putBoolean("isLoggedIn",true);
                                editor.putString("uname",name);
                                editor.apply();
                                Toast.makeText(login_activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(login_activity.this, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(login_activity.this, "Record Not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}