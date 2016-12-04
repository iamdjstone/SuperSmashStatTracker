package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

@SuppressWarnings("WeakerAccess")
public class GameVersionList {

    String currentGameVersion;

    private int[] gameVersionList =
            {R.id.game_version_64,
            R.id.game_version_melee,
            R.id.game_version_brawl,
            R.id.game_version_wii_u,
            R.id.game_version_3ds};

    public int[] getGameVersionList(){
        return gameVersionList;
    }

    public void setCurrentGameVersion(String gameVersion){
        currentGameVersion = gameVersion;
    }

    public String getCurrentGameVersion(){
        return currentGameVersion;
    }

}


