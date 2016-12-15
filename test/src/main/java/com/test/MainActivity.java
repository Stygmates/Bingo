package com.test;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction("COUCOU");
        registerReceiver(new BingoReceiver(),filter);
    }
    public void onButton(View view){
        Intent background = new Intent(this, BingoService.class);
        startService(background);
        Button button = (Button)findViewById(R.id.button);
        button.setBackgroundColor(Color.RED);
    }
}
