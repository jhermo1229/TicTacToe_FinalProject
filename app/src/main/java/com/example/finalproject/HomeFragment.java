package com.example.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * HomeFragment - This is the main page of the project
 */
public class HomeFragment extends Fragment {


    public static final String PLAYER_NAME = "Player Name";
    public static final String CREATE = "Create";
    public static final String ONE = "one";
    public static final String TWO = "two";
    public static final String CANCEL = "Cancel";
    public static final String SET_NAME_FOR_PLAYER = "Set name for player ";
    private Button startBtn, viewScoreBtn;
    private View gameView;
    public String playerOneName;
    public String playerTwoName;
    private PlayerListAdapter mAdapter;

    //HomeFragment constructor
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gameView = inflater.inflate(R.layout.fragment_home, container, false);

        startBtn = gameView.findViewById(R.id.startBtn);

        startBtn.setOnClickListener((gradeEntryView) -> {
            enterNameDialogBox(ONE);
        });

        viewScoreBtn = gameView.findViewById(R.id.scoreBtn);

        viewScoreBtn.setOnClickListener((gradeEntryView) -> {

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(ViewScoreFragment.TAG);
            transaction.replace(R.id.homeFrame, new ViewScoreFragment());
            transaction.commit();
        });


        return gameView;
    }

    //Alert Dialog box for getting the player name
    private void enterNameDialogBox(final String player) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText text = new EditText(getActivity());
        builder.setTitle(PLAYER_NAME).setMessage(SET_NAME_FOR_PLAYER + player).setView(text);
        builder.setPositiveButton(CREATE, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {

                //If player clicks the create button, function will call a recursive method to itself to enter
                //the same details with player two.
                if (player.equals(ONE)) {
                    playerOneName = text.getText().toString();
                    if (!playerOneName.isEmpty() && !playerOneName.startsWith(" ")) {
                        enterNameDialogBox(TWO);
                    } else {
                        enterNameDialogBox(ONE);
                    }
                } else {
                    playerTwoName = text.getText().toString();
                    if (!playerTwoName.isEmpty() && !playerTwoName.startsWith(" ")) {

                        //After player two, the game fragment will be called.
                        GameFragment gameFrag = new GameFragment();
                        Bundle args = new Bundle();
                        args.putString("playerOneName", playerOneName);
                        args.putString("playerTwoName", playerTwoName);
                        gameFrag.setArguments(args);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.addToBackStack(GameFragment.TAG);
                        transaction.replace(R.id.homeFrame, gameFrag);

                        transaction.commit();
                    } else {
                        enterNameDialogBox(TWO);
                    }
                }
            }
        });
        builder.setNegativeButton(CANCEL, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {
            }
        });
        builder.create().show();
    }
}