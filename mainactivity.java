package com.example.healthfriendly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button getbtnWelcomeLogin,getbtnWelcomeRegister;
    public void init() {
        getbtnWelcomeLogin=(Button)findViewById(R.id.btnWelcomeLogin);
        getbtnWelcomeRegister=(Button)findViewById(R.id.btnWelcomeRegister);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getbtnWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainActivity.this, Login.class);
                startActivity(intentLogin);
                finish();
            }
        });
        getbtnWelcomeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                finish();
            }
        });

    }
}
