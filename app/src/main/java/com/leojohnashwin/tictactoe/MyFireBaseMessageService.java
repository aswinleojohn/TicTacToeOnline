package com.leojohnashwin.tictactoe;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;

import static android.content.ContentValues.TAG;

public class MyFireBaseMessageService extends FirebaseMessagingService
{
    String button;
    int playstatus=1;
    JSONArray opponentfcmid=new JSONArray();

    MainActivity ma = new MainActivity();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            if(remoteMessage.getNotification().getBody().equals("GameRequest")  && playstatus==1){
                playstatus=0;
                opponentfcmid.put(remoteMessage.getData());
                ma.sendMessage(opponentfcmid,"GameRequestAccepted","");
            }
            else if(remoteMessage.getNotification().getBody().equals("move")){
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
                button=remoteMessage.getData().toString();
                ma.makeMove(button);
            }
            else if(remoteMessage.getNotification().getBody().equals("GameRequestAccepted")){
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String fcmid=pref.getString("fcmid","");
                ma.GameStart(fcmid);
            }
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

}
