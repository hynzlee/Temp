package com.sample.fortest;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class IDListData {

    private static String id;
    private String name;
    private static int profile;


    public IDListData(String id, String name, int profile) {
        this.id = id;
        this.name = name;
        this.profile = profile;
    }
    public static String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getProfile() {
        return profile;
    }
    public void setProfile(int profile) {
        this.profile = profile;
    }
    public HashMap<String, String> getHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        return map;
    }

    @Override
    public String toString(){
        return id;
    }

}
