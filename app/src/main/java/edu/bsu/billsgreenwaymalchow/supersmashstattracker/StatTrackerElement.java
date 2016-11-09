package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.Locale;

import javax.xml.transform.TransformerException;

public class StatTrackerElement{

    private Element tracker;
    private Attr nameAttr;
    private Attr gameVersionAttr;
    private Attr winsAttr;
    private Attr lossesAttr;
    private Document document;

    public void createStatTrackerElement() {
        tracker = document.createElement("tracker");
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
    public void updateStatKeeper() throws TransformerException {
        StatKeeper statKeeper = new StatKeeper();
        statKeeper.update(this);
    }
}
