package com.sample.fortest;

import java.util.HashMap;
import java.util.Objects;

public class TodoData {

    private String title;
    private String date;
    private int photo;
    private String todo;

    public TodoData(String title, String date, int photo, String todo) {
        this.title = title;
        this.date = date;
        this.photo = photo;
        this.todo = todo;
    }

    public TodoData(HashMap<String, String> map){
        title = map.get("title");
        date = map.get("date");
        todo = map.get("todo");
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
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
    public HashMap<String, String> getHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("date", date);
        map.put("photo", Integer.toString(photo));
        map.put("todo", todo);
        return map;
    }
}
