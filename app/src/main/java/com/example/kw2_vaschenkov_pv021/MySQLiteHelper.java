package com.example.kw2_vaschenkov_pv021;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kw2_vaschenkov_pv021.Data.Person;
import com.example.kw2_vaschenkov_pv021.Data.Product;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private final static String TAG = "==========My TAG:=====";
    private final static String dbName = "db";
    private final static int dbVersion = 1;
    public final static String tableProducts = "Products";
    private static final ArrayList<Product> products = new ArrayList<Product>();
    public final static String tablePersons = "Persons";
    private static final ArrayList<Person> persons = new ArrayList<Person>();

    public MySQLiteHelper(Context context) {
        super(context, MySQLiteHelper.dbName, null, MySQLiteHelper.dbVersion);
        products.add(new Product(1, "Y3", 100, 1));
        products.add(new Product(2, "Adidas", 200, 5));
        products.add(new Product(3, "Nike", 300, 2));
        products.add(new Product(4, "Puma", 250, 3));

        persons.add(new Person("Frank", "Doctor", 32, true));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableProducts + "(" +
                "id integer not null primary key autoincrement, " +
                "name text, " + "price real, " +
                "weight integer)";
        db.execSQL(query);

        for (Product p : products){
            ContentValues row = new ContentValues();
            row.put("name", p.name);
            row.put("price", p.price);
            row.put("weight", p.weight);
            db.insert(tableProducts, null, row);
        }

        query = "CREATE TABLE " + tablePersons + "(" +
                "id integer not null primary key autoincrement, " +
                "name text, " + "speciality text, " +
                "age integer, gender boolean)";
        db.execSQL(query);

        Log.d(TAG, "onCreate: " + db.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

    }
}
