package com.example.example1.mywechat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class playerAdapter extends RecyclerView.Adapter<playerAdapter.myViewHolder> {

        private List<Player> list;
        private Context context;
        private View inflater;

    public playerAdapter(List<Player> playerList) {
        list = playerList;
    }

    @NonNull
    @Override
    public playerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        myViewHolder myViewHolder = new myViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull playerAdapter.myViewHolder holder, int position) {
        Player player = list.get(position);
        holder.playerImage.setImageResource(player.getImageid());
        holder.playerName.setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        View playerView;
        ImageView playerImage;
        TextView playerName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView;
            playerImage = itemView.findViewById(R.id.imageView);
            playerName = itemView.findViewById(R.id.textViewPlayerName);
        }
    }

}
