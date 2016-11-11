package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
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
    private Element statKeeper;

    public StatTrackerWriter() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
        statKeeper = document.createElement("statKeeper");
        document.appendChild(statKeeper);
    }

    public void setFile(File file) {
        saveFile = file;
    }

    public void createSaveXMLDocument() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    private void printToScreen() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }

    public void writeToFile() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(saveFile);
        transformer.transform(source, result);
    }

    public void createStatTrackerElement() throws TransformerException {
        Element tracker = document.createElement("tracker");
        statKeeper.appendChild(tracker);
        nameAttr = document.createAttribute("name");
        tracker.setAttributeNode(nameAttr);
        gameVersionAttr = document.createAttribute("gameVersion");
        tracker.setAttributeNode(gameVersionAttr);
        winsAttr = document.createAttribute("wins");
        tracker.setAttributeNode(winsAttr);
        lossesAttr = document.createAttribute("losses");
        tracker.setAttributeNode(lossesAttr);
    }

    public void updateNameAndGameVersion(String name, String gameVersion){
        nameAttr.setValue(name);
        gameVersionAttr.setValue(gameVersion);
    }

    public void updateWinsAndLosses(int gameWins, int gameLosses){
        winsAttr.setValue(String.format(Locale.getDefault(), "%d", gameWins));
        lossesAttr.setValue(String.format(Locale.getDefault(), "%d", gameLosses));
        try {
            printToScreen();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
