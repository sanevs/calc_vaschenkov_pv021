package com.example.kw2_vaschenkov_pv021;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private final static String TAG = "==========My TAG:=====";
    private final static String dbName = "db";
    private final static int dbVersion = 1;
    public final static String tableProducts = "Products";

    public MySQLiteHelper(Context context) {
        super(context, MySQLiteHelper.dbName, null, MySQLiteHelper.dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableProducts + "(" +
                "id integer not null primary key autoincrement, " +
                "name text, " + "price real, " +
                "weight integer)";
        db.execSQL(query);

        ContentValues row = new ContentValues();
        row.put("name", "Sneakers");
        row.put("price", 23.0);
        row.put("weight", 15);
        db.insert(tableProducts, null, row);

        row = new ContentValues();
        row.put("name", "Mars");
        row.put("price", 13.90);
        row.put("weight", 50);
        db.insert(tableProducts,null, row);

        Log.d(TAG, "onCreate: " + db.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
