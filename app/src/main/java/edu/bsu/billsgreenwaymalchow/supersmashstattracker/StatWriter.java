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

    public String findMostPlayedGameVersion(){
        String gameVersion = "";
        HashMap<String, Integer> gameVersionPlays = new HashMap<String, Integer>();
        gameVersionPlays.put("Nintendo 64",0);
        gameVersionPlays.put("Melee",0);
        gameVersionPlays.put("Brawl",0);
        gameVersionPlays.put("Wii U",0);
        gameVersionPlays.put("3DS",0);
        NodeList nodeList = document.getElementsByTagName("tracker");
        if(nodeList.getLength()==0){
            return "None";
        }
        int plays = 0;
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            int trackerPlays = Integer.parseInt(e.getAttribute("wins")) +
                    Integer.parseInt(e.getAttribute("losses"));
            for (HashMap.Entry<String, Integer> entry : gameVersionPlays.entrySet()) {
                if(entry.getKey().equals(e.getAttribute("gameVersion"))){
                    entry.setValue(entry.getValue() + trackerPlays);
                    if(entry.getValue()==plays){
                        gameVersion = "Multiple";
                    }
                    if(entry.getValue()>plays){
                        plays = entry.getValue();
                        gameVersion =  e.getAttribute("gameVersion");
                    }
                }
            }
        }
        return gameVersion;
    }
}
