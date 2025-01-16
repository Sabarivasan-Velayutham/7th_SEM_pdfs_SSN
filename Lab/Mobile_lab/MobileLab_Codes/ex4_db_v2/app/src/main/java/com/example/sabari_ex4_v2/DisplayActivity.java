package com.example.sabari_ex4_v2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        TextView record = (TextView) findViewById(R.id.record);
        Intent i = getIntent();
        String record_display = i.getStringExtra("record_details");
        record.setText(record_display);
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DisplayActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
    }
}
