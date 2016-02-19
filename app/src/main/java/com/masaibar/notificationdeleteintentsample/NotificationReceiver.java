package com.masaibar.notificationdeleteintentsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by masaibar on 2016/02/19.
 */
public class NotificationReceiver extends BroadcastReceiver{

    //MainActivity側からも参照されるのでpublic
    public static final String CLICK_NOTIFICATION = "click_notification";
    public static final String DELETE_NOTIFICATION = "delete_notification";

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        switch (action) {
            case CLICK_NOTIFICATION:
                //通知タップ時のイベントを書く
                Toast.makeText(context, "通知がタップされました", Toast.LENGTH_SHORT).show();
                break;

            case DELETE_NOTIFICATION:
                //通知削除時のイベントを書く
                Toast.makeText(context, "通知が削除されました", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
