package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {


    }

    public void move2Register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        this.finish();
    }
}
