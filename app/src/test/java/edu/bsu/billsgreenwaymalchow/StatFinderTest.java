package edu.bsu.billsgreenwaymalchow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.bsu.billsgreenwaymalchow.supersmashstattracker.StatFinder;

public class StatFinderTest {

    private Document document;
    private StatFinder statFinder;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = new FileInputStream("/Users/dakotamalchow/AndroidStudioProjects/SuperSmashStatTracker/app/src/test/assets/TestAsset.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(inputStream);
        statFinder = new StatFinder(document);
        statFinder.calculateTotals();
    }

    @Test
    public void testStatFinderFindsMostPlayedGameVersion(){
        String mostPlayedGameVersion = statFinder.findMostPlayedGameVersion();
        Assert.assertEquals("Brawl", mostPlayedGameVersion);
    }

    @Test
    public void testWriterFindsNoMostPlayedGameVersion(){
        NodeList nodeList = document.getElementsByTagName("tracker");
        Element firstElement = (Element)nodeList.item(0);
        Element secondElement = (Element)nodeList.item(1);
        Element thirdElement = (Element)nodeList.item(2);
        firstElement.setAttribute("wins", "0");
        secondElement.setAttribute("wins", "0");
        secondElement.setAttribute("losses", "0");
        thirdElement.setAttribute("wins", "0");
        thirdElement.setAttribute("losses", "0");
        Assert.assertEquals("None", statFinder.findMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsTieForMostPlayedGameVersion(){
        NodeList nodeList = document.getElementsByTagName("tracker");
        Element firstElement = (Element)nodeList.item(0);
        firstElement.setAttribute("wins", "6000");
        Assert.assertEquals("Multiple", statFinder.findMostPlayedGameVersion());
    }

    @Test
    public void testFinderCalculatesTotalWins(){
        int totalWins = statFinder.getTotalWins();
        Assert.assertEquals(3500, totalWins);
    }

    @Test
    public void testFinderCalculatesTotalLosses(){
        int totalLosses = statFinder.getTotalLosses();
        Assert.assertEquals(4000, totalLosses);
    }

    @Test
    public void testFinderReturnsCorrectNumberOfStatTrackers(){
        int totalNumberOfTrackers = statFinder.findTotalNumberOfTrackers();
        Assert.assertEquals(3, totalNumberOfTrackers);
    }
}
