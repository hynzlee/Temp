package com.sample.fortest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.PendingIntent.getActivity;
import static androidx.core.app.ActivityCompat.startActivityForResult;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>
 {
     private Retrofit retrofit;
     private RetrofitInterface retrofitInterface;
     private String BASE_URL = "http://192.249.18.163:80";
     private ArrayList<HashMap<String, String>> localDataSet;
     private String id;
     private Context context;
     int REQUEST_IMAGE_CAPTURE = 1;
     String mCurrentPhotoPath;
     final static int TAKE_PICTURE = 1;
     static final int REQUEST_TAKE_PHOTO = 1;
     private static final int REQUEST_CAMERA = 100;
     BitmapConverter bc;
     Fragment1 fragment;

     public void setMainActivity(MainActivity mainActivity) {
         this.mainActivity = mainActivity;
     }

     private MainActivity mainActivity;

     public void setContext(Context context) {
         this.context = context;
     }

     public void SetFragment(Fragment1 fragment1) {
         fragment = fragment1;
     }

     /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private TextView editText;
        private ImageButton imageEditButton;
        private ImageButton  textEditButton;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.textView = (TextView) view.findViewById(R.id.tdTitle);
            this.editText = (TextView) view.findViewById(R.id.tdEdit);
            this.imageEditButton = (ImageButton) view.findViewById(R.id.imgButton);
            this.textEditButton = (ImageButton) view.findViewById(R.id.button1_2);
        }



        public TextView getEditText() {
            return editText;
        }

        public TextView getTextView() { return textView; }

        public ImageButton getImageButton() {return imageEditButton; }

        public ImageButton getTextEditButton() {return textEditButton; };


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
        bc = new BitmapConverter();
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
        String temp =  localDataSet.get(position).get("photo");
        if(localDataSet.size() !=0)
            Log.e("todo",localDataSet.get(0).get("photo"));
        if(temp != null){
            Bitmap bm = bc.StringToBitmap(temp);
            if(bm != null) {
                ImageButton button = viewHolder.getImageButton();
                button.setImageBitmap(bm);
                button.setScaleType(ImageButton.ScaleType.CENTER_CROP);
            }
        }else{
            viewHolder.getImageButton().setImageResource(R.drawable.white);
        }
        viewHolder.getImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카메라 앱을 여는 소스
                sendTakePhotoIntent();
                mainActivity.setItemIndex(position);
                Log.e("text","camera");
            }
        });
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
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
//        Log.e("todo", String.valueOf(localDataSet.size()));
        return localDataSet.size();
    }

     private void sendTakePhotoIntent() {
         Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         ((Activity)context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
     }

}
