package com.example.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * GameAdapter - Adapter for the recycler view of the game
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private ArrayList<Bitmap> arrayBoxes, arrayWin;
    private Context context;
    private Bitmap heartBitmap, xBitmap;
    private String winningPlayer, playerOneName, playerTwoName;

    //Constructor with parameter for context and the number of boxes in the grid
    public GameAdapter(Context context, ArrayList<Bitmap> arrayBoxes) {
        this.context = context;
        this.arrayBoxes = arrayBoxes;
        heartBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_heart);
        xBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_x);
        arrayWin = new ArrayList<>();
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win1));
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win2));
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win3));
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win4));
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win5));
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win6));
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win7));
        arrayWin.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.win8));
        playerOneName = GameFragment.playerOneName;
        playerTwoName = GameFragment.playerTwoName;

    }


    //Created view holder for the game adapter
    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView box_table;

        public ViewHolder(@NonNull View v) {
            super(v);
            box_table = itemView.findViewById(R.id.box_table);
        }
    }

    //OnCreate, inflate the box table layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.box_table, parent, false));
    }

    //Bind each boxes with the array passed
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.box_table.setImageBitmap(arrayBoxes.get(position));

        holder.box_table.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Check first if box has a value already
                if (arrayBoxes.get(position) == null) {

                    //player one is set to true thus will always start first
                    //If user click a box, it will be set and then added a bitmap depending on who is the player
                    if (GameFragment.playerOneTurn) {
                        arrayBoxes.set(position, heartBitmap);
                        GameFragment.playerOneTurn = false;
                        GameFragment.player_turn.setText("Turn of " + playerTwoName);
                    } else {
                        arrayBoxes.set(position, xBitmap);
                        GameFragment.playerOneTurn = true;
                        GameFragment.player_turn.setText("Turn of " + playerOneName);
                    }

                    //Win checker on click. If win will display a layout who is the winner
                    if (checkWin()) {
                        win();
                    }
                    notifyItemChanged(position);
                }
            }
        });
    }

    private boolean checkWin() {

        //Check if horizontal rows of table has a winning player. Need to check a box if null to prevent winning from the start of the game (all boxes are null)
        if ((arrayBoxes.get(0) == arrayBoxes.get(1)) && (arrayBoxes.get(1) == arrayBoxes.get(2)) && arrayBoxes.get(0) != null) {
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(5));
            checkWinningPlayer(0);
            return true;
        } else if ((arrayBoxes.get(3) == arrayBoxes.get(4)) && (arrayBoxes.get(4) == arrayBoxes.get(5)) && arrayBoxes.get(3) != null) {
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(6));
            checkWinningPlayer(5);
            return true;
        } else if ((arrayBoxes.get(6) == arrayBoxes.get(7)) && (arrayBoxes.get(7) == arrayBoxes.get(8)) && arrayBoxes.get(6) != null) {
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(7));
            checkWinningPlayer(6);
            return true;

            //Check if vertical columns of table has a winning player
        } else if (arrayBoxes.get(0) == arrayBoxes.get(3) && arrayBoxes.get(3) == arrayBoxes.get(6) && arrayBoxes.get(0) != null) {
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(2));
            checkWinningPlayer(0);
            return true;
        } else if ((arrayBoxes.get(1) == arrayBoxes.get(4)) && (arrayBoxes.get(4) == arrayBoxes.get(7)) && arrayBoxes.get(1) != null) {
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(3));
            checkWinningPlayer(1);
            return true;
        }else if ((arrayBoxes.get(2) == arrayBoxes.get(5)) && (arrayBoxes.get(5) == arrayBoxes.get(8)) && arrayBoxes.get(2) != null) {
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(4));
            checkWinningPlayer(2);
            return true;

            //Check if slant boxes has a winning player
        }else if(arrayBoxes.get(0)==arrayBoxes.get(4)&&arrayBoxes.get(4)==arrayBoxes.get(8)&&arrayBoxes.get(0)!=null){
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(1));
            checkWinningPlayer(0);
            return true;
        }else if(arrayBoxes.get(2)==arrayBoxes.get(4)&&arrayBoxes.get(4)==arrayBoxes.get(6)&&arrayBoxes.get(2)!=null){
            GameFragment.img_stroke.setImageBitmap(arrayWin.get(0));
            checkWinningPlayer(2);
            return true;
        }
        return false;
    }

    //Gets the array box as a parameter (any of the 3 combination box can be sent) and then checks
    //what bitmap is inserted in the array
    private void checkWinningPlayer(int i) {
        if (arrayBoxes.get(i)==heartBitmap){
            winningPlayer = GameFragment.playerOneName;
        }else{
            winningPlayer = GameFragment.playerTwoName;
        }
    }

    //Main activity contains the main score and it loops depending on the winner.
    //Also contains the setting of the textview in the fragment for the respective score
    private void win() {
        GameFragment.win_relative_layout.setVisibility(View.VISIBLE);
        if(winningPlayer.equals(playerOneName)){
            MainActivity.playerOneScore++;
            GameFragment.playerOneTextView.setText(playerOneName + ": " + MainActivity.playerOneScore);
            GameFragment.txt_win.setText(playerOneName + " wins!!!");

        }else{
            MainActivity.playerTwoScore++;
            GameFragment.playerTwoTextView.setText(playerTwoName + ": " + MainActivity.playerTwoScore);
            GameFragment.txt_win.setText(playerTwoName + " wins!!!");

        }

    }

    @Override
    public int getItemCount() {
        return arrayBoxes.size();
    }


    public ArrayList<Bitmap> getArrayBoxes() {
        return arrayBoxes;
    }

    public void setArrayBoxes(ArrayList<Bitmap> arrayBoxes) {
        this.arrayBoxes = arrayBoxes;
    }
}
