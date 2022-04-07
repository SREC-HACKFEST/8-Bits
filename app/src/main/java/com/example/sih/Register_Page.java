package com.example.sih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class Register_Page extends AppCompatActivity {
    //FirebaseDatabase db;
   // private DatabaseReference dbref;
    FirebaseAuth mAuth;
    EditText remail,rpassword;
    MaterialButton register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        remail = findViewById(R.id.remail);
        rpassword = findViewById(R.id.rconfirmpassword);

        register_btn = findViewById(R.id.signupbtn);

        mAuth = FirebaseAuth.getInstance();
        //dbref = FirebaseDatabase.getInstance().getReference();
        register_btn.setOnClickListener(view -> {
            createUser();
        });
    }

    private void createUser()
    {
        String email_id=remail.getText().toString();
        String password=rpassword.getText().toString();

        if(TextUtils.isEmpty(email_id)){
            remail.setError("Email Cannot Be Empty");
            remail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            rpassword.setError("password Cannot Be Empty");
            rpassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email_id,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register_Page.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register_Page.this,Login.class));
                    }else{
                        Toast.makeText(Register_Page.this,"Registeration Failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

            
        }
    }
}