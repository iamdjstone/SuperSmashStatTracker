package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public class TotalStatParser {

    private NodeList nodeList;

    public TotalStatParser(Document document){
        nodeList = document.getElementsByTagName("tracker");
    }
    public TotalStatTrackerReport createTotalStatTrackerReport(){
        TotalStatTrackerReport totalStatTrackerReport = new TotalStatTrackerReport();
        for (int itemNumber = 0; itemNumber < nodeList.getLength(); itemNumber++) {
            Element e = (Element) nodeList.item(itemNumber);
            totalStatTrackerReport.addToTotalWins(Integer.parseInt(e.getAttribute("wins")));
            totalStatTrackerReport.addToTotalLosses(Integer.parseInt(e.getAttribute("losses")));
            totalStatTrackerReport.updateGameVersionHashMap(e.getAttribute("gameVersion"),
                    Integer.parseInt(e.getAttribute("wins")));
        }
        totalStatTrackerReport.setTotalStatTrackers(getLengthOfDocumentParsed());
        return totalStatTrackerReport;
    }

    public int getLengthOfDocumentParsed(){
        return nodeList.getLength();
    }
}
