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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment2 extends Fragment {
    ImageButton button;
    Dialog dialog;
    View view;
    String gusetName= "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2, container, false);
        ArrayList<HashMap<String, String>> room = ((MainActivity)getActivity()).getMachIDtoRoomList();

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        RoomAdapter adapter = new RoomAdapter(getContext(), room);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog);

        button = view.findViewById(R.id.roomaddBut);
        button.setOnClickListener(new View.OnClickListener() {
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
    public void SetText(TextView tx, ArrayList arrayList){
        String st = "";
        for(int i = 0; i<arrayList.size();i++)
            st += arrayList.get(i) + "\n";
        tx.setText(st);
    }

    public void showDialog(){
        dialog.show(); // 다이얼로그 띄우기




        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        ArrayList<HashMap<String, String>> array = ((MainActivity)getActivity()).idListArray();
        ArrayList<String> guestList = new ArrayList<>();
        gusetName = "";


        //mail 버튼
        EditText editText = dialog.findViewById(R.id.guestMail);
        Button gBtn = dialog.findViewById(R.id.guestButton);
        TextView tx = dialog.findViewById(R.id.guestList);
        gBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                String input = editText.getText().toString().replace(" ", "");
                boolean inData = false;
                if(!guestList.contains(input)) {
                    for (int i = 0; i < array.size(); i++) {
                        if (array.get(i).get("id").equals(input)) {
                            guestList.add(input);
                            SetText(tx, guestList);
                            Toast.makeText(getContext(), input, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "No User", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });
    }
}