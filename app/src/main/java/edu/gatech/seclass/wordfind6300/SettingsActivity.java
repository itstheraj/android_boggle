package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private int foldrange( int val, int lower, int upper ) {
        if( val < lower ) { val = lower ; }
        if( val > upper ) { val = upper; }
        return val;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);

        final int time_BASE = 3;
        final int size_BASE = 4;
        final int time_MIN = 1;
        final int time_MAX = 5;
        final int size_MIN = 4;
        final int size_MAX = 8;

        final Player locply = MainActivity.ply;
        final Settings locset = locply.getSettings();

        //System.out.println("default game.time:"+lengthGamePrev);
        //System.out.println("default board.size:"+sizeBoardPrev);
        final int[] lengthGame = {2};
        final int[] sizeBoard = {0};

        final boolean[] semaphore = {true};

        final SeekBar tmSk = findViewById(R.id.seekBar);
        final SeekBar szSk = findViewById(R.id.seekBar2);
        final Spinner ltDrop = findViewById(R.id.spinner);
        final Spinner wtDrop = findViewById(R.id.spinner2);
        final TextView wtchng = findViewById(R.id.textView10);

        //set seek bar to previous value
        tmSk.setProgress(lengthGame[0]);
        szSk.setProgress(sizeBoard[0]);

        final Map<String, Integer> locmap = locset.getMap();

        final Button btnBack = findViewById(R.id.btn_bk_st);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("game.time:"+lengthGame[0]);
                System.out.println("board.size:"+sizeBoard[0]);
                if (sizeBoard[0] == 0)
                {
                    sizeBoard[0] += 4;
                }
                Settings newSets = new Settings(lengthGame[0], sizeBoard[0], locmap);
                locply.setSettings(newSets);

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        final Button btnUpd = findViewById(R.id.button2);
        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String symbol = ltDrop.getSelectedItem().toString();
                Integer wt = Integer.valueOf(wtDrop.getSelectedItem().toString());
                wtchng.setText(String.format("Last Weight Changed: %s %s", symbol, wtDrop.getSelectedItem().toString()));
                //System.out.println(wtchng.getText());
                locmap.put( symbol, wt ); // ltDrop.getSelectedItem().toString() Integer.valueOf(wtDrop.getSelectedItem().toString())
                wtDrop.setSelection(0);

                locset.changeLetterWeight( new Letter(symbol,wt), wt );
                Settings newSets = new Settings(lengthGame[0], sizeBoard[0], locmap);
                //locply.setSettings(newSets);
            }
        });

        szSk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                // fold seek bar value to range[size_MIN, size_MAX]

                //System.out.println(sizeBoard[0]);
                sizeBoard[0] = progress + 4;
                System.out.println("board.size:"+sizeBoard[0]);

            }
        });


        tmSk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                // fold seek bar value to range[time_MIN, time_MAX]

                //System.out.println(lengthGame[0]);
                lengthGame[0] = progress + 1;
                System.out.println("game.time:"+lengthGame[0]);
            }
        });

        wtDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

}
