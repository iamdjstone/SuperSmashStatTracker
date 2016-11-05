package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WinLossCounterTest {

private WinLossCounter winLossCounter;
    @Before
        public void Setup() {
                winLossCounter = new WinLossCounter();
        }

    @Test
    public void percentage_isCorrect(){
        winLossCounter.addWin();
        winLossCounter.addWin();
        winLossCounter.addWin();
        winLossCounter.addLoss();
        winLossCounter.addLoss();
        winLossCounter.addLoss();
        Assert.assertEquals(50.0, winLossCounter.getWinPercentage(), .01);
    }
    @Test
    public void totalMatchesIsCorrect(){
        winLossCounter.addWin();
        winLossCounter.addLoss();
        assertEquals(2, winLossCounter.getTotalMatches());
    }
}