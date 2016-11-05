package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


import org.junit.Assert;
import org.junit.Before;

public class StatKeeperTest {
    private StatKeeper statKeeper;
    private StatTracker statTracker;

    @Before
    public void setup(){
        statKeeper = new StatKeeper();
        statTracker = new StatTracker();
    }
}
