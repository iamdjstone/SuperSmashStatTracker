package edu.bsu.billsgreenwaymalchow;

import org.junit.Assert;
import org.junit.Before;
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

public class StatReaderTest {

    private Document document;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("TestAsset.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(inputStream);
    }

    @Test
    public void testDOMReturnsCorrectAttribute(){
        NodeList nodeList = document.getElementsByTagName("tracker");
        Element firstElement = (Element)nodeList.item(0);
        Assert.assertEquals("vs Gestwicki", firstElement.getAttribute("name"));
    }
}
