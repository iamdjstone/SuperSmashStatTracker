package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

public class StatTracker {

    private String name;
    private String gameVersion;
    private int wins = 0;
    private int losses = 0;

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

    public void addWin(){
        wins++;
    }

    public void addLoss(){
        losses++;
    }

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public int getTotalMatches(){
        return wins + losses;
    }

    public double getWinPercentage(){
        return (double)wins/getTotalMatches() * 100;
    }
}