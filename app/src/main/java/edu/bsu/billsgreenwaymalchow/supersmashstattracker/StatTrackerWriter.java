package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class StatTrackerWriter {

    private Attr nameAttr;
    private Attr gameVersionAttr;
    private Attr winsAttr;
    private Attr lossesAttr;
    private Document document;
    private File saveFile;

    public void setFile(File file){
        saveFile = file;
    }

    public void createSaveXMLDocument() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    public void printToScreen() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
        System.out.println("Print to Screen");
    }

    public void writeToFile() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(saveFile);
        transformer.transform(source, result);
        printToScreen();
        System.out.println("Write to File");
    }

    public void createStatTrackerElement() throws TransformerException, ParserConfigurationException {
        createSaveXMLDocument();
        System.out.println("create Stat Tracker Element 0");
        Element tracker = document.createElement("tracker");
        System.out.println("create Stat Tracker Element 1");
        tracker.appendChild(document);
        System.out.println("create Stat Tracker Element 2");
        nameAttr = document.createAttribute("name");
        System.out.println("create Stat Tracker Element 3");
        tracker.setAttributeNode(nameAttr);
        System.out.println("create Stat Tracker Element 4");
        gameVersionAttr = document.createAttribute("gameVersion");
        System.out.println("create Stat Tracker Element 5");
        tracker.setAttributeNode(gameVersionAttr);
        System.out.println("create Stat Tracker Element 6");
        winsAttr = document.createAttribute("wins");
        System.out.println("create Stat Tracker Element 7");
        tracker.setAttributeNode(winsAttr);
        System.out.println("create Stat Tracker Element 8");
        lossesAttr = document.createAttribute("losses");
        System.out.println("create Stat Tracker Element 9");
        tracker.setAttributeNode(lossesAttr);
        System.out.println("create Stat Tracker Element 10");
        writeToFile();
        System.out.println("create Stat Tracker Element 11");
    }

    public void updateNameAndGameVersion(StatTracker statTracker){
        nameAttr.setValue(statTracker.getName());
        gameVersionAttr.setValue(statTracker.getGameVersion());
    }

    public void updateWinsAndLosses(StatTracker statTracker){
        winsAttr.setValue(String.format(Locale.getDefault(), "%d", statTracker.getWins()));
        lossesAttr.setValue(String.format(Locale.getDefault(), "%d", statTracker.getLosses()));
    }

}
