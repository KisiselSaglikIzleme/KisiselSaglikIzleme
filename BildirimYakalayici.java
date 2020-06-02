package com.example.saglik;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BildirimYakalayıcı extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager bildirimYoneticisi = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification bildirim =intent.getParcelableExtra("nesne");
        bildirimYoneticisi.notify(1,bildirim);


    }
}
