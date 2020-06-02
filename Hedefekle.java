package com.example.saglik;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Hedefekle extends Fragment {

   private Button hedefkaydet;
   private EditText gunlukkalori,textadim,textsu;
  private TextView hedefsu,hedefadim,hedefkalori;
    private FirebaseDatabase database;
    private Context Context;
    private FirebaseAuth mAuth;

    public Hedefekle() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View hedefekle =inflater.inflate(R.layout.fragment_hedefekle, container, false);
       mAuth = FirebaseAuth.getInstance();
        final String user_id = mAuth.getCurrentUser().getUid();
        database=FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("hedef");
        gunlukkalori = (EditText)hedefekle.findViewById(R.id.gunlukkalori);
        textadim = (EditText)hedefekle.findViewById(R.id.textadim);
        textsu = (EditText) hedefekle.findViewById(R.id.textsu);
        hedefsu = (TextView)hedefekle.findViewById(R.id.hedefsu);
        hedefadim = (TextView)hedefekle.findViewById(R.id.hedefadim);
        hedefkalori = (TextView)hedefekle.findViewById(R.id.hedefkalori);
        hedefkaydet = (Button)hedefekle.findViewById(R.id.hedefkaydet);
        hedefkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference idRef = dbRef.push();
                String h_adim,h_kalori,h_su;
                h_adim=textadim.getText().toString();
                h_kalori = gunlukkalori.getText().toString();
                h_su = textsu.getText().toString();


                if (!h_adim.equals("") && !h_kalori.equals("") && !h_su.equals("")){


                    idRef.child("Hedef_Kalori").setValue(h_kalori);
                    idRef.child("Hedef_Adim").setValue(h_adim);
                    idRef.child("Hedef_Su").setValue(h_su);

                    gunlukkalori.setText("");
                    textadim.setText("");
                    textsu.setText("");
                }else{
                    Toast.makeText(Context,"alanlar boş olamaz",Toast.LENGTH_SHORT).show();
                }

            }
        });









       return hedefekle;
    }
}
