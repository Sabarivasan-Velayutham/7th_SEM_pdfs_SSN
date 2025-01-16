package com.example.sabari_ex4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Retrieve extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve);

        Button backr = findViewById(R.id.backr);
        backr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Retrieve.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button retrieve = findViewById(R.id.retrieve);
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.idr);
                String id = textView.getText().toString();
                Database db = new Database(Retrieve.this);
                Cursor cursor = db.retrieve(id);
                String name = "", brand = "", desc = "", price = "";
                if (cursor.moveToFirst()) {
                    do {
// Retrieve values from 'column1' and 'column2' as strings
                        int columnIndex = cursor.getColumnIndex("name");
                        if (columnIndex != -1) {
                            name = cursor.getString(columnIndex);
                        }

                        columnIndex = cursor.getColumnIndex("brand");
                        if (columnIndex != -1) {
                            brand = cursor.getString(columnIndex);
                        }

                        columnIndex = cursor.getColumnIndex("desc");
                        if (columnIndex != -1) {
                            desc = cursor.getString(columnIndex);
                        }

                        columnIndex = cursor.getColumnIndex("price");
                        if (columnIndex != -1) {
                            price = cursor.getString(columnIndex);
                        }

                    } while (cursor.moveToNext());
                }
                textView = findViewById(R.id.namer);
                textView.setText(name);
                textView = findViewById(R.id.brandr);
                textView.setText(brand);
                textView = findViewById(R.id.descr);
                textView.setText(desc);
                textView = findViewById(R.id.pricer);
                textView.setText(price);
            }
        });
    }
}
