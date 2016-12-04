package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import java.util.ArrayList;

public class AttributeList {
    private ArrayList<String> attributeList = new ArrayList<>();

    public ArrayList<String> AttributeList(){
        attributeList.add("id");
        attributeList.add("name");
        attributeList.add("gameVersion");
        attributeList.add("wins");
        attributeList.add("losses");
        return attributeList;
    }

    public ArrayList<String> getAttributeList(){
        return attributeList;
    }
}
