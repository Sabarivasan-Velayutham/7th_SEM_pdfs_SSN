package com.example.sabari_ex4_v2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    String brand = "";
    String name = "";
    String description = "";
    String[] product_items = {"--Select--", "Chocolate", "Soft drinks",
            "Chips", "Energy Drinks", "Biscuits", "Misc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.insert_activity);
        EditText product_id = (EditText) findViewById(R.id.entered_product_id);
        Spinner product_name = (Spinner)
                findViewById(R.id.entered_product_name);
        ArrayAdapter sp_item = new ArrayAdapter(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                product_items);
        sp_item.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        product_name.setAdapter(sp_item);
        RadioGroup product_brand = findViewById(R.id.brand);
        EditText product_description = (EditText)
                findViewById(R.id.entered_product_description);
        EditText product_price = (EditText)
                findViewById(R.id.entered_product_price);
        TextView error = (TextView) findViewById(R.id.error);
        error.setText("");
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean id_flag = true, id_len_flag = true;
                String id = product_id.getText().toString();
                try {
                    int id_validation = Integer.parseInt(id);
                    if(id_validation / 1000 == 0 || id_validation / 1000 >

                            9)

                    {
                        id_len_flag = false;
                    }
                }
                catch (NumberFormatException nfe)

                {
                    id_flag = false;
                }
                if(id_flag == false || id_len_flag == false)
                {
                    error.setText("Product ID must be 4 digits");
                    product_id.setText("");
                }
                else
                {
                    name = product_name.getSelectedItem().toString();
                    description = product_description.getText().toString();
                    int rid = product_brand.getCheckedRadioButtonId();
                    if(rid == R.id.brand1)
                    {
                        brand = "Brand 1";
                    }
                    else if(rid == R.id.brand2)
                    {
                        brand = "Brand 2";
                    }
                    else if(rid == R.id.brand3)
                    {
                        brand = "Brand 3";
                    }
                    String price = product_price.getText().toString();
                    Boolean price_flag = true;
                    try {
                        int price_validation = Integer.parseInt(price);
                    }
                    catch(NumberFormatException nfe) {
                        price_flag = false;
                    }
                    if(price_flag == false)
                        error.setText("Price should be a number");
                    else
                    {
                        DBHelper db = new DBHelper(getApplicationContext());
                        Boolean check_insert = db.insert_record(id, name,
                                brand, description, price);
                        System.out.println(id+" "+brand);
                        String x = id + " " + name + " " + brand + " " +
                                description + " " + price;
                        System.out.println(check_insert);
                        if(check_insert == true)
                        {
                            Intent j = new Intent(InsertActivity.this,
                                    MainActivity.class);
                            startActivity(j);

                        }
                        else
                        {
                            error.setText("Record could not be inserted!");
                            product_id.setText("");
                            product_name.setAdapter(sp_item);
                            product_brand.clearCheck();
                            product_description.setText("");
                            product_price.setText("");
                        }
                    }
                }
            }
        });
    }
}

