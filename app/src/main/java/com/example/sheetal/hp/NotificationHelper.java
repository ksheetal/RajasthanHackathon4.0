package com.example.sheetal.hp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by sheetal on 4/18/2018.
 */

public class NotificationHelper extends ContextWrapper{

    private static final String channelID = "Sheetal Kumar";
    private static final String channelName = "Sheetal Channel";
    private NotificationManager notificationManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHelper(Context base) {
        super(base);
        createchannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createchannel() {

        NotificationChannel notificationChannel = new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(R.color.colorPrimary);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(notificationChannel);
    }

    public NotificationManager getManager() {
        if(notificationManager == null){
            notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getsheetalChannelNotification(String title, String body){
        return new Notification.Builder(getApplicationContext(),channelID)
                .setContentText(body)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);
    }
}
