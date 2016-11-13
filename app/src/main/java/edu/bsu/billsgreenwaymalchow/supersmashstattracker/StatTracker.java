package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTracker{

    private String name;
    private String gameVersion;
    private int wins;
    private int losses;


    StatTracker(String name, String gameVersion) throws ParserConfigurationException {
        setName(name);
        setGameVersion(gameVersion);
    }
    public void setWins(int wins){
        this.wins = wins;
    }

    public void setLosses(int losses){
        this.losses = losses;
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
        return wins;
    }

    public int getLosses(){
        return losses;
    }



    public void passFileToStatTrackerWriter(File file) throws TransformerException, ParserConfigurationException {

    }

}