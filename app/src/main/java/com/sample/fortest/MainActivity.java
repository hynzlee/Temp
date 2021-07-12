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
import android.content.Context;
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNV;
    final private static String TAG = "CAMERA";
    String strNickname, strProfile, strEmail;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.163:80";
    ArrayList<HashMap<String, String>> todo;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initial
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        todo = new ArrayList<>();
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
        MakeRetrofit();
        SetLoginData();
        GetTodoData();
        //프로필 사진 url을 사진으로 보여줌
        //Glide.with(this).load(strProfile).into(ivProfile);

    }
    public void SetLoginData() {
        HashMap<String, String> loginHashMap = new HashMap<>();
        loginHashMap.put("id", strEmail);
        loginHashMap.put("name", strNickname);
        Call<Void> call = retrofitInterface.executeKakakoLogin(loginHashMap);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //call 다시 오는 내용값 send()에 들어가는 , response status() 안에 들어가는 값
                if (response.code() == 200) {
                    //로그인 성공시 할짓
//                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_LONG).show();
                } else if (response.code() == 404) {
                       //화면 종료
//                    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void GetTodoData(){
        HashMap<String, String> getToDohash = new HashMap<>();
        getToDohash.put("id", strEmail);
        Call<ArrayList<TodoData>> call = retrofitInterface.execiteToDo(getToDohash);
        call.enqueue(new Callback<ArrayList<TodoData>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoData>> call, Response<ArrayList<TodoData>> response) {
                //call 다시 오는 내용값 send()에 들어가는 , response status() 안에 들어가는 값
                if (response.code() == 200) {
                    for(int i = 0;i<response.body().size();i++){
                        todo.add(response.body().get(i).getHashMap());
                        Log.e("test",response.body().get(i).toString());
                    }
                    Toast.makeText(context, "todo 받기 성", Toast.LENGTH_LONG).show();
                } else if (response.code() == 404) {
                    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TodoData>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    public void MakeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
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
//        todo.add(new TodoData("jsuk10@naver.com","다이어트","2021.7.12", 0, "밥 반절만 먹기").getHashMap());
//        todo.add(new TodoData("jsuk10@naver.com","공부하기","2021.7.10", 0, "수학의 정석 한 장 풀기").getHashMap());
        return todo;
    }
}