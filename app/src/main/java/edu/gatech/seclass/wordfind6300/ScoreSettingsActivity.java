package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_settings);

        String sessionID = getIntent().getStringExtra("EXTRA_SESSION_ID");
        Integer gmID = Integer.parseInt(sessionID);

        Player locply = MainActivity.ply;
        ScoreList ss = locply.scoreStats;

        Settings locset = ss.getGameSettings(gmID);

        TextView bsize = findViewById(R.id.textView13);
        TextView tlimit = findViewById(R.id.textView14);
        TextView bigWord = findViewById(R.id.textView16);

        bsize.setText(String.format("%d", locset.getBoardSize()));
        tlimit.setText(String.format("%d", locset.getGameLength()));
        bigWord.setText(String.format("%s", locply.scoreStats.getGameByID(gmID).getBestWord()));

    }
}
