package com.example.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
    private Button startBtn;
    private View gameView;
    private String playerOneName;
    private String playerTwoName;

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
                    enterNameDialogBox( TWO);
                } else {
                    playerTwoName = text.getText().toString();

                    //After player two, the game fragment will be called.
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.homeFrame, new GameFragment());
                    transaction.commit();
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