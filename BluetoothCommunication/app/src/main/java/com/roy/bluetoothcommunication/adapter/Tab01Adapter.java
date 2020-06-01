package com.roy.bluetoothcommunication.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.roy.bluetoothcommunication.R;

import java.util.Arrays;
import java.util.List;

public class Tab01Adapter extends RecyclerView.Adapter<Tab01Adapter.MyViewHolder> {
    private List<String> dataSet;

    @Override
    public String toString() {
        return "Tab01Adapter{" +
                "dataSet=" + Arrays.toString(dataSet.toArray()) +
                ", context=" + context +
                '}';
    }

    public void setDataSet(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    private Context context;

    public Tab01Adapter(List<String> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    public Tab01Adapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textView;
        private CardView cardView;

        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.card_text);
            cardView = v.findViewById(R.id.cardItem);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position));
    }

    // retuern dataset's length
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
