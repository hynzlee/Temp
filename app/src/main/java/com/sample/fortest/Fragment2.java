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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sample.fortest.DateUtil;

public class Fragment2 extends Fragment {
    ImageButton button;
    Dialog dialog;
    View view;
    String gusetName= "";
    DateUtil dateUtil;
    ArrayList<String> guestList;
    ArrayList<HashMap<String, String>> room;
    private SwipeRefreshLayout swipeRefreshLayout;
    GridView gridView;
    RoomAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2, container, false);

        swipeRefreshLayout = view.findViewById(R.id.tab2_swipe);

        room = ((MainActivity)getActivity()).getMachIDtoRoomList();

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog);
        dateUtil = new DateUtil();
        button = view.findViewById(R.id.roomaddBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(); // 아래 showDialog() 함수 호출
            }
        });
        refresh();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    ((MainActivity)getActivity()).MakeRoomData();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        );

        // 리사이클러뷰에 TodoAdapter 객체 지정.
        //RoomAdapter adapter = new RoomAdapter(room) ;

        return view;
    }

    public void refresh(){
        gridView = (GridView) view.findViewById(R.id.gridView);
        adapter = new RoomAdapter(getContext(), room,(MainActivity)getActivity());
        gridView.setAdapter(adapter) ;
    }
    public void SetText(TextView tx, ArrayList arrayList){
        String st = "";
        for(int i = 0; i<arrayList.size();i++)
            st += arrayList.get(i) + "\n";
        tx.setText(st);
    }
    public void resetText(){
        ((EditText)dialog.findViewById(R.id.roomName)).setText("");
        ((EditText)dialog.findViewById(R.id.fine)).setText("");
        ((EditText)dialog.findViewById(R.id.edit_textDate)).setText("");
    }

    public void showDialog(){
        dialog.show(); // 다이얼로그 띄우기

        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        ArrayList<HashMap<String, String>> array = ((MainActivity)getActivity()).idListArray();
        guestList = new ArrayList<>();
        gusetName = "";
        //mail 버튼
        EditText editText = dialog.findViewById(R.id.guestMail);
        EditText roomName = dialog.findViewById(R.id.roomName);
        EditText fine = dialog.findViewById(R.id.fine);
        EditText date = dialog.findViewById(R.id.edit_textDate);
        Button gBtn = dialog.findViewById(R.id.guestButton);
        Button addBtn = dialog.findViewById(R.id.addButton);
        TextView tx = dialog.findViewById(R.id.guestList);


        gBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                String input = editText.getText().toString().replace(" ", "");
                boolean inData = false;
                if (!guestList.contains(input)) {
                    for (int i = 0; i < array.size(); i++) {
                        if (array.get(i).get("id").equals(input)) {
                            guestList.add(input);
                            SetText(tx, guestList);
                            editText.setText("");
                            //Toast.makeText(getContext(), input, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "No User", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                HashMap<String, String> hash = new HashMap<String, String>();
                hash.put("roomName", String.valueOf(roomName.getText()));
                for(int i = 0; i < guestList.size();i++) {
                    String guestindex = "guests" + String.valueOf(i+1);
                    hash.put(guestindex,guestList.get(i));
                }
                hash.put("fine", String.valueOf(fine.getText()));
                //데이트 바꿔주는 작업
                String datestring = String.valueOf(date.getText());
                datestring = datestring.substring(0, 2) + "." + datestring.substring(2, 4) + "."+datestring.substring(4, 6);
                try {
                    hash.put("endDay", dateUtil.getDate(datestring,14));
                } catch (ParseException e) {
                    hash.put("endDay", "99.99.99");
                }
                hash.put("startDay", datestring);
                //endTime, endDay,id를 더 넣어줘야함
                ((MainActivity)getActivity()).setRoom(hash);
                resetText();
                tx.setText("");
                dialog.dismiss();
            }
        });
    }

    public void setRoom(ArrayList<RoomData> machIDtoRoomList) {
        room = new ArrayList<>();
        for(int i = 0 ; i < machIDtoRoomList.size();i++) {
            room.add(machIDtoRoomList.get(i).getHashMap());
        }
    }
}