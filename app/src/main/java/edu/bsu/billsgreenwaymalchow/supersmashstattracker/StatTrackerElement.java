package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Locale;

public class StatTrackerElement {

    Document document;
    private Attr nameAttr;
    private Attr gameVersionAttr;
    private Attr winsAttr;
    private Attr lossesAttr;
    private Element tracker;

    public void createStatTrackerElement(Element statKeeper) {
        tracker = document.createElement("tracker");
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

    public void update(StatTracker statTracker){
        nameAttr.setValue(statTracker.getName());
        gameVersionAttr.setValue(statTracker.getGameVersion());
        winsAttr.setValue(String.format(Locale.getDefault(), "%d", statTracker.getWins()));
        lossesAttr.setValue(String.format(Locale.getDefault(), "%d", statTracker.getLosses()));
    }

    public Element getElement(){
        return tracker;
    }
}
