package com.example.sabari_ex8;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class SMSReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "sms_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
// Handle incoming SMS and display a notification.
        if (intent.getAction() != null && intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if (messages != null && messages.length > 0) {
                String sender = messages[0].getOriginatingAddress();
                String messageBody = messages[0].getMessageBody();
                createNotification(context, sender, messageBody);
            }
        }
    }

    private void createNotification(Context context, String sender, String messageBody) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "SMS Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent messageBoxIntent = new Intent(context, MessageBoxActivity.class);
        messageBoxIntent.putExtra("sender", sender);
        messageBoxIntent.putExtra("message", messageBody);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, messageBoxIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.noti)
                .setContentTitle("New SMS")
                .setContentText("From: " + sender)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }
}
