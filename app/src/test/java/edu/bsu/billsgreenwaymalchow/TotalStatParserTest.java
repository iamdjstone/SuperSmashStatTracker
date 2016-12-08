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

import edu.bsu.billsgreenwaymalchow.supersmashstattracker.TotalStatParser;

public class TotalStatParserTest {

    private TotalStatParser totalStatParser;

    public void setUp(String resourceName) throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        totalStatParser = new TotalStatParser(document);
    }

    @Test
    public void testStatFinderFindsMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        totalStatParser.getTotalStatTrackerReport();
        Assert.assertEquals("Brawl", totalStatParser.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsNoMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetTrackersWithNoPlays");
        totalStatParser.getTotalStatTrackerReport();
        Assert.assertEquals("None", totalStatParser.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsTieForMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetTiedTrackers");
        totalStatParser.getTotalStatTrackerReport();
        Assert.assertEquals("Multiple", totalStatParser.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsNoneForNone() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetNoTrackers");
        totalStatParser.getTotalStatTrackerReport();
        Assert.assertEquals("None", totalStatParser.getMostPlayedGameVersion());
    }

    @Test
    public void testFinderCalculatesTotalWins() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        totalStatParser.getTotalStatTrackerReport();
        int totalWins = totalStatParser.getTotalWins();
        Assert.assertEquals(3500, totalWins);
    }

    @Test
    public void testFinderCalculatesTotalLosses() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        totalStatParser.getTotalStatTrackerReport();
        int totalLosses = totalStatParser.getTotalLosses();
        Assert.assertEquals(4000, totalLosses);
    }

    @Test
    public void testFinderReturnsCorrectNumberOfStatTrackers() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        int totalNumberOfTrackers = totalStatParser.findTotalNumberOfTrackers();
        Assert.assertEquals(3, totalNumberOfTrackers);
    }
}
