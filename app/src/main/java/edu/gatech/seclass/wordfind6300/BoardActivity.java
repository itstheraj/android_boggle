package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TableLayout;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class BoardActivity extends AppCompatActivity {

    //Game locgame; //= localply.getGame();
    Board locboard; //locgame.getBoard();
    TableRow tr[]; //= new TableRow(this);
    Button buttons[];
    final Player localply = MainActivity.ply;
    final Game locgame = localply.getGame();
    Settings locsets = locgame.getSettings();
    int timer = locsets.gameLength * 60;
    final TextView[] time = new TextView[1];
    final Handler myHandler = new Handler();
    Timer myTimer = new Timer();
    boolean usedLetters[][];// = new boolean[locboard.getSize()-1][locboard.getSize()-1];

    //TextView currWord = (TextView) findViewById(R.id.lbl_curr_word);

    @Override
    public void onBackPressed()
    {
        Player locply = MainActivity.ply;
        Game locgame = locply.getGame();
        locgame.exitGame();
        locply.scoreStats.append(locgame);
        myHandler.removeCallbacks(myRunnable);
        myTimer.cancel();
        localply.playGame();
        super.onBackPressed();
    }


    private void UpdateGUI() {
        timer--;
        System.out.println(timer);
        myHandler.post(myRunnable);
        if (timer <= 0)
        {
            myHandler.removeCallbacks(myRunnable);
            myTimer.cancel();
            this.onBackPressed();
        }
    }


    public void setTimer() {
        timer--;
        System.out.println(timer);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                time[0].setText(String.valueOf((timer)));
                if (timer <=0 )
                {
                    myTimer.cancel();
                    BoardActivity.this.onBackPressed();
                }
            }
        });
    }

    final Runnable myRunnable = new Runnable() {
        public void run() {
            time[0].setText(String.valueOf(timer));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        time[0] = findViewById(R.id.lbl_time);

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {setTimer();}
        }, 0, 1000);


        final Player localply = MainActivity.ply;
        final Game locgame = localply.getGame();
        //this.locgame = localply.getGame();
        Settings locsets = locgame.getSettings();
        System.out.println("HERE BRO");
        System.out.println(locsets.getBoardSize());
        //final Board locboard = locgame.getBoard();
        locboard = locgame.getBoard();
        final Context ctxt = this;
        usedLetters = new boolean[locboard.getSize()][locboard.getSize()];
        //String currWord = "";

        final int size = locsets.getBoardSize();
        // When board class/game class is made we can change above line easily

        final TextView currWord = (TextView) findViewById(R.id.lbl_curr_word);

        this.tr = new TableRow[size+1];
        this.buttons = new Button[size*size];

        final TableLayout gridTab = (TableLayout) findViewById(R.id.grid_game);
        // dynamically create table based on board size param
        for (int rn = 1; rn < size + 1; rn++)
        {
            //TableRow tr = new TableRow(this);
            tr[rn] = new TableRow(this);
            TableLayout.LayoutParams lparams = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            //tr.setLayoutParams(lparams);
            tr[rn].setLayoutParams(lparams);

            final DisplayMetrics displayMetrics = new DisplayMetrics();

            for (int cn = 1; cn < size + 1; cn++)
            {
                final Button b = new Button(this);
                //Button b = new Button(this);
                System.out.println("HRE BRUH");
                System.out.println(rn);
                System.out.println(cn);
                System.out.println(locboard.getLetterArr().length);
                b.setText(String.format("%s", locboard.getLetterArr()[rn-1][cn-1]));
                b.setTextSize(12-size);
                //b.setText("A");
                b.setId( size*(rn-1) + (cn-1));
                System.out.println(470/size);
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;
                b.setLayoutParams(new TableRow.LayoutParams((int)(.5*width)/size, (int)(height*.5)/size));
                final String ltrSet = locboard.getLetterArr()[rn-1][cn-1];
                final int finalRn = rn;
                final int finalCn = cn;

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!usedLetters[finalRn-1][finalCn-1]) {
                            String toSet = currWord.getText().toString() + ltrSet;
                            currWord.setText(toSet);
                            usedLetters[finalRn - 1][finalCn - 1] = true;
                            // when you click a button
                            // it should loop over every other button and if it is adjacent
                            // make it clickable
                            // if it is not make it unclickable
                            for (int btnRowInd = 0; btnRowInd < size; btnRowInd++) {
                                for (int btnColInd = 0; btnColInd < size; btnColInd++) {
                                    Button temp = findViewById(size * (btnRowInd) + (btnColInd));
                                    if (locboard.adjacent(finalRn - 1, finalCn - 1, btnRowInd, btnColInd)) {
                                        System.out.println(btnRowInd);
                                        System.out.println(btnColInd);
                                        temp.setClickable(true);
                                    } else {
                                        System.out.println(btnRowInd);
                                        System.out.println(btnColInd);
                                        temp.setClickable(false);
                                    }
                                }
                            }
                        } b.setClickable(false);
                    }
                });
                //tr.addView(b);
                tr[rn].addView(b);
            }

            //gridTab.addView(tr);
            gridTab.addView(tr[rn]);
        }

        final Button clrbtn = findViewById(R.id.btn_clr);
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < size * size; i++)
                {
                    Button rstBtn = (Button) findViewById(i);
                    rstBtn.setClickable(true);
                }
                currWord.setText("Current Word: ");
            }
        });

        final Button exitBtn = findViewById(R.id.btn_exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player locply = MainActivity.ply;
                Game locgame = locply.getGame();
                locgame.exitGame();
                locply.scoreStats.append(locgame);
                myHandler.removeCallbacks(myRunnable);
                myTimer.cancel();
                localply.playGame();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        final Button subbtn = findViewById(R.id.btn_submit);
        final LinearLayout boxer = findViewById(R.id.words_used_box);
        final TextView score = findViewById(R.id.lbl_score);
        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < size * size; i++) {
                    Button rstBtn = (Button) findViewById(i);
                    rstBtn.setClickable(true);
                }
                TextView addTxt = new TextView(ctxt);
                int idx = currWord.getText().toString().indexOf(':') + 1;
                String wordStr = currWord.getText().toString().substring(idx);

                System.out.println("word<"+wordStr+">");
                wordStr = wordStr.trim();
                boolean block = false;
                if(wordStr.length() < 2){
                    System.out.println("+" + wordStr + "+");
                    Toast errorToast = Toast.makeText(BoardActivity.this, "Please enter 2 or more characters.", Toast.LENGTH_SHORT);
                    errorToast.show();
                    System.out.println("reached length < 2");
                    block = true;
                }
                if(wordStr.equalsIgnoreCase("Qu")){
                    Toast errorToast = Toast.makeText(BoardActivity.this, "Word Cannot be 'QU'.", Toast.LENGTH_SHORT);
                    errorToast.show();
                    System.out.println("reached word = 'Qu'");
                    block = true;
                }
                if (locgame.checkDuplicate(wordStr))
                {
                    Toast errorToast = Toast.makeText(BoardActivity.this, "Word cannot be entered twice.", Toast.LENGTH_SHORT);
                    errorToast.show();
                    System.out.println("Double word used");
                    block = true;
                }

                locboard.setCurrentWord(wordStr);
                boolean ok;
                if (block == false)
                {
                     ok = locgame.enterWord();
                }
                else
                {
                    ok = false;
                }
                if( ok ) {
                addTxt.setText(wordStr);
                boxer.addView(addTxt);
                }
                currWord.setText("Current Word: ");

                int uScore = locgame.getTotalScore();
                score.setText(String.format("Score: %d", uScore));

                // got to add word to word list and increment its frequency upon submit
                localply.wordStats.append(wordStr);


            }
        });

		//reroll board
        final Button rollbtn = findViewById(R.id.btn_roll);
        rollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usedLetters = new boolean[locboard.getSize()][locboard.getSize()];
                gridTab.removeAllViews();

                locgame.reroll();

                locboard = locgame.getBoard();

                for (int rn = 1; rn < size + 1; rn++)
                {
                    tr = new TableRow[size+1];
                    //TableRow tr = new TableRow(this);
                    tr[rn] = new TableRow(BoardActivity.this);
                    TableLayout.LayoutParams lparams = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    //tr.setLayoutParams(lparams);
                    tr[rn].setLayoutParams(lparams);

                    final DisplayMetrics displayMetrics = new DisplayMetrics();

                    for (int cn = 1; cn < size + 1; cn++)
                    {
                        final Button b = new Button(BoardActivity.this);
                        //Button b = new Button(this);
                        b.setText(String.format("%s", locboard.getLetterArr()[rn-1][cn-1]));
                        b.setTextSize(12-size);
                        //b.setText("A");
                        b.setId( size*(rn-1) + (cn-1));
                        System.out.println(470/size);
                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        int height = displayMetrics.heightPixels;
                        int width = displayMetrics.widthPixels;
                        b.setLayoutParams(new TableRow.LayoutParams((int)(.5*width)/size, (int)(height*.5)/size));
                        final int finalRn = rn;
                        final int finalCn = cn;
                        final String ltrSet = locboard.getLetterArr()[rn-1][cn-1];
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!usedLetters[finalRn-1][finalCn-1]) {
                                    String toSet = currWord.getText().toString() + ltrSet;
                                    currWord.setText(toSet);
                                    usedLetters[finalRn - 1][finalCn - 1] = true;
                                    // when you click a button
                                    // it should loop over every other button and if it is adjacent
                                    // make it clickable
                                    // if it is not make it unclickable
                                    for (int btnRowInd = 0; btnRowInd < size; btnRowInd++) {
                                        for (int btnColInd = 0; btnColInd < size; btnColInd++) {
                                            Button temp = findViewById(size * (btnRowInd) + (btnColInd));
                                            if (locboard.adjacent(finalRn - 1, finalCn - 1, btnRowInd, btnColInd)) {
                                                System.out.println(btnRowInd);
                                                System.out.println(btnColInd);
                                                temp.setClickable(true);
                                            } else {
                                                System.out.println(btnRowInd);
                                                System.out.println(btnColInd);
                                                temp.setClickable(false);
                                            }
                                        }
                                    }
                                }b.setClickable(false);
                            }
                        });
                        //tr.addView(b);
                        tr[rn].addView(b);
                    }

                    //gridTab.addView(tr);
                    gridTab.addView(tr[rn]);

                    int uScore = locgame.getTotalScore();
                    score.setText(String.format("Score: %d", uScore));
                    currWord.setText("Current Word: ");
                }

                /*
                BoardMe(locgame,size);
                for (int i = 0; i < size * size; i++) {
                    Button rstBtn = (Button) findViewById(i);
                    rstBtn.setClickable(true);
                }
                TextView addTxt = new TextView(ctxt);
                int idx = currWord.getText().toString().indexOf(':') + 1;
                String wordStr = currWord.getText().toString().substring(idx);

System.out.println("word<"+wordStr+">");
wordStr = wordStr.trim();
                locboard.setCurrentWord(wordStr);
                boolean ok = locgame.enterWord();
                if( ok ) {
                addTxt.setText(wordStr);
                boxer.addView(addTxt);
                }
                currWord.setText("Current Word: ");

                int uScore = locgame.getTotalScore();

                score.setText(String.format("Score: %d", uScore));
                localply.scoreStats.append(locgame);
                 */
            }
        });

    }

        protected void BoardMe( Game thegame, int size) {
            thegame.reroll();
            //final Board locboard = thegame.getBoard();
            this.locboard = thegame.getBoard();
            //TableLayout gridTab = (TableLayout) findViewById(R.id.grid_game);
            final TextView currWord = (TextView) findViewById(R.id.lbl_curr_word);
            //gridTab.RemoveAllViews();
            //for (int rn = 1; rn < size + 1; rn++) {
            //    //table.getChildCount
            //    gridTab.removeViews(1,rn-1);
            //}
            // dynamically create table based on board size param
            for (int rn = 1; rn < size + 1; rn++)
            {
                for (int cn = 1; cn < size + 1; cn++)
                {
                    int id = size*(rn-1) + (cn-1);
                    String symbol = locboard.getLetterArr()[rn-1][cn-1];
                    //System.out.println("b:"+symbol);
                    final Button b = (Button) findViewById(id);
                    //this.tr[rn].removeView(b);
                    b.setText(String.format("%s", symbol)); // locboard.getLetterArr()[rn-1][cn-1]));
                    final String ltrSet = symbol; //locboard.getLetterArr()[rn-1][cn-1];
                    b.setClickable(true);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String toSet = currWord.getText().toString() + ltrSet;
                            currWord.setText(toSet);
                            b.setClickable(true);
                        }
                    });
                    //this.tr[rn].addView(b);
                }
                //gridTab.addView(tr[rn]);
            }
        }

}
