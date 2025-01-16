package com.example.sabari_ex5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t1 = findViewById(R.id.t1);
        Thread1 th1 = new Thread1(t1);
        TextView t2 = findViewById(R.id.t2);
        Thread2 th2 = new Thread2(t2);
        TextView t3 = findViewById(R.id.t3);
        Thread3 th3 = new Thread3(t3);

        final boolean[] init = {false};
        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!init[0]) {
                    th1.start();
                    th2.start();
                    th3.start();
                    init[0] = true;
                } else {
                    Log.d("debug", "hello");
                    th1.pause(false);
                    th2.pause(false);
                    th3.pause(false);
                }
            }
        });

        Button stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                th1.pause(true);
                th2.pause(true);
                th3.pause(true);
            }
        });
    }
}