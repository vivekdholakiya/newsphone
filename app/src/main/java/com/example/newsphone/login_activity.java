package com.example.newsphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class login_activity extends AppCompatActivity {

    EditText email,password;
    MaterialButton log_btn;
    TextView reg_btn;
    String s_name,s_pass,s_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email= findViewById(R.id.log_email);
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

        s_email=email.getText().toString();
        s_pass=password.getText().toString();

        if (s_email.isEmpty()){
            email.setError("Please fill Field");
            email.requestFocus();
            return;
        }
        if (s_pass.isEmpty()){
            password.setError("Please fill Field");
            password.requestFocus();
            return;
        }
        chackfromdb();


    }

    private void chackfromdb() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Username ");
        reference.child(s_name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String db_pass=snapshot.child("password").getValue(String.class);
                            if (s_pass.equals(db_pass)){
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("name",s_name);
                                Toast.makeText(login_activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(login_activity.this, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(login_activity.this, "Record NOt found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}