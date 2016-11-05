package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WinLossCounterTest {

private StatTracker stats;
    @Before
        public void Setup() {
                stats = new StatTracker();
        }

    @Test
    public void percentage_isCorrect(){
        stats.addWin();
        stats.addWin();
        stats.addWin();
        stats.addLoss();
        stats.addLoss();
        stats.addLoss();
        Assert.assertEquals(50.0, stats.getWinPercentage(), .01);
    }
    @Test
    public void totalMatchesIsCorrect(){
        stats.addWin();
        stats.addLoss();
        assertEquals(2, stats.getTotalMatches());
    }
}