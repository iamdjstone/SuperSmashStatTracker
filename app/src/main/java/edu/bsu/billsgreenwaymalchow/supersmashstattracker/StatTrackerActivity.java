package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class StatTrackerActivity extends AppCompatActivity{

    private StatWriter statWriter = new StatWriter();
    private StatReader statReader = new StatReader();
    TotalStatParser totalStatParser;
    private int id = 0;
    static final private int NAME_GAMEVERSION = 1;
    static final private int WIN_LOSS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_tracker_list);
        loadData();
    }

    @Override
    protected void onResume(){
        super.onResume();
        setContentView(R.layout.stat_tracker_list);
        listenForCreateStatTrackerButtonClick();
        try {
            createButtons();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        updateTotalStatisticsOnActivity();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        attemptToCreateNewSaveXML();
    }

    private void loadData(){
        String FILENAME = "statData.xml";
        File file = getApplicationContext().getFileStreamPath(FILENAME);
        if(file.exists()){
            statReader.createDocument(file);
            statWriter.setDocument(statReader.getDocument());
            totalStatParser = new TotalStatParser(statReader.getDocument());
            id = totalStatParser.getLengthOfDocumentParsed();
        } else {
            statWriter.createMainElement();
        }
    }
    private void listenForCreateStatTrackerButtonClick() {
        Button createStatTrackerButton = (Button) findViewById(R.id.create_stat_tracker_button);
        createStatTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StatTrackerActivity.this, CreateStatTrackerActivity.class);
                startActivityForResult(i, NAME_GAMEVERSION);
            }
        });
    }

    private void createButtons() throws ParserConfigurationException {
        NodeList nodeList = statWriter.document.getElementsByTagName("tracker");
        LinearLayout statTrackerScrollList = (LinearLayout) findViewById(R.id.linear_layout_scrollbar);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for(int i = 0; i < nodeList.getLength(); i++){
            Element e = (Element) nodeList.item(i);
            final int id = Integer.parseInt(e.getAttribute("id"));
            final int wins = Integer.parseInt(e.getAttribute("wins"));
            final int losses = Integer.parseInt(e.getAttribute("losses"));
            final String gameVersion = e.getAttribute("gameVersion");
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
                    i.putExtra("gameVersion", gameVersion);
                    startActivityForResult(i, WIN_LOSS);
                }
            });
        }
    }

    private void updateTotalStatisticsOnActivity() {
        totalStatParser = new TotalStatParser(statWriter.getDocument());
        TotalStatTrackerReport mostRecentStatTrackerReport = (totalStatParser.createTotalStatTrackerReport());
        String gameVersionText = "Most Played Game Version: " + mostRecentStatTrackerReport.getMostPlayedGameVersion();
        String totalWinsText = "Total Wins: " + mostRecentStatTrackerReport.getTotalWins();
        String totalLossesText = "Total Losses: " + mostRecentStatTrackerReport.getTotalLosses();
        TextView mostPlayedGameVersionTextView = (TextView) findViewById(R.id.most_played_game_version);
        TextView totalWins = (TextView) findViewById(R.id.totalWins);
        TextView totalLosses = (TextView) findViewById(R.id.totalLosses);
        mostPlayedGameVersionTextView.setText(gameVersionText);
        totalWins.setText(totalWinsText);
        totalLosses.setText(totalLossesText);
    }

    private void attemptToCreateNewSaveXML() {
        String FILENAME = "statData.xml";
        File file = getApplicationContext().getFileStreamPath(FILENAME);
        try {
            statWriter.writeToFile(file);
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        setContentView(R.layout.stat_tracker_list);
        if ((requestCode == NAME_GAMEVERSION) && (resultCode== RESULT_OK)){
            createElementFromNewStatTracker(data);
        }
        if ((requestCode == WIN_LOSS) && (resultCode== RESULT_OK)){
            updateWinsAndLossesForElement(data);
        }
    }

    private void createElementFromNewStatTracker(Intent data) {
        id++;
        StatWriterBuilder newStatWriterBuilder = new StatWriterBuilder();
        newStatWriterBuilder.setId(id);
        newStatWriterBuilder.setName(data.getStringExtra("trackerName"));
        newStatWriterBuilder.setGameVersion(data.getStringExtra("gameVersion"));
        newStatWriterBuilder.setWins(0);
        newStatWriterBuilder.setLosses(0);
        try {
            statWriter.createStatTrackerElement(newStatWriterBuilder);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void updateWinsAndLossesForElement(Intent data) {
        int returnId = data.getIntExtra("returnId", 0);
        int newWins = data.getIntExtra("totalWins", 0);
        int newLosses = data.getIntExtra("totalLosses", 0);
        NodeList nodeList = statWriter.document.getElementsByTagName("tracker");
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            if (Integer.parseInt(e.getAttribute("id"))==returnId){
                e.setAttribute("wins", Integer.toString(newWins));
                e.setAttribute("losses", Integer.toString(newLosses));
            }
        }
    }
}