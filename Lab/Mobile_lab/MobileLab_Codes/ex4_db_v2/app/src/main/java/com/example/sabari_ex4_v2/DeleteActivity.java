package com.example.sabari_ex4_v2;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class DeleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);

        EditText product_id = (EditText)
                findViewById(R.id.entered_product_id);
        TextView error = (TextView) findViewById(R.id.error);
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = product_id.getText().toString();
                DBHelper db = new DBHelper(getApplicationContext());
                Boolean check = db.delete_record(id);
                if(check == true)
                {
                    Intent i = new Intent(DeleteActivity.this,
                            MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    error.setText("Could not delete!");
                    product_id.setText("");
                }
            }
        });
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DeleteActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
    }
}

