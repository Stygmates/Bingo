package com.bingo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        JoueurScoreDB joueurScoreDB = new JoueurScoreDB(this,null,null,1);
        ArrayList <JoueurScore> liste = joueurScoreDB.tableauScore();
        //TextView textview = (TextView)findViewById(R.id.textViewScore);
        //textview.setText(joueurScoreDB.toString());
        TableLayout table = (TableLayout) findViewById(R.id.table);
        TableRow row;
        TextView tv1,tv2;

        row = new TableRow(this);
        tv1 = new TextView(this);
        tv1.setText("Pseudo");
        tv1.setGravity(Gravity.CENTER);
        tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        tv2 = new TextView(this);
        tv2.setText("Niveau");
        tv2.setGravity(Gravity.CENTER);
        tv2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        row.addView(tv1);
        row.addView(tv2);

        table.addView(row);

        Iterator<JoueurScore> iterator = liste.iterator();
        while(iterator.hasNext())
        {
            JoueurScore joueurScore = iterator.next();
            row = new TableRow(this);
            tv1 = new TextView(this);
            tv1.setText(joueurScore.getPseudo());
            tv1.setGravity(Gravity.CENTER);

            tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            tv2 = new TextView(this);
            tv2.setText(Integer.toString(joueurScore.getNiveau()));
            tv2.setGravity(Gravity.CENTER);

            tv2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tv1);
            row.addView(tv2);

            table.addView(row);
        }

    }
}
