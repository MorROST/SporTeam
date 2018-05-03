package com.yonimor.sporteam.sporteam;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by TheYoni on 06/01/2018.
 */

public class FirebaseMessaging extends FirebaseMessagingService {
  private static final String TAG = "FirebaseMessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getNotification()!=null)
        {
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String body)
    {
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSount = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("yoni the king")
                .setContentText(body).setAutoCancel(true).setSound(notificationSount)
                .setContentIntent(pendingIntent);

        NotificationManager notificationMennager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMennager.notify(0,notificationBuilder.build());
    }

}
