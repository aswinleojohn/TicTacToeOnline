package com.leojohnashwin.tictactoe;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

public class MyFireBaseInstanceService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String fcmid;
        editor.putString("fcmid", refreshedToken);
        editor.commit();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        fcmid=pref.getString("fcmid","");
        Log.d(TAG, "Refreshed token123: " + fcmid);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
    }
}
