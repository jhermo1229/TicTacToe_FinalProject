package com.example.finalproject;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * GameFragment - main fragment for the tic-tac-toe game
 */
public class GameFragment extends Fragment {

    private View gameView;
    private GameAdapter gameAdapter;
    private RecyclerView gameRV;
    public static boolean playerOneTurn = true;
    public static ImageView img_stroke;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gameView = inflater.inflate(R.layout.fragment_game, container, false);

        //Recycler view for the game board
        gameRV = gameView.findViewById(R.id.tictactoeboard);
        img_stroke = gameView.findViewById(R.id.img_stroke);

        //Number of created boxes for the board - total of 9 boxes
        ArrayList<Bitmap> arrayBoxes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            arrayBoxes.add(null);
        }

        //Recycler adapter passing the grid and context as parameter
        gameAdapter = new GameAdapter(getContext(), arrayBoxes);

        //Number of grid columns for the Grid layout
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        gameRV.setLayoutManager(layoutManager);
        gameRV.setAdapter(gameAdapter);
        return gameView;
    }
}