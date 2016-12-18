package com.bingo;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tit-a on 12/12/2016.
 */

public class BingoService extends IntentService {
    int niveau;
    int []valeurPassees;
    int nbValeursPassees;
    BroadcastReceiver receiver;
    public BingoService() {
        super("BingoService");
        valeurPassees = new int[100];
        nbValeursPassees = 0;
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        final Timer timer = new Timer();
        IntentFilter filter = new IntentFilter();
        filter.addAction("RESTART_ACTION");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                valeurPassees = new int[100];
                nbValeursPassees = 0;
                niveau = intent.getIntExtra("niveau", -1);
            }
        };
        registerReceiver(receiver,filter);
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
                    timer.cancel();
                    stopSelf();
                }
            }
        };
        timer.schedule(task, 01,1000-100*niveau);
    }

    @Override
    public void onDestroy() {
        Log.d("Toast3","WTF3");
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
