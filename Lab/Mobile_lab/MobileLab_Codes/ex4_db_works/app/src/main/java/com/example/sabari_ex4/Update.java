package com.example.sabari_ex4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        Button backu = findViewById(R.id.backu);
        backu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(Update.this);
                TextView textView = findViewById(R.id.idu);
                String id = textView.getText().toString();
                textView = findViewById(R.id.priceu);
                String price = textView.getText().toString();
                db.updateProduct(id, price);
            }
        });
    }
}
