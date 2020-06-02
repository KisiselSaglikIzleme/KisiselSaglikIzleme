package com.example.saglik;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HedefFragment extends Fragment  {
    private EditText gıdaadı,calori;
    private Button kaydet;
    private ListView ListView;
   private FirebaseDatabase database;
    private Context Context;
    private Toolbar toolbar2;
    private RecyclerView rv;
    private FloatingActionButton fab;


    public HedefFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View aramaGida = inflater.inflate(R.layout.fragment_hedef, container, false);

        gıdaadı=(EditText) aramaGida.findViewById(R.id.gıdaadi);
        calori=(EditText) aramaGida.findViewById(R.id.kalori);
        kaydet =(Button)aramaGida.findViewById(R.id.gıdakaydet);
        database=FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("aramagida");
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference idRef= dbRef.push();
                String g_adi, kalori;
                g_adi = gıdaadı.getText().toString();
                kalori = calori.getText().toString();

                if (!g_adi.equals("") && !calori.equals("")){
                    idRef.child("Gida_Adi").setValue(g_adi);
                    idRef.child("Kalorisi").setValue(kalori);
                    gıdaadı.setText("");
                    calori.setText("");
                }else{
                    Toast.makeText(Context, "alanlar boş olamaz", Toast.LENGTH_SHORT).show();
                }

            }
        });













        return aramaGida;

     }

















    }


