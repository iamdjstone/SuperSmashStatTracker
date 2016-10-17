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

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public int getTotalMatches(){
        return wins + losses;
    }

    public String getWinPercentage(){
        double percentage = (double)wins/getTotalMatches() * 100;
        return String.format("%.2f", percentage) + "%";
    }
}
