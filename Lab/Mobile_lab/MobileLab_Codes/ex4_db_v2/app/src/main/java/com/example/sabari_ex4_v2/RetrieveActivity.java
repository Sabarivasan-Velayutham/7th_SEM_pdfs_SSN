package com.example.sabari_ex4_v2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class RetrieveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_activity);
        EditText product_id = (EditText)
                findViewById(R.id.entered_product_id);
        TextView error = (TextView) findViewById(R.id.error);
        Button retrieve = (Button) findViewById(R.id.retrieve);
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = product_id.getText().toString();
                DBHelper db = new DBHelper(getApplicationContext());
                Cursor record = db.retrieve_record(id);
                if(record.getCount() != 1)

                {
                    error.setText("Could not retrieve!");
                    product_id.setText("");
                }
                else
                {
                    error.setText("Could retrieve!");
                    String record_details = "";
                    while(record.moveToNext())
                    {
                        record_details += "ID: " + record.getString(0) +
                                "\n";
                        record_details += "Name: " + record.getString(1) +
                                "\n";
                        record_details += "Brand: " + record.getString(2) +
                                "\n";
                        record_details += "Description: " +
                                record.getString(3) + "\n";
                        record_details += "Price: " + record.getString(4) +
                                "\n\n";

                    }
                    Intent i = new Intent(RetrieveActivity.this,
                            DisplayActivity.class);
                    i.putExtra("record_details", record_details);
                    startActivity(i);
                }
            }
        });
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RetrieveActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
    }
}

