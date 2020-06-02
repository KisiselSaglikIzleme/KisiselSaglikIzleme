package com.example.saglik;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.IntegerRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */


public class SaglikhesaplaFragment extends Fragment {
    Button sonuc;
    EditText boy,kilo,yas;
    TextView text,text2;
    Float bki, boytext,kilotext;
    Integer yuvarlama;
    FirebaseDatabase database;


    public SaglikhesaplaFragment() {
        // Required empty public constructor




    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_saglikhesapla, container, false);
        database=FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("bki");
       sonuc =(Button)view.findViewById(R.id.sonuc);
       kilo = (EditText)view.findViewById(R.id.kilo);
       boy = (EditText)view.findViewById(R.id.boy);
       yas = (EditText)view.findViewById(R.id.yas);
       text = (TextView) view.findViewById(R.id.textVier1);
       text2 = (TextView) view.findViewById(R.id.textVier2);

       sonuc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               kilotext=Float.parseFloat(kilo.getText().toString());
               boytext=Float.parseFloat(boy.getText().toString());
               bki= kilotext/(boytext*boytext);
               yuvarlama=(int)Math.ceil(bki);

               text.setText("Vücut Kütle Endeksiniz:"+yuvarlama.toString());

               if(yuvarlama<=18.5){
                   text2.setText("Zayıf");
               }else if(18.5<= yuvarlama && yuvarlama <= 24.5){
                   text2.setText("Kilonuz Normal");
               }else if(25.0<= yuvarlama && yuvarlama <= 29.9){
                   text2.setText("Fazla Kilolu");
               }else if(30.0<= yuvarlama && yuvarlama<=34.9){
                   text2.setText("1. Derece Obez");
               }else if (35.0<=yuvarlama && yuvarlama <= 40){
                   text2.setText("2. Derece Obez");
               }else {
                   text2.setText("3. Derece Obez");
               }


               DatabaseReference idRef= dbRef.push();
               String t_yuvarlama;
               t_yuvarlama = text.getText().toString();

               if (!t_yuvarlama.equals("")){
                   idRef.child("bki").setValue(t_yuvarlama);
               }

           }
       });




        return view;
    }
}
