package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import java.util.ArrayList;
import java.util.List;

public class TrackerList {

    private ArrayList<String> trackerList = new ArrayList<>();

    public void addTracker(String trackerName){
        trackerList.add(trackerName);
    }

    public ArrayList<String> getTrackerList(){
        return trackerList;
    }

    public String getTrackerName(int ordinal){
        return trackerList.get(ordinal);
    }
}