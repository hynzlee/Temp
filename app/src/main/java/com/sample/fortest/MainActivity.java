package com.sample.fortest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNV;
    final private static String TAG = "CAMERA";
    String strNickname, strProfile, strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initial
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom Navigation
        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());
                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.navigation_1);

        //LoginActicity->MainActivity Intent 가져오기
        //TextView tvNickname = findViewById(R.id.tvNickname);
        //ImageView ivProfile = findViewById(R.id.ivProfile);
        //TextView tvEmail = findViewById(R.id.tvEmail);
        Intent intent = getIntent();
        strNickname = intent.getStringExtra("name");
        strProfile = intent.getStringExtra("profile");
        strEmail = intent.getStringExtra("email");
        //tvNickname.setText(strNickname);
        //tvEmail.setText(strEmail);

        //프로필 사진 url을 사진으로 보여줌
        //Glide.with(this).load(strProfile).into(ivProfile);

    }
    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                fragment = new Fragment1();
            } else if (id == R.id.navigation_2){
                fragment = new Fragment2();
            } else if (id == R.id.navigation_3) {
                fragment = new Fragment3();
            }
            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }
    public ArrayList<HashMap<String, String>> todoArray(){
        ArrayList<HashMap<String, String>> todo = new ArrayList<>();
        todo.add(new TodoData("다이어트","2021.7.12", 0, "밥 반절만 먹기").getHashMap());
        todo.add(new TodoData("공부하기","2021.7.10", 0, "수학의 정석 한 장 풀기").getHashMap());
        return todo;
    }
    public ArrayList<HashMap<String, String>> RoomArray(){
        ArrayList<HashMap<String, String>> room = new ArrayList<>();
        /*private String roomName;
        private String id;
        private ArrayList<String> guest;
        private int fine;
        private ArrayList<String> fines;
        private String startDay;*/
        //ArrayList<String> guest = new ArrayList<>();
        //room.add(new RoomData("다이어트","hji0104@naver.com", guest, 300, fines, "2021.7.9").getHashMap());
        return room;
    }
}