package com.example.sabari_ex5;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

public class Thread1 extends Thread {
    TextView t;
    int red = 120;
    int green = 120;
    int blue = 120;
    boolean paused = false;
    Object lock = new Object();

    Thread1(TextView t) {
        this.t = t;
    }

    public void pause(boolean paused) {
        synchronized (lock) {
            if (paused)
                this.paused = true;
            else {
                this.paused = false;
                lock.notifyAll();
            }
        }
        Log.d("Debug", "" + paused);
    }

    public void run() {
        while (true) {
            try {
                int color = Color.rgb(red, green, blue);
                t.setTextColor(color);
                red = (red + 20) % 255;
                green = (green + 10) % 255;
                blue = (blue + 5) % 255;
                Thread.sleep(500);
                synchronized (lock) {
                    while (paused) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

