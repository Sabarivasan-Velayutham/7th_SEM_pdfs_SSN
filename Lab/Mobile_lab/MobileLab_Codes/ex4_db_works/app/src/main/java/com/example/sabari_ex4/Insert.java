package com.example.sabari_ex4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Insert extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);

        Button backi = findViewById(R.id.backi);
        backi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Insert.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button submiti = findViewById(R.id.submiti);
        submiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(Insert.this);

                TextView textView = findViewById(R.id.idi);
                String id = textView.getText().toString();

                textView = findViewById(R.id.namei);
                String name = textView.getText().toString();

                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String brand = selectedRadioButton.getText().toString();
                Log.d("Debug", brand);

                textView = findViewById(R.id.desci);
                String desc = textView.getText().toString();
                Log.d("Debug", desc);

                textView = findViewById(R.id.pricei);
                String price = textView.getText().toString();

                db.addProduct(id, name, brand, desc, price);
            }
        });
    }
}
