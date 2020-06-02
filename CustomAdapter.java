package com.example.saglik;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<HedefModel> hedefList;
    public CustomAdapter(Activity activity, ArrayList<HedefModel> hedefList){
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.hedefList = hedefList;
    }
    @Override
    public int getCount() {
        return hedefList.size();
    }

    @Override
    public Object getItem(int position) {
        return hedefList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       HedefModel hedef = hedefList.get(position);
       @SuppressLint({"ViewHolder", "InflateParams"}) View satir = layoutInflater.inflate(R.layout.custom_satir,null);
       TextView kalori = (TextView)satir.findViewById(R.id.Hedef_kalori);
       TextView adim = (TextView)satir.findViewById(R.id.Hedef_adim);
       TextView su = (TextView)satir.findViewById(R.id.Hedef_Su);
       kalori.setText(hedef.getKalori());
       adim.setText(hedef.getAdim());
       su.setText(hedef.getSu());
       return satir;
    }
}

