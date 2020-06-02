package com.example.saglik;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MesajServis  extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("Başlık",remoteMessage.getNotification().getTitle());
        Log.e("İçerik",remoteMessage.getNotification().getBody());
    }
}
