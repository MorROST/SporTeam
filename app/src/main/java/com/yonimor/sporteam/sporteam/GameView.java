package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.yonimor.sporteam.sporteam.com.data.Game;

public class GameView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        Intent intent = getIntent();
        Game extraGame = (Game) intent.getSerializableExtra("game");
    }
}
