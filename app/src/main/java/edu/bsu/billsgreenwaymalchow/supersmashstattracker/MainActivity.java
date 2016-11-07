package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {

    private static StatKeeper statKeeper = new StatKeeper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptToCreateNewSaveXML();
                Intent firstIntent = new Intent(MainActivity.this, StatTrackerActivity.class);
                startActivity(firstIntent);
            }
        });
    }
    public void attemptToCreateNewSaveXML() {
        try {
            statKeeper.createSaveXMLDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        //statKeeper.update();
    }
}
