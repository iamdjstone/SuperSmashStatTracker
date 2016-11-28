package edu.bsu.billsgreenwaymalchow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.bsu.billsgreenwaymalchow.supersmashstattracker.StatReader;
import edu.bsu.billsgreenwaymalchow.supersmashstattracker.StatWriter;

public class StatReaderTest {

    private StatReader statReader;
    private InputStream inputStream;
    private Document document;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("TestAsset.xml");
        inputStream = new FileInputStream("/Users/dakotamalchow/AndroidStudioProjects/SuperSmashStatTracker/app/src/test/assets/TestAsset.xml");
        statReader = new StatReader();
        document = statReader.createDocumentForTest(inputStream);
    }

    @Test
    public void testDOMReturnsCorrectAttribute(){
        NodeList nodeList = document.getElementsByTagName("tracker");
        Element firstElement = (Element)nodeList.item(0);
        Assert.assertEquals("vs Gestwicki", firstElement.getAttribute("name"));
    }

    @Test
    public void testDOMReturnsCorrectNumberOfStatTrackers(){
        int totalNumberOfTrackers = statReader.findTotalNumberOfTrackers();
        Assert.assertEquals(3, totalNumberOfTrackers);
    }

    @Test
    public void testWriterFindsMostPlayedGameVersion(){
        StatWriter statWriter =  new StatWriter();
        statWriter.setDocument(statReader.getDocument());
        Assert.assertEquals("Brawl", statWriter.findMostPlayedGameVersion());
    }

    @Test
    public void testWriterFindsTieForMostPlayedGameVersion(){
        NodeList nodeList = document.getElementsByTagName("tracker");
        Element firstElement = (Element)nodeList.item(0);
        firstElement.setAttribute("wins", "6000");
        StatWriter statWriter =  new StatWriter();
        statWriter.setDocument(document);
        Assert.assertEquals("Multiple", statWriter.findMostPlayedGameVersion());
    }
}
