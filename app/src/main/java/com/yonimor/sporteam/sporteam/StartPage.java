package com.yonimor.sporteam.sporteam;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.yonimor.sporteam.sporteam.com.data.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class StartPage extends Activity {

    static ConnectionUtil connectionUtil = null;
    private static final int PERMS_REQUEST_CODE = 123;
    String email;
    CheckBox keepLoged;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        if(isNetworkStatusAvialable (getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "internet avialable", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();
        }
        ConnectionHandler();



    }

    public void ConnectionHandler()
    {
        result = Connecting();
        if (result == ConnectionData.OK)
        {
            Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            this.email = preferences.getString("email", "");

            if (!email.equals("") && !email.equals("null")) {
                finish();
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            }
        }
        else if(result == ConnectionData.NOT_OK)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Connection Problem");
            builder.setMessage("No Connection to server please reload");

            builder.setPositiveButton("Reloade",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent in = new Intent(StartPage.this, StartPage.class);
                    finish();
                    startActivity(in);
                    dialog.cancel();

                }
            });
            AlertDialog alertdialog=builder.create();
            alertdialog.show();
        }
        else
        {
            Toast.makeText(this, "Somthing Wrong", Toast.LENGTH_LONG).show();
        }

    }


    public int Connecting()
    {
        Integer a = ConnectionData.SOMTHING_WRONG;
        if (hasPermissions()){
            // our app has permissions.

            AsyncClassConnect i = new AsyncClassConnect();
            try {
                a = i.execute().get();
                return a;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else {
            //our app doesn't have permissions, So i m requesting permissions.
            requestPerms();
        }
        return a;
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
            AsyncClassConnect i = new AsyncClassConnect();
            try {
                i.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else {
            // we will give warning to user that they haven't granted permissions.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)){
                    Toast.makeText(this, "Permissions denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

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

    class AsyncClassConnect extends AsyncTask<Void, Void, Integer>{

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                connectionUtil = new ConnectionUtil();
                return ConnectionData.OK;

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return ConnectionData.NOT_OK;
        }
    }


    public void login(View view) {
        EditText email,password;
        email = (EditText) findViewById(R.id.email_txt_login);
        password = (EditText) findViewById(R.id.password_txt_login);

        String name;
        name = StartPage.connectionUtil.LogIn(email.getText().toString(),password.getText().toString());
        if(!name.equals("") && !name.equals(String.valueOf(ConnectionData.SOMTHING_WRONG))) {

            keepLoged = (CheckBox) findViewById(R.id.stayLogedIn_txt_login);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            if (keepLoged.isChecked()) {
                 editor.putString("email", email.getText().toString());
            }
            Toast.makeText(this, "Hello " + name + "!", Toast.LENGTH_LONG).show();
            editor.putString("name", name);
            editor.commit();
            Intent in = new Intent(this, Home.class);
            startActivity(in);
            finish();
        }
        else if(name.equals(""))
        {
            Toast.makeText(this, "Email or password are incorrect", Toast.LENGTH_LONG).show();
        }
        else if(name.equals(String.valueOf(ConnectionData.SOMTHING_WRONG)))
        {
            Toast.makeText(this, "Somthing is wrong contact the help center", Toast.LENGTH_LONG).show();
        }

    }

    public void reg(View view) {
        Intent in = new Intent(this, Register.class);
        startActivity(in);
    }

    public void justForTestOpenGMaps(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q= G center kfar Saba");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
}