package com.example.sabari_ex5;

import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class Thread2 extends Thread {
    TextView t;
    int dir = 1;
    int translationDistance = 300;
    boolean paused = false;
    Object lock = new Object();

    Thread2(TextView t) {
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
        while (!paused) {
            try {
                TranslateAnimation animation;
                if (dir == 1) {
                    animation = new TranslateAnimation(-translationDistance,
                            translationDistance, 0, 0);
                } else {
                    animation = new TranslateAnimation(translationDistance,
                            -translationDistance, 0, 0);
                }
                animation.setDuration(3000); // Keep the total duration the same
                animation.setFillAfter(true);
                t.startAnimation(animation);
                Thread.sleep(3000);
                dir = 1 - dir;
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

