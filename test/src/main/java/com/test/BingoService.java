package com.test;

import android.app.IntentService;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tit-a on 12/12/2016.
 */

public class BingoService extends IntentService {
    public BingoService() {
        super("BingoService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("COUCOU");
                broadcastIntent.putExtra("coucou", "test");
                sendBroadcast(broadcastIntent);
            }
        };
                timer.schedule(task, 01,5000);
    }
}
