package com.example.sih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText lusername,lpassword;

    MaterialButton loginbtn,registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lusername = findViewById(R.id.username);
        lpassword = findViewById(R.id.password);

        loginbtn = findViewById(R.id.loginbtn);
        registerbtn = findViewById(R.id.registerbtn);

        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(view -> {
            loginuser();
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register_Page.class);
                startActivity(intent);
            }
        });
    }
    protected void loginuser(){
        String email_id=lusername.getText().toString();
        String password=lpassword.getText().toString();

        if(TextUtils.isEmpty(email_id)){
            lusername.setError("Email Cannot Be Empty");
            lusername.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            lpassword.setError("password Cannot Be Empty");
            lpassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email_id,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,Dashboard.class));
                    }else{
                        Toast.makeText(Login.this,"Login Failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}