package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yonimor.sporteam.sporteam.R;
import com.yonimor.sporteam.sporteam.com.data.*;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ListView gameList;
    ArrayList allGames;
    GamesAdapter complexGameAdapter;
    TextView hello_txtview;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.name = preferences.getString("name", "");
        setTitle("Hello " + name + "!");

        gameList= (ListView) findViewById(R.id.games_listView_home);

        allGames = StartPage.connectionUtil.GetAllGames();
        FillGamesListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.setting_menu:	// move to user setting activity
              //



            case R.id.callSupport_menu: 	// take a call to the main Dev
                //


            case R.id.logout_menu:      //Log out and go back to the main activity (Login)
                //

            case R.id.take_pic_menu:
                //

        }
        return true;
    }

    private void FillGamesListView()
    {
        complexGameAdapter = new GamesAdapter(this, R.layout.games_list_view,allGames);
        gameList.setAdapter(complexGameAdapter);
    }

    public void Filter(View view) {
        Intent in = new Intent(this, Filter.class);
        startActivity(in);
    }

    public void HostGame(View view) {
        Intent in = new Intent(this, HostCreateNewGame.class);
        startActivity(in);
    }

    public void myAccount(View view) {


    }
}