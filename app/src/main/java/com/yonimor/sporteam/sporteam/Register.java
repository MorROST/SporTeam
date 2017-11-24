package com.yonimor.sporteam.sporteam;

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.yonimor.sporteam.sporteam.com.data.*;

public class Register extends Activity {


    EditText name_editxt, password_editxt,repeatPassword_editxt,phone_editxt, email_editxt, age_editxt;
    RadioGroup group;
    String email, password,passwordRepeat, name, gender;
    String age, phone;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textView = (TextView) findViewById(R.id.error_label);
        textView.setText("");

        name_editxt = (EditText) findViewById(R.id.name_editxt);
        password_editxt = (EditText) findViewById(R.id.passField);
        repeatPassword_editxt = (EditText) findViewById(R.id.repeatPass_editxt);
        phone_editxt = (EditText) findViewById(R.id.phone_txt_register);
        email_editxt = (EditText) findViewById(R.id.email_txt_register);
        age_editxt = (EditText) findViewById(R.id.age_txt_register);

        group = (RadioGroup)findViewById(R.id.genderGroup);
        group.check(R.id.maleRBut);

    }

    public boolean validations() {

        boolean email_bool = false, password_bool = false, passwordRepeat_bool = false, name_bool = false , age_bool = false, phone_bool = false;  //boolean that check all details are valid


        ////////////////////////////////EMAIL ADDRESS///////////////////////////////////
        final EditText emailValidate = (EditText) findViewById(R.id.email_txt_register);


        String email = emailValidate.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";


        if (email.matches(emailPattern)) {
            textView.setText("");
            email_editxt.setBackgroundColor(Color.TRANSPARENT);
            email_bool = true;
        } else {
            textView.setText("Invalid email address");
            email_editxt.setBackgroundColor(Color.RED);
            email_bool = false;
        }

        //////////////////////////////////PASSWORD & REPEAT////////////////////////////////////////
        if (passwordRepeat.equals("") || password.equals("")) {
            if(password.equals("")) {
                password_bool = false;
                password_editxt.setBackgroundColor(Color.RED);
                textView.setText("Password cannot be empty");
            }
            else {
                passwordRepeat_bool = false;
                repeatPassword_editxt.setBackgroundColor(Color.RED);
                textView.setText("Password cannot be empty");
            }


        } else if (!passwordRepeat.equals("") && !password.equals("")) {

            if(passwordRepeat.equals(password)) {
                password_editxt.setBackgroundColor(Color.TRANSPARENT);
                repeatPassword_editxt.setBackgroundColor(Color.TRANSPARENT);
                password_bool = true;
                passwordRepeat_bool = true;
            }
            else
            {
                password_editxt.setBackgroundColor(Color.RED);
                repeatPassword_editxt.setBackgroundColor(Color.RED);
                textView.setText("password doesn't match");
            }

        }
        //////////////////////////////////FIRST NAME  //////////////////////////////////

        if (name.equals("")) {

            name_bool = false;
            name_editxt.setBackgroundColor(Color.RED);
            textView.setText("Please tell us your name....");

        }
        else if (!name.equals(""))
        {
            String namePattern = "[a-zA-Z]+\\.?";

            if (name.matches(namePattern)) {
                textView.setText("");
                name_bool = true;
                name_editxt.setBackgroundColor(Color.TRANSPARENT);
            } else {
                textView.setText("Name can only contains letters");
                name_editxt.setBackgroundColor(Color.RED);
                name_bool = false;
            }


        }
        //////////////////////////////////  AGE  &  PHONE  ////////////////////////////////////////
        if (age.equals("") || phone.equals("")) {
            if (phone.equals("")) {
                phone_editxt.setBackgroundColor(Color.RED);
                phone_bool = false;
                textView.setText("Phone filed cannot be empty....");

                if(age.equals(""))
                {
                    age_editxt.setBackgroundColor(Color.RED);
                    age_bool = false;
                    textView.setText("Age filed cannot be empty....");
                }
            }
            else {
                age_editxt.setBackgroundColor(Color.RED);
                age_bool = false;
            }
        }
        if ((!age.equals("")) || (!phone.equals("")))
        {
            if(!phone.equals(""))
            {
                phone_editxt.setBackgroundColor(Color.TRANSPARENT);
                phone_bool = true;

                if(!age.equals(""))
                {
                    age_editxt.setBackgroundColor(Color.TRANSPARENT);
                    age_bool = true;
                }
            }
            else
            {
                age_editxt.setBackgroundColor(Color.TRANSPARENT);
                age_bool = true;
            }
        }




        //////////////////////////////////S U M M A R Y/////////////////////////////////////////////
        if(email_bool && password_bool && passwordRepeat_bool && name_bool && age_bool && phone_bool )
        {

            return true;
        }
        else
        {

            return false;
        }
    }

    public void Register(View view) {
        fillVariables();

        int checkIfWorked = ConnectionData.SOMTHING_WRONG;

        if (validations())
        {

            User u = new User (name, password ,email ,gender , phone,Integer.parseInt(age_editxt.getText().toString()));
            checkIfWorked = StartPage.connectionUtil.Register(u);
            if(checkIfWorked==ConnectionData.OK)
            {
                Toast.makeText(this, name + " was added" , Toast.LENGTH_LONG).show();
                finish();
            }
            else if(checkIfWorked==ConnectionData.NOT_OK)
            {
                Toast.makeText(this, "This email is already registered" , Toast.LENGTH_LONG).show();
            }
            else if(checkIfWorked==ConnectionData.SOMTHING_WRONG)
            {
                Toast.makeText(this, "There was a problem" , Toast.LENGTH_LONG).show();
            }

        }

    }

    public void back(View view) {
        finish();
        Intent in = new Intent(this, StartPage.class);
        startActivity(in);
    }

    public void fillVariables() {


        int selectedID = group.getCheckedRadioButtonId();
        RadioButton selectedRB = (RadioButton) findViewById(selectedID);
        gender = selectedRB.getText().toString();

        email = email_editxt.getText().toString();
        password = password_editxt.getText().toString();
        passwordRepeat = repeatPassword_editxt.getText().toString();
        name = name_editxt.getText().toString();
        age = age_editxt.getText().toString();
        phone = phone_editxt.getText().toString();
    }
}