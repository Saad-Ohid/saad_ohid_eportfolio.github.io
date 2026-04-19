package com.saad.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "InventoryStore.db";
    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, qty INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    public void addItem(String name, int qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("qty", qty);
        db.insert("items", null, values);
        db.close();
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM items", null);
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("items", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}