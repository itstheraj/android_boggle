package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        TableLayout lyt_words = findViewById(R.id.lyt_words);
        Player locply = MainActivity.ply;
        Map<String, WordList.WordFreq> lookup = locply.wordStats.getWordMap();
        Map<String, WordList.WordFreq> visited = new HashMap<>();
        List<String> words = locply.wordStats.getWords();

        for (String currWord : words)
        {
            // get each words frequency and if we havent seen it yet put it on the board
            WordList.WordFreq wf = lookup.get(currWord);
            if (!(visited.keySet().contains(currWord)))
            {
                visited.put(currWord, wf);
                TableRow tradd = new TableRow(this);
                Space spc1 = new Space(this);
                spc1.setLayoutParams(new TableRow.LayoutParams(100, 10));
                Space spc2 = new Space(this);
                spc2.setLayoutParams(new TableRow.LayoutParams(100, 10));
                TextView txtv1 = new TextView(this);
                txtv1.setText(currWord);

                TextView txtv2 = new TextView(this);
                txtv2.setText(String.format("%d", wf.freak));
                tradd.addView(spc1);
                tradd.addView(txtv1);
                tradd.addView(spc2);
                tradd.addView(txtv2);

                lyt_words.addView(tradd);
            }

        }

    }
}
