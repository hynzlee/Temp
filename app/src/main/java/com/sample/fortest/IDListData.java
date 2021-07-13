package com.sample.fortest;

import java.util.HashMap;

public class IDListData {

    private String id;
    private String name;
    private String photo;


    public IDListData(String id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }
    public String getId() {
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
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public HashMap<String, String> getHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("photo", String.valueOf(photo));
        return map;
    }

    @Override
    public String toString(){
        return id;
    }

}
