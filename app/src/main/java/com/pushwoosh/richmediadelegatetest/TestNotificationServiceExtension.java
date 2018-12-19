package com.pushwoosh.richmediadelegatetest;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.pushwoosh.notification.NotificationServiceExtension;
import com.pushwoosh.notification.PushMessage;

public class TestNotificationServiceExtension extends NotificationServiceExtension {

    @Override
    protected void startActivityForPushMessage(PushMessage message){
        if (!TestingApp.getIsLoggedIn()){
            super.startActivityForPushMessage(message);
        }else {
            Intent launchIntent = new Intent(getApplicationContext(), MainActivity.class);
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

            getApplicationContext().startActivity(launchIntent);
        }

    }

    @Override
    public boolean onMessageReceived(final PushMessage message) {
        Log.d("OnMessage", "NotificationService.onMessageReceived: " + message.toJson().toString());

        // automatic foreground push handling
        if (isAppOnForeground()&&message.toJson().has("rm")) {
            Handler mainHandler = new Handler(getApplicationContext().getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    handleNotification(message.toBundle());
                }
            });

            return true;
        }

        return false;
    }
}
