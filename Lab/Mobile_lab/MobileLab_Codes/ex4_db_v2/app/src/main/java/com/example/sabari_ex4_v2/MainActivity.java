package com.example.sabari_ex4_v2;

//import com.example.databasemanagement.DBHelper;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//DBHelper db = new DBHelper(getApplicationContext());
        Button insert = (Button) findViewById(R.id.button_insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(i);
            }
        });
        Button update = (Button) findViewById(R.id.button_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        UpdateActivity.class);
                startActivity(i);
            }
        });
        Button delete = (Button) findViewById(R.id.button_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        DeleteActivity.class);
                startActivity(i);
            }
        });
        Button retrieve = (Button) findViewById(R.id.button_retrieve);
        retrieve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        RetrieveActivity.class);
                startActivity(i);
            }
        });
        Button retrieve_all = (Button)
                findViewById(R.id.button_retrieve_all);
        retrieve_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(getApplicationContext());
                Cursor record = db.retrieve_all_records();
                String record_details = "";
                while(record.moveToNext())
                {
                    record_details += "ID: " + record.getString(0) + "\n";
                    record_details += "Name: " + record.getString(1) + "\n";
                    record_details += "Brand: " + record.getString(2) + "\n";
                    record_details += "Description: " + record.getString(3) +
                            "\n";
                    record_details += "Price: " + record.getString(4) +
                            "\n\n";

                }
                Intent i = new Intent(MainActivity.this,
                        DisplayActivity.class);
                i.putExtra("record_details", record_details);
                startActivity(i);
            }
        });
    }
}
