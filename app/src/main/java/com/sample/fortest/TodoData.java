package com.sample.fortest;

import java.util.HashMap;
import java.util.Objects;

public class TodoData {

    private String id;
    private String title;
    private String date;
    private int photo;
    private String toDo;

    public TodoData(String id,String title, String date, int photo, String todo) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.photo = photo;
        this.toDo = todo;
    }

    public TodoData(HashMap<String, String> map){
        id = map.get("id");
        title = map.get("title");
        date = map.get("date");
        toDo = map.get("toDo");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public String getTodo() {
        return toDo;
    }

    public void setTodo(String todo) {
        this.toDo = todo;
    }

    public HashMap<String, String> getHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("date", date);
        map.put("photo", Integer.toString(photo));
        map.put("toDo", toDo);
        return map;
    }
    @Override
    public String toString(){
        return title + "\n" +toDo;
    }
}
