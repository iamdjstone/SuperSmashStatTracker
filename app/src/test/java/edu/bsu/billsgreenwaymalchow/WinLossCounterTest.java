package edu.bsu.billsgreenwaymalchow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.bsu.billsgreenwaymalchow.supersmashstattracker.WinLossCounter;

import static org.junit.Assert.assertEquals;

public class WinLossCounterTest {

private WinLossCounter winLossCounter;

    @Before
        public void Setup() {
                winLossCounter = new WinLossCounter();
        }

    @Test
    public void testPercentageIsCorrect(){
        winLossCounter.addWin();
        winLossCounter.addWin();
        winLossCounter.addWin();
        winLossCounter.addLoss();
        winLossCounter.addLoss();
        winLossCounter.addLoss();
        Assert.assertEquals(0.5, winLossCounter.getWinPercentage(), .01);
    }

    @Test
    public void testTotalMatchesIsCorrect(){
        winLossCounter.addWin();
        winLossCounter.addLoss();
        assertEquals(2, winLossCounter.getTotalMatches());
    }
}