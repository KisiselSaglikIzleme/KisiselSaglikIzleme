package com.example.healthfriendly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private Toolbar actionbarLogin;
    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    public void init(){
        actionbarLogin = (Toolbar)findViewById(R.id.actionbarLogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        txtEmail=(EditText)findViewById(R.id.txtEmailLogin);
        txtPassword=(EditText)findViewById(R.id.txtPasswordLogin);
        btnLogin=(Button)findViewById(R.id.btnLogin);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email=txtEmail.getText().toString();
        String password= txtPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "email alanı boş olamaz", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "şifre alanı boş olamaz", Toast.LENGTH_SHORT).show();
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "GİRİŞ BAŞARILI", Toast.LENGTH_SHORT).show();
                        Intent anaIntent = new Intent(Login.this, anaekran.class);
                        startActivity(anaIntent);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "giriş başarısız", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
