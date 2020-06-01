package com.kyriexu.homework06.fragment;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kyriexu.homework06.R;
import com.kyriexu.homework06.adapter.Tab01Adapter;
import com.kyriexu.homework06.service.ChatService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab01Fragment extends Fragment {
    private final List<String> dataSet = new ArrayList<>();
    private ChatService chatService = new ChatService();
    private final static String TAG = Tab01Fragment.class.getSimpleName();

    public Tab01Fragment() {
        // Required empty public constructor
    }


    // 将xml文件变成fragment对象,方便java控制
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = inflater.inflate(R.layout.tab01, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcy_view);

        // layout manager
        FragmentActivity activity = getActivity();

        // change content not change layout size of recyclerview
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL, false);

        // set layout manager
        recyclerView.setLayoutManager(layoutManager);

        Button btnSearch = view.findViewById(R.id.btn_search);
        final Tab01Adapter adapter = new Tab01Adapter(dataSet, activity);
        // get an adapter instance
        adapter.setDataSet(dataSet);
        // set adapter for recyclerview
        recyclerView.setAdapter(adapter);

        Button btnAccept = view.findViewById(R.id.btn_accept);

        btnAccept.setOnClickListener((btn)->{
            new Thread(()->{
                try {
                    chatService.receiveMsg();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        // Button btnSend = view.findViewById(R.id.btn_send);
        // btnSend.setOnClickListener(btn->{
        //     new Thread(()->{
        //         chatService.sendMsg();
        //     }).start();
        // });


        btnSearch.setOnClickListener(v -> {
            new Thread(() -> {
                Log.i(TAG, "查找");
                chatService.searching(activity, 0);
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(200);
                    Log.i(TAG, "获取设备");
                    Set<BluetoothDevice> devices = chatService.getDevices();
                    devices.forEach(device -> {
                        if (!dataSet.contains(device.getName()))
                            dataSet.add(device.getName());
                    });
                    adapter.setDataSet(dataSet);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
        recyclerView.setAdapter(adapter);
        // try {
        //     count.await();
        // }
        // catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        return view;
    }
}
