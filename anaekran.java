package com.example.healthfriendly;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class anaekran extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){
        auth = FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anaekran);
        init();
    }

    @Override
    protected void onStart() {
        if(currentUser == null){
            Intent welcomeIntent = new Intent(anaekran.this, MainActivity.class);
            startActivity(welcomeIntent);
            finish();
        }

        super.onStart();
    }


}
