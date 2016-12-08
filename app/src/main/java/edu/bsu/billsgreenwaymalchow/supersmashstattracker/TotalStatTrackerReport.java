package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Element;

import java.util.HashMap;

/**
 * Created by Danny on 12/7/2016.
 */

public class TotalStatTrackerReport {
    private int totalWins;
    private int totalLosses;
    private String mostPlayedGameVersion;
    private int mostGameVersionPlays;
    private HashMap<String, Integer> gameVersionPlays;

    public TotalStatTrackerReport(){
        gameVersionPlays = new HashMap<>();
        gameVersionPlays.put("Nintendo 64", 0);
        gameVersionPlays.put("Melee", 0);
        gameVersionPlays.put("Brawl", 0);
        gameVersionPlays.put("Wii U", 0);
        gameVersionPlays.put("3DS", 0);
    }

    public void addToTotalWins(int currentStatTrackerWins){
        totalWins += currentStatTrackerWins;
    }

    public void addToTotalLosses(int currentStatTrackerLosses){
        totalLosses += currentStatTrackerLosses;
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

    public void updateGameVersionHashMap(String currentGameVersionType, int thisAmountOfTrackerPlays){
        for (HashMap.Entry<String, Integer> entry : gameVersionPlays.entrySet()) {
            if (entry.getKey().equals(currentGameVersionType)){
                entry.setValue(entry.getValue() + thisAmountOfTrackerPlays);
                if (entry.getValue() == mostGameVersionPlays) {
                    mostPlayedGameVersion = "Multiple";
                }
                if (entry.getValue() > mostGameVersionPlays) {
                    mostGameVersionPlays = entry.getValue();
                    mostPlayedGameVersion = currentGameVersionType;
                }
            }
        }
    }

}
