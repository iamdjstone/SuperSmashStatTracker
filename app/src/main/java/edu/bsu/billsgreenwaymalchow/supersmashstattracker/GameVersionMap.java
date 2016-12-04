package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.annotation.SuppressLint;

import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public class GameVersionMap {

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, String> gameVersionHashMap = new HashMap<>();

    public GameVersionMap(){
        gameVersionHashMap.put(R.id.game_version_64, "Nintendo 64");
        gameVersionHashMap.put(R.id.game_version_melee, "Melee");
        gameVersionHashMap.put(R.id.game_version_brawl, "Brawl");
        gameVersionHashMap.put(R.id.game_version_wii_u, "Wii U");
        gameVersionHashMap.put(R.id.game_version_3ds, "3DS");
    }

    public HashMap<Integer, String> getGameVersionHashMap() {
        return gameVersionHashMap;
    }
}
