package com.example.mywechat2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class friendAdapter extends RecyclerView.Adapter<friendAdapter.myViewHolder> {

        private List<Friend> list;
        private Context context;
        private View inflater;

    public friendAdapter(List<Friend> friendList) {
        list = friendList;
    }

    @NonNull
    @Override
    public friendAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        myViewHolder myViewHolder = new myViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull friendAdapter.myViewHolder holder, int position) {
        Friend friend = list.get(position);
        holder.friendImage.setImageResource(friend.getImageid());
        holder.friendName.setText(friend.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        View friendView;
        ImageView friendImage;
        TextView friendName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            friendView = itemView;
            friendImage = itemView.findViewById(R.id.imageView);
            friendName = itemView.findViewById(R.id.textViewPlayerName);
        }
    }

}
