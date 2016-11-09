package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateStatTrackerActivity extends AppCompatActivity implements View.OnClickListener {

    public String selectedGameVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stat_tracker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createGameVersionButtons();
    }

    private void createGameVersionButtons(){
        Button gameVersion64 = (Button) findViewById(R.id.game_version_64);
        gameVersion64.setOnClickListener(this);
        Button gameVersionMelee = (Button) findViewById(R.id.game_version_melee);
        gameVersionMelee.setOnClickListener(this);
        Button gameVersionBrawl = (Button) findViewById(R.id.game_version_brawl);
        gameVersionBrawl.setOnClickListener(this);
        Button gameVersionWiiU = (Button) findViewById(R.id.game_version_wii_u);
        gameVersionWiiU.setOnClickListener(this);
        Button gameVersion3DS = (Button) findViewById(R.id.game_version_3ds);
        gameVersion3DS.setOnClickListener(this);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.game_version_64:
                showToastMessage("Nintendo 64");
                selectedGameVersion = "Nintendo 64";
                break;
            case R.id.game_version_melee:
                showToastMessage("Melee");
                selectedGameVersion = "Melee";
                break;
            case R.id.game_version_brawl:
                showToastMessage("Brawl");
                selectedGameVersion = "Brawl";
                break;
            case R.id.game_version_wii_u:
                showToastMessage("Wii U");
                selectedGameVersion = "Wii U";
                break;
            case R.id.game_version_3ds:
                showToastMessage("3DS");
                selectedGameVersion = "3DS";
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
        String s = "You selected game version: ";
        Toast.makeText(CreateStatTrackerActivity.this, s + gameVersion, Toast.LENGTH_SHORT).show();
    }
}
