package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


public class StatCollector {

    public int wins = 0;
    public int losses = 0;

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
        return (double)wins/getTotalMatches();
    }

}
