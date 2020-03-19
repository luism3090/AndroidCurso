package com.example.chistesgratisparawhastapp.Services;

import android.content.Intent;
import android.widget.Toast;

import com.example.chistesgratisparawhastapp.NuevosChistesActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseServiceMensajes extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        /*
        Intent nuevosChistes = new Intent(getApplicationContext(), NuevosChistesActivity.class);

        nuevosChistes.putExtra("id_usuario","50");

        startActivity(nuevosChistes);
        */

    }
}
