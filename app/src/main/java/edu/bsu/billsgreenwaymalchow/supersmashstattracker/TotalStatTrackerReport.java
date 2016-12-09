package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import java.util.HashMap;


@SuppressWarnings("WeakerAccess")
public class TotalStatTrackerReport {
    private int totalWins;
    private int totalLosses;
    private String mostPlayedGameVersion = "None";
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
        if (totalLosses + totalWins == 0){
            mostPlayedGameVersion = "None";
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

}
