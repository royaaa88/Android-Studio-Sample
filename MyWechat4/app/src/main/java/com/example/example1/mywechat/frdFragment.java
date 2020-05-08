package com.example.example1.mywechat;


import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.example1.mywechat.crud.MyDAO;
import com.example.example1.mywechat.friends.Friend;
import com.example.example1.mywechat.friends.friendAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class frdFragment extends Fragment implements View.OnClickListener {

    private MyDAO myDAO;  //数据库访问对象
    private List<Friend> friendList = new ArrayList<>();


    private EditText et_name;  //数据表包含3个字段，第1字段为自增长类型
    private EditText et_age;
    private EditText et_choose;

    private  String selId=null;  //选择项i

    public frdFragment() {
        // Required empty public constructor
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.friend_main, container, false);

        Button bt_add= view.findViewById(R.id.bt_add);bt_add.setOnClickListener(this);
        Button bt_modify=view.findViewById(R.id.bt_modify);bt_modify.setOnClickListener(this);
        Button bt_del=view.findViewById(R.id.bt_del);bt_del.setOnClickListener(this);

        et_name=(EditText)view.findViewById(R.id.et_name);
        et_age=(EditText)view.findViewById(R.id.et_age);
        et_choose=(EditText)view.findViewById(R.id.et_choose);

        myDAO = new MyDAO(getContext());  //创建数据库访问对象
        if(myDAO.getRecordsNumber()==0) {  //防止重复运行时重复插入记录
            myDAO.insertInfo("tian", 20);   //插入记录
            myDAO.insertInfo("wang", 40); //插入记录
        }


        RecyclerView recyclerView = view.findViewById(R.id.rv_friends);
        displayRecords();   //显示记录
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        friendAdapter adapter = new friendAdapter(friendList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void displayRecords(){  //显示记录方法定义
        friendList.clear();
        Cursor all = myDAO.allQuery();
        for (int i = 0; i < myDAO.getRecordsNumber(); i++) {
            all.moveToNext();//不能马上从游标中取值
            Friend friend=new Friend(i,all.getString(1),all.getInt(2));
            friendList.add(friend);
        }
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {  //实现的接口方法
        selId= et_choose.getText().toString();
        if(selId!=null) {  //选择了列表项后，可以增加/删除/修改
            String p1 = et_name.getText().toString().trim();
            int p2 = Integer.parseInt(et_age.getText().toString());
            switch (v.getId()){
                case  R.id.bt_add:
                    myDAO.insertInfo(p1,p2);
                    break;
                case  R.id.bt_modify:
                    myDAO.updateInfo(p1,p2,selId);
                    Toast.makeText(getContext(),"更新成功！",Toast.LENGTH_SHORT).show();
                    break;
                case  R.id.bt_del:
                    myDAO.deleteInfo(selId);
                    Toast.makeText(getContext(),"删除成功！",Toast.LENGTH_SHORT).show();
                    et_name.setText(null);et_age.setText(null); selId=null; //提示
            }
        }else{  //未选择列表项
            if(v.getId()==R.id.bt_add) {  //单击添加按钮
                String p1 = et_name.getText().toString();
                String p2=et_age.getText().toString();
                if(p1.equals("")||p2.equals("")){  //要求输入了信息
                    Toast.makeText(getContext(),"姓名和年龄都不能空！",Toast.LENGTH_SHORT).show();
                }else{
                    myDAO.insertInfo(p1, Integer.parseInt(p2));  //第2参数转型
                }
            } else{   //单击了修改或删除按钮
                Toast.makeText(getContext(),"请先选择记录！",Toast.LENGTH_SHORT).show();
            }
        }
        displayRecords();//刷新ListView对象
    }
}
