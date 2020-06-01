package com.roy.bluetoothcommunication.pojo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import androidx.annotation.RestrictTo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Bluetooth {
    private static final int DURATION = 120;
    private static final UUID UUIDSTR = UUID.fromString("8d10d83a-3985-482d-b465-8e4c1fa1c496");

    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private Set<BluetoothDevice> devices = new HashSet<>();

    public boolean isConnected(){
        return bluetoothAdapter.getState() == BluetoothAdapter.STATE_TURNING_ON;
    }

    // 没在搜寻的时候返回列表
    // 有可能是空列表,所以要判断
    public Set<BluetoothDevice> getDevices() {
        if (isSearching())
            devices = bluetoothAdapter.getBondedDevices();
        return devices;
    }

    public boolean isOpen() {
        return bluetoothAdapter.isEnabled();
    }

    public boolean isSupport() {
        return bluetoothAdapter != null;
    }

    public void open(Activity context, int requestCode) {
        assert (bluetoothAdapter != null);
        if (!bluetoothAdapter.isEnabled()) {
            // 请求打开蓝牙
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            context.startActivityForResult(intent, requestCode);
        }
    }

    public boolean close() {
        assert (bluetoothAdapter != null);
        return bluetoothAdapter.disable();
    }

    public void searchDevice() {
        assert (bluetoothAdapter != null);
        // 当前没在扫描，就扫描
        if (!isSearching())
            bluetoothAdapter.startDiscovery();
    }

    public void isSearchable(Activity context, int requestCode) {
        assert (bluetoothAdapter != null);
        // 创建信使
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        // 设置可被查找的时间
        // 默认为120s,最大300s
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DURATION);
        // 发送通知给系统
        context.startActivityForResult(intent, requestCode);
    }

    public boolean isSearching() {
        assert (bluetoothAdapter != null);
        return bluetoothAdapter.isDiscovering();
    }

    public void acceptMessage() throws IOException {
        BluetoothServerSocket serverSocket =
                bluetoothAdapter.listenUsingRfcommWithServiceRecord(bluetoothAdapter.getName(),
                        UUIDSTR);
        BluetoothSocket socket = serverSocket.accept(60);
        BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = new byte[1024];
        int read = 0;
        while ((read = in.read(bytes)) != -1) {
            String s = new String(bytes, 0, read);
            System.out.println(s);
        }
        in.close();
        socket.close();
    }

    public void sendMessage(BluetoothDevice device, String msg) throws IOException {
        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(UUIDSTR);
        socket.connect();
        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
        out.write(msg.getBytes());
        out.flush();
        out.close();
        socket.close();
    }
}
