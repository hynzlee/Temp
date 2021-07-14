package com.sample.fortest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.sample.fortest.DateUtil;

public class Fragment3 extends Fragment {
    ListView listView;
    View view;
    String guest0,guest1,guest2,guest3;
    ContentAdapter contentAdapter;
    Button button;
    String clickDay;
    HashMap<String, String> rommHashMap;

    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_3, container, false);

        //현지 임의 데이
        //ArrayList<HashMap<String, String>> roomArray = roomArray();
        //ArrayList<HashMap<String, String>> todoArray = roomArray();
        //ArrayList<String> emailArray = new ArrayList<>();
        //HashMap<String, String> hash = roomArray.get(0);

        CalendarView  calendar = (CalendarView) view.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String days = "";
                year /= 100;
                days += (year < 10)? "0"+ Integer.toString(year) : Integer.toString(year);
                days += (month < 10)? "0"+Integer.toString(month+1) : Integer.toString(month+1);
                days += (dayOfMonth < 10)? "0"+Integer.toString(dayOfMonth) : Integer.toString(dayOfMonth);
                Log.e("day",days);
            }
        });
        listView = (ListView) view.findViewById(R.id.list3);


        return view;
    }
    public void setHash(HashMap<String, String> map){
        this.rommHashMap = map;
    }

}
