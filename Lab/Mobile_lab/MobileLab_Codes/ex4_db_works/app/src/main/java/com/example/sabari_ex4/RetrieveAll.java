package com.example.sabari_ex4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class RetrieveAll extends AppCompatActivity {
    private TextView newTextView(String text) {
        TextView tv = new TextView(RetrieveAll.this);
        tv.setText(text);
        tv.setPadding(4, 4, 4, 4);
        tv.setTextSize(24);
        TableRow.LayoutParams layoutParams = new
                TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, // Adjust width as needed
                TableRow.LayoutParams.WRAP_CONTENT // Adjust height as needed
        );
        layoutParams.rightMargin = 20;
        tv.setLayoutParams(layoutParams);
        return tv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_all);

        Database db = new Database(RetrieveAll.this);
        Cursor cursor = db.retrieveAll();
        ConstraintLayout parent = findViewById(R.id.parent);

        TableLayout tl = new TableLayout(RetrieveAll.this);
        TableRow trh = new TableRow(RetrieveAll.this);
        TextView tv0 = newTextView("Id");
        trh.addView(tv0);
        tv0 = newTextView("Name");
        trh.addView(tv0);
        tv0 = newTextView("Brand");
        trh.addView(tv0);
        tv0 = newTextView("Description");
        trh.addView(tv0);
        tv0 = newTextView("Price");
        trh.addView(tv0);
        tl.addView(trh);
        String id = "", name = "", brand = "", desc = "", price = "";
        if (cursor.moveToFirst()) {
            do {
// Retrieve values from 'column1' and 'column2' as strings
                int columnIndex = cursor.getColumnIndex("id");
                if (columnIndex != -1) {
                    id = cursor.getString(columnIndex);
                }
                columnIndex = cursor.getColumnIndex("name");
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
                Log.d("Debug", id + " " + name + " " + brand + " " + desc + " " + price);
                TableRow tr = new TableRow(RetrieveAll.this);
                TextView tv1 = newTextView(id);
                TextView tv2 = newTextView(name);
                TextView tv3 = newTextView(brand);
                TextView tv4 = newTextView(desc);
                TextView tv5 = newTextView(price);
                tr.addView(tv1);
                tr.addView(tv2);
                tr.addView(tv3);
                tr.addView(tv4);
                tr.addView(tv5);
                tl.addView(tr);
            } while (cursor.moveToNext());
        }
        parent.addView(tl);

        Button retrieveAll = findViewById(R.id.backra);
        retrieveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new
                        Intent(RetrieveAll.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
