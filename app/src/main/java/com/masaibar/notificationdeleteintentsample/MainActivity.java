package com.masaibar.notificationdeleteintentsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonBroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationWithBroadcast();
            }
        });

        //普通のPendingIntent対応っぽい通知
        Button buttonIntent = (Button) findViewById(R.id.buttonIntent);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationWithIntent();
            }
        });
    }

    private void showNotificationWithBroadcast() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification Title")
                .setContentText("Notification Text")
                .setContentIntent( //通知クリック時のPendingIntent
                        getPendingIntentWithBroadcast(NotificationReceiver.CLICK_NOTIFICATION)
                )
                .setDeleteIntent(  //通知の削除時のPendingIntent
                        getPendingIntentWithBroadcast(NotificationReceiver.DELETE_NOTIFICATION)
                )
                .build();

        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, notification);
    }

    private PendingIntent getPendingIntentWithBroadcast(String action) {
        return PendingIntent.getBroadcast(getApplicationContext(), 0 , new Intent(action), 0);
    }

    private void showNotificationWithIntent() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Notification Title")
                .setContentText("Notification Text")
                .setContentIntent( //通知クリック時のPendingIntent クリックされたらYahoo!をブラウザで表示
                        getPendingIntent("http://www.yahoo.co.jp")
                )
                .setDeleteIntent(  //通知の削除時のPendingIntent 削除されたらQiitaをブラウザで表示
                        getPendingIntent("http://qiita.com")
                )
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, notification);
    }

    private PendingIntent getPendingIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        return PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
    }
}
