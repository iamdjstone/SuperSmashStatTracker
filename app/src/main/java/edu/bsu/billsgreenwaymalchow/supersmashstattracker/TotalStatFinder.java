package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public class TotalStatFinder {

    private String mostPlayedGameVersion = "";
    private HashMap<String, Integer> gameVersionPlays;
    private int plays;
    private NodeList nodeList;
    private int totalWins = 0;
    private int totalLosses = 0;

    public TotalStatFinder(Document document){
        nodeList = document.getElementsByTagName("tracker");
    }

    public void calculateStats(){
        initializeHashMapForGameVersions();
        calculateTotalTrackerStats();
        if (totalWins+totalLosses==0){
            mostPlayedGameVersion = "None";
        }
    }

    private void initializeHashMapForGameVersions() {
        gameVersionPlays = new HashMap<>();
        gameVersionPlays.put("Nintendo 64", 0);
        gameVersionPlays.put("Melee", 0);
        gameVersionPlays.put("Brawl", 0);
        gameVersionPlays.put("Wii U", 0);
        gameVersionPlays.put("3DS", 0);
    }

    private void calculateTotalTrackerStats(){
        for (int itemNumber = 0; itemNumber < nodeList.getLength(); itemNumber++) {
            Element e = (Element) nodeList.item(itemNumber);
            int win = Integer.parseInt(e.getAttribute("wins"));
            int loss = Integer.parseInt(e.getAttribute("losses"));
            totalWins += win;
            totalLosses += loss;
            updateGameVersionHashMap(e, win + loss);
        }
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

    public int getTotalWins(){
        return totalWins;
    }

    public int getTotalLosses(){
        return totalLosses;
    }

    public String getMostPlayedGameVersion(){
        return mostPlayedGameVersion;
    }

    public int findTotalNumberOfTrackers(){
        return nodeList.getLength();
    }
}
