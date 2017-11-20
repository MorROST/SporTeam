package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yonimor.sporteam.sporteam.R;
import com.yonimor.sporteam.sporteam.com.data.*;

import java.util.ArrayList;

public class Home extends Activity {

    ListView gameList;
    ArrayList allGames;

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
        ArrayAdapter<Game> adap = new ArrayAdapter<Game>(this,R.layout.games_list_view,allGames);
        gameList.setAdapter(adap);
    }
}
