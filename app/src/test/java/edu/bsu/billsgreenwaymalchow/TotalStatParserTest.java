package edu.bsu.billsgreenwaymalchow;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.bsu.billsgreenwaymalchow.supersmashstattracker.TotalStatParser;
import edu.bsu.billsgreenwaymalchow.supersmashstattracker.TotalStatTrackerReport;

public class TotalStatParserTest {

    private TotalStatParser totalStatParser;
    private Document document;

    public void setUp(String resourceName) throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(inputStream);
        totalStatParser = new TotalStatParser(document);
    }

    @Test
    public void testReadFirstElement() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        NodeList children = document.getChildNodes();
        Element rssElement = (Element)children.item(0);
        Assert.assertEquals("statKeeper", rssElement.getNodeName());
    }

    @Test
    public void testReadFirstAttributeCorrectly() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        NodeList statTrackerAttributes = document.getElementsByTagName("tracker");
        Element firstId = (Element)statTrackerAttributes.item(0);
        Assert.assertEquals("1", firstId.getAttribute("id"));
    }

    @Test
    public void testStatFinderFindsMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        TotalStatTrackerReport totalStatTrackerReport = totalStatParser.createTotalStatTrackerReport();
        Assert.assertEquals("Brawl", totalStatTrackerReport.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsNoMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetTrackersWithNoPlays.xml");
        TotalStatTrackerReport totalStatTrackerReport = totalStatParser.createTotalStatTrackerReport();
        Assert.assertEquals("None", totalStatTrackerReport.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsTieForMostPlayedGameVersion() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetTiedTrackers.xml");
        TotalStatTrackerReport totalStatTrackerReport = totalStatParser.createTotalStatTrackerReport();
        Assert.assertEquals("Multiple", totalStatTrackerReport.getMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsNoneForNoGamesPlayed() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAssetNoTrackers.xml");
        TotalStatTrackerReport totalStatTrackerReport = totalStatParser.createTotalStatTrackerReport();
        Assert.assertEquals("None", totalStatTrackerReport.getMostPlayedGameVersion());
    }

    @Test
    public void testFinderCalculatesTotalWins() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        TotalStatTrackerReport totalStatTrackerReport = totalStatParser.createTotalStatTrackerReport();
        int totalWins = totalStatTrackerReport.getTotalWins();
        Assert.assertEquals(3500, totalWins);
    }
    @Test
    public void testFinderCalculatesTotalLosses() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        TotalStatTrackerReport totalStatTrackerReport = totalStatParser.createTotalStatTrackerReport();
        int totalLosses = totalStatTrackerReport.getTotalLosses();
        Assert.assertEquals(4000, totalLosses);
    }
    @Test
    public void testParserReturnsCorrectNumberOfStatTrackers() throws IOException, SAXException, ParserConfigurationException {
        setUp("TestAsset.xml");
        int totalNumberOfTrackers = totalStatParser.getLengthOfDocumentParsed();
        Assert.assertEquals(3, totalNumberOfTrackers);
    }
}
