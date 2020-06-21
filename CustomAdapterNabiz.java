package com.example.saglik;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterNabiz extends BaseAdapter {

    LayoutInflater layoutInflater;
    ArrayList<NabizModel> nabizlist;
    public CustomAdapterNabiz(Activity activity,ArrayList<NabizModel> nabizlist){
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nabizlist = nabizlist;
    }


    @Override
    public int getCount() {
        return nabizlist.size();
    }

    @Override
    public Object getItem(int position) {
        return nabizlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NabizModel nabiz = nabizlist.get(position);
        View satir = layoutInflater.inflate(R.layout.custom_satirnabiz,null);
        TextView pulse = (TextView)satir.findViewById(R.id.pulseView);
        pulse.setText(nabiz.getNabiz());
        return satir;



    }
}
