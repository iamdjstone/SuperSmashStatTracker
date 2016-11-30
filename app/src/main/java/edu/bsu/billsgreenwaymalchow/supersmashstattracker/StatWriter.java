package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@SuppressWarnings("WeakerAccess")
public class StatWriter {

    public Document document;
    private Element statKeeper;

    public StatWriter() {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            if (documentBuilder != null) {
                document = documentBuilder.newDocument();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void createMainElement(){
        statKeeper = document.createElement("statKeeper");
        document.appendChild(statKeeper);
    }

    public void writeToFile(File saveFile) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(saveFile);
        transformer.transform(source, result);
    }

    public void createStatTrackerElement(StatHolder statHolder) throws TransformerException {
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
        idAttr.setValue(Integer.toString(statHolder.getId()));
        nameAttr.setValue(statHolder.getName());
        gameVersionAttr.setValue(statHolder.getGameVersion());
        winsAttr.setValue(Integer.toString(statHolder.getWins()));
        lossesAttr.setValue(Integer.toString(statHolder.getLosses()));
    }

    public void setDocument(Document document){
        this.document = document;
        NodeList nodeList = document.getElementsByTagName("statKeeper");
        statKeeper = (Element) nodeList.item(0);
    }

    public Document getDocument(){
        return document;
    }
}
