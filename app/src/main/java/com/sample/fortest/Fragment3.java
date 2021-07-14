package com.sample.fortest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment3 extends Fragment {
    TextView username1, username2, username3, username4, ClickData;
    TextView fine1, fine2, fine3, fine4;
    ImageView iv_1, iv_2, iv_3, iv_4;
    View view;
    String guest0, guest1, guest2, guest3;
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
                days += (year < 10)? "0"+ Integer.toString(year) : Integer.toString(year);
                days += (month < 10)? "0"+Integer.toString(month+1) : Integer.toString(month+1);
                days += (dayOfMonth < 10)? "0"+Integer.toString(dayOfMonth) : Integer.toString(dayOfMonth);
                Log.e("day",days);


                ClickData = (TextView) view.findViewById(R.id.clickDate);

                fine1 = (TextView) view.findViewById(R.id.Fine1);
                fine2 = (TextView) view.findViewById(R.id.Fine2);
                fine3 = (TextView) view.findViewById(R.id.Fine3);
                fine4 = (TextView) view.findViewById(R.id.Fine4);

                username1 = (TextView) view.findViewById(R.id.User1);
                username2 = (TextView) view.findViewById(R.id.User2);
                username3 = (TextView) view.findViewById(R.id.User3);
                username4 = (TextView) view.findViewById(R.id.User4);

                iv_1 = (ImageView) view.findViewById(R.id.userImage1);
                iv_2 = (ImageView) view.findViewById(R.id.userImage2);
                iv_3 = (ImageView) view.findViewById(R.id.userImage3);
                iv_4 = (ImageView) view.findViewById(R.id.userImage4);

                ClickData.setText(days);


            }
        });


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
