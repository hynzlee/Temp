package com.sample.fortest;

import java.util.ArrayList;
import java.util.HashMap;

public class IDListData {

    private static String id;
    private String name;
    private static int profile;
    private ArrayList<String> roomName;


    public IDListData(String id, String name, int profile, ArrayList<String> roomName) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.roomName = roomName;
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
    public ArrayList getRoomName() {
        return roomName;
    }
    public void setRoomName(ArrayList roomName) {
        this.roomName = roomName;
    }
    public HashMap<String, String> getHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        for(int i=0 ; i<4 ;i++){
            map.put("roomName"+Integer.toString(i), roomName.get(i));
        }
        return map;
    }

}
