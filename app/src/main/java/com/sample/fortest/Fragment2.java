package com.sample.fortest;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
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
    View view;
    ArrayList<String> idList;
    ListView listView;
    ArrayAdapter arrayAdapter;
    View diaLogView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2, container, false);
        idList = new ArrayList<>();
        diaLogView =((View)inflater.inflate(R.layout.dialog, container, false));
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

        idList.add("ss");
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, idList);
        listView = diaLogView.findViewById(R.id.guestList);
        listView.setAdapter(arrayAdapter);
        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        ArrayList<HashMap<String, String>> array = new ArrayList<>();
        array.add(new IDListData("hji0104@naver.com", "이현지", R.drawable.home).getHashMap());
        array.add(new IDListData("hji0103@naver.com", "이현지1", R.drawable.date).getHashMap());
        array.add(new IDListData("hji0102@naver.com", "이현지2", R.drawable.list).getHashMap());
        array.add(new IDListData("hji0101@naver.com", "이현지3", R.drawable.caminit).getHashMap());



        //mail 버튼
        EditText editText = dialog.findViewById(R.id.guestMail);
        Button gBtn = dialog.findViewById(R.id.guestButton);
        gBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                String input = editText.getText().toString().replace(" ","");
                boolean inData = false;
                for(int i=0;i<array.size();i++){
                    if(array.get(i).get("id").equals(input)) {
                        inData = true;
                        idList.add(input);
                        Toast.makeText(getContext(), input, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(!inData) {
                    Toast.makeText(getContext(), "No User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}