package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StatTrackerActivity.class));
            }
        });
    }
    private void insertWin(){
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.TRACKER_WIN, winLossCounter.getWins());
        Uri statURI = getContentResolver().insert(StatsProvider.CONTENT_URI, values);
    }
}