package com.example.nitheesh_ex_3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView carImageView;
    private ImageView sunImageView;
    private Button forwardButton;
    private Button backwardButton;
    private Button sunFlipButton;
    private int carXPosition = 0; // Initial X position of the car
    private final int endpoint = 800;// Predefined endpoint
    private final int startpoint = 0;
    boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carImageView = findViewById(R.id.carImageView);
        sunImageView = findViewById(R.id.sunId);
        forwardButton = findViewById(R.id.forwardButton);
        backwardButton = findViewById(R.id.backwardButton);
        sunFlipButton = findViewById(R.id.sunFlipId);

        forwardButton.setOnClickListener(new View.OnClickListener() {
            boolean isOperationInProgress = false;

            @Override
            public void onClick(View v) {
                if (!isOperationInProgress) {
                    isOperationInProgress = true;
                    backwardButton.setEnabled(false);
                    forwardButton.setEnabled(false);
                    final Handler handler = new Handler();

                    final Runnable carMovement = new Runnable() {
                        @Override
                        public void run() {
                            carXPosition += 10;
                            if (carXPosition <= endpoint) {
                                carImageView.setX(carXPosition);
                                handler.postDelayed(this, 100); // 100milliseconds delay
                            } else {
                                isOperationInProgress = false;
                                backwardButton.setEnabled(true);
                                forwardButton.setEnabled(true);
                            }
                        }
                    };
                    handler.post(carMovement);
                }
            }
        });

        backwardButton.setOnClickListener(new View.OnClickListener() {
            boolean isOperationInProgress = false;
            private boolean isCarFlipped = false;

            @Override
            public void onClick(View v) {
                if (!isOperationInProgress) {
                    isOperationInProgress = true;
                    backwardButton.setEnabled(false);
                    forwardButton.setEnabled(false);
                    flipCar();
                    final Handler handler = new Handler();
                    final Runnable carMovement = new Runnable() {
                        @Override
                        public void run() {
                            carXPosition -= 10;
                            if (carXPosition >= startpoint) {
//                                milliseconds delay
                                carImageView.setX(carXPosition);
                                handler.postDelayed(this, 100); // 100
                            } else {
                                isOperationInProgress = false;
                                backwardButton.setEnabled(true);
                                forwardButton.setEnabled(true);
                                flipCar();
                            }
                        }
                    };
                    handler.post(carMovement);
                }
            }

            private void flipCar() {
                ObjectAnimator flipAnimator;
                if (isCarFlipped) {
                    flipAnimator = ObjectAnimator.ofFloat(carImageView,
                            "rotationY", 180f, 0f);
                    isCarFlipped = false;
                } else {
                    flipAnimator = ObjectAnimator.ofFloat(carImageView,
                            "rotationY", 0f, 180f);
                    isCarFlipped = true;
                }
                flipAnimator.setDuration(500);
                flipAnimator.setInterpolator(new
                        AccelerateDecelerateInterpolator());
                flipAnimator.start();
            }

        });

        sunImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    sunImageView.setScaleX(2);
                    sunImageView.setScaleY(2);
                    isClicked = true;
                } else {
                    sunImageView.setScaleX(1);
                    sunImageView.setScaleY(1);
                    isClicked = false;
                }
            }
        });

        sunFlipButton.setOnClickListener(new View.OnClickListener() {
            private boolean isArrowFlipped = false;

            @Override
            public void onClick(View v) {
                ObjectAnimator flipAnimator;
                if (isArrowFlipped) {
                    flipAnimator = ObjectAnimator.ofFloat(sunImageView, "rotationY", 180f, 0f);
                    isArrowFlipped = false;
                } else {
                    flipAnimator = ObjectAnimator.ofFloat(sunImageView, "rotationY", 0f, 180f);
                    isArrowFlipped = true;
                }
                flipAnimator.setDuration(500);
                flipAnimator.setInterpolator(new
                        AccelerateDecelerateInterpolator());
                flipAnimator.start();
            }
        });
    }
}