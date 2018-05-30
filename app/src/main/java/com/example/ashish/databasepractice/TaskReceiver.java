package com.example.ashish.databasepractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ashish on 16/7/17.
 */

public class TaskReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = new PendingIntent.getActivity(context,123,new Intent())
        Notification notification = new Notification.Builder(context).setContentTitle("TASK").setContentText("Data was Updated").setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent).
    }
}


