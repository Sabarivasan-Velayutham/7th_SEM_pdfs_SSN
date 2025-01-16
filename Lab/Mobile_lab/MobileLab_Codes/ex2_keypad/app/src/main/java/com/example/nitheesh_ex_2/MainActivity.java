package com.example.nitheesh_ex_2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class MainActivity extends AppCompatActivity
{
    Boolean capsOn;
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keypad);
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String str = intent.getStringExtra("prev_text");
        mTextView = (TextView) findViewById(R.id.textView22);
        mTextView.setText(str);
        capsOn=false;
    }

    public void toggleNum(android.view.View view){
        TextView mTextView = (TextView) findViewById(R.id.textView22);
        String old = (String) mTextView.getText();
        Intent intent = new Intent(this, NumberPad.class);
        intent.putExtra("prev_text",old);
        startActivity(intent);
    }
    public void sendMessage(android.view.View view)
    {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        if (mTextView == null) {
            return; // Add a null check to avoid crashes
        }
        TextView mTextView = (TextView) findViewById(R.id.textView22);
        if(buttonText.equals("Del")){
            String old = (String) mTextView.getText();
            if(old.length()>=1)
                mTextView.setText(old.substring(0,old.length()-1));
        }
        else if(buttonText.equals("| |")){
            String old = (String) mTextView.getText();
            mTextView.setText(old+" ");
        }
        else if(buttonText.equals("Ret")){
            String old = (String) mTextView.getText();
            mTextView.setText(old+"\n");
        }
        else if(buttonText.equals("Caps")){
            capsOn = !capsOn;
        }
        else{
            String old = (String) mTextView.getText();
            if(!capsOn)
                mTextView.setText(old+buttonText);
            else
                mTextView.setText(old+(buttonText.toUpperCase()));
        }
    }
}