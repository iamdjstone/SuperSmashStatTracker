package edu.bsu.billsgreenwaymalchow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.bsu.billsgreenwaymalchow.supersmashstattracker.StatFinder;

public class StatFinderTest {

    Document document;
    StatFinder statFinder;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = new FileInputStream("/Users/dakotamalchow/AndroidStudioProjects/SuperSmashStatTracker/app/src/test/assets/TestAsset.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        statFinder = new StatFinder();
        document = documentBuilder.parse(inputStream);
    }

    @Test
    public void testStatFinderFindsMostPlayedGameVersion(){
        String mostPlayedGameVersion = statFinder.findMostPlayedGameVersion(document);
        Assert.assertEquals("Brawl", mostPlayedGameVersion);
    }

    @Test
    public void testWriterFindsTieForMostPlayedGameVersion(){
        NodeList nodeList = document.getElementsByTagName("tracker");
        Element firstElement = (Element)nodeList.item(0);
        firstElement.setAttribute("wins", "6000");
        Assert.assertEquals("Multiple", statFinder.findMostPlayedGameVersion(document));
    }
}
