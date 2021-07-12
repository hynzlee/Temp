package com.sample.fortest;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private TextView editText;
        private ImageView imageView;
        private Button button1, button2;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.textView = (TextView) view.findViewById(R.id.tdTitle);
            this.editText = (TextView) view.findViewById(R.id.tdEdit);
            this.imageView = (ImageView) view.findViewById(R.id.imgView);
            this.button1 = (Button) view.findViewById(R.id.button1_1);
            this.button2 = (Button) view.findViewById(R.id.button1_2);
        }

        public TextView getEditText() {
            return editText;
        }

        public TextView getTextView() { return textView; }

        public ImageView getImageView() {return imageView; }

        public Button getButton1() {return button1; }

        public Button getButton2() {return button2; };


    }
    public TodoAdapter(ArrayList<HashMap<String, String>> dataSet) {

        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position).get("title"));
        viewHolder.getEditText().setText(localDataSet.get(position).get("todo"));
        viewHolder.getImageView().setImageResource(R.drawable.white);

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}
