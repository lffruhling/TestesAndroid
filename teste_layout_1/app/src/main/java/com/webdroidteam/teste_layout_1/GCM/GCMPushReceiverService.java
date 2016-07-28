package com.webdroidteam.teste_layout_1.GCM;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;
import com.webdroidteam.teste_layout_1.MenuActivity;
import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.SplashActivity;

/**
 * Created by Leonardo on 12/05/2016.
 *
 * o receptor para o nosso notificação push android usando GCM
 */
public class GCMPushReceiverService extends GcmListenerService{

    //Este metodo será chamado a cada nova menssagem recebida
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //Busca a messagem para o bundle
        String message = data.getString("message");
        String title = data.getString("title");
        //Exibe uma mensagem de notificação
        sendNotification(message, title);
    }

    //Este metodo é o gerador da notificação e da exibição da notificação
    private void sendNotification(String message, String title){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, noBuilder.build()); //0 = ID da notificação
    }
}
