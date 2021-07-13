package com.sample.fortest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomAdapter extends ArrayAdapter {

    String clickRoom;

    private Context context;
    private List list;
    private MainActivity ma;

    class ViewHolder {
        public ImageButton iv_image, iv_button;
        public Button button;
        public ImageView iv_image1;
        public ImageView iv_image2;
        public ImageView iv_image3;
        public ImageView iv_image4;
        public TextView tv_1;
    }

    public RoomAdapter(Context context, ArrayList list,MainActivity ma) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.ma = ma;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final RoomAdapter.ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.room_item, parent, false);
        }

        viewHolder = new RoomAdapter.ViewHolder();
        //viewHolder.tv_name = (TextView) convertView.findViewById(R.id.textView_name);
        viewHolder.iv_button = (ImageButton) convertView.findViewById(R.id.groupImage);
        viewHolder.tv_1 = (TextView) convertView.findViewById(R.id.roomName);
        viewHolder.iv_image = (ImageButton) convertView.findViewById(R.id.groupImage);
        viewHolder.iv_image1 = (ImageView) convertView.findViewById(R.id.user1);
        viewHolder.iv_image2 = (ImageView) convertView.findViewById(R.id.user2);
        viewHolder.iv_image3 = (ImageView) convertView.findViewById(R.id.user3);
        viewHolder.iv_image4 = (ImageView) convertView.findViewById(R.id.user4);

        final HashMap<String, String> room = (HashMap<String, String>)list.get(position);

        // Icon 설정
        //viewHolder.iv_image1.setImageResource(room.get(""));

        // Name 설정
        viewHolder.tv_1.setText(room.get("roomName"));
        viewHolder.iv_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext().getApplicationContext(), "버튼을 눌렀습니다", Toast.LENGTH_LONG).show();
                clickRoom = viewHolder.tv_1.getText().toString();
                //Toast.makeText(getContext().getApplicationContext(), clickRoom, Toast.LENGTH_LONG).show();

                ma.BottomNavigate(R.id.navigation_3);
            }
        });


        return convertView;
    }

}