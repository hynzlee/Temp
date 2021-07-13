package com.sample.fortest;

import android.content.Intent;
import android.os.Bundle;
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

    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_3, container, false);

        //현지 임의 데이
        ArrayList<HashMap<String, String>> roomArray = roomArray();
        ArrayList<HashMap<String, String>> todoArray = roomArray();
        ArrayList<String> emailArray = new ArrayList<>();
        HashMap<String, String> hash = roomArray.get(0);

        CalendarView  calendar = (CalendarView) view.findViewById(R.id.calendarView);
        /*
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = Integer.toString(year)+"."+Integer.toString(month)+"."+Integer.toString(dayOfMonth);
                String guest = "guest";
                String temp;
                for(int i=1;i<4;i++){
                    temp = hash.get(guest+Integer.toString(i));
                    if(temp == null) break;
                    else{
                        emailArray.add(temp);
                    }
                }
            }
        });*/
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
    public ArrayList<HashMap<String, String>> roomArray(){
        ArrayList<HashMap<String, String>> array = new ArrayList<>();
        HashMap<String, String> hash = new HashMap<>();
        hash.put("id","hji0104@naver.com");
        hash.put("guest1","hji0101@naver.com");
        hash.put("guest2","hji0102@naver.com");
        hash.put("guest3","hji0103@naver.com");
        hash.put("fine","300");
        hash.put("startDay","21.07.14");
        hash.put("endDay","21.07.27");
        array.add(hash);
        return array;
    }
    public ArrayList<HashMap<String, String>> todoArray(){
        ArrayList<HashMap<String, String>> array = new ArrayList<>();
        HashMap<String, String> hash1 = new TodoData("hji0104@naver.com","다이어트","21.07.16",Integer.toString(R.drawable.list),"hihi").getHashMap();
        HashMap<String, String> hash2 = new TodoData("hji0103@naver.com","다이어트","21.07.16",null,"hello").getHashMap();

        HashMap<String, String> hash3 = new TodoData("hji0102@naver.com","다이어트","21.07.16",Integer.toString(R.drawable.list),"hoho").getHashMap();
        HashMap<String, String> hash4 = new TodoData("hji0104@naver.com","다이어트","21.07.18",Integer.toString(R.drawable.list),"h").getHashMap();

        array.add(hash1);
        array.add(hash2);

        array.add(hash3);
        array.add(hash4);
        return array;
    }

}
