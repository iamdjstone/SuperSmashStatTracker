package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.widget.Button;

import java.util.ArrayList;

public class TrackerList {

    private ArrayList<Button> trackerList = new ArrayList<>();

    public void addTracker(Button button){
        trackerList.add(button);
    }

    public ArrayList<Button> getTrackerList(){
        return trackerList;
    }

    public Button getTrackerName(int ordinal){
        return trackerList.get(ordinal);
    }
}