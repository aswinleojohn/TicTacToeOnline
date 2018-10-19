package com.leojohnashwin.tictactoe;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class UserOnline {

    public String fcmid;
    public Date date;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UserOnline() {
    }

    public UserOnline(String fcmid, Date date) {
        this.fcmid = fcmid;
        this.date = date;
    }
}