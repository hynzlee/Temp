package com.sample.fortest;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomData {
    private String roomName;
    private String id;
    private ArrayList<String> guests;
    private int fine;
    private ArrayList<String> fines;
    private String startDay;

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getFine() { return fine; }
    public void setFine(int fine) { this.fine = fine; }
    public String getStartDay() {return startDay; }
    public void setStartDay(String startDay) {this.startDay = startDay; }
    public ArrayList getGuests() { return guests; }
    public void setGuests(ArrayList<String> guests) {this.guests = guests;}
    public ArrayList getFines() { return fines; }
    public void setFines(ArrayList<String> fines) {this.fines = fines;}


    public HashMap<String, String> getHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("roomName", roomName);
        map.put("id", id);
        map.put("fine", Integer.toString(fine));
        map.put("startDay", startDay);
        for(int i=0 ; i<4 ;i++){
            map.put("guests"+Integer.toString(i), guests.get(i));
        }
        for(int i=0 ; i<4 ;i++){
            map.put("fines"+Integer.toString(i), fines.get(i));
        }
        return map;
    }

}
