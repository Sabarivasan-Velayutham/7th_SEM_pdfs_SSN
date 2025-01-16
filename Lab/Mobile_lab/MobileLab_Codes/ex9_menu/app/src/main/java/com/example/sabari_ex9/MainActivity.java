package com.example.sabari_ex9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView country, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        country = findViewById(R.id.country);
        desc = findViewById(R.id.desc);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.country, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.ind:
// Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show(); country.setText("India");
                desc.setText(R.string.india);
                return true;
            case R.id.bah:
// Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show(); country.setText("Bahrain");
                desc.setText(R.string.bahrain);
                return true;
            case R.id.is:
// Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show(); country.setText("Israel");
                desc.setText(R.string.israel);
                return true;
            case R.id.chn:
// Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show(); country.setText("China");
                desc.setText(R.string.china);
                return true;
            case R.id.can:
// Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show(); country.setText("Canada");
                desc.setText(R.string.canada);
                return true;
            case R.id.uk:
// Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show(); country.setText("UK");
                desc.setText(R.string.uk);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}