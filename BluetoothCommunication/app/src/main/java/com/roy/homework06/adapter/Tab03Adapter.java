package com.roy.bluetoothcommunication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roy.bluetoothcommunication.R;
import com.roy.bluetoothcommunication.pojo.Friend;

import java.util.List;


public class Tab03Adapter extends RecyclerView.Adapter<Tab03Adapter.MyViewHolder> {
    private Context context;
    private List<Friend> dataSet;

    public Tab03Adapter(Context context, List<Friend> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(@NonNull View v) {
            super(v);
            textView = v.findViewById(R.id.item_txt);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Friend friend = dataSet.get(position);
        holder.textView.setText(friend.getName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
