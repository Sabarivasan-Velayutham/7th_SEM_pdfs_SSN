package com.example.sabari_ex8;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class MessageBoxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_box);

        TextView senderTextView = findViewById(R.id.senderTextView);
        TextView messageTextView = findViewById(R.id.messageTextView);
// Get data from the intent
        String sender = getIntent().getStringExtra("sender");
        String message = getIntent().getStringExtra("message");
// Display sender and message in TextViews
        senderTextView.setText("Sender: " + sender);
        messageTextView.setText("Message: " + message);
    }
}
