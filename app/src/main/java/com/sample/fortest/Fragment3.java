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
import java.util.HashMap;


public class Fragment3 extends Fragment {
    TextView username1, username2, username3, username4;
    TextView fine1, fine2, fine3, fine4;
    ImageView iv_1, iv_2, iv_3, iv_4;
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

            }
        });


        return view;
    }
    public void setHash(HashMap<String, String> map){
        this.rommHashMap = map;
    }



}
