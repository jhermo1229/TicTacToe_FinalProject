package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewScoreFragment extends Fragment {

    public static final String ID = "id";
    public static final String PLAYER_ONE_NAME = "player_one";
    public static final String PLAYER_TWO_NAME = "player_two";
    public static final String PLAYER_ONE_SCORE = "player_one_score";
    public static final String PLAYER_TWO_SCORE = "player_two_score";
    public static final String DATE = "game_timestamp";
    private RecyclerView mRecyclerview;
    private List<Player> playerList = new ArrayList<>();
    private PlayerListAdapter mAdapter;
    DatabaseHelper dbh;


    public ViewScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_view_score, container, false);

        //getting the recycler view
        mRecyclerview = viewFragment.findViewById(R.id.recyclerView);

        //initializing database
        dbh = new DatabaseHelper(getActivity());

        //Getting all the available data using getAll parameter
        Cursor cursor = dbh.viewData();
        System.out.println("CURSOR: " + cursor);
        //if no record was found, will toast a no records message.
        //Otherwise will set the values to object
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Records Found", Toast.LENGTH_SHORT).show();
            return viewFragment;
        } else {
            //Setting the values in object then added to list
            if (cursor.moveToFirst()) {
                do {
                    Player player = new Player();
                    player.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                    player.setPlayerOneName(cursor.getString(cursor.getColumnIndexOrThrow(PLAYER_ONE_NAME)));
                    player.setPlayerTwoName(cursor.getString(cursor.getColumnIndexOrThrow(PLAYER_TWO_NAME)));
                    player.setPlayerOneWin(cursor.getInt(cursor.getColumnIndexOrThrow(PLAYER_ONE_SCORE)));
                    player.setPlayerTwoWin(cursor.getInt(cursor.getColumnIndexOrThrow(PLAYER_TWO_SCORE)));
                    player.setCurrentTimeStamp(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));

                    System.out.println(">>>>" + player.getPlayerOneWin());
                    playerList.add(player);
                } while (cursor.moveToNext());
            }
        }

        //Closing the cursor and database then binding to the adapter needed
        cursor.close();
        dbh.close();
        bindAdapter();

        return viewFragment;
    }

    //Binding the list to recyclerview by using the object adapter
    private void bindAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(layoutManager);
        mAdapter = new PlayerListAdapter(playerList, getContext());
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}