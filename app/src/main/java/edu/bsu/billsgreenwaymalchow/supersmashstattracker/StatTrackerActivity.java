package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.io.File;
import java.util.Scanner;

public class StatTrackerActivity extends AppCompatActivity{

    private StatTracker thisStatTracker;
    private static StatTrackerWriter statTrackerWriter = new StatTrackerWriter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_tracker_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        attemptToCreateNewSaveXML();
        listenForCreateStatTrackerButtonClick();
    }


    public void attemptToCreateNewSaveXML() {
        try {
            String FILENAME = "statData.xml";
            File file = getApplicationContext().getFileStreamPath(FILENAME);
            statTrackerWriter.setFile(file);
            if (!file.exists()) {
                statTrackerWriter.writeToFile();
                Scanner input = new Scanner(file);
                while (input.hasNextLine()) {
                    System.out.println(input.nextLine());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void listenForCreateStatTrackerButtonClick() {
        Button createStatTrackerButton = (Button) findViewById(R.id.create_stat_tracker_button);
        createStatTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StatTrackerActivity.this, CreateStatTrackerActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if ((requestCode == 1) && (resultCode== RESULT_OK)){
            String newName = data.getStringExtra("trackerName");
            String gameVersion = data.getStringExtra("gameVersion");
            thisStatTracker = new StatTracker(newName, gameVersion);
            attemptToPushToStatTrackerWriter();
            createNewButtonFromName(newName);
        }
    }

    private void attemptToPushToStatTrackerWriter(){
        try {
            thisStatTracker.pushToStatTrackerWriter();
            System.out.println("Attempt To Push To Stat Tracker Writer");
        } catch (Exception e) {
            System.out.println("Error in Attempt to Push Stat Tracker Writer");
            e.printStackTrace();
        }

    }

    private void createNewButtonFromName(String newName) {
        Button thisStatTrackerButton = new Button(this);
        thisStatTrackerButton.setText(newName);
        LinearLayout statTrackerScrollList = (LinearLayout) findViewById(R.id.linear_layout_scrollbar);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        statTrackerScrollList.addView(thisStatTrackerButton, lp);
        thisStatTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatTrackerActivity.this, WinLossActivity.class));
            }
        });
    }
}
