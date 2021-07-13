package com.sample.fortest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.sample.fortest.DateUtil;

public class Fragment3 extends Fragment {
    RecyclerView recyclerView;
    View view;
    DayAdapter dayAdapter;
    Button button;
    Date date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_3, container, false);

        //Fragment3의 상단 바에 day를 확인할 수 있는 버튼
        String[] day = new String[14];
        for(int i = 0; i < day.length; i++){
            day[i] = "day"+Integer.toString(i+1);
        }
        recyclerView = view.findViewById(R.id.recycler3) ;
        dayAdapter = new DayAdapter(day);
        recyclerView.setAdapter(dayAdapter) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //roomdata 내가 임의로 만들었당
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
        hash.put("startDay", "21.07.10");
        hash.put("endDay", "21.07.24");
        ArrayList<HashMap<String, String>> room = new ArrayList<>();
        room.add(hash);


        /*
        button = recyclerView.findViewById(R.id.dayCount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                String input = button.getText().toString();
                //int inputDateInt = Integer.parseInt(input.substring(3));
                //String startDay = room.get(0).get("startDay");
                //Toast.makeText(getContext(), input, Toast.LENGTH_SHORT).show();
            }
        });*/



        return view;
    }
}
