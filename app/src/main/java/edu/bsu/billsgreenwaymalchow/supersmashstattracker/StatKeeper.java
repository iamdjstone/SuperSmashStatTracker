package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class StatKeeper {

    public void createSaveXMLDocument() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element statTrackers = document.createElement("statTrackers");
        document.appendChild(statTrackers);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult (System.out);
        transformer.transform(source, result);
    }
/*
    public Document writeXMLDocument(Document document, String iTrackerName) throws ParserConfigurationException {
        document.appendChild(statTrackers);

        Element statTracker = document.createElement("statTracker");
        statTrackers.appendChild(statTracker);

        Attr trackerName = document.createAttribute("Name");
        trackerName.setValue(iTrackerName);
        statTracker.setAttributeNode(trackerName);

        Attr trackerWins = document.createAttribute("Wins");
        trackerWins.setValue(iTrackerWins);
        statTracker.setAttributeNode(trackerWins);

        Attr trackerLosses  = document.createAttribute("Losses");
        trackerLosses.setValue(iTrackerLosses);
        statTracker.setAttributeNode(trackerLosses);

        return document;
    }
    */
}
