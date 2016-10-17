package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WinLossCounter winLossCounter = new WinLossCounter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText winPercentage = (EditText) findViewById(R.id.percentageEditText);
                setContentView(R.layout.win_loss);
                Button addWinButton = (Button) findViewById(R.id.addWinButton);
                final EditText totalWins = (EditText) findViewById(R.id.winTotalEditText);
                addWinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        winLossCounter.addWin();
                        totalWins.setText(Integer.toString(winLossCounter.getWins()));
                        //winPercentage.setText(Double.toString(winLossCounter.getWinPercentage()));
                    }
                });
                Button addLossButton = (Button) findViewById(R.id.addLossButton);
                final EditText totalLosses = (EditText) findViewById(R.id.lossTotalEditText);
                addLossButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        winLossCounter.addLoss();
                        totalLosses.setText(Integer.toString(winLossCounter.getLosses()));
                        //winPercentage.setText(Double.toString(winLossCounter.getWinPercentage()));
                    }
                });
            }
        });
    }
}
