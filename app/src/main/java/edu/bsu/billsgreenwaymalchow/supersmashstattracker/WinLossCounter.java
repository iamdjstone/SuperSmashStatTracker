package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

public class WinLossCounter {

    private int wins;
    private int losses;

    public void setWins(int wins){
        this.wins = wins;
    }

    public void setLosses(int losses){
        this.losses = losses;
    }

    public void addWin(){
        wins++;
    }

    public void addLoss(){
        losses++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinPercentage(){
        return (double)wins/ getTotalMatches() * 100;
    }

    public int getTotalMatches(){
        return wins + losses;
    }
}
