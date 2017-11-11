package com.yonimor.sporteam.sporteam;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.yonimor.sporteam.sporteam.com.data.*;

import java.io.IOException;

public class StartPage extends Activity {

    static ConnectionUtil connectionUtil = null;
    private static final int PERMS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        if (hasPermissions()){
            // our app has permissions.
            new Thread(new Runnable(){
                public void run(){
                    try {
                        connectionUtil = new ConnectionUtil();
                    } catch (IOException e) {
                        Toast.makeText(null, "NOT connected", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }).start();
            if (connectionUtil != null)
                Toast.makeText(this, "connected", Toast.LENGTH_LONG).show();
        }
        else {
            //our app doesn't have permissions, So i m requesting permissions.
            requestPerms();
        }
    }

    private boolean hasPermissions(){
        int res = 0;
        //string array of permissions,
        String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE
        ,Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE};

        for (String perms : permissions){
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                return false;
            }
        }
        return true;
    }

    private void requestPerms(){
        String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE
                ,Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions,PERMS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean allowed = true;

        switch (requestCode){
            case PERMS_REQUEST_CODE:

                for (int res : grantResults){
                    // if user granted all permissions.
                    allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
                }

                break;
            default:
                // if user not granted permissions.
                allowed = false;
                break;
        }

        if (allowed){
            //user granted all permissions we can perform our task.
            try {
                connectionUtil = new ConnectionUtil();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Problem with server please try later", Toast.LENGTH_LONG).show();
            }
        }
        else {
            // we will give warning to user that they haven't granted permissions.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)){
                    Toast.makeText(this, "Internet Permissions denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }




    public void login(View view) {
        Toast.makeText(this, "Problem with server please try later", Toast.LENGTH_LONG).show();

    }

    public void reg(View view) {
        Intent in = new Intent(this, Register.class);
        startActivity(in);
    }
}
