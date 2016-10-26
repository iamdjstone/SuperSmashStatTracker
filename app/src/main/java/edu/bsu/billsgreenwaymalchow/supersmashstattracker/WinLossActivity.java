package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class WinLossActivity extends AppCompatActivity {

    WinLossCounter winLossCounter = new WinLossCounter();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_loss);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addWinOrLoss();
    }

    private void addWinOrLoss(){
        final EditText winPercentage = (EditText) findViewById(R.id.percentage_edit_text);
        Button addWinButton = (Button) findViewById(R.id.add_win_button);
        final EditText totalWins = (EditText) findViewById(R.id.win_total_edit_text);
        addWinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winLossCounter.addWin();
                totalWins.setText(String.format(Locale.getDefault(), "%d", winLossCounter.getWins()));
                winPercentage.setText(String.format(Locale.getDefault(), "%f", (winLossCounter.getWinPercentage())));
            }
        });
        Button addLossButton = (Button) findViewById(R.id.addLossButton);
        final EditText totalLosses = (EditText) findViewById(R.id.loss_total_editt_text);
        addLossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winLossCounter.addLoss();
                totalLosses.setText(String.format(Locale.getDefault(),"%d", winLossCounter.getLosses()));
                winPercentage.setText(String.format(Locale.getDefault(),"%f",(winLossCounter.getWinPercentage())));
            }
        });
    }
}