package com.example.newsphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regiater_activity extends AppCompatActivity {

    EditText r_user,r_email,r_mobile,r_password,r_conform_password;
    MaterialButton register;
    TextView btn1;

    String s_name, email, mobile, password, con_password;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiater);

        r_user = findViewById(R.id.r_user);
        r_email = findViewById(R.id.r_email);
        r_mobile = findViewById(R.id.r_mobile);
        r_password = findViewById(R.id.r_password);
        r_conform_password = findViewById(R.id.r_conform_password);
        register = findViewById(R.id.register); //register button
        btn1 = findViewById(R.id.btn1); //for - Alrady have an account?Sign-in



        //progress

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait......");
        progressDialog.setCanceledOnTouchOutside(false);




        btn1.setOnClickListener(new View.OnClickListener() {    //for - Alrady have an account?Sign-in
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(regiater_activity.this,login_activity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });

    }

    private void Validation() {
        s_name = r_user.getText().toString();
        email = r_email.getText().toString();
        mobile = r_mobile.getText().toString();
        password = r_password.getText().toString();
        con_password = r_conform_password.getText().toString();

       if (s_name.isEmpty()){
           r_user.setError("Please fill Field");
           r_user.requestFocus();
           return;
       }

        if (email.isEmpty()){
            r_email.setError("Please fill Field");
            r_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            r_email.setError("Please enter valid email");
            r_email.requestFocus();
            return;
        }
        if (mobile.isEmpty()){
            r_mobile.setError("Please fill Field");
            r_mobile.requestFocus();
            return;
        }
        if (!checkMobile(mobile)){
            r_mobile.setError("Enter valid mobile number");
            r_mobile.requestFocus();
            return;
        }
        if (password.isEmpty()){
            r_password.setError("Please fill Field");
            r_password.requestFocus();
            return;
        }
        else if (!checkPassword(password)){
            r_password.setError("Enter maximum 6 digits");
            r_password.requestFocus();
            return;
        }
        if (con_password.isEmpty()){
            r_conform_password.setError("Please fill Field");
            r_conform_password.requestFocus();
            return;
        }
        else if (!password.equals(con_password)) {
            r_conform_password.setError("not match");
            r_conform_password.requestFocus();
            return;
        }
        createAccount();

    }

    private void createAccount() {
        progressDialog.setMessage("Creating account.....");
        progressDialog.show();

        sendDatatodb();
    }

    private void sendDatatodb() {
        String regtime=""+System.currentTimeMillis();//"vivek", 2132, 323.123, true
        HashMap<String,Object> data=new HashMap<>();
        data.put("username",s_name);
        data.put("email",email);
        data.put("mobile",mobile);
        data.put("password",password);

        //database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.child(mobile).setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //db update
                        progressDialog.dismiss();
                        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("isLoggedIn",true);
                        editor.putString("uname",s_name);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Registered Successfull", Toast.LENGTH_SHORT).show();

                        // dashbord
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        


    }

    private boolean checkPassword(String password) {
        Pattern p = Pattern.compile(".{6}");
        Matcher m = p.matcher(password);
        return  m.matches();
    }

    private boolean checkMobile(String mobile) {
        Pattern p = Pattern.compile("[0-9]{10}");
        Matcher m = p.matcher(mobile);
        return  m.matches();
    }

}