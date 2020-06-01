package com.kyriexu.homework06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.kyriexu.homework06.fragment.Tab01Fragment;
import com.kyriexu.homework06.fragment.Tab02Fragment;
import com.kyriexu.homework06.fragment.Tab03Fragment;
import com.kyriexu.homework06.fragment.Tab04Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment tab01;
    private Fragment tab02;
    private Fragment tab03;
    private Fragment tab04;

    private ImageButton btn1 ;
    private ImageButton btn2 ;
    private ImageButton btn3 ;
    private ImageButton btn4 ;

    // fragment通讯的控制器
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        welcomPage();

    }

    // demo 方法
    private void initFragment(){
        // 要换成这种方式，原来的getFragmentManager()被弃用了，官方文档说明使用这个
        manager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        // 将fragment放入content中
        transaction.add(R.id.content,tab01);
        transaction.add(R.id.content,tab02);
        transaction.add(R.id.content,tab03);
        transaction.add(R.id.content,tab04);
        // 事务提交
        transaction.commit();
    }

    // 默认的页面
    private void welcomPage(){
        tab01 = new Tab01Fragment();
        tab02 = new Tab02Fragment();
        tab03 = new Tab03Fragment();
        tab04 = new Tab04Fragment();

        // 设置按钮的颜色
        btn1.setImageResource(R.drawable.tab_weixin_pressed);
        manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content,tab01);

        transaction.commit();
    }

    private void initView(){
        btn1 = findViewById(R.id.bottom_icon_btn1);
        btn2 = findViewById(R.id.bottom_icon_btn2);
        btn3 = findViewById(R.id.bottom_icon_btn3);
        btn4 = findViewById(R.id.bottom_icon_btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()){
            case R.id.bottom_icon_btn1:
                Log.i(v.getId()+"",v.getId()+"");
                btn1.setImageResource(R.drawable.tab_weixin_pressed);
                btn2.setImageResource(R.drawable.tab_find_frd_normal);
                btn3.setImageResource(R.drawable.tab_address_normal);
                btn4.setImageResource(R.drawable.tab_settings_normal);
                transaction.replace(R.id.content,tab01);
                break;
            case R.id.bottom_icon_btn2:
                Log.i(v.getId()+"",v.getId()+"");
                btn1.setImageResource(R.drawable.tab_weixin_normal);
                btn2.setImageResource(R.drawable.tab_find_frd_pressed);
                btn3.setImageResource(R.drawable.tab_address_normal);
                btn4.setImageResource(R.drawable.tab_settings_normal);
                transaction.replace(R.id.content,tab02);
                break;
            case R.id.bottom_icon_btn3:
                Log.i(v.getId()+"",v.getId()+"");
                btn1.setImageResource(R.drawable.tab_weixin_normal);
                btn2.setImageResource(R.drawable.tab_find_frd_normal);
                btn3.setImageResource(R.drawable.tab_address_pressed);
                btn4.setImageResource(R.drawable.tab_settings_normal);
                transaction.replace(R.id.content,tab03);
                break;
            case R.id.bottom_icon_btn4:
                Log.i(v.getId()+"",v.getId()+"");
                btn1.setImageResource(R.drawable.tab_weixin_normal);
                btn2.setImageResource(R.drawable.tab_find_frd_normal);
                btn3.setImageResource(R.drawable.tab_address_normal);
                btn4.setImageResource(R.drawable.tab_settings_pressed);
                transaction.replace(R.id.content,tab04);
                break;
        }
        transaction.commit();
    }




}
