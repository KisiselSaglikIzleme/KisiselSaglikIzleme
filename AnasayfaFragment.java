package com.example.saglik;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.SensorManager;
import android.media.session.PlaybackState;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.telephony.RadioAccessSpecifier;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AnasayfaFragment extends Fragment {
    private EditText kisiselbilgi;
    private ListView listviewbki;
    private  ArrayList<BkiModel> bkilist;
    FirebaseDatabase database;

    private TextView tv_steps;






    public AnasayfaFragment() {



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_anasayfa, container, false);

        tv_steps = (TextView)view.findViewById(R.id.anasayfasteps);


        bkilist = new ArrayList<BkiModel>();
        listviewbki = (ListView)view.findViewById(R.id.listviewbki);
        database=FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("bki");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String bki = ds.child("bki").getValue().toString();
                    bkilist.add(new BkiModel(bki));
                }
                CustomAdapterBki customAdapter= new CustomAdapterBki(getActivity(),bkilist);
                listviewbki.setAdapter(customAdapter);
                dbRef.removeEventListener(this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        return view;
    }

}
