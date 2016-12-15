package com.bingo;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tit-a on 12/12/2016.
 */

public class BingoService extends IntentService {
    int []valeurPassees;
    int nbValeursPassees;
    public BingoService() {
        super("BingoService");
        valeurPassees = new int[100];
        nbValeursPassees = 0;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent broadcastIntent = new Intent();
                Random random = new Random();
                if(nbValeursPassees < 100) {
                    int nombre = random.nextInt(100);
                    while (valeurPassees[nombre] == 1) {
                        nombre = random.nextInt(100);
                    }
                    valeurPassees[nombre] = 1;
                    nbValeursPassees++;
                    broadcastIntent.setAction("NUMBER_ACTION");
                    broadcastIntent.putExtra("number", nombre);
                    sendBroadcast(broadcastIntent);
                    Log.d("Nombrevaleurs",Integer.toString(nbValeursPassees));

                }
                else if(nbValeursPassees == 100)
                {
                    broadcastIntent.setAction("NUMBER_ACTION");
                    broadcastIntent.putExtra("number", -1);
                    Log.d("TOAST","PAS SUPPRIME");
                    sendBroadcast(broadcastIntent);
                    //stopSelf();
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 01,50);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Toast3","WTF3");
    }
}
