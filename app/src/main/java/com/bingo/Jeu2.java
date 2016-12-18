package com.bingo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;


public class Jeu2 extends AppCompatActivity {
    private int nombre = -1;
    Grille grille;
    int[] validees;


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
        startService(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu2);
        restart();
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ItemAdapter itemAdapter = new ItemAdapter(this,this.grille.getGrille());
        gridview.setAdapter(itemAdapter);

    }
}
