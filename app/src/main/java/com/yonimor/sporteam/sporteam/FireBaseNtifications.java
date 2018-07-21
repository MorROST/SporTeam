package com.yonimor.sporteam.sporteam;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by TheYoni on 18/12/2017.
 */

public class FireBaseNtifications extends FirebaseInstanceIdService{
    private static final String TAG = "FireBaseNtifications";
    String email;


    @Override
    public void onTokenRefresh() {
        String latestToken = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.email = preferences.getString("name", "");
        StartPage.connectionUtil.SendRegistrationToServer(this.email, latestToken);
    }
}
