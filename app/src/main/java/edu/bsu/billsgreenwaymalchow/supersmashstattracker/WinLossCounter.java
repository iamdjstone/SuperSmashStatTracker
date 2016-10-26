package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

class WinLossCounter {

    private int wins = 0;
    private int losses = 0;

    void addWin(){
        wins++;
    }

    void addLoss(){
        losses++;
    }

    int getWins(){
        return wins;
    }

    int getLosses(){
        return losses;
    }

    int getTotalMatches(){
        return wins + losses;
    }

    double getWinPercentage(){
        return Math.round((double)wins/getTotalMatches() * 100);
    }
}
