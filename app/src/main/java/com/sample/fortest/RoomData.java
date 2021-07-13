package com.sample.fortest;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomData {
    String roomName;
    String id;
    String guest1;
    String guest2;
    String guest3;
    int fine;
    int totalFine1;
    int totalFine2;
    int totalFine3;
    int totalFine4;
    String startDay;
    String endDay;


    public int getFine() { return fine; }
    public void setFine(String fine) { this.fine = Integer.parseInt(fine); }
    public String getTotalFine1() { return Integer.toString(totalFine1); }
    public void setTotalFine1(String totalFine1) { this.totalFine1 = Integer.parseInt(totalFine1); }
    public String getTotalFine2() { return Integer.toString(totalFine2); }
    public void setTotalFine2(String totalFine2) { this.totalFine2 = Integer.parseInt(totalFine2); }
    public String getTotalFine3() { return Integer.toString(totalFine3); }
    public void setTotalFine3(String totalFine3) { this.totalFine3 = Integer.parseInt(totalFine3); }
    public String getTotalFine4() { return Integer.toString(totalFine4); }
    public void setTotalFine4(String totalFine4) { this.totalFine4 = Integer.parseInt(totalFine4); }

    public RoomData(String roomName, String id, ArrayList<String> guest, int fine, String startDay, String endDay) {
        this.roomName = roomName;
        this.id = id;
        this.guest1 = (guest.get(0) != null ) ? guest.get(0) : null;
        this.guest2 = (guest.get(1) != null ) ? guest.get(1) : null;
        this.guest3 = (guest.get(2) != null ) ? guest.get(2) : null;
        this.fine = fine;
        this.totalFine1 = 0;
        this.totalFine2 = 0;
        this.totalFine3 = 0;
        this.totalFine4 = 0;
        this.startDay = startDay;
    }


    @Override
    public String toString(){
        return roomName + id + startDay;
    }


    public HashMap<String, String> getHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("roomName", roomName);
        map.put("id", id);
        map.put("fine", Integer.toString(fine));
        map.put("guest1",guest1);
        map.put("guest2",guest2);
        map.put("guest3",guest3);
        map.put("totalFine1",Integer.toString(totalFine1));
        map.put("totalFine2",Integer.toString(totalFine2));
        map.put("totalFine3",Integer.toString(totalFine3));
        map.put("totalFine4",Integer.toString(totalFine4));
        map.put("startDay", startDay);


        return map;
    }

}
