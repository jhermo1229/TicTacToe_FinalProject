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
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private Button startBtn;
    private View gameView;
    private String playerOneName;
    private String playerTwoName;


    public HomeFragment() {
        // Required empty public constructor
    }


//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        final EditText text = new EditText(getActivity());
//
//        builder.setTitle("New Profile").setMessage("Name this new profile").setView(text);
//        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface di, int i) {
//                final String name = text.getText().toString();
//                //do something with it
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface di, int i) {
//            }
//        });
//        builder.create().show();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gameView = inflater.inflate(R.layout.fragment_home, container, false);

        startBtn = gameView.findViewById(R.id.startBtn);

        startBtn.setOnClickListener((gradeEntryView) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final EditText text = new EditText(getActivity());

            builder.setTitle("Player Name").setMessage("Set name for player one").setView(text);
            builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface di, int i) {
                    playerOneName = text.getText().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    final EditText text = new EditText(getActivity());

                    builder.setTitle("Player Name").setMessage("Set name for player two").setView(text);
                    builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface di, int i) {
                            playerTwoName = text.getText().toString();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                            transaction.addToBackStack(GameFragment.TAG);
                            transaction.replace(R.id.frame, new GameFragment());
                            transaction.commit();

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface di, int i) {
                        }
                    });
                    builder.create().show();


                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface di, int i) {
                }
            });
            builder.create().show();

        });
        return gameView;
    }
}