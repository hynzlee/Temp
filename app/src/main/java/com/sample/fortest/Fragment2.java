package com.sample.fortest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        //todo가 담긴 data 받아오기
        HashMap<String, String> hash = new HashMap<>();
        hash.put("roomName", "다이어트");
        hash.put("id", "hji0104@naver.com");
        hash.put("guest1", "1");
        hash.put("guest2", "2");
        hash.put("guest3", "3");
        hash.put("fine", "1");
        hash.put("totalFine1", "1");
        hash.put("totalFine2", "1");
        hash.put("totalFine3", "1");
        hash.put("totalFine4", "1");
        hash.put("startDay", "2021.7.10");
        hash.put("endDay", "2021.7.24");
        ArrayList<HashMap<String, String>> room = new ArrayList<>();
        room.add(hash);


        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        RoomAdapter adapter = new RoomAdapter(getContext(), room);


        // 리사이클러뷰에 TodoAdapter 객체 지정.
        //RoomAdapter adapter = new RoomAdapter(room) ;
        gridView.setAdapter(adapter) ;

        return view;
    }

}