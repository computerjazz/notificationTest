package com.danielmerrill.notificationtest;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button fiveSecButton;
    Button nowButton;
    int notificationId;
    EditText editTitle;
    EditText editMessage;
    String title;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fiveSecButton = (Button) findViewById(R.id.fiveSecButton);
        nowButton = (Button) findViewById(R.id.nowButton);
        editTitle = (EditText) findViewById(R.id.notificationTitle);
        editMessage = (EditText) findViewById(R.id.notificationMessage);
        notificationId = 0;

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        final NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        fiveSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleText = editTitle.getText().toString();
                String messageText = editMessage.getText().toString();

                AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
                intent.putExtra("title", titleText);
                intent.putExtra("message", messageText);

                // Each notification needs a unique ID or else it will recycle old intents
                int id = (int) (Math.random() * Integer.MAX_VALUE);


                PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, 0);
                // set for 5 seconds later
                alarmMgr.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 1000, alarmIntent);
                Log.i("MainActivity","Fired off 5 sec notification");

            }
        });


        nowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNowNotification(mBuilder, mNotifyMgr);
            }
        });


    }

    private void sendNowNotification(NotificationCompat.Builder mBuilder, NotificationManager mNotifyMgr) {

        mBuilder.setSmallIcon(R.drawable.hyundai_logo)
                .setContentTitle("Now Notify")
                .setContentText("You've been notified!")
                .setShowWhen(false);
        mNotifyMgr.notify(notificationId++, mBuilder.build());
    }
}
