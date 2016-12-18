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
    Joueur joueur;


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
        this.joueur = this.getIntent().getParcelableExtra("joueur");
        this.niveau = 1;
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
                if(valeur == nombre)
                {
                    validees[valeur] = 1;
                    textView.setBackgroundColor(Color.CYAN);
                }
                for(int i = 0; i < 9; i++)
                {
                    if(validees[i] != 1)
                    {
                        perdu = true;
                    }
                }
                if(!perdu)
                {
                    niveau++;
                    tvNiveau.setText(Integer.toString(niveau));
                    restart();
                }
            }
        });
    }
}
