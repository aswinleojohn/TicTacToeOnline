package com.leojohnashwin.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    ValueEventListener vel;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    Date date;
    String myCode="X",opponentCode;
    int dbcount=0;
    String availableopponent;
    ArrayList<String> allavailableopponents = new ArrayList<String>();
    JSONArray recepient;
    String fcmid,openentfcmid;
    Button button,b00button,b01button,b02button,b10button,b11button,b12button,b20button,b21button,b22button,infobtn,pbtn,qbtn;
    OkHttpClient mClient = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("user");
        recepient=new JSONArray();
        b00button = (Button) findViewById(R.id.b00);
        b01button = (Button) findViewById(R.id.b01);
        b02button = (Button) findViewById(R.id.b02);
        b10button = (Button) findViewById(R.id.b10);
        b11button = (Button) findViewById(R.id.b11);
        b12button = (Button) findViewById(R.id.b12);
        b20button = (Button) findViewById(R.id.b20);
        b21button = (Button) findViewById(R.id.b21);
        b22button = (Button) findViewById(R.id.b22);
        infobtn = (Button) findViewById(R.id.infobtn);
        pbtn = (Button) findViewById(R.id.pbtn);
        qbtn = (Button) findViewById(R.id.qbtn);


        // assign click listener to the OK button (btnOK)
        b00button.setOnClickListener(oclb00button);
        b01button.setOnClickListener(oclb01button);
        b02button.setOnClickListener(oclb02button);
        b10button.setOnClickListener(oclb10button);
        b11button.setOnClickListener(oclb11button);
        b12button.setOnClickListener(oclb12button);
        b20button.setOnClickListener(oclb20button);
        b21button.setOnClickListener(oclb21button);
        b22button.setOnClickListener(oclb22button);
        infobtn.setClickable(false);
        pbtn.setOnClickListener(oclpbtn);
        qbtn.setOnClickListener(oclqbtn);
        pbtn.setText("Play");
        qbtn.setText("Quit");
        infobtn.setText("Click Play to Start");


    }
    View.OnClickListener oclb00button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            openentfcmid=pref.getString("fcmid","");
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b00button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb01button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b01button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb02button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b02button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb10button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b10button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb11button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b11button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb12button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b12button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb20button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b20button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb21button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b21button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };
    View.OnClickListener oclb22button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recepient.put(openentfcmid);
            b00button.setText(myCode);
            sendMessage(recepient,"move","b22button");
            b00button.setClickable(false);
            opponentTurn();
        }
    };

    View.OnClickListener oclpbtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                SetOnline();
                pbtn.setClickable(false);
                infobtn.setText("Looking for Opponents..");
                qbtn.setText("Cancel");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    View.OnClickListener oclqbtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(qbtn.getText().equals("Cancel")) {
                SetOffline();
                qbtn.setText("Quit");
                infobtn.setText("Click Play to Start");
                pbtn.setClickable(true);
            }
            else {
                SetOffline();
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        }
    };

    void SetOnline() throws InterruptedException {
        date = new Date();
        FindAnOpponent();
        //m1FirebaseDatabase.child("zero").setValue("nothing");
        //Log.d(TAG,"data added as zero");
        /*mFirebaseDatabase.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName){
                for (int i = 0; i < dbcount; i++) {
                    Log.d(TAG,"Inside child Child Added");
                    Log.d(TAG,dataSnapshot.getKey()+" --- "+dataSnapshot.getChildrenCount());

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        fcmid=pref.getString("fcmid","");
        //fcmid="aswin123"
        Log.d(TAG, "fcm id is "+fcmid);
        UserOnline useronline = new UserOnline(fcmid, date);
        mFirebaseDatabase.child(fcmid).setValue(useronline);
        Log.d(TAG, "Insert Successfull");


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SetOffline();
    }

    void RequestGame(String availableopponent){
        JSONArray recipient=new JSONArray();
        recipient.put(availableopponent);
        sendMessage(recipient,"GameRequest",fcmid);
    }
    void FindAnOpponent(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        fcmid=pref.getString("fcmid","");
        final DatabaseReference m1FirebaseDatabase;
        FirebaseDatabase m1FirebaseInstance;
        m1FirebaseInstance = FirebaseDatabase.getInstance();
        m1FirebaseDatabase = m1FirebaseInstance.getReference();
        m1FirebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot snap:dataSnapshot.getChildren()){
                    Log.d(TAG,"Inside Root Child Added");
                    Log.d(TAG,dataSnapshot.getChildrenCount() + ""+snap.getKey());
                    allavailableopponents.add(snap.getKey());
                    dbcount++;
                }
                if(dbcount>1){
                    m1FirebaseDatabase.removeEventListener(this);
                    availableopponent=allavailableopponents.get(1);
                    mFirebaseDatabase.child(availableopponent).removeValue();
                    Log.d(TAG, availableopponent+" successfully removed");
                    RequestGame(availableopponent);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void GameStart(String opponentfcmid){
        myTurn();
        recepient.put(opponentfcmid);
    }
    void SetOffline(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        fcmid=pref.getString("fcmid","");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("user");
        mFirebaseDatabase.child(fcmid).removeValue();
        Log.d(TAG, "successfully removed");
    }

    public void sendMessage(final JSONArray recipient, final String body, final String message) {

        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("body", body);
                    JSONObject data = new JSONObject();
                    data.put("message", message);
                    root.put("notification", notification);
                    root.put("data", data);
                    root.put("registration_ids", recipient);

                    String result = postToFCM(root.toString());
                    Log.d("Main Activity", "Result: " + result);
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                try {
                    JSONObject resultJson = new JSONObject(result);
                    int success, failure;
                    success = resultJson.getInt("success");
                    failure = resultJson.getInt("failure");
                    Toast.makeText(MainActivity.this, "Message Success: " + success + "Message Failed: " + failure, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Message Failed, Unknown error occurred.", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    String postToFCM(String bodyString) throws IOException {



        final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, bodyString);
        Request request = new Request.Builder()
                .url(FCM_MESSAGE_URL)
                .post(body)
                .addHeader("Authorization", "key=" + "AAAAu1sY3Oo:APA91bHIFr_YcIoYOa8sOuqDGxQ-S1KST8O7iHRB2XlCVwn4IQo0q96jZBpOyxa6qyAn4NidxZGYplugFfh_0ZU7agnVrvaoh-MlryQtqvOJ8xzWPE1lokgbYKigxIcU-BODdMiQqi6N")
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    void SetUnClickable(){
        b00button.setClickable(false);
        b01button.setClickable(false);
        b02button.setClickable(false);
        b10button.setClickable(false);
        b11button.setClickable(false);
        b12button.setClickable(false);
        b20button.setClickable(false);
        b21button.setClickable(false);
        b22button.setClickable(false);
    }

    void SetClickable(){
        b00button.setClickable(true);
        b01button.setClickable(true);
        b02button.setClickable(true);
        b10button.setClickable(true);
        b11button.setClickable(true);
        b12button.setClickable(true);
        b20button.setClickable(true);
        b21button.setClickable(true);
        b22button.setClickable(true);
    }

    void makeMove(String button){
        if(button.equals("b00button")){
            b00button.setText(opponentCode);
            b00button.setClickable(false);
            myTurn();
        }
        else if(button.equals("b01button")){
            b01button.setText(opponentCode);
            b01button.setClickable(false);
        }
        else if(button.equals("b02button")){
            b02button.setText(opponentCode);
            b02button.setClickable(false);
        }
        else if(button.equals("b10button")){
            b10button.setText(opponentCode);
            b10button.setClickable(false);
        }
        else if(button.equals("b11button")){
            b11button.setText(opponentCode);
            b11button.setClickable(false);
        }
        else if(button.equals("b12button")){
            b12button.setText(opponentCode);
            b12button.setClickable(false);
        }
        else if(button.equals("b20button")){
            b20button.setText(opponentCode);
            b20button.setClickable(false);
        }
        else if(button.equals("b21button")){
            b21button.setText(opponentCode);
            b21button.setClickable(false);
        }
        else if(button.equals("b22button")){
            b22button.setText(opponentCode);
            b22button.setClickable(false);
        }

    }

    void myTurn(){
        infobtn.setText("Its Your Move");
        infobtn.setBackgroundColor(Color.GREEN);
    }
    void opponentTurn(){
        infobtn.setText("Waiting for the Opponents Move");
        infobtn.setBackgroundColor(Color.RED);
    }

}
