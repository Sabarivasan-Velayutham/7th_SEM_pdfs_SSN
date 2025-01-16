package com.example.sabari_ex5;

import android.util.Log;
import android.widget.TextView;

public class Thread3 extends Thread {
    TextView t;
    int ctr = 0;
    boolean paused = false;
    Object lock = new Object();

    Thread3(TextView t) {
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
        while (ctr < 3000 && !paused) {
            try {
                Thread.sleep(1000);
                ctr += 1;
// Update the TextView on the UI thread
                t.post(new Runnable() {
                    @Override
                    public void run() {
                        t.setText(Integer.toString(ctr));
                    }
                });
                synchronized (lock) {
                    while (paused) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
            } catch (InterruptedException e) {
                paused = true;
                e.printStackTrace();
            }
        }
    }
}