package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTrackerWriterTest {

    private StatTrackerWriter statTrackerWriter;

    @Before
    public void setUp() throws TransformerException, ParserConfigurationException {
        statTrackerWriter = new StatTrackerWriter();
        statTrackerWriter.createStatTrackerElement();
    }

    @Test
    public void nameIsCorrect(){
        statTrackerWriter.updateNameAndGameVersion("Vs Gestwicki", "Melee");
        NodeList nodeList = statTrackerWriter.document.getElementsByTagName("tracker");
        Element firstElement = (Element) nodeList.item(0);
        Assert.assertEquals("Vs Gestwicki", firstElement.getAttribute("name"));
    }

    @Test
    public void WinsAreCorrect(){
        statTrackerWriter.updateWinsAndLosses(10, 3);
        NodeList nodeList = statTrackerWriter.document.getElementsByTagName("tracker");
        Element firstElement = (Element) nodeList.item(0);
        Assert.assertEquals("10", firstElement.getAttribute("wins"));
    }
}