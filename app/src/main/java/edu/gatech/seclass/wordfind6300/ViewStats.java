package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class ViewStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);


        final Button button = findViewById(R.id.btn_word);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WordListActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        final Button button2 = findViewById(R.id.btn_game);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ScoreListActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
