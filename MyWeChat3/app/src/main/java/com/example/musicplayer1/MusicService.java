package com.example.musicplayer1;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MusicService extends Service {
    @Nullable
    MyReceiver serviceReceiver;
    AssetManager am;
    String[] musics=new String[]{"music0.mp3","music1.mp3","music2.mp3","music3.mp3"};
    MediaPlayer mPlayer;
    //0x11表示没有播放，0x12代表正在播放，0x13代表暂停
    int status=0x11;
    int current=0;

    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate(){
        super.onCreate();
        am=getAssets();
        //创建BroadcastReceiver
        serviceReceiver=new MyReceiver();
        //创建IntentFilter
        IntentFilter filter=new IntentFilter();
        filter.addAction(MainActivity.CTL_ACTION);
        registerReceiver(serviceReceiver,filter);
        mPlayer=new MediaPlayer();
        //为MediaPlayer播放完成事件绑定监听器
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                if (current>=4)
                {
                    current=0;
                }
                Intent sendIntent = new Intent(MainActivity.UPDATE_ACTION);
                sendIntent.putExtra("update",0x14);
                sendIntent.putExtra("current",current);
                //发送广播，将被Activity组件中的BroadcastReceiver接收
                sendBroadcast(sendIntent);
                //准备播放音乐
                prepareAndPlay(musics[current]);
            }
        });
    }

    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int control =intent.getIntExtra("control",-1);
            switch (control)
            {
                //播放或暂停
                case 1:
                    //原来处于没有播放状态
                    if (status==0x11)
                    {
                        //准备并播放音乐
                        prepareAndPlay(musics[current]);
                        status=0x12;
                    }
                    //原来处于播放状态
                    else if (status==0x12)
                    {
                        //暂停
                        mPlayer.pause();
                        //改变为暂停状态
                        status=0x13;
                    }
                    //原来处于暂停状态
                    else if (status==0x13)
                    {
                        //播放
                        mPlayer.start();
                        //改变状态
                        status=0x12;
                    }
                    break;
                //停止声音
                case 2:
                    //如果原来正在播放或暂停
                    if (status==0x12||status==0x13) {
                        //停止播放
                        mPlayer.stop();
                        status = 0x11;
                    }
                    break;
                case 3:
                    //原来处于没有播放或暂停状态
                    if (status==0x11||status==0x13)
                    {
                        if(current==0) {
                            current=3;
                            prepareAndPlay(musics[current]);
                        }
                        //准备并播放音乐
                        else {
                            current=current-1;
                            prepareAndPlay(musics[current]);
                        }
                        status=0x12;
                    }
                    //原来处于播放状态
                    else if (status==0x12)
                    {
                        //上一首//准备并播放音乐
                        if(current==0) {
                            current=3;
                            prepareAndPlay(musics[current]);
                        }
                        else {
                            current=current-1;
                            prepareAndPlay(musics[current]);
                        }
                    }
                    break;
                case 4:
                    //原来处于没有播放或暂停状态
                    if (status==0x11||status==0x13)
                    {
                        if(current==3) {
                            current=0;
                            prepareAndPlay(musics[current]);
                        }   //准备并播放音乐
                        else {
                            current=current+1;
                            prepareAndPlay(musics[current]);
                        }
                        status=0x12;
                    }
                    else if (status==0x12)
                    {
                        if(current==3) {
                            current=0;
                            prepareAndPlay(musics[current]);
                        }
                        else {
                            current=current+1;
                            prepareAndPlay(musics[current]);
                        }
                    }
                    break;
            }
            //广播通知Activity更改图标、文本框
            Intent sendIntent=new Intent(MainActivity.UPDATE_ACTION);
            sendIntent.putExtra("update",status);
            sendIntent.putExtra("current",current);
            //发送广播，将被Activity组件中的BroadcastReceiver接收
            sendBroadcast(sendIntent);
        }
        }


    private void prepareAndPlay(String music)
    {
        try
        {
            //打开指定音乐文件
            AssetFileDescriptor afd=am.openFd(music);
            mPlayer.reset();
            //使用MediaPlayer加载指定的音乐文件
            mPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            //准备声音
            mPlayer.prepare();
            //播放
            mPlayer.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
