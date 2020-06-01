package com.roy.bluetoothcommunication.service;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;

import com.roy.bluetoothcommunication.pojo.Bluetooth;

import java.io.IOException;
import java.util.Set;

public class ChatService {
    private Bluetooth bluetooth = new Bluetooth();

    private void open(Activity context, int requestcode) {
        if (bluetooth.isSupport()){
            bluetooth.open(context, requestcode);
            // if (bluetooth.isOpen()) {
            //     Toast.makeText(context,"蓝牙已经打开",Toast.LENGTH_LONG).show();
            // }
        }
    }

    public void searching(Activity context, int requestcode){
        open(context,requestcode);
        if (bluetooth.isOpen())
            bluetooth.searchDevice();
        else
            System.out.println("打开蓝牙");
    }

    public Set<BluetoothDevice> getDevices(){
        return bluetooth.getDevices();
    }

    public void sendMsg(BluetoothDevice device,String msg) throws IOException {
        if (bluetooth.isConnected()){
            bluetooth.sendMessage(device,msg);
        }

    }

    public void receiveMsg() throws IOException {
        if (bluetooth.isOpen()) {
            bluetooth.acceptMessage();
        }
    }
}
