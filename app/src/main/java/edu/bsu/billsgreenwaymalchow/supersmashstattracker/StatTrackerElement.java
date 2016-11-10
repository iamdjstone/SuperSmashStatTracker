package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileNotFoundException;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTrackerElement{

    private Element tracker;
    private Attr nameAttr;
    private Attr gameVersionAttr;
    private Attr winsAttr;
    private Attr lossesAttr;

    public void createStatTrackerElement() {
        Document document = createNewElementDocument();
        Element tracker = document.createElement("tracker");
        nameAttr = document.createAttribute("name");
        tracker.setAttributeNode(nameAttr);
        gameVersionAttr = document.createAttribute("gameVersion");
        tracker.setAttributeNode(gameVersionAttr);
        winsAttr = document.createAttribute("wins");
        tracker.setAttributeNode(winsAttr);
        lossesAttr = document.createAttribute("losses");
        tracker.setAttributeNode(lossesAttr);
    }

    public void updateNameAndGameVersion(StatTracker statTracker){
        nameAttr.setValue(statTracker.getName());
        gameVersionAttr.setValue(statTracker.getGameVersion());
    }

    public void updateWinsAndLosses(StatTracker statTracker){
        winsAttr.setValue(String.format(Locale.getDefault(), "%d", statTracker.getWins()));
        lossesAttr.setValue(String.format(Locale.getDefault(), "%d", statTracker.getLosses()));
    }

    public Element getTheXMLElement(){
        return tracker;
    }

    public void updateStatKeeper() throws TransformerException, ParserConfigurationException, FileNotFoundException {
        StatKeeper statKeeper = new StatKeeper();
        statKeeper.update(this);
    }

    public Document createNewElementDocument(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
            return builder.newDocument();
    }
}
