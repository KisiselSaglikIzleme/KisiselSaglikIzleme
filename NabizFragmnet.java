package com.example.saglik;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import pub.devrel.easypermissions.EasyPermissions;

import static android.content.Context.SENSOR_SERVICE;




public class NabizFragment extends Fragment   {
    



    public NabizFragment() {
        boolean checkSelfPermission;

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View viewnabiz= inflater.inflate(R.layout.fragment_nabiz, container, false);






        return viewnabiz;
    }



}

