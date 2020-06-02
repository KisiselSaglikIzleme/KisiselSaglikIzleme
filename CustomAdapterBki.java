package com.example.saglik;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterBki extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<BkiModel> bkiList;
    public CustomAdapterBki(Activity activity, ArrayList<BkiModel> bkiList){
        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bkiList = bkiList;
    }
    @Override
    public int getCount() {
        return bkiList.size();
    }

    @Override
    public Object getItem(int position) {
        return bkiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BkiModel bki = bkiList.get(position);
        View satir = layoutInflater.inflate(R.layout.custom_satirbki,null);
        TextView bkilist = (TextView) satir.findViewById(R.id.textViewBki);
        bkilist.setText(bki.getBki());

        return satir;
    }
}
