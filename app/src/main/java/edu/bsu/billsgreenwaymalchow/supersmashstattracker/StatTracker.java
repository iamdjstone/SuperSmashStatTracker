package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


import java.io.FileNotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTracker{

    private String name;
    private String gameVersion;
    private WinLossCounter winLossCounter = new WinLossCounter();
    private StatTrackerWriter statTrackerWriter = new StatTrackerWriter();

    StatTracker(String name, String gameVersion){
        setName(name);
        setGameVersion(gameVersion);
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setGameVersion(String gameVersion){
        this.gameVersion = gameVersion;
    }

    public String getName() {
        return name;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public int getWins(){
        return winLossCounter.getWins();
    }

    public int getLosses(){
        return winLossCounter.getLosses();
    }

    public void pushToStatTrackerWriter() throws TransformerException, ParserConfigurationException, FileNotFoundException {
        System.out.println("Push To Stat Tracker Writer 0");
        statTrackerWriter.createStatTrackerElement();
        System.out.println("Push To Stat Tracker Writer 1");
        statTrackerWriter.updateNameAndGameVersion(this);
        System.out.println("Push To Stat Tracker Writer 2");
        statTrackerWriter.updateWinsAndLosses(this);
        System.out.println("Push To Stat Tracker Writer 3");
    }

}