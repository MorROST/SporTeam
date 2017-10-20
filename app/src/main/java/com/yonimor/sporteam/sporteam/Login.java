package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class Login extends Activity {
    static ConnectionUtil connectionUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            connectionUtil = new ConnectionUtil();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problem with server please try later", Toast.LENGTH_LONG).show();
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
