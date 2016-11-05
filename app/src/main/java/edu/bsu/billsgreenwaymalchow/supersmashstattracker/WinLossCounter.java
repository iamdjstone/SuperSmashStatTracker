package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

public class WinLossCounter {

    private int wins = 0;
    private int losses = 0;

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
