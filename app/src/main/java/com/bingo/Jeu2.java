package com.bingo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


public class Jeu2 extends AppCompatActivity {
    private int nombre;
    Grille grille;
    int[] validees;
    int niveau;
    TextView tvNiveau;
    TextView tvLimcoin;
    Button buttonNewGrid;
    Button buttonScore;
    Button buttonNextlvl;
    Joueur joueur;
    JoueurScoreDB joueurScoreDB;


    public void restart()
    {
        this.grille = new Grille();
        validees = new int[9];

        //Listener sur le nombre aléatoire courant
        final TextView textView = (TextView)this.findViewById(R.id.nombretire);
        IntentFilter filter = new IntentFilter();
        filter.addAction("NUMBER_ACTION");
        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                nombre = intent.getIntExtra("number", 0);
                if(nombre != -1)
                {
                    textView.setText(Integer.toString(nombre));
                }
                else
                {
                    unregisterReceiver(this);
                }
            }
        };
        registerReceiver(receiver,filter);
        Intent intent = new Intent(this, BingoService.class);
        intent.putExtra("niveau", niveau);
        startService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu2);
        tvNiveau = (TextView)findViewById(R.id.niveau);
        tvLimcoin = (TextView)findViewById(R.id.limcoin);
        buttonNewGrid = (Button)findViewById(R.id.newGridButton);
        buttonScore = (Button)findViewById(R.id.ScoreButton);
        buttonNextlvl = (Button)findViewById(R.id.NextlvlButton);
        joueurScoreDB = new JoueurScoreDB(this, null, null, 1);


        this.joueur = this.getIntent().getParcelableExtra("joueur");
        if(this.joueur.getLimcoin()<250)
        {
            buttonNewGrid.setEnabled(false);
        }
        this.niveau = this.getIntent().getIntExtra("lvl", 1);
        tvNiveau.setText(Integer.toString(this.niveau));
        this.nombre = -1;
        if(this.joueur != null)
        {
            tvLimcoin.setText(Integer.toString(joueur.getLimcoin()));
        }
        else
        {
            tvLimcoin.setText(Integer.toString(500));
        }
        restart();
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ItemAdapter itemAdapter = new ItemAdapter(this,this.grille.getGrille());
        gridview.setAdapter(itemAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView textView = (TextView)v;
                boolean perdu = false;
                int valeur = Integer.parseInt(textView.getText().toString());
                if((valeur == nombre)&&(validees[valeur] == 0)) {
                    validees[valeur] = 1;
                    textView.setBackgroundColor(Color.CYAN);

                    for (int i = 0; i < 9; i++) {
                        if (validees[i] != 1) {
                            perdu = true;
                        }
                    }
                    if (!perdu) {
                        buttonNextlvl.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void onNewGridButton(View view)
    {
        this.joueur.setLimcoin(this.joueur.getLimcoin() - 250);
        Intent intent = new Intent(this, Jeu2.class);
        intent.putExtra("joueur",this.joueur);
        intent.putExtra("niveau", this.niveau);
        startActivity(intent);
    }

    public void onNextlvlButton(View view)
    {
        joueur.setLimcoin(joueur.getLimcoin() + 500);
        Intent intent = new Intent(this, Jeu2.class);
        intent.putExtra("joueur",this.joueur);
        intent.putExtra("niveau",this.niveau + 1);
        startActivity(intent);
    }

    public void onScoreButton(View view)
    {
        joueurScoreDB.addJoueur(joueur,this.niveau);
        Intent scoreIntent = new Intent(this,Score.class);
        startActivity(scoreIntent);
    }

    public void onRestartButton(View view)
    {
        joueur.setLimcoin(500);
        joueurScoreDB.addJoueur(joueur,this.niveau);
        Intent intent = new Intent(this, Jeu2.class);
        intent.putExtra("joueur", joueur);
        intent.putExtra("niveau",niveau);
        startActivity(intent);
    }
}
