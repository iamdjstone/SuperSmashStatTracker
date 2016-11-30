package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class StatFinder {

    public String findMostPlayedGameVersion(Document document) {
        String gameVersion = "";
        HashMap<String, Integer> gameVersionPlays = new HashMap<>();
        gameVersionPlays.put("Nintendo 64", 0);
        gameVersionPlays.put("Melee", 0);
        gameVersionPlays.put("Brawl", 0);
        gameVersionPlays.put("Wii U", 0);
        gameVersionPlays.put("3DS", 0);
        NodeList nodeList = document.getElementsByTagName("tracker");
        if (nodeList.getLength() == 0) {
            return "None";
        }
        int plays = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            int trackerPlays = Integer.parseInt(e.getAttribute("wins")) +
                    Integer.parseInt(e.getAttribute("losses"));
            for (HashMap.Entry<String, Integer> entry : gameVersionPlays.entrySet()) {
                if (entry.getKey().equals(e.getAttribute("gameVersion"))) {
                    entry.setValue(entry.getValue() + trackerPlays);
                    if (entry.getValue() == plays) {
                        gameVersion = "Multiple";
                    }
                    if (entry.getValue() > plays) {
                        plays = entry.getValue();
                        gameVersion = e.getAttribute("gameVersion");
                    }
                }
            }
        }
        return gameVersion;
    }
}
