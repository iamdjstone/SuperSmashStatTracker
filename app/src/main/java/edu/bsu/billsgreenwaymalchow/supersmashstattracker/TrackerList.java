package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.widget.Button;

import java.util.ArrayList;

class TrackerList {

    private ArrayList<Button> trackerList = new ArrayList<>();

    void addTracker(Button button){
        trackerList.add(button);
    }

}