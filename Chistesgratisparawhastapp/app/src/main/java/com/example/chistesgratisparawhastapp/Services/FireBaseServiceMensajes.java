package com.example.chistesgratisparawhastapp.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.Html;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.chistesgratisparawhastapp.BusquedaChistesActivity;
import com.example.chistesgratisparawhastapp.MainActivity;
import com.example.chistesgratisparawhastapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FireBaseServiceMensajes extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String chiste = remoteMessage.getData().get("chiste");
        //Mensaje(chiste);
        mostrarNotificacion(chiste);

    }

/*
    private void Mensaje(String chiste){

        Intent i = new Intent(MainActivity.MENSAJE);
        i.putExtra("chiste",chiste);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);
    }
*/

    private void mostrarNotificacion(String chiste) {

        //Intent intent = new Intent(this, MainActivity.class);

        //Aqui intento seleccionar la actividad que se abre con el push
        Intent intent = new Intent(this, MainActivity.class);

        //intent.putExtra("ms", "Message by Jorgesys"); //* Valor a enviar!

        // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Chistes gratis parar whatsApp")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml("<b>Nuevo chiste:</b><br>"+chiste)))
                .setContentText("Nuevo chiste "+chiste)
                .setSound(soundUri)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Random random = new Random();

        notificationManager.notify(random.nextInt(), notificationBuilder.build());

    }
}
