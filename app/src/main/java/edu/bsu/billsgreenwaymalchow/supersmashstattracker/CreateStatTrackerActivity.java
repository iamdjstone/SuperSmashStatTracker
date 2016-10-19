package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateStatTrackerActivity extends AppCompatActivity {

    TrackerList trackerList = new TrackerList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stat_tracker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        final EditText trackerName = (EditText) findViewById(R.id.trackerName);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackerList.addTracker(trackerName.getText().toString());
                finish();
            }
        });
    }
}