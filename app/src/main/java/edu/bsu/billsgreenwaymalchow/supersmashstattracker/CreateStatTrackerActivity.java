package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateStatTrackerActivity extends AppCompatActivity implements View.OnClickListener {

    private String selectedGameVersion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stat_tracker);
        createGameVersionButtons();
    }

    private void createGameVersionButtons(){
        GameVersionList gameVersionList = new GameVersionList();
        int[] listOfGameVersions = gameVersionList.getGameVersionList();
        for (int i = 0; i < listOfGameVersions.length; i++){
            Button thisButton = (Button) findViewById(listOfGameVersions[i]);
            thisButton.setOnClickListener(this);
        }
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.game_version_64:
                selectedGameVersion = "Nintendo 64";
                showToastMessage(selectedGameVersion);
                break;
            case R.id.game_version_melee:
                selectedGameVersion = "Melee";
                showToastMessage(selectedGameVersion);
                break;
            case R.id.game_version_brawl:
                selectedGameVersion = "Brawl";
                showToastMessage(selectedGameVersion);
                break;
            case R.id.game_version_wii_u:
                selectedGameVersion = "Wii U";
                showToastMessage(selectedGameVersion);
                break;
            case R.id.game_version_3ds:
                selectedGameVersion = "3DS";
                showToastMessage(selectedGameVersion);
                break;
            case R.id.submit_button:
                Intent intent = new Intent();
                final EditText trackerName = (EditText) findViewById(R.id.trackerName);
                intent.putExtra("trackerName", trackerName.getText().toString());
                intent.putExtra("gameVersion", selectedGameVersion);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    private void showToastMessage(String gameVersion){
        String gameVersionStatement = "You selected game version: ";
        Toast.makeText(CreateStatTrackerActivity.this, gameVersionStatement + gameVersion, Toast.LENGTH_SHORT).show();
    }
}
