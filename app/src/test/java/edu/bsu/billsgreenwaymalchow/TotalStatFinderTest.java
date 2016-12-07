package edu.bsu.billsgreenwaymalchow;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.bsu.billsgreenwaymalchow.supersmashstattracker.TotalStatFinder;

public class TotalStatFinderTest {

    private TotalStatFinder totalStatFinder;

    public void setUp(String resourceName) throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        totalStatFinder = new TotalStatFinder(document);
    }

    @Test
    public void testStatFinderFindsMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        totalStatFinder.calculateStats();
        Assert.assertEquals("Brawl", totalStatFinder.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsNoMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetTrackersWithNoPlays");
        totalStatFinder.calculateStats();
        Assert.assertEquals("None", totalStatFinder.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsTieForMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetTiedTrackers");
        totalStatFinder.calculateStats();
        Assert.assertEquals("Multiple", totalStatFinder.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsNoneForNone() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetNoTrackers");
        totalStatFinder.calculateStats();
        Assert.assertEquals("None", totalStatFinder.getMostPlayedGameVersion());
    }

    @Test
    public void testFinderCalculatesTotalWins() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        totalStatFinder.calculateStats();
        int totalWins = totalStatFinder.getTotalWins();
        Assert.assertEquals(3500, totalWins);
    }

    @Test
    public void testFinderCalculatesTotalLosses() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        totalStatFinder.calculateStats();
        int totalLosses = totalStatFinder.getTotalLosses();
        Assert.assertEquals(4000, totalLosses);
    }

    @Test
    public void testFinderReturnsCorrectNumberOfStatTrackers() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        int totalNumberOfTrackers = totalStatFinder.findTotalNumberOfTrackers();
        Assert.assertEquals(3, totalNumberOfTrackers);
    }
}
