package com.example.saglik;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdaptergida extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Gıda> gıdaList;

    public CustomAdaptergida (Activity activity,ArrayList<Gıda> gıdaList){
        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.gıdaList = gıdaList;
    }


    @Override
    public int getCount() {
        return gıdaList.size();
    }

    @Override
    public Object getItem(int position) {
        return gıdaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gıda gıda = gıdaList.get(position);
        View satir = layoutInflater.inflate(R.layout.custom_satirgida,null);
        TextView name = (TextView) satir.findViewById(R.id.textViewİsim);
        TextView kalori = (TextView)satir.findViewById(R.id.textViewKalori);
        name.setText(gıda.getName());
        kalori.setText(gıda.getKalori());
        return satir;
    }
}
