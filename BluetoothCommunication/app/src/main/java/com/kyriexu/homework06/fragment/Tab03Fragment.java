package com.kyriexu.homework06.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kyriexu.homework06.R;
import com.kyriexu.homework06.adapter.Tab03Adapter;
import com.kyriexu.homework06.db.SQLUtil;
import com.kyriexu.homework06.pojo.Friend;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab03Fragment extends Fragment {

    public Tab03Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 获取上下文
        final FragmentActivity context = getActivity();
        final SQLUtil util = new SQLUtil(context);
        // 查询所有数据


        View view = inflater.inflate(R.layout.tab03, container, false);


        FloatingActionButton btn = view.findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出框
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // 设置标题
                builder.setTitle("请输入名称");
                // 输入框
                final EditText edt_txt = new EditText(context);
                // 创建poJo
                final Friend friend = new Friend();
                // 设置编辑框
                builder.setView(edt_txt);
                // 确定按钮点击事件
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edt_txt.getText().toString();
                        friend.setName(name);
                        boolean b = util.insertFriend(friend);
                        if (b)
                            Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", null);
                // 展示
                builder.show();
            }
        });
        // 查询所有的
        List<Friend> friends = util.getAllFriends();
        Tab03Adapter adapter = new Tab03Adapter(context, friends);
        RecyclerView recyclerView = view.findViewById(R.id.rcy_contact);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        // set layout manager
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
