package com.sample.fortest;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sample.fortest.DateUtil;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    private ArrayList<HashMap<String, String>> arrayList;
    private String[] localDataSet;
    public String clickDay ="";
    private Context context;
    private MainActivity mainActivity;

    public DayAdapter(ArrayList<HashMap<String, String>> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setActivity(MainActivity ma) {
        mainActivity = ma;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private Button button;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.dayCount);
            button = (Button) view.findViewById(R.id.dayCount);
        }

        public TextView getTextView() {
            return textView;
        }
        public Button getButton() {
            return button;
        }
    }

    public DayAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.day_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ArrayList<HashMap<String, String>> room = hashArray();
        viewHolder.getTextView().setText(localDataSet[position]);
        viewHolder.getButton().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String input = viewHolder.getButton().getText().toString();
                int inputDateInt = Integer.parseInt(input.substring(3));
                String startDay = room.get(0).get("startDay");
                try {
                    clickDay = DateUtil.addDate(startDay,0,0,inputDateInt-1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Toast.makeText(v.getContext(), clickDay, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
    public ArrayList<HashMap<String, String>> hashArray(){
        ArrayList<HashMap<String, String>> room = new ArrayList<>();
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
        hash.put("startDay", "21.07.10");
        hash.put("endDay", "21.07.24");
        room.add(hash);
        return room;
    }
    public String getClickDay(){
        return clickDay;
    }
}
