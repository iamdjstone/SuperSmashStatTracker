package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class StatFinder {

    private String mostPlayedGameVersion = "";
    private HashMap<String, Integer> gameVersionPlays;
    private int plays;
    private Document document;
    private NodeList nodeList;


    public StatFinder(Document document){
        this.document = document;
    }

    public String findMostPlayedGameVersion(){
        initializeHashMapForGameVersions();
        checkIfDocumentHasTrackers();
        countTrackerPlays();
        return mostPlayedGameVersion;
    }

    private void initializeHashMapForGameVersions() {
        gameVersionPlays = new HashMap<>();
        gameVersionPlays.put("Nintendo 64", 0);
        gameVersionPlays.put("Melee", 0);
        gameVersionPlays.put("Brawl", 0);
        gameVersionPlays.put("Wii U", 0);
        gameVersionPlays.put("3DS", 0);
    }

    private void checkIfDocumentHasTrackers() {
        nodeList = document.getElementsByTagName("tracker");
        if (nodeList.getLength() == 0) {
            mostPlayedGameVersion = "None";
        }
    }

    private void countTrackerPlays(){
        plays = 0;
        for (int itemNumber = 0; itemNumber < nodeList.getLength(); itemNumber++) {
            Element e = (Element) nodeList.item(itemNumber);
            int trackerPlays = Integer.parseInt(e.getAttribute("wins")) +
                    Integer.parseInt(e.getAttribute("losses"));
            updateGameVersionHashMap(e, trackerPlays);
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
}
