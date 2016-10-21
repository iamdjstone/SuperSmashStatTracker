package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class CreateStatTrackerActivity extends AppCompatActivity {

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
                Intent intent = new Intent();
                intent.putExtra("trackerName", trackerName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
