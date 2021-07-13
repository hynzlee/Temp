package com.sample.fortest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>
 {
     private Retrofit retrofit;
     private RetrofitInterface retrofitInterface;
     private String BASE_URL = "http://192.249.18.163:80";
     private ArrayList<HashMap<String, String>> localDataSet;
     private String id;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private TextView editText;
        private ImageView imageView;
        private Button imageEditButton, textEditButton;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.textView = (TextView) view.findViewById(R.id.tdTitle);
            this.editText = (TextView) view.findViewById(R.id.tdEdit);
            this.imageView = (ImageView) view.findViewById(R.id.imgView);
            this.imageEditButton = (Button) view.findViewById(R.id.button1_1);
            this.textEditButton = (Button) view.findViewById(R.id.button1_2);
        }



        public TextView getEditText() {
            return editText;
        }

        public TextView getTextView() { return textView; }

        public ImageView getImageView() {return imageView; }

        public Button getImageEditButton() {return imageEditButton; }

        public Button getTextEditButton() {return textEditButton; };


    }
    public TodoAdapter(ArrayList<HashMap<String, String>> dataSet,String id) {
        localDataSet = dataSet;
        this.id = id;
    }
    public void SetToDo(ArrayList<HashMap<String, String>> dataSet){
        localDataSet = dataSet;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_item, viewGroup, false);

        MakeRetrofit();
        return new ViewHolder(view);
    }

     public void MakeRetrofit(){
         retrofit = new Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         retrofitInterface = retrofit.create(RetrofitInterface.class);
     }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String title = localDataSet.get(position).get("title");
        String todo = localDataSet.get(position).get("toDo");
        viewHolder.getTextView().setText(title);
        viewHolder.getEditText().setText(todo);
        viewHolder.getTextEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> getToDohash = new HashMap<>();
                getToDohash.put("id", id);
                getToDohash.put("title",title);
                getToDohash.put("toDo",viewHolder.getEditText().getText().toString());
                localDataSet.get(position).put("toDo",viewHolder.getEditText().getText().toString());
                Call<Void> call = retrofitInterface.executeSetToDo(getToDohash);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        //call 다시 오는 내용값 send()에 들어가는 , response status() 안에 들어가는 값
                        if (response.code() == 200) {
                            //성공시 액션
                            Log.e("ToDo","GetiToDO");
                        } else if (response.code() == 400) {
                            //오류 액션
                            Log.e("ToDo","ToDO is not");
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("ToDO","fail");
                    }
                });
            }
        });
        viewHolder.getImageView().setImageResource(R.drawable.white);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.e("todo", String.valueOf(localDataSet.size()));
        return localDataSet.size();
    }

}
