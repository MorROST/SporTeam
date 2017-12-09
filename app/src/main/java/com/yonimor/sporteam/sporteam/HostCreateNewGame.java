package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yonimor.sporteam.sporteam.com.data.ConnectionData;
import com.yonimor.sporteam.sporteam.com.data.Game;

public class HostCreateNewGame extends Activity {

    EditText address, time, date, numberOfPlayers;
    Spinner city, sportType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_create_new_game);

        address = (EditText) findViewById(R.id.address_editText_hostGame);
        time = (EditText) findViewById(R.id.time_editText_hostGame);
        date = (EditText) findViewById(R.id.date_editText_hostGame);
        numberOfPlayers = (EditText) findViewById(R.id.numberOfPlayers_editText_hostGame);
        city = (Spinner) findViewById(R.id.pickCity_Spinner);
        sportType = (Spinner) findViewById(R.id.gameCategory_Spinner);

    }

    public void Back(View view) {
        finish();
    }

    public void Submit(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "");

        Game g = new Game(name,sportType.getSelectedItem().toString(),city.getSelectedItem().toString(),
                time.getText().toString(), date.getText().toString(), address.getText().toString(),
                Integer.parseInt(numberOfPlayers.getText().toString()));
        int checkIfWorked;
        checkIfWorked = StartPage.connectionUtil.InsertGame(g);
        if(checkIfWorked== ConnectionData.OK)
        {
            Toast.makeText(this, "new game was added" , Toast.LENGTH_LONG).show();
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
