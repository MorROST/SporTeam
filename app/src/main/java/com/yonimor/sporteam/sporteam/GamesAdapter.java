package com.yonimor.sporteam.sporteam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yonimor.sporteam.sporteam.com.data.Game;

import java.util.List;

/**
 * Created by TheYoni on 24/11/2017.
 */

public class GamesAdapter extends ArrayAdapter<Game> {

    public GamesAdapter(Context context, int resource, List<Game> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.games_list_view, null);
        }

        Game game = getItem(position);


        TextView createdBy = (TextView) v.findViewById(R.id.hostName_txt_gameListView);
        TextView city = (TextView) v.findViewById(R.id.city_txt_gameListView);
        TextView address = (TextView) v.findViewById(R.id.addres_txt_gameListView);
        TextView date = (TextView) v.findViewById(R.id.date_txt_gameListView);
        TextView time = (TextView) v.findViewById(R.id.time_txt_gameListView);
        TextView sportType = (TextView) v.findViewById(R.id.sportType_txt_gameListView);
        TextView numberOfPlayers = (TextView) v.findViewById(R.id.numOfPlayers_txt_gameListView);


        createdBy.setText("Created By: " + game.getCreatedBy());
        city.setText("City: " + game.getCity());
        address.setText("Address: " + game.getLoaction());
        date.setText("Date: " + game.getDate());
        time.setText("Time: " + game.getTime());
        sportType.setText("Sport type: " + game.getSportType());
        numberOfPlayers.setText("Number of empty places: " + String.valueOf(game.getNumberOfPlayers()));


        return v;
    }
}
