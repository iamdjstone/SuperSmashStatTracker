package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class StatFinder {

    private String mostPlayedGameVersion = "";
    private HashMap<String, Integer> gameVersionPlays;
    private int plays;
    private NodeList nodeList;
    private int totalWins = 0;
    private int totalLosses = 0;

    public StatFinder(Document document){
        nodeList = document.getElementsByTagName("tracker");
    }

    public String findMostPlayedGameVersion(){
        initializeHashMapForGameVersions();
        if (countTrackerPlays()==0){
            mostPlayedGameVersion = "None";
        }
        return mostPlayedGameVersion;
    }

    public void calculateTotals(){
        for(int itemNumber = 0; itemNumber < nodeList.getLength(); itemNumber++) {
            Element e = (Element) nodeList.item(itemNumber);
            totalWins += Integer.parseInt(e.getAttribute("wins"));
            totalLosses += Integer.parseInt(e.getAttribute("losses"));
        }
    }

    public int getTotalWins(){
        return totalWins;
    }

    public int getTotalLosses(){
        return totalLosses;
    }

    private void initializeHashMapForGameVersions() {
        gameVersionPlays = new HashMap<>();
        gameVersionPlays.put("Nintendo 64", 0);
        gameVersionPlays.put("Melee", 0);
        gameVersionPlays.put("Brawl", 0);
        gameVersionPlays.put("Wii U", 0);
        gameVersionPlays.put("3DS", 0);
    }

    private int countTrackerPlays(){
        plays = 0;
        for (int itemNumber = 0; itemNumber < nodeList.getLength(); itemNumber++) {
            Element e = (Element) nodeList.item(itemNumber);
            int trackerPlays = Integer.parseInt(e.getAttribute("wins")) +
                    Integer.parseInt(e.getAttribute("losses"));
            updateGameVersionHashMap(e, trackerPlays);
        }
        return plays;
    }

    private void updateGameVersionHashMap(Element e, int thisAmountOfTrackerPlays){
        for (HashMap.Entry<String, Integer> entry : gameVersionPlays.entrySet()) {
            if (entry.getKey().equals(e.getAttribute("gameVersion"))) {
                entry.setValue(entry.getValue() + thisAmountOfTrackerPlays);
                if (entry.getValue() == plays) {
                    mostPlayedGameVersion = "Multiple";
                }
                if (entry.getValue() > plays) {
                    plays = entry.getValue();
                    mostPlayedGameVersion = e.getAttribute("gameVersion");
                }
            }
        }
    }
}
