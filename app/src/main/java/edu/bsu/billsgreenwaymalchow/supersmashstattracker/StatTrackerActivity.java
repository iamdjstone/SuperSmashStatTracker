package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import javax.xml.transform.TransformerException;

public class StatTrackerActivity extends AppCompatActivity{

    StatTracker thisStatTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_tracker_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listenForCreateStatTrackerButtonClick();
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
            attemptToPushToStatTrackerElement();
            createButtonsFromName(newName);
        }
    }

    private void attemptToPushToStatTrackerElement(){
        try {
            thisStatTracker.pushToStatTrackerElement();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void createButtonsFromName(String newName) {
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
