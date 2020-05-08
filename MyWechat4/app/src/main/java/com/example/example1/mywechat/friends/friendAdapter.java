package com.example.example1.mywechat.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example1.mywechat.R;


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
        inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item,parent,false);
        myViewHolder myViewHolder = new myViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull friendAdapter.myViewHolder holder, int position) {
        Friend friend = list.get(position);
        holder.friendName.setText(friend.getName());
        holder.friendAge.setText(String.valueOf(friend.getAge()));
        holder.friendID.setText(String.valueOf(friend.getId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        View friendView;
        TextView friendName;
        TextView friendAge;
        TextView friendID;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            friendView = itemView;
            friendName = itemView.findViewById(R.id.tvname);
            friendAge = itemView.findViewById(R.id.tvage);
            friendID = itemView.findViewById(R.id.tv_id);
        }
    }

}

