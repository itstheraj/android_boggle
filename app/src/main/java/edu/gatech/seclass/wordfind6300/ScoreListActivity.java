package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class ScoreListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        TableLayout tly = (TableLayout) findViewById(R.id.lyt_games);

        Player locply = MainActivity.ply;
        List<Game> gameslst = locply.scoreStats.sortStats();
        for (Game gm : gameslst)
        {
            TableRow tradd = new TableRow(this);
            Button b = new Button(this);
            b.setText(String.format("%d", gm.getID()));
            final Game gm2 = gm;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ScoreSettingsActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID", String.format("%d", gm2.getID()));
                    view.getContext().startActivity(intent);
                }
            });

            Stats st = gm.getStats();

            Space spc1 = new Space(this);
            spc1.setLayoutParams(new TableRow.LayoutParams(100, 10));
            Space spc2 = new Space(this);
            spc2.setLayoutParams(new TableRow.LayoutParams(100, 10));
            Space spc3 = new Space(this);
            spc3.setLayoutParams(new TableRow.LayoutParams(100, 10));

            TextView txt1 = new TextView(this);
            txt1.setText(String.format("%d", st.getScore()));

            TextView txt2 = new TextView(this);
            txt2.setText(String.format("%d", st.getResets()));

            TextView txt3 = new TextView(this);
            txt3.setText(String.format("%d", st.getWords()));

            tradd.addView(b);
            tradd.addView(spc1);
            tradd.addView(txt1);
            tradd.addView(spc2);
            tradd.addView(txt2);
            tradd.addView(spc3);
            tradd.addView(txt3);

            tly.addView(tradd);
        }

    }
}
