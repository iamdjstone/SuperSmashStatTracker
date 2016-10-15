package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
private StatCollector stats;
    @Before
        public void Setup() {
                stats = new StatCollector();
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
        assertEquals(0.5, stats.getWinPercentage(), 0.0);
    }

    @Test
    public void totalMatchesIsCorrect(){
        stats.addWin();
        stats.addLoss();
        assertEquals(2, stats.getTotalMatches());
    }
}