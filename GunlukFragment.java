package com.example.saglik;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class GunlukFragment extends Fragment {
    private  FirebaseDatabase database;
   private  ArrayList<HedefModel> hedefList;
   private ListView listView;
   private CustomAdapter adapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View gunluk =inflater.inflate(R.layout.fragment_gunluk, container, false);
        hedefList = new ArrayList<HedefModel>();
        listView = (ListView) gunluk.findViewById(R.id.listView);
        database =FirebaseDatabase.getInstance();

        final DatabaseReference dbRef=database.getReference("hedef");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    String kalori = ds.child("Hedef_Kalori").getValue().toString();
                    String adim = ds.child("Hedef_Adim").getValue().toString();
                    String su =ds.child("Hedef_Su").getValue().toString();
                    hedefList.add(new HedefModel(kalori,adim,su));
                }
                CustomAdapter adapter = new CustomAdapter(getActivity(),hedefList);
                listView.setAdapter(adapter);
                dbRef.removeEventListener(this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







         return gunluk;
    }
}
