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

import com.sample.fortest.DateUtil;

public class Fragment2 extends Fragment {
    ImageButton button;
    Dialog dialog;
    View view;
    String gusetName= "";
    ArrayList<String> guestList;
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
                showDialog(); // 아래 showDialog() 함수 호출
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
    public void resetText(){
        EditText editText1 = dialog.findViewById(R.id.edit_roomName);
        EditText editText2 = dialog.findViewById(R.id.edit_fine);
        EditText editText3 = dialog.findViewById(R.id.edit_textDate);
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
    }

    public void showDialog(){
        dialog.show(); // 다이얼로그 띄우기

        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        ArrayList<HashMap<String, String>> array = ((MainActivity)getActivity()).idListArray();
        HashMap<String, String> roomhash = new HashMap<>();
        guestList = new ArrayList<>();
        gusetName = "";


        //mail 버튼
        EditText editText = dialog.findViewById(R.id.guestMail);

        Button gBtn = dialog.findViewById(R.id.guestButton);
        Button aBtn = dialog.findViewById(R.id.addButton);

        TextView tx = dialog.findViewById(R.id.guestList);

        EditText editText1 = dialog.findViewById(R.id.edit_roomName);
        EditText editText2 = dialog.findViewById(R.id.edit_fine);
        EditText editText3 = dialog.findViewById(R.id.edit_textDate);

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
                            editText.setText("");
                            //Toast.makeText(getContext(), input, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "No User", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gcount = guestList.size();
                String guest = "guest";
                for(int i = 0; i<gcount; i++){
                    roomhash.put(guest+Integer.toString(i+1),guestList.get(i));
                }
                roomhash.put("RoomName",editText1.getText().toString());
                String startday = editText2.getText().toString();
                roomhash.put("startDay",startday);
                roomhash.put("fine",editText3.getText().toString());
                array.add(roomhash);
                resetText();
                tx.setText("");
                dialog.dismiss();
            }
        });
    }
}