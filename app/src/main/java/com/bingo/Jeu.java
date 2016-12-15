package com.bingo;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Jeu extends AppCompatActivity {
    private int nombre = -1;
    Grille grille;
    int[] validees;

    public Grille newGrille()
    {
        Grille grille1 = new Grille();
        Button button = (Button)findViewById(R.id.button1);
        button.setText(Integer.toString(grille1.getGrille()[0]));
        button = (Button)findViewById(R.id.button2);
        button.setText(Integer.toString(grille1.getGrille()[1]));
        button = (Button)findViewById(R.id.button3);
        button.setText(Integer.toString(grille1.getGrille()[2]));
        button = (Button)findViewById(R.id.button4);
        button.setText(Integer.toString(grille1.getGrille()[3]));
        button = (Button)findViewById(R.id.button5);
        button.setText(Integer.toString(grille1.getGrille()[4]));
        button = (Button)findViewById(R.id.button6);
        button.setText(Integer.toString(grille1.getGrille()[5]));
        button = (Button)findViewById(R.id.button7);
        button.setText(Integer.toString(grille1.getGrille()[6]));
        button = (Button)findViewById(R.id.button8);
        button.setText(Integer.toString(grille1.getGrille()[7]));
        button = (Button)findViewById(R.id.button9);
        button.setText(Integer.toString(grille1.getGrille()[8]));
        return grille1;
    }

    public void setupjeu()
    {
        //Creation de la grille et modification des boutons
        grille = newGrille();
        validees = new int[9];
        for(int i = 0; i < 9; i++)
        {
            validees[i] = 0;
        }

        //Listener sur le nombre aléatoire courant
        final TextView textView = (TextView)this.findViewById(R.id.number);
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
        setContentView(R.layout.activity_jeu);
        setupjeu();
    }


    public void onRestart(View view)
    {
        setupjeu();
    }
    public void onButton(View view)
    {
        Button button = (Button)findViewById(view.getId());
        boolean egal = false;
        switch (view.getId())
        {
            case R.id.button1:
                egal = nombre == grille.getGrille()[0];
                break;
            case R.id.button2:
                egal = nombre == grille.getGrille()[1];
                break;
            case R.id.button3:
                egal = nombre == grille.getGrille()[2];
                break;
            case R.id.button4:
                egal = nombre == grille.getGrille()[3];
                break;
            case R.id.button5:
                egal = nombre == grille.getGrille()[4];
                break;
            case R.id.button6:
                egal = nombre == grille.getGrille()[5];
                break;
            case R.id.button7:
                egal = nombre == grille.getGrille()[6];
                break;
            case R.id.button8:
                egal = nombre == grille.getGrille()[7];
                break;
            case R.id.button9:
                egal = nombre == grille.getGrille()[8];
                break;
            default:
                Toast.makeText(this,"Coucou",Toast.LENGTH_SHORT).show();
                break;
        }
        if(egal)
        {
            button.setBackgroundColor(Color.BLUE);
        }
    }
}
