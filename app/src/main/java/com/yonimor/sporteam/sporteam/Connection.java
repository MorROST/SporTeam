package com.yonimor.sporteam.sporteam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by TheYoni on 07/07/2018.
 */

public class Connection {


    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    public static AlertDialog.Builder connectionLostMessage(Context con)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(con);
        builder.setTitle("Connection Problem");
        builder.setMessage("No Connection to server please reload");

        builder.setPositiveButton("Reload",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        return builder;
    }
}
