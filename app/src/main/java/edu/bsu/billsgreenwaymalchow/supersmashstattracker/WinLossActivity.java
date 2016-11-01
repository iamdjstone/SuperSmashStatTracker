package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class WinLossActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

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
                final String percentageFormat = String.format(Locale.getDefault(),"%.2f",winLossCounter.getWinPercentage()) + "%";
                totalWins.setText(String.format(Locale.getDefault(), "%d", winLossCounter.getWins()));
                winPercentage.setText(percentageFormat);
                insertWin();
            }
        });
        Button addLossButton = (Button) findViewById(R.id.addLossButton);
        final EditText totalLosses = (EditText) findViewById(R.id.loss_total_editt_text);
        addLossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winLossCounter.addLoss();
                final String percentageFormat = String.format(Locale.getDefault(),"%.2f",winLossCounter.getWinPercentage()) + "%";
                totalLosses.setText(String.format(Locale.getDefault(),"%d", winLossCounter.getLosses()));
                winPercentage.setText(percentageFormat);
                insertLoss();
            }
        });
    }


    private void insertWin(){
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.TRACKER_WIN, winLossCounter.getWins());
    }

    private void insertLoss(){
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.TRACKER_LOSS, winLossCounter.getLosses());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, StatsProvider.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}