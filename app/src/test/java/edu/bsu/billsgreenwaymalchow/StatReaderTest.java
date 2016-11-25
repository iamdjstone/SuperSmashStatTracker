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

public class StatReaderTest {

    private StatReader statReader;
    private InputStream inputStream;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("TestAsset.xml");
        inputStream = new FileInputStream("/Users/dakotamalchow/AndroidStudioProjects/SuperSmashStatTracker/app/src/test/assets/TestAsset.xml");
        statReader = new StatReader();
    }

    @Test
    public void testDOMReturnsCorrectAttribute(){
        Document document = statReader.createDocumentForTest(inputStream);
        NodeList nodeList = document.getElementsByTagName("tracker");
        Element firstElement = (Element)nodeList.item(0);
        Assert.assertEquals("vs Gestwicki", firstElement.getAttribute("name"));
    }

    public void testDOMReturnsCorrectNumberOfStatTrackers(){
        int totalNumberOfTrackers = statReader.findTotalNumberOfTrackers();
        Assert.assertEquals(2, totalNumberOfTrackers);
    }

}