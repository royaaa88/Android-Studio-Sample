package com.example.mywechat2;

import android.app.Fragment;
import android.os.Bundle;
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
public class frdFragment extends Fragment {

    private List<Friend> friendList = new ArrayList<>();

    public frdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.tab02, container, false);
        View view = inflater.inflate(R.layout.tab02, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView);
        initFrd();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        friendAdapter adapter = new friendAdapter(friendList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initFrd() {
        for(int i = 0; i < 10; i++) {
            Friend frdGaga=new Friend(i, R.drawable.lady_gaga, "Lady Gaga");
            friendList.add(frdGaga);
            Friend frdAdam=new Friend(i, R.drawable.adam, "Adam Levin");
            friendList.add(frdAdam);
            Friend frdAdele=new Friend(i, R.drawable.adele, "Adele Adkins");
            friendList.add(frdAdele);
            Friend frdGreyson=new Friend(i, R.drawable.greyson, "Greyson Chance");
            friendList.add(frdGreyson);
            Friend frdRihanna=new Friend(i, R.drawable.myrihanna, "Rihanna Fenty");
            friendList.add(frdRihanna);
            Friend frdLana=new Friend(i, R.drawable.lana, "Lana Del Rey");
            friendList.add(frdLana);
            Friend frdKaty=new Friend(i, R.drawable.katy, "Lady Gaga");
            friendList.add(frdKaty);
            Friend frdLorde=new Friend(i, R.drawable.lorde, "Katy Perry");
            friendList.add(frdLorde);
            Friend frdBruno=new Friend(i, R.drawable.bruno_mars, "Bruno Mars");
            friendList.add(frdBruno);
            Friend frdTroye=new Friend(i, R.drawable.troye, "Troye Sivan");
            friendList.add(frdTroye);

        }
    }
}
