package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class WinLossActivity extends AppCompatActivity{

    WinLossCounter winLossCounter = new WinLossCounter();
    private int id;
    private int wins;
    private int losses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_loss);
        //noinspection ConstantConditions - back button will never return null because there is always a page before it to return to
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        wins = intent.getIntExtra("wins", 0);
        losses = intent.getIntExtra("losses", 0);
        setGameVersionText(intent.getStringExtra("gameVersion"));
        winLossCounter.setWins(wins);
        winLossCounter.setLosses(losses);
        addWinOrLoss();
        listenForSaveButtonClick();
    }

    private void setGameVersionText(String gameVersion){
        if(gameVersion.equals("")){
            gameVersion = "None";
        }
        String gameVersionText = "Game Version: " + gameVersion;
        TextView gameVersionTextView = (TextView) findViewById(R.id.current_game_version);
        gameVersionTextView.setText(gameVersionText);
    }

    private void addWinOrLoss(){
        Button addWinButton = (Button) findViewById(R.id.add_win_button);
        final TextView totalWins = (TextView) findViewById(R.id.total_wins_text_view);
        totalWins.setText(String.format(Locale.getDefault(), "%d", wins));
        updatePercentage();
        addWinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winLossCounter.addWin();
                wins = winLossCounter.getWins();
                totalWins.setText(String.format(Locale.getDefault(), "%d", wins));
                updatePercentage();
            }
        });
        Button addLossButton = (Button) findViewById(R.id.addLossButton);
        final TextView totalLosses = (TextView) findViewById(R.id.total_losses_text_view);
        totalLosses.setText(String.format(Locale.getDefault(),"%d", losses));
        addLossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winLossCounter.addLoss();
                losses = winLossCounter.getLosses();
                totalLosses.setText(String.format(Locale.getDefault(),"%d", losses));
                updatePercentage();
            }
        });
    }

    private void listenForSaveButtonClick(){
        Button saveStatsButton = (Button) findViewById(R.id.saveStatsButton);
        saveStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("returnId", id);
                intent.putExtra("totalWins", wins);
                intent.putExtra("totalLosses", losses);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void updatePercentage(){
        final TextView winPercentage = (TextView) findViewById(R.id.win_percentage_text_view);
        if(!Double.isNaN(winLossCounter.getWinPercentage())) {
            final String percentageFormat = String.format(Locale.getDefault(), "%.2f", winLossCounter.getWinPercentage() * 100) + "%";
            winPercentage.setText(percentageFormat);
        }
        else{
            winPercentage.setText(R.string.zero_percent);
        }
    }
}
