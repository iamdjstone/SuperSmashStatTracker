package edu.bsu.billsgreenwaymalchow.supersmashstattracker;


import javax.xml.transform.TransformerException;

public class StatTracker{

    private String name;
    private String gameVersion;
    private WinLossCounter winLossCounter = new WinLossCounter();
    private StatTrackerElement statTrackerElement = new StatTrackerElement();

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

    public void pushToStatTrackerElement() throws TransformerException {
        statTrackerElement.updateNameAndGameVersion(this);
        statTrackerElement.updateWinsAndLosses(this);
        statTrackerElement.createStatTrackerElement();
        statTrackerElement.updateStatKeeper();

    }

}