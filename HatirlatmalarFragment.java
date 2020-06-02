package com.example.saglik;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HatirlatmalarFragment extends Fragment {

    private Button butonbildirdis,butonbildiruyku,butonbildirsu;
    private NotificationCompat.Builder builder;
    private TextView tith,water,sleep;
    private ImageView imgsleep,imgwater,imgtith;
    public HatirlatmalarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Hatırlatmalar = inflater.inflate(R.layout.fragment_hatirlatmalar, container, false);
        imgsleep = (ImageView)Hatırlatmalar.findViewById(R.id.imgsleep);
        imgtith=(ImageView)Hatırlatmalar.findViewById(R.id.imgtith);
        imgwater = (ImageView)Hatırlatmalar.findViewById(R.id.imgwater);
        tith = (TextView) Hatırlatmalar.findViewById(R.id.tith);
        water=(TextView)Hatırlatmalar.findViewById(R.id.water);
        sleep=(TextView)Hatırlatmalar.findViewById(R.id.sleeps);
        butonbildirdis = (Button) Hatırlatmalar.findViewById(R.id.butonbildirdis);
        butonbildirsu= (Button) Hatırlatmalar.findViewById(R.id.butonbildirsu);
        butonbildiruyku = (Button)Hatırlatmalar.findViewById(R.id.butonbildiruyku);
        butonbildirsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gecikmeliBildirim();

            }

            private void gecikmeliBildirim() {
                NotificationManager bildirimyoneticisi = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent = new Intent(getActivity(),HedefFragment.class);
                PendingIntent gidilecekintent = PendingIntent.getActivity(getActivity(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);




                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    String kanalId ="kanalId";
                    String kanalAd ="kanalAd";
                    String kanalTanim="kanalTanim";
                    int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel kanal = bildirimyoneticisi.getNotificationChannel(kanalId);
                    if (kanal == null){
                        kanal = new NotificationChannel(kanalId,kanalAd,kanalOnceligi);
                        kanal.setDescription(kanalTanim);
                        bildirimyoneticisi.createNotificationChannel(kanal);
                    }
                    builder = new NotificationCompat.Builder(getActivity(),kanalId);
                    builder.setContentTitle("Saglik");
                    builder.setContentText("Su İçmeyi Unutma !");
                    builder.setSmallIcon(R.drawable.resim);
                    builder.setAutoCancel(true);
                    builder.setContentIntent(gidilecekintent);



                }else {
                    builder = new NotificationCompat.Builder(getActivity());
                    builder.setContentTitle("Saglik");
                    builder.setContentText("Su İçmeyi Unutma !");
                    builder.setSmallIcon(R.drawable.resim);
                    builder.setAutoCancel(true);
                    builder.setContentIntent(gidilecekintent);
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);

                }
                Intent boradcastIntent =new Intent(getActivity(),BildirimYakalayıcı.class);
                boradcastIntent.putExtra("nesne",builder.build());
                PendingIntent gidilecekBroadcast = PendingIntent.getBroadcast(getActivity(),0,boradcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                long gecikme = SystemClock.elapsedRealtime()+5000;

                Calendar calendar= Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,8);
                calendar.set(Calendar.MINUTE,30);



                AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1000*60*30,gidilecekBroadcast);



                bildirimyoneticisi.notify(1,builder.build());

            }
        });

        butonbildiruyku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uykuBildirim();
            }

            private void uykuBildirim() {
                NotificationManager bildirimyoneticisi = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent = new Intent(getActivity(),HedefFragment.class);
                PendingIntent gidilecekintent = PendingIntent.getActivity(getActivity(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);




                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    String kanalId ="kanalId";
                    String kanalAd ="kanalAd";
                    String kanalTanim="kanalTanim";
                    int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel kanal = bildirimyoneticisi.getNotificationChannel(kanalId);
                    if (kanal == null){
                        kanal = new NotificationChannel(kanalId,kanalAd,kanalOnceligi);
                        kanal.setDescription(kanalTanim);
                        bildirimyoneticisi.createNotificationChannel(kanal);
                    }
                    builder = new NotificationCompat.Builder(getActivity(),kanalId);
                    builder.setContentTitle("Saglik");
                    builder.setContentText("Uyku Vakti !");
                    builder.setSmallIcon(R.drawable.resim);
                    builder.setAutoCancel(true);
                    builder.setContentIntent(gidilecekintent);



                }else {
                    builder = new NotificationCompat.Builder(getActivity());
                    builder.setContentTitle("Saglik");
                    builder.setContentText("Uyku Vakti !");
                    builder.setSmallIcon(R.drawable.resim);
                    builder.setAutoCancel(true);
                    builder.setContentIntent(gidilecekintent);
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);

                }
                Intent boradcastIntent =new Intent(getActivity(),BildirimYakalayıcı.class);
                boradcastIntent.putExtra("nesne",builder.build());
                PendingIntent gidilecekBroadcast = PendingIntent.getBroadcast(getActivity(),0,boradcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                long gecikme = SystemClock.elapsedRealtime()+5000;

                Calendar calendar= Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,21);
                calendar.set(Calendar.MINUTE,30);



                AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1000*60*60*24,gidilecekBroadcast);



                bildirimyoneticisi.notify(1,builder.build());

            }
        });
        butonbildirdis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                disfırcalamaBildirim();
            }

            private void disfırcalamaBildirim() {
                NotificationManager bildirimyoneticisi = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent = new Intent(getActivity(),HedefFragment.class);
                PendingIntent gidilecekintent = PendingIntent.getActivity(getActivity(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);




                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    String kanalId ="kanalId";
                    String kanalAd ="kanalAd";
                    String kanalTanim="kanalTanim";
                    int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel kanal = bildirimyoneticisi.getNotificationChannel(kanalId);
                    if (kanal == null){
                        kanal = new NotificationChannel(kanalId,kanalAd,kanalOnceligi);
                        kanal.setDescription(kanalTanim);
                        bildirimyoneticisi.createNotificationChannel(kanal);
                    }
                    builder = new NotificationCompat.Builder(getActivity(),kanalId);
                    builder.setContentTitle("Saglik");
                    builder.setContentText("Diş Fırçalama Zamanı !");
                    builder.setSmallIcon(R.drawable.resim);
                    builder.setAutoCancel(true);
                    builder.setContentIntent(gidilecekintent);



                }else {
                    builder = new NotificationCompat.Builder(getActivity());
                    builder.setContentTitle("Saglik");
                    builder.setContentText("Diş Fırçalama Zamanı !");
                    builder.setSmallIcon(R.drawable.resim);
                    builder.setAutoCancel(true);
                    builder.setContentIntent(gidilecekintent);
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);

                }
                Intent boradcastIntent =new Intent(getActivity(),BildirimYakalayıcı.class);
                boradcastIntent.putExtra("nesne",builder.build());
                PendingIntent gidilecekBroadcast = PendingIntent.getBroadcast(getActivity(),0,boradcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                long gecikme = SystemClock.elapsedRealtime()+5000;

                Calendar calendar= Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,8);
                calendar.set(Calendar.MINUTE,30);



                AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1000*60*60*12,gidilecekBroadcast);



                bildirimyoneticisi.notify(1,builder.build());
            }


        });







        return Hatırlatmalar;
    }



}
