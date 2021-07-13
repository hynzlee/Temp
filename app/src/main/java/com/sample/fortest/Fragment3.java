package com.sample.fortest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
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
    ListView listView;
    View view;
    DayAdapter dayAdapter;
    ContentAdapter contentAdapter;
    Button button;
    String clickDay;

    private Intent intent;
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
        MainActivity ma =  ((MainActivity)getActivity());
        dayAdapter.setActivity(ma);
        recyclerView.setAdapter(dayAdapter) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        /*
        intent = getActivity().getIntent();// 인텐트 받아오기
        clickDay = intent.getStringExtra("date"); //Adapter에서 받은 키값 연결
        Toast.makeText(getContext(), clickDay, Toast.LENGTH_SHORT).show();*/

        //listView = view.findViewById(R.id.dayContent);
        //contentAdapter = new contentAdapter();

        listView = (ListView) view.findViewById(R.id.list3);
        /*
        customAdapter = new CustomAdapter(getContext(), customer);
        customListView.setAdapter(customAdapter);

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext().getApplicationContext(), "채팅방으로 이동합니다.", Toast.LENGTH_LONG).show();
                //((MainActivity)getActivity()).replaceFragment(Fragment3.newInstance());

            }
        });
        return rootView;*/


        return view;
    }

}
