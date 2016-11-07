package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import java.io.Serializable;

public class StatTracker implements Serializable{

    private String name;
    private String gameVersion;
    private WinLossCounter winLossCounter = new WinLossCounter();

    public void setName(String name) {
        this.name = name;
    }

    public void setGameVersion(String gameVersion){
        this.gameVersion = gameVersion;
    }

    public String getName() {
        return name;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public int getWins(){
        return winLossCounter.getWins();
    }

    public int getLosses(){
        return winLossCounter.getLosses();
    }
}