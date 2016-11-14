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

@SuppressWarnings("WeakerAccess")
public class StatTrackerWriter {

    public Document document;
    private File saveFile;
    private Element statKeeper;

    public StatTrackerWriter() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        document = documentBuilder.newDocument();
        statKeeper = document.createElement("statKeeper");
        document.appendChild(statKeeper);
    }

    public void setFile(File file) {
        saveFile = file;
    }

    //will use in next iteration
    @SuppressWarnings("unused")
    public void writeToFile() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(saveFile);
        transformer.transform(source, result);
    }

    public void createStatTrackerElement(StatTracker statTracker) throws TransformerException {
        Element tracker = document.createElement("tracker");
        statKeeper.appendChild(tracker);
        Attr idAttr = document.createAttribute("id");
        tracker.setAttributeNode(idAttr);
        Attr nameAttr = document.createAttribute("name");
        tracker.setAttributeNode(nameAttr);
        Attr gameVersionAttr = document.createAttribute("gameVersion");
        tracker.setAttributeNode(gameVersionAttr);
        Attr winsAttr = document.createAttribute("wins");
        tracker.setAttributeNode(winsAttr);
        Attr lossesAttr = document.createAttribute("losses");
        tracker.setAttributeNode(lossesAttr);

        idAttr.setValue(Integer.toString(statTracker.getId()));
        nameAttr.setValue(statTracker.getName());
        gameVersionAttr.setValue(statTracker.getGameVersion());
        winsAttr.setValue(Integer.toString(statTracker.getWins()));
        lossesAttr.setValue(Integer.toString(statTracker.getLosses()));
    }
}
