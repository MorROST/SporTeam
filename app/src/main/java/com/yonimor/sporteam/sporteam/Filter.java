package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yonimor.sporteam.sporteam.com.data.Game;

import java.util.ArrayList;


public class Filter extends Activity {

    ArrayList<Game> filteredGames_ArrayList;
    String name; //This is the first name of the current connect user
    String email; //This is the Email  of the current connect user
    CheckBox myGames_cb , soccer_cb , basketball_cb , tennis_cb;
    boolean myGames , soccer , basketball , tennis;
    RadioGroup myGames_Rgroup,gameCategory_Rgroup;
    RadioButton selectedMyGames_rb , myGames_rb, myRegisterGames_rb ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.name = preferences.getString("name", "");
        this.email = preferences.getString("email", "");
        /////////////S H O U L D   B E   D E L E T E D ? ? ? ?//////////////
/*        myGames_cb = (CheckBox) findViewById(R.id.myGames_cb_filter);
        soccer_cb = (CheckBox) findViewById(R.id.gameSoccer_cb_filter);
        basketball_cb = (CheckBox) findViewById(R.id.gameBasketball_cb_filter);
        tennis_cb = (CheckBox) findViewById(R.id.gameTennis_cb_filter);*/
        /////////////////////////////////////////////////////////////////////
        myGames_Rgroup = (RadioGroup)findViewById(R.id.myGames_r_group_filter);
        gameCategory_Rgroup = (RadioGroup)findViewById(R.id.gameCategory_r_group_filter);
        myGames_rb = (RadioButton)findViewById(R.id.myGames_rb_filter);
        myRegisterGames_rb = (RadioButton)findViewById(R.id.myRegisterGames_rb_filter);
    }


    public void submitFilter(View view) {

        if (myGames_rb.isChecked() || myRegisterGames_rb.isChecked()) {

            if (myGames_rb.isChecked()) {
                filteredGames_ArrayList = StartPage.connectionUtil.GetAllMyGames_filter(name);
                Intent resultIntent = new Intent(this, Home.class);
                resultIntent.putExtra("gameList", filteredGames_ArrayList);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {

                    filteredGames_ArrayList = StartPage.connectionUtil.GetMyRegistredGames(email);
                    Intent resultIntent = new Intent(this, Home.class);
                    resultIntent.putExtra("gameList", filteredGames_ArrayList);
                    setResult(RESULT_OK, resultIntent);
                    finish();

            }


        }
    }
}
