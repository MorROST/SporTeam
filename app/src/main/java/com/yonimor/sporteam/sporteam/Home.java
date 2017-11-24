package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gameList= (ListView) findViewById(R.id.games_listView_home);

        allGames = StartPage.connectionUtil.GetAllGames();
        FillGamesListView();
    }

    private void FillGamesListView()
    {
        complexGameAdapter = new GamesAdapter(this, R.layout.games_list_view,allGames);
        gameList.setAdapter(complexGameAdapter);
    }
}