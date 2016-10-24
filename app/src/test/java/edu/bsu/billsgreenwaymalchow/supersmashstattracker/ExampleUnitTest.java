package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
private WinLossCounter stats;
    @Before
        public void Setup() {
                stats = new WinLossCounter();
        }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void percentage_isCorrect(){
        stats.addWin();
        stats.addWin();
        stats.addWin();
        stats.addLoss();
        stats.addLoss();
        stats.addLoss();
        assertEquals(0.5, stats.getWinPercentage());
    }

    @Test
    public void totalMatchesIsCorrect(){
        stats.addWin();
        stats.addLoss();
        assertEquals(2, stats.getTotalMatches());
    }
}