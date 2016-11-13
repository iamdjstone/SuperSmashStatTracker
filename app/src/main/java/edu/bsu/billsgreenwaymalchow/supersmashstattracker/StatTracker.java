package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


import javax.xml.parsers.ParserConfigurationException;

@SuppressWarnings("WeakerAccess")
public class StatTracker{

    private String name;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
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

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

}