package com.yonimor.sporteam.sporteam;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yonimor.sporteam.sporteam.R;
import com.yonimor.sporteam.sporteam.com.data.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ListView gameList;
    ArrayList <Game> allGames, updatedGames;
    GamesAdapter complexGameAdapter;
    TextView hello_txtview;
    String name;
    ImageView userPic;
    int allGamesReloadCount = 0;
    final Handler handler = new Handler();

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.name = preferences.getString("name", "");
        setTitle("Hello " + name + "!");

        gameList= (ListView) findViewById(R.id.games_listView_home);
        if(allGames!=null) {
            FillGamesListView();
        }

        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game game = allGames.get(position);

            }
        });
        allGames = StartPage.connectionUtil.GetAllGames(0);

        RefreshGames();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        userPic = (ImageView)findViewById(R.id.userImage_imageView);
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

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage(name + getResources().getString(R.string.logoutDialog_home));
                alert.setCancelable(true);

                alert.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home.this);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("email","");
                                editor.commit();
                                Intent in = new Intent(Home.this, StartPage.class);
                                startActivity(in);
                                handler.removeCallbacksAndMessages(null);
                                finish();

                            }
                        });

                alert.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = alert.create();
                alert11.show();
                break;

            case R.id.take_pic_menu:
                Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePic.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePic, REQUEST_IMAGE_CAPTURE);
                }

        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            /*  display image in HOME activity:
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userPic.setImageBitmap(imageBitmap);
            Toast.makeText(this, imageBitmap.toString(), Toast.LENGTH_LONG).show();
            */

            //try to save image to DB
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bArray = bos.toByteArray();

            System.out.println(bArray.length);

        }
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

    public void RefreshGames()
    {

        final int delay = 5000; //milliseconds
        handler.postDelayed(new Runnable(){
            public void run(){
                if(StartPage.isNetworkStatusAvialable (getApplicationContext())) {
                    if(allGames==null || allGames.size()==0)
                    {
                        updatedGames = StartPage.connectionUtil.GetAllGames(0);
                        allGames = updatedGames;
                    }
                    else {
                        updatedGames = StartPage.connectionUtil.GetAllGames(allGames.get(allGames.size() - 1).getGameNumber());
                        if (updatedGames != null) {
                            Home.this.allGamesReloadCount++;
                            if (Home.this.allGamesReloadCount < 6) {

                                for (Game game : updatedGames) {
                                    allGames.add(game);
                                }
                            } else {
                                updatedGames = StartPage.connectionUtil.GetAllGames(0);
                                allGames = updatedGames;
                                Home.this.allGamesReloadCount = 0;
                            }
                            FillGamesListView();
                        }
                             else {
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Home.this);
                                builder.setTitle("Connection Problem");
                                builder.setMessage("Client Server error please reload");

                                builder.setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent in = new Intent(Home.this, StartPage.class);
                                        finish();
                                        startActivity(in);
                                        dialog.cancel();
                                        StartPage.connectionUtil = null;
                                        handler.removeCallbacksAndMessages(null);

                                    }
                                });
                                android.app.AlertDialog alertdialog = builder.create();
                                alertdialog.show();

                        }
                    }
                } else {
                    android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(Home.this);
                    builder.setTitle("Connection Problem");
                    builder.setMessage("No intenet Connection please reload");

                    builder.setPositiveButton("Reload",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent in = new Intent(Home.this, StartPage.class);
                            finish();
                            startActivity(in);
                            dialog.cancel();
                            handler.removeCallbacksAndMessages(null);

                        }
                    });
                    android.app.AlertDialog alertdialog=builder.create();
                    alertdialog.show();
                }

                handler.postDelayed(this, delay);
            }
        }, delay);
    }
}