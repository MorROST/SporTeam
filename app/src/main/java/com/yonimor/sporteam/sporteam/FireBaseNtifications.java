package com.yonimor.sporteam.sporteam;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by TheYoni on 18/12/2017.
 */

public class FireBaseNtifications extends FirebaseInstanceIdService{
    private static final String TAG = "FireBaseNtifications";

    @Override
    public void onTokenRefresh() {
        String latestToken = FirebaseInstanceId.getInstance().getToken();
    }
}
