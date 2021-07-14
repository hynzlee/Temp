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
    String guest0, guest1, guest2, guest3;
    ContentAdapter contentAdapter;
    Button button;
    String clickDay;
    HashMap<String, String> rommHashMap;
    ArrayList<TodoData> todoList;
    Integer guestCount = 0;
    //데이, 이미지값 들어
    HashMap<String, String> hostHash;
    HashMap<String, String> guest1Hash;
    HashMap<String, String> guest2Hash;
    HashMap<String, String> guest3Hash;

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_3, container, false);


        CalendarView calendar = (CalendarView) view.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String days = "";
                year /= 100;
                days += (year < 10) ? "0" + Integer.toString(year) : Integer.toString(year);
                days += (month < 10) ? "0" + Integer.toString(month + 1) : Integer.toString(month + 1);
                days += (dayOfMonth < 10) ? "0" + Integer.toString(dayOfMonth) : Integer.toString(dayOfMonth);
                Log.e("day", days);
                //투두리스트에서 해당 이미지 뽑아오지

            }
        });
        listView = (ListView) view.findViewById(R.id.list3);
        //룸데이타에서 fines 보여주기


        return view;
    }

    public void setHash(HashMap<String, String> map) {
        this.rommHashMap = map;
    }

    public void setData(ArrayList<HashMap<String, String>> todoList, HashMap<String,String> roomdata) {
        hostHash = new HashMap<>();
        guest1Hash = new HashMap<>();
        guest2Hash = new HashMap<>();
        guest3Hash = new HashMap<>();
        int maxindex = 0;
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).get("id") == roomdata.get("id")) {
                hostHash.put(todoList.get(i).get("date"), todoList.get(i).get("photo"));
            } else if (todoList.get(i).get("id") == roomdata.get("guest1") ){
                maxindex = maxindex > 1 ? maxindex : 1;
                guest1Hash.put(todoList.get(i).get("date"), todoList.get(i).get("photo"));
            } else if (todoList.get(i).get("id") == roomdata.get("guest2")) {
                maxindex = maxindex > 2 ? maxindex : 2;
                guest2Hash.put(todoList.get(i).get("date"), todoList.get(i).get("photo"));
            } else if (todoList.get(i).get("id") == roomdata.get("guest3")) {
                maxindex = 3;
                guest3Hash.put(todoList.get(i).get("date"), todoList.get(i).get("photo"));
            }
            this.rommHashMap = roomdata;
        }
    }
}
