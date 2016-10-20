package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class CreateStatTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stat_tracker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        final Button statTrackerButton = new Button(this);
        final EditText trackerName = (EditText) findViewById(R.id.trackerName);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollView statTrackerScrollView = (ScrollView) findViewById(R.id.stat_tracker_scroll_view);
                ScrollView.LayoutParams scrollViewLayout = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT);
                statTrackerButton.setText(trackerName.getText().toString());
                scrollViewLayout.addView()
                finish();
            }
        });
    }
}
