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

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomAdapter extends ArrayAdapter {

    private Context context;
    private List list;

    class ViewHolder {
        public ImageButton iv_image;
        public Button button;
        public ImageView iv_image1;
        public ImageView iv_image2;
        public ImageView iv_image3;
        public ImageView iv_image4;
        public TextView tv_1;
    }

    public RoomAdapter(Context context, ArrayList list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
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

        return convertView;
    }

}