package com.sample.fortest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class Fragment1 extends Fragment{
    private ArrayList<HashMap<String, String>> todo;
    private static final String TAG = "exception 정수형 바꾸기";
    private ImageButton imageButton;
    //ImageView iv_photo;
    private final static int TAKE_PICTURE = 1;
    private String mCurrentPhotoPath;
    private final static int REQUEST_TAKE_PHOTO = 1;
    private TextView camera;
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TodoAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_1, container, false);
        //todo가 담긴 data 받아오기
        swipeRefreshLayout = view.findViewById(R.id.tab1_swife);
        recyclerView = view.findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;
        todo = new ArrayList<HashMap<String, String>>();
        todo = ((MainActivity)getActivity()).todoArray();
        // 리사이클러뷰에 TodoAdapter 객체 지정.
        readToDo();



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        readToDo();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        return view;
    }

    public void updateTest(){

    }

    public void readToDo(){
        todo = ((MainActivity)getActivity()).todoArray();
        adapter = new TodoAdapter(todo, ((MainActivity)getActivity()).strEmail);
        adapter.SetToDo(todo);
        recyclerView.setAdapter(adapter) ;

    }

}
