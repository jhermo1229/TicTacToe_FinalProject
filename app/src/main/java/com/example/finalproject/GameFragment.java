package com.example.finalproject;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * GameFragment - main fragment for the tic-tac-toe game
 */
public class GameFragment extends Fragment {

    public static final String PLAYER_ONE_NAME = "playerOneName";
    public static final String PLAYER_TWO_NAME = "playerTwoName";
    private View gameView;
    public static String TAG = GameFragment.class.getName();
    public static final String RECORD_ADDED_SUCCESSFULLY = "Record Added Successfully";
    public static final String UNSUCCESSFUL_IN_RECORD_ADDING_PLEASE_CHECK_LOGS = "Unsuccessful in record adding. Please check logs";
    private GameAdapter gameAdapter;
    private RecyclerView gameRV;
    public static boolean playerOneTurn = true;
    public static ImageView img_stroke, img_win;
    public static String playerOneName, playerTwoName;
    public static RelativeLayout win_relative_layout;

    public static TextView playerOneTextView, playerTwoTextView, txt_win, player_turn;
    private Button btn_reset, btn_again, btn_home;

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    private DatabaseHelper dbh;
    private Boolean insertStat;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gameView = inflater.inflate(R.layout.fragment_game, container, false);
        //Initializing the active database
        dbh = new DatabaseHelper(getActivity());
        player_turn = gameView.findViewById(R.id.player_turn);
        playerOneTextView = gameView.findViewById(R.id.playerOne_win);
        playerTwoTextView = gameView.findViewById(R.id.playerTwo_win);
        playerOneName = getArguments().getString(PLAYER_ONE_NAME);
        playerTwoName = getArguments().getString(PLAYER_TWO_NAME);
        playerOneTextView.setText(playerOneName + ": 0");
        playerTwoTextView.setText(playerTwoName + ": 0");
        player_turn.setText("Turn of " + playerOneName);
        win_relative_layout = gameView.findViewById(R.id.win_relative_layout);

        txt_win = gameView.findViewById(R.id.txt_win);

        //Recycler view for the game board
        gameRV = gameView.findViewById(R.id.tictactoeboard);
        img_stroke = gameView.findViewById(R.id.img_stroke);
        btn_again = gameView.findViewById(R.id.btn_again);
        btn_home = gameView.findViewById(R.id.btn_home);


        //Number of created boxes for the board - total of 9 boxes
        ArrayList<Bitmap> arrayBoxes = getBitmaps();

        //Recycler adapter passing the grid and context as parameter
        gameAdapter = new GameAdapter(getContext(), arrayBoxes);

        //Number of grid columns for the Grid layout
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        gameRV.setLayoutManager(layoutManager);
        gameRV.setAdapter(gameAdapter);

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                win_relative_layout.setVisibility(View.INVISIBLE);
                reset();
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                Player player = new Player();
                player.setPlayerOneName(playerOneName);
                player.setPlayerTwoName(playerTwoName);
                player.setPlayerOneWin(MainActivity.playerOneScore);
                player.setPlayerTwoWin(MainActivity.playerTwoScore);
                player.setCurrentTimeStamp(String.valueOf(sdf1.format(System.currentTimeMillis())));

                //passing the object to the database handler
                insertStat = dbh.InsertPlayer(player);

                //if values was added successfully will toast a success message. If not, will toast a unsuccessful message
                if (insertStat) {
                    Toast.makeText(getActivity(), RECORD_ADDED_SUCCESSFULLY, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), UNSUCCESSFUL_IN_RECORD_ADDING_PLEASE_CHECK_LOGS, Toast.LENGTH_SHORT).show();
                }

                MainActivity.playerOneScore = 0;
                MainActivity.playerTwoScore = 0;
                getParentFragmentManager().popBackStack();
            }


        });

        return gameView;
    }


    private void reset() {
        ArrayList<Bitmap> arrayBox = getBitmaps();
        img_stroke.setImageBitmap(null);
        gameAdapter.setArrayBoxes(arrayBox);
        gameAdapter.notifyDataSetChanged();
        playerOneTurn = true;
        player_turn.setText("Turn of " + playerOneName);
    }


    //Resetting the boxes
    @NonNull
    private ArrayList<Bitmap> getBitmaps() {
        ArrayList<Bitmap> arrayBoxes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            arrayBoxes.add(null);
        }
        return arrayBoxes;
    }
}