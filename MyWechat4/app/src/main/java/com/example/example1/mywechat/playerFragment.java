package com.example.example1.mywechat;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class playerFragment extends Fragment {

    private List<Player> playerList = new ArrayList<>();

    public playerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab01, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        initPayers();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        playerAdapter adapter = new playerAdapter(playerList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initPayers() {
        for (int i = 0; i < 10; i++) {
            Player player_bi = new Player(i,R.drawable.player_bi,"Brandon Ingram");
            playerList.add(player_bi);
            Player player_kb = new Player(i,R.drawable.player_kb,"Kobe Bryant");
            playerList.add(player_kb);
            Player player_lbj = new Player(i,R.drawable.player_lbj,"LeBron James");
            playerList.add(player_lbj);
            Player player_sc = new Player(i,R.drawable.player_sc,"Stephen Curry");
            playerList.add(player_sc);
            Player player_kd = new Player(i,R.drawable.player_kd,"Kevin Durant");
            playerList.add(player_kd);
            Player player_dr = new Player(i,R.drawable.player_dr,"Derrick Rose");
            playerList.add(player_dr);
            Player player_ga = new Player(i,R.drawable.player_ga,"Giannis Antetokounmpo");
            playerList.add(player_ga);
            Player player_jh = new Player(i,R.drawable.player_jh,"James Harden");
            playerList.add(player_jh);
            Player player_ad = new Player(i,R.drawable.player_ad,"Anthony Davis");
            playerList.add(player_ad);
        }
    }
}
