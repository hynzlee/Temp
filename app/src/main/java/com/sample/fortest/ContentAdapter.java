package com.sample.fortest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContentAdapter extends ArrayAdapter {
    private Context context;
    private List list;

    class ViewHolder {
        public ImageView iv_profile;
        public TextView tv_name;
        public ImageView iv_todo;
        public TextView tv_todo;
    }

    //ContentAdapter의 생성자
    public ContentAdapter(Context context, ArrayList list){
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    //position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.day_list, parent, false);
        }


        viewHolder = new ContentAdapter.ViewHolder();
        viewHolder.tv_name = (TextView) convertView.findViewById(R.id.day_profile_name);
        viewHolder.tv_todo = (TextView) convertView.findViewById(R.id.day_todo_text);

        viewHolder.iv_profile = (ImageView) convertView.findViewById(R.id.day_profile_image);
        viewHolder.iv_todo = (ImageView) convertView.findViewById(R.id.day_todo_image);
        /*
        final Customer customer = (Customer) list.get(position);
        //viewHolder.tv_name.setText(customer.getName());
        viewHolder.tv_name.setText(customer.getLocation());
        viewHolder.tv_todo.setText("평점");
        viewHolder.iv_profile.setImageResource(customer.getImage());
        viewHolder.iv_todo.setImageResource(customer.getImage());*/

        //Return the completed view to render on screen
        return convertView;
    }
}
