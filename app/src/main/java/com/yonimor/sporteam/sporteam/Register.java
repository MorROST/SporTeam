package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.yonimor.sporteam.sporteam.com.data.*;

public class Register extends Activity {
    EditText userName, password, phone, email, age;
    RadioGroup group;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.userField);
        password = (EditText) findViewById(R.id.passField);
        phone = (EditText) findViewById(R.id.phone_txt_register);
        email = (EditText) findViewById(R.id.email_txt_register);
        age = (EditText) findViewById(R.id.age_txt_register);

        group = (RadioGroup)findViewById(R.id.genderGroup);
        group.check(R.id.maleRBut);

    }
    public boolean ValidCheck()
    {
        if (userName.length()<4 || userName.length()>12)
        {
            Toast.makeText(this, "User name must be more then 4 char and less than 13 char" , Toast.LENGTH_LONG).show();
            return false;
        }
        else if(password.length()<4 || password.length()>12)
        {
            Toast.makeText(this, "Password must be more then 4 char and less than 13 char" , Toast.LENGTH_LONG).show();
            return false;
        }

        else if(phone.length()!=10)
        {
            Toast.makeText(this, "Phone must be 10 char" , Toast.LENGTH_LONG).show();
            return false;
        }

        else if(1==1)
        {
            Toast.makeText(this, "Must validate email" , Toast.LENGTH_LONG).show();
            //return false;
        }

        return true;
    }


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!NOT USING VALIDATION FOR NOW!!
    public void Register(View view) {
        //boolean check = ValidCheck();
        int selectedID = group.getCheckedRadioButtonId();
        RadioButton selectedRB = (RadioButton) findViewById(selectedID);
        gender = selectedRB.getText().toString();
        //if (check)
        //{
            User u = new User(userName.getText().toString(),password.getText().toString(),email.getText().toString(),gender, phone.getText().toString(),Integer.parseInt(age.getText().toString()));
            Login.connectionUtil.Register(u);
        //}
    }

    public void back(View view) {
        finish();
        Intent in = new Intent(this, StartPage.class);
        startActivity(in);
    }
}
