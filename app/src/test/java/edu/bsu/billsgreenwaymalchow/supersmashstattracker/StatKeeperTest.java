package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


import org.junit.Assert;
import org.junit.Before;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatKeeperTest {
    private StatKeeper statKeeper;
    private StatTracker statTracker;

    @Before
    public void setup(){
        statKeeper = new StatKeeper();
        statTracker = new StatTracker();
    }
}
