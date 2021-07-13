package com.sample.fortest;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class Fragment2 extends Fragment {
    FloatingActionButton fab;
    Dialog dialog;
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

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog);

        fab = view.findViewById(R.id.Fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(); // 아래 showDialog01() 함수 호출
            }
        });

        // 리사이클러뷰에 TodoAdapter 객체 지정.
        //RoomAdapter adapter = new RoomAdapter(room) ;
        gridView.setAdapter(adapter) ;

        return view;
    }
    public void showDialog(){
        dialog.show(); // 다이얼로그 띄우기

        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */

        // 위젯 연결 방식은 각자 취향대로~
        // '아래 아니오 버튼'처럼 일반적인 방법대로 연결하면 재사용에 용이하고,
        // '아래 네 버튼'처럼 바로 연결하면 일회성으로 사용하기 편함.
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        // 아니오 버튼
        Button noBtn = dialog.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });
        // 네 버튼
        dialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                Toast.makeText(getContext(), "hihi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}