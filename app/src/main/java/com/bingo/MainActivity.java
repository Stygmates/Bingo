package com.bingo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    JoueurDB joueurDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joueurDB = new JoueurDB(this, null, null, 1);

    }

    public void onButton(View view)
    {
        TextView textView = (TextView)findViewById(R.id.pseudo);
        Joueur joueur = new Joueur(textView.getText().toString());
        joueurDB.addJoueur(joueur);
        //Intent intent = new Intent(this, Jeu.class);
        //startActivity(intent);

        //Intent score = new Intent(this, Score.class);
        //startActivity(score);

        Intent jeu2 = new Intent(this, Jeu2.class);
        jeu2.putExtra("joueur",joueur);
        startActivity(jeu2);
    }
    public void onButtonScore(View view)
    {
        Intent score = new Intent(this, Score.class);
        startActivity(score);
    }
}