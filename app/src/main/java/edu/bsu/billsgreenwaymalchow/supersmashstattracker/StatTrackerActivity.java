package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTrackerActivity extends AppCompatActivity{

    private StatTracker thisStatTracker;
    private ArrayList<StatTracker> statTrackerList = new ArrayList<>();
    private StatTrackerWriter statTrackerWriter = new StatTrackerWriter();

    public StatTrackerActivity() throws ParserConfigurationException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_tracker_list);
        attemptToCreateNewSaveXML();
        listenForCreateStatTrackerButtonClick();
        try {
            createButtons();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    private void createButtons() throws ParserConfigurationException {
        NodeList nodeList = statTrackerWriter.document.getElementsByTagName("tracker");
        LinearLayout statTrackerScrollList = (LinearLayout) findViewById(R.id.linear_layout_scrollbar);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for(int i = 0; i < nodeList.getLength(); i++){
            Intent intent = getIntent();
            Element e = (Element) nodeList.item(i);
            int newWins = intent.getIntExtra("totalWins", 1);
            int newLosses = intent.getIntExtra("totalLosses", 2);
            thisStatTracker = new StatTracker(e.getAttribute("name"), e.getAttribute("gameVersion"));
            thisStatTracker.setWins(newWins);
            thisStatTracker.setLosses(newLosses);
            statTrackerWriter.updateWinsAndLosses(newWins, newLosses);
            Button thisStatTrackerButton = new Button(this);
            thisStatTrackerButton.setText(e.getAttribute("name"));
            statTrackerScrollList.addView(thisStatTrackerButton, lp);
            thisStatTrackerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(StatTrackerActivity.this, WinLossActivity.class);
                    i.putExtra("wins", thisStatTracker.getWins());
                    i.putExtra("losses", thisStatTracker.getLosses());
                    startActivity(i);
                }
            });
        }
    }

    public void attemptToCreateNewSaveXML() {
        try {
            String FILENAME = "statData.xml";
            File file = getApplicationContext().getFileStreamPath(FILENAME);
            if (!file.exists()) {
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
            try {
                thisStatTracker = new StatTracker(newName, gameVersion);
                statTrackerList.add(thisStatTracker);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            attemptToPushToStatTrackerWriter();
            createNewButtonFromName(newName);
        }
        if ((requestCode == 2) && (resultCode== RESULT_OK)){
            int newWins = data.getIntExtra("totalWins", 1);
            int newLosses = data.getIntExtra("totalLosses", 2);
            thisStatTracker.setWins(newWins);
            thisStatTracker.setLosses(newLosses);
            statTrackerWriter.updateWinsAndLosses(newWins, newLosses);
        }
    }

    private void attemptToPushToStatTrackerWriter(){
        try {
            pushToStatTrackerWriter();
        } catch (Exception e) {
            System.out.println("Error in Attempt to Push Stat Tracker Writer");
            e.printStackTrace();
        }
    }

    private void createNewButtonFromName(final String newName) {
        Button thisStatTrackerButton = new Button(this);
        thisStatTrackerButton.setText(newName);
        LinearLayout statTrackerScrollList = (LinearLayout) findViewById(R.id.linear_layout_scrollbar);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        statTrackerScrollList.addView(thisStatTrackerButton, lp);
        thisStatTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StatTrackerActivity.this, WinLossActivity.class);
                i.putExtra("wins", thisStatTracker.getWins());
                i.putExtra("losses", thisStatTracker.getLosses());
                startActivityForResult(i, 2);
            }
        });
    }

    public void pushToStatTrackerWriter() throws TransformerException, ParserConfigurationException {
        statTrackerWriter.createStatTrackerElement();
        statTrackerWriter.updateNameAndGameVersion(thisStatTracker.getName(), thisStatTracker.getGameVersion());
        statTrackerWriter.updateWinsAndLosses(thisStatTracker.getWins(), thisStatTracker.getLosses());
    }
}
