package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTrackerWriterTest {

    private Element firstElement;

    @Before
    public void setUp() throws TransformerException, ParserConfigurationException {
        StatTracker statTracker = new StatTracker();
        statTracker.setId(0);
        statTracker.setName("Vs Gestwicki");
        statTracker.setGameVersion("Nintendo 64");
        statTracker.setWins(10);
        statTracker.setLosses(5);
        StatTrackerWriter statTrackerWriter = new StatTrackerWriter();
        statTrackerWriter.createStatTrackerElement(statTracker);
        NodeList nodeList = statTrackerWriter.document.getElementsByTagName("tracker");
        firstElement = (Element) nodeList.item(0);
    }

    @Test
    public void idIsCorrect() throws TransformerException {
        Assert.assertEquals(Integer.toString(0), firstElement.getAttribute("id"));
    }

    @Test
    public void nameIsCorrect() throws TransformerException {
        Assert.assertEquals("Vs Gestwicki", firstElement.getAttribute("name"));
    }

    @Test
    public void gameVersionIsCorrect() throws TransformerException {
        Assert.assertEquals("Nintendo 64", firstElement.getAttribute("gameVersion"));
    }

    @Test
    public void winsAreCorrect() throws TransformerException {
        Assert.assertEquals(Integer.toString(10), firstElement.getAttribute("wins"));
    }

    @Test
    public void LossesAreCorrect() throws TransformerException {
        Assert.assertEquals(Integer.toString(5), firstElement.getAttribute("losses"));
    }

}