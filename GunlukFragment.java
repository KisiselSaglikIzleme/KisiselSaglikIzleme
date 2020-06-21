package com.example.saglik;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.View;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class GunlukFragment extends Fragment {
    private  FirebaseDatabase database;
   private  ArrayList<HedefModel> hedefList;
   private ListView listView;
   private CustomAdapter adapter;
    Button sonuc;
    EditText boy,kilo,yas,hedef;
    TextView text,text2;
    Float bki, boytext,kilotext;
    Integer yuvarlama;

    Button attirBtn,azaltBtn;
    TextView sonucTxt;


    public Integer sayi ;
    public SharedPreferences spref;
    public SharedPreferences.Editor sprefeditor;
    public int hedefsu;
    public boolean mDevam = false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View gunluk =inflater.inflate(R.layout.fragment_gunluk, container, false);
        hedefList = new ArrayList<HedefModel>();
        listView = (ListView) gunluk.findViewById(R.id.listView);
        database =FirebaseDatabase.getInstance();

        final DatabaseReference dbRef=database.getReference("hedef");
        hedef =(EditText)gunluk.findViewById(R.id.hedefff);
        attirBtn = (Button)gunluk.findViewById(R.id.ARTIR);
        azaltBtn = (Button)gunluk.findViewById(R.id.AZALT);
        sonucTxt = (TextView)gunluk.findViewById(R.id.snc);
        final Vibrator[] titresim ={(Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE)} ;
        spref = getActivity().getSharedPreferences("saklama_XML", Context.MODE_PRIVATE);
        sayi=spref.getInt("sayi",0);
        sonucTxt.setText(sayi.toString());


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

        attirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hedefsu = Integer.parseInt(hedef.getText().toString());

                if (sayi == hedefsu && mDevam == false){

                    sonucTxt.setText(sayi.toString());
                    Toast.makeText(getActivity().getApplicationContext(),"Hedefe ulaşıldı",Toast.LENGTH_LONG).show();
                    titresim[0].vibrate(700);

                }
                else
                {
                    titresim[0].vibrate(100);


                    sprefeditor = spref.edit();

                    sprefeditor.putInt("sayi",sayi++);


                    sonucTxt.setText("");
                    sonucTxt.setText(sayi.toString());
                    sprefeditor.commit();
                    mDevam = false;
                }







            }
        });
        azaltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayi--;
                sonucTxt.setText(String.valueOf(sayi));
            }
        });











        return gunluk;
    }
}
