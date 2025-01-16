package com.example.sabari_ex4_v2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        EditText product_id = (EditText)
                findViewById(R.id.entered_product_id);
        EditText product_price = (EditText)
                findViewById(R.id.entered_product_price);
        TextView error = (TextView) findViewById(R.id.error);
        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = product_id.getText().toString();
                String price = product_price.getText().toString();
                DBHelper db = new DBHelper(getApplicationContext());
                Boolean check = db.update_record(id, price);
                if(check == true)
                {
                    Intent i = new Intent(UpdateActivity.this,
                            MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    error.setText("Could not update!");
                    product_id.setText("");
                    product_price.setText("");
                }
            }
        });
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
    }
}

