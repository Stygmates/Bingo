package com.bingo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        JoueurDB joueurDB = new JoueurDB(this,null,null,1);
        //TextView textview = (TextView)findViewById(R.id.textViewScore);
        //textview.setText(joueurDB.toString());
    }
}
