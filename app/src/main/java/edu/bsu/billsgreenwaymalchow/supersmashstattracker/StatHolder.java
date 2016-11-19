package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

@SuppressWarnings("WeakerAccess")
public class StatHolder {

    private int id;
    private String name;
    private String gameVersion;
    private int wins;
    private int losses;

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameVersion(String gameVersion){
        this.gameVersion = gameVersion;
    }

    public void setWins(int wins){
        this.wins = wins;
    }

    public void setLosses(int losses){
        this.losses = losses;
    }

    public int getId() {
        return id;
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

}