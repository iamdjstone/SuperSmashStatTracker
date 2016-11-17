package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTrackerActivity extends AppCompatActivity{

    private StatTrackerWriter statTrackerWriter = new StatTrackerWriter();
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_tracker_list);
        attemptToCreateNewSaveXML();
    }

    @Override
    protected void onResume(){
        super.onResume();
        listenForCreateStatTrackerButtonClick();
        try {
            createButtons();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //iteration 3 - save data when destroyed
    }

    private void attemptToCreateNewSaveXML() {
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

    private void createButtons() throws ParserConfigurationException {
        NodeList nodeList = statTrackerWriter.document.getElementsByTagName("tracker");
        LinearLayout statTrackerScrollList = (LinearLayout) findViewById(R.id.linear_layout_scrollbar);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for(int i = 0; i < nodeList.getLength(); i++){
            Element e = (Element) nodeList.item(i);
            final int id = Integer.parseInt(e.getAttribute("id"));
            final int wins = Integer.parseInt(e.getAttribute("wins"));
            final int losses = Integer.parseInt(e.getAttribute("losses"));
            Button thisStatTrackerButton = new Button(this);
            thisStatTrackerButton.setText(e.getAttribute("name"));
            statTrackerScrollList.addView(thisStatTrackerButton, lp);
            thisStatTrackerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(StatTrackerActivity.this, WinLossActivity.class);
                    i.putExtra("id", id);
                    i.putExtra("wins", wins);
                    i.putExtra("losses", losses);
                    startActivityForResult(i, 2);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        System.out.println("onActivityResult");
        setContentView(R.layout.stat_tracker_list);
        if ((requestCode == 1) && (resultCode== RESULT_OK)){
            createElementFromNewStatTracker(data);
        }
        if ((requestCode == 2) && (resultCode== RESULT_OK)){
            updateWinsAndLossesForElement(data);
        }
    }

    private void createElementFromNewStatTracker(Intent data) {
        id++;
        StatTracker newStatTracker = new StatTracker();
        newStatTracker.setId(id);
        newStatTracker.setName(data.getStringExtra("trackerName"));
        newStatTracker.setGameVersion(data.getStringExtra("gameVersion"));
        newStatTracker.setWins(0);
        newStatTracker.setLosses(0);
        try {
            statTrackerWriter.createStatTrackerElement(newStatTracker);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void updateWinsAndLossesForElement(Intent data) {
        int returnId = data.getIntExtra("returnId", 0);
        int newWins = data.getIntExtra("totalWins", 0);
        int newLosses = data.getIntExtra("totalLosses", 0);
        NodeList nodeList = statTrackerWriter.document.getElementsByTagName("tracker");
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            if (Integer.parseInt(e.getAttribute("id"))==returnId){
                e.setAttribute("wins", Integer.toString(newWins));
                e.setAttribute("losses", Integer.toString(newLosses));
            }
        }
    }
}
