package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

public class StatTracker {

    private String name;
    private String gameVersion;
    private int wins;
    private int losses;
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