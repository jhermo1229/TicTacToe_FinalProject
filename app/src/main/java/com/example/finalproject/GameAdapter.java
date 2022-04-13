package com.example.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
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

    private ArrayList<Bitmap> arrayBoxes;
    private Context context;

    //Constructor with parameter for context and the number of boxes in the grid
    public GameAdapter(Context context, ArrayList<Bitmap> arrayBoxes) {
        this.context = context;
        this.arrayBoxes = arrayBoxes;

    }


    //Created view holder for the game adapter
    class ViewHolder extends RecyclerView.ViewHolder{

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
