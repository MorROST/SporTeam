package com.yonimor.sporteam.sporteam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText userName, password, id;
    RadioGroup group;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.userField);
        password = (EditText) findViewById(R.id.passField);
        id = (EditText) findViewById(R.id.idField);

        group = (RadioGroup)findViewById(R.id.genderGroup);
        group.check(R.id.maleRBut);
    }
    public boolean validCheck()
    {
        if (userName.length()<4)
        {
            Toast.makeText(this, "User name must be more then 4 char" , Toast.LENGTH_LONG).show();
            return false;
        }
        else if(password.length()<4)
        {
            Toast.makeText(this, "Password must be more then 4 char" , Toast.LENGTH_LONG).show();
            return false;
        }

        else if(id.length()!=10)
        {
            Toast.makeText(this, "Phone must be 10 char" , Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public void back(View view) {
        finish();
        Intent in = new Intent(this, Login.class);
        startActivity(in);
    }
}
