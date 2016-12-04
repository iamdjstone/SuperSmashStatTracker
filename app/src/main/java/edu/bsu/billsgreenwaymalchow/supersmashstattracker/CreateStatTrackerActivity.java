package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class CreateStatTrackerActivity extends AppCompatActivity {

    private String selectedGameVersion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stat_tracker);
        ListenForGameVersionButtons();
        ListenForSubmitButton();
    }

    private void ListenForGameVersionButtons(){
        GameVersionMap gameVersionMap = new GameVersionMap();
        HashMap<Integer, String> gameVersionHashMap = gameVersionMap.getGameVersionHashMap();
        for (final HashMap.Entry<Integer, String> entry : gameVersionHashMap.entrySet()){
            Button thisButton = (Button) findViewById(entry.getKey());
            thisButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedGameVersion = entry.getValue();
                    showToastMessage(selectedGameVersion);
                }
            });
        }
    }

    private void ListenForSubmitButton() {
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                final EditText trackerName = (EditText) findViewById(R.id.trackerName);
                intent.putExtra("trackerName", trackerName.getText().toString());
                intent.putExtra("gameVersion", selectedGameVersion);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void showToastMessage(String gameVersion){
        String gameVersionStatement = "You selected game version: ";
        Toast.makeText(CreateStatTrackerActivity.this, gameVersionStatement + gameVersion, Toast.LENGTH_SHORT).show();
    }
}
