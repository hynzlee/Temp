package com.sample.fortest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

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
    //아이디 담겨 있는 List
    ArrayList<IDListData> IdLists;
    ArrayList<RoomData> machIDtoRoomList;

    HashMap<String, String> getToDohash;
    int REQUEST_IMAGE_CAPTURE = 1;
    String bitmapString;
    BitmapConverter bitmapConverter;
    int itemPosition;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    Context context;
    IDListData ID;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initial
        super.onCreate(savedInstanceState);
        bitmapConverter = new BitmapConverter();
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        todo = new ArrayList<>();
        getToDohash = new HashMap<>();
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
        Intent intent = getIntent();
        strNickname = intent.getStringExtra("name");
        strProfile = intent.getStringExtra("profile");
        strEmail = intent.getStringExtra("email");
        ID =  new IDListData(strEmail,strNickname,strProfile);
        MakeRetrofit();
        SetLoginData();
        GetTodoData();
        GetIDListData();
        MakeRoomData();
        //프로필 사진 url을 사진으로 보여줌
        //Glide.with(this).load(strProfile).into(ivProfile);

    }


    public void MakeRoomData(HashMap<String,String> map){
        Call<Void> call = retrofitInterface.executeMakeRoom(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    //로그인 성공시 할짓
                    //룸리프래쉬
                } else if (response.code() == 404) {
                    //화면 종료
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void MakeRoomData(){
        HashMap<String,String> map =  new HashMap<String,String>();
        map.put("id", strEmail);
        Call<ArrayList<RoomData>> call = retrofitInterface.getAllRoomData(map);
        call.enqueue(new Callback<ArrayList<RoomData>>() {
            @Override
            public void onResponse(Call<ArrayList<RoomData>> call, Response<ArrayList<RoomData>> response) {
                if (response.code() == 200) {
                    //로그인 성공시 할짓
                    machIDtoRoomList = response.body();
                    if(fragment2 == null)
                        fragment2 = new Fragment2();
                    fragment2.setRoom(machIDtoRoomList);
                } else if (response.code() == 404) {
                    //화면 종료
                }
            }
            @Override
            public void onFailure(Call<ArrayList<RoomData>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void SetLoginData() {
        HashMap<String, String> loginHashMap = ID.getHashMap();
        Call<Void> call = retrofitInterface.executeKakakoLogin(loginHashMap);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    //로그인 성공시 할짓
                } else if (response.code() == 404) {
                    //화면 종료
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void SetToDoImage(int index) {
        HashMap<String, String> todoList = todo.get(index);
        Call<Void> call = retrofitInterface.executeSetPhoto(todoList);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    //로그인 성공시 할짓
                } else if (response.code() == 404) {
                    //화면 종료
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void GetIDListData() {
        Call<ArrayList<IDListData>> call = retrofitInterface.executeSignup(new HashMap<String,String>());
        call.enqueue(new Callback<ArrayList<IDListData>>() {
            @Override
            public void onResponse(Call<ArrayList<IDListData>> call, Response<ArrayList<IDListData>> response) {
                if (response.code() == 200) {
                    //로그인 성공시 할짓
                    IdLists = response.body();
                    Log.e("id",IdLists.toString());
                } else if (response.code() == 404) {
                    //화면 종료
                }
            }
            @Override
            public void onFailure(Call<ArrayList<IDListData>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void GetTodoData(){
        getToDohash.put("id", strEmail);
        Call<ArrayList<TodoData>> call = retrofitInterface.execiteToDo(getToDohash);
        call.enqueue(new Callback<ArrayList<TodoData>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoData>> call, Response<ArrayList<TodoData>> response) {
                //call 다시 오는 내용값 send()에 들어가는 , response status() 안에 들어가는 값
                if (response.code() == 200) {
                    todo.clear();
                    for(int i = 0;i<response.body().size();i++){
                        todo.add(response.body().get(i).getHashMap());
                        Log.e("test",response.body().get(i).toString());
                    }
                    ((Fragment1)fragment1).setAdapter(todo);
                } else if (response.code() == 404) {
                    Toast.makeText(context, "ToDo가 없습니다.", Toast.LENGTH_LONG).show();
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



    public void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                fragment1 = (fragment1 == null) ? new Fragment1() : fragment1;
                fragment = fragment1;
            } else if (id == R.id.navigation_2){
                fragment2 = (fragment2 == null) ? new Fragment2() : fragment2;
                fragment = fragment2;
            } else if (id == R.id.navigation_3) {
                fragment3 = (fragment3 == null) ? new Fragment3() : fragment3;
                fragment = fragment3;
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
        return todo;
    }
    public ArrayList<HashMap<String, String>> idListArray(){
        ArrayList<HashMap<String, String>> tempHash = new ArrayList<>();
        try {
            for(int i =0 ;i< IdLists.size();i++){
                tempHash.add(IdLists.get(i).getHashMap());
            }
        }catch(NullPointerException e){ }

        return tempHash;
    }


    public ArrayList<HashMap<String, String>> getMachIDtoRoomList() {
        ArrayList<HashMap<String, String>> tempHash = new ArrayList<>();
        try {
            for (int i = 0; i < machIDtoRoomList.size(); i++) {
                tempHash.add(machIDtoRoomList.get(i).getHashMap());
            }
        }catch(NullPointerException e){
            Toast.makeText(context, "새로운 방을 만들어주세요~", Toast.LENGTH_LONG).show();
        }
        return tempHash;
    }
    //여기서부터 현지 임의 DB

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap tempBitmap = (Bitmap) extras.get("data");
            bitmapString = bitmapConverter.BitmapToString(tempBitmap);
            ((Fragment1)fragment1).getPosition(itemPosition, bitmapString);
            SetToDoImage(itemPosition);
            Toast.makeText(context, "새로고침 하여 확인해 주세요", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }
    public void setItemIndex(int itemIndex) {
        this.itemPosition = itemIndex;
    }

    public void setToDo(ArrayList<HashMap<String, String>> todo) {
        this.todo = todo;
    }

    // endDay, id를 더 넣어줘야함
    public void setRoom(HashMap<String, String> hash) {
        hash.put("id",strEmail);
        MakeRoomData(hash);
    }
}