package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTracker{

    private String name;
    private String gameVersion;
    private WinLossCounter winLossCounter = new WinLossCounter();


    StatTracker(String name, String gameVersion) throws ParserConfigurationException {
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

}