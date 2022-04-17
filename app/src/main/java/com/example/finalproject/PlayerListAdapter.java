package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Player> playerList;

    public PlayerListAdapter(List<Player> list, Context context) {
        super();
        playerList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextId;
        public TextView mTextPlayerOneName;
        public TextView mTextPlayerOneScore;
        public TextView mTextPlayerTwoName;
        public TextView mTextPlayerTwoScore;
        public TextView mDate;

        public ViewHolder(View v) {
            super(v);
            mTextId = v.findViewById(R.id.txtId);
            mTextPlayerOneName = v.findViewById(R.id.player_one_name);
            mTextPlayerOneScore = v.findViewById(R.id.player_one_score);
            mTextPlayerTwoName = v.findViewById(R.id.player_two_name);
            mTextPlayerTwoScore = v.findViewById(R.id.player_two_score);
            mDate = v.findViewById(R.id.date);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Player playerAdapter = playerList.get(position);
        ((ViewHolder) holder).mTextId.setText(Integer.toString(playerAdapter.getId()));
        ((ViewHolder) holder).mTextPlayerOneName.setText(playerAdapter.getPlayerOneName());
        ((ViewHolder) holder).mTextPlayerOneScore.setText(Integer.toString(playerAdapter.getPlayerOneWin()));
        ((ViewHolder) holder).mTextPlayerTwoName.setText(playerAdapter.getPlayerTwoName());
        ((ViewHolder) holder).mTextPlayerTwoScore.setText(Integer.toString(playerAdapter.getPlayerTwoWin()));
        ((ViewHolder) holder).mDate.setText(playerAdapter.getCurrentTimeStamp());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}
