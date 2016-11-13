package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class WinLossActivity extends AppCompatActivity{

    WinLossCounter winLossCounter = new WinLossCounter();
    private int wins;
    private int losses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_loss);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        wins = intent.getIntExtra("wins", 0);
        losses = intent.getIntExtra("losses", 0);
        winLossCounter.setWins(wins);
        winLossCounter.setLosses(losses);
        addWinOrLoss();
    }

    private void addWinOrLoss(){
        Button addWinButton = (Button) findViewById(R.id.add_win_button);
        final EditText totalWins = (EditText) findViewById(R.id.win_total_edit_text);
        totalWins.setText(String.format(Locale.getDefault(), "%d", wins));
        addWinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winLossCounter.addWin();
                wins = winLossCounter.getWins();
                totalWins.setText(String.format(Locale.getDefault(), "%d", wins));
                final String percentageFormat = String.format(Locale.getDefault(),"%.2f", winLossCounter.getWinPercentage() * 100) + "%";
                final EditText winPercentage = (EditText) findViewById(R.id.percentage_edit_text);
                winPercentage.setText(percentageFormat);
            }
        });
        Button addLossButton = (Button) findViewById(R.id.addLossButton);
        final EditText totalLosses = (EditText) findViewById(R.id.loss_total_editt_text);
        totalLosses.setText(String.format(Locale.getDefault(),"%d", losses));
        addLossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winLossCounter.addLoss();
                losses = winLossCounter.getLosses();
                totalLosses.setText(String.format(Locale.getDefault(),"%d", losses));
                final String percentageFormat = String.format(Locale.getDefault(),"%.2f", winLossCounter.getWinPercentage() * 100) + "%";
                final EditText winPercentage = (EditText) findViewById(R.id.percentage_edit_text);
                winPercentage.setText(percentageFormat);
            }
        });
        Button saveStatsButton = (Button) findViewById(R.id.saveStatsButton);
        saveStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("totalWins", wins);
                intent.putExtra("totalLosses", losses);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
