package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yonimor.sporteam.sporteam.R;
import com.yonimor.sporteam.sporteam.com.data.*;

import java.util.ArrayList;

public class Home extends Activity {

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
        hello_txtview = (TextView) findViewById(R.id.hello_txt_home);
        hello_txtview.setText("Hello " + name + "!");

        gameList= (ListView) findViewById(R.id.games_listView_home);

        allGames = StartPage.connectionUtil.GetAllGames();
        FillGamesListView();
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
}