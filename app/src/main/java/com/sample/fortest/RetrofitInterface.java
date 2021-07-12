package com.sample.fortest;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/kakaoLogin")
    Call<Void> executeKakakoLogin (@Body HashMap<String, String> map);

    @POST("/findID")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/setToDoText")
    Call<Void> executeSetToDo (@Body HashMap<String, String> map);

    @POST("/makeRoom")
    Call<Void> executeMakeRoom (@Body HashMap<String, String> map);

    @POST("/setToDoPhoto")
    Call<Void> executeSetPhoto (@Body HashMap<String, String> map);

    @POST("/getRoombyRoomName")
    Call<RoomData> executegetRoom (@Body HashMap<String, String> map);

    @POST("/getToDoByid")
    Call<ArrayList<TodoData>> execiteToDo (@Body HashMap<String, String> map);

    @POST("/getToDoByRoomtitle")
    Call<ArrayList<TodoData>> execiteToDobyRoom (@Body HashMap<String, String> map);
}
