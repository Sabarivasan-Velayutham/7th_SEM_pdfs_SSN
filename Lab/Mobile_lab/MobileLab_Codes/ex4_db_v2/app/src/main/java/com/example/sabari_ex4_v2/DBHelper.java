package com.example.sabari_ex4_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.database.Cursor;
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "ProductDB.db", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE product (" +
                "id INT PRIMARY KEY, " +
                "name Text, " +
                "brand Text, " +
                "description Text, " +
                "price INT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS product");
    }
    //Insertion
    public Boolean insert_record(String id, String name, String brand, String
            description, String price) {

//To get db connection
        SQLiteDatabase db = this.getWritableDatabase();
//To write into db
        ContentValues content_value = new ContentValues();
        content_value.put("id", id);
        content_value.put("name", name);
        content_value.put("brand", brand);
        content_value.put("description", description);
        content_value.put("price", price);
//Execute insertion
        Long result = db.insert("product", null, content_value);
        if(result == -1) return false;
        return true;
    }
    //Updation
    public Boolean update_record(String id, String price) {
//To get db connection
        SQLiteDatabase db = this.getWritableDatabase();
//To write into db
        ContentValues content_value = new ContentValues();
        content_value.put("price", price);
        Cursor record = db.rawQuery("SELECT * FROM product WHERE id = ?", new
                String []{id});

//Execute updation if there is a record to be updated
        if(record.getCount() > 0)
        {
            int result = db.update("product", content_value, "id = ?", new

                    String []{id});
            if(result == -1) return false;
            else return true;
        }
        return false;

    }
    //Deletion
    public Boolean delete_record(String id) {
//To get db connection
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor record = db.rawQuery("SELECT * FROM product WHERE id = ?", new
                String []{id});

//Execute deletion if there is a record to be deleted
        if(record.getCount() > 0)
        {
            int result = db.delete("product", "id = ?", new String []{id});
            if(result == -1) return false;
            else return true;
        }
        return false;
    }
    //Retrieval
    public Cursor retrieve_record(String id) {
//To get db connection
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor record = db.rawQuery("SELECT * FROM product WHERE id = ?", new
                String []{id});
        return record;
    }
    //Retrieve all
    public Cursor retrieve_all_records() {
//To get db connection
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor records = db.rawQuery("SELECT * FROM product", null);
        return records;
    }
}

