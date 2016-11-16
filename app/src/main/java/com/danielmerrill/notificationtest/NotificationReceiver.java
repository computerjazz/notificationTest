package com.danielmerrill.notificationtest;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by danielmerrill on 11/9/16.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();


        String title = (String) extras.get("title");
        String message = (String) extras.get("message");
        Log.i("NotificationReceiver", "got a notification: " + title +", " + message);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.hyundai_logo);
               // .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MyActivity.class), 0))


        notificationManager.notify(0, notification.build());
    }
}
