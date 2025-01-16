package com.example.sabari_ex4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "product";
    private static final int DB_VERSION = 3;
    private static final String TABLE_NAME = "prod";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String BRAND_COL = "brand";
    private static final String DESC_COL = "desc";
    private static final String PRICE_COL = "price";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "
                + NAME_COL + " TEXT,"
                + BRAND_COL + " TEXT,"
                + DESC_COL + " TEXT,"
                + PRICE_COL + " TEXT)";
// at last we are calling a exec sql
// method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addProduct(String id, String name, String brand, String desc,
                           String price) {
// on below line we are creating a variable for
// our sqlite database and calling writable method
// as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
// on below line we are creating a
// variable for content values.
        ContentValues values = new ContentValues();
// on below line we are passing all values
// along with its key and value pair.
        values.put(ID_COL, id);
        values.put(NAME_COL, name);
        values.put(BRAND_COL, brand);
        values.put(DESC_COL, desc);
        values.put(PRICE_COL, price);
// after adding all values we are passing
// content values to our table.
        db.insert(TABLE_NAME, null, values);
// at last we are closing our
// database after adding database.
        db.close();
    }

    public void deleteProduct(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COL + "= ?", new String[]{id});
    }

    public void updateProduct(String id, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRICE_COL, price);
        db.update(TABLE_NAME, values, ID_COL + "=?", new
                String[]{String.valueOf(id)});
    }

    public Cursor retrieveAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public Cursor retrieve(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                NAME_COL,
                BRAND_COL,
                DESC_COL,
                PRICE_COL
        };
// Define the condition for retrieval (e.g., where id = ?)
        String selection = ID_COL + " = ?";
        String[] selectionArgs = {id};
// Execute the query
        Cursor cursor = db.query(
                TABLE_NAME, // Table name
                projection, // Columns to return
                selection, // Selection (WHERE clause)
                selectionArgs, // Selection arguments
                null, // Group by
                null, // Having
                null // Order by
        );
// The cursor now contains the retrieved row(s)
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
// this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
