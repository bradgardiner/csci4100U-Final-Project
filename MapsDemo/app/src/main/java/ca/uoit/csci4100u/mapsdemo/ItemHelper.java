package ca.uoit.csci4100u.mapsdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by shayne on 2017-12-10.
 */

public class ItemHelper extends SQLiteOpenHelper{
    static final int DATABASE_VERSION = 1;
    Context context;

    static final String ItemTable = "Items";
    static final String MuffinTable = "Muffins";
    static final String TeaTable = "Tea";
    static final String DonutTable = "Donuts";

    static final String ItemCreate = "CREATE TABLE menuItems(\n" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\tdesc TEXT NOT NULL \n" +
            ");";
    static final String ItemsItems = "INSERT INTO menuItems(\n" +
            "\tVALUES (\"Coffee\", 1.99, \"Tim Hortons Original Blend Coffee: Small, Medium, and Large\"),\n" +
            "\tVALUES (\"Dark Roast Coffee\", 1.99, \"Tim Hortons Dark Roast Coffee: Small, Medium, and Large\"),\n" +
            "\tVALUES (\"Decaf Coffee\", 1.99, \"Tim Hortons Decaf Coffee: Small, Medium, and Large\"),\n" +
            "\tVALUES (\"Speciialty Tea\", 1.99, \"Specialty Teas\"),\n" +
            "\tVALUES (\"Speciialty Tea\", 1.99, \"Specialty Teas\"),\n" +
            "\tVALUES(\"Hot Breakfast Sandwich\", 2.99, \"Hot Breakfast Sandwich\"),\n" +
            "\tVALUES(\"Bagel B.E.L.T\", 3.99, \"BELT\"),\n" +
            "\tVALUES(\"HashBrown\", 1.99, \"HashBrown\"),\n" +
            "\tVALUES(\"Grilled Panini\", 3.99, \"Grilled Panini\"),\n" +
            "\tVALUES(\"Crispy Chicken\", 3,99, \"Chrispy Chicken\"),\n" +
            "\tVALUES(\"Soup\", 2.99, \"Soup\"),\n" +
            "\tVALUES(\"Chili\", 1.99 , \"Chilli\"),\n" +
            "\tVALUES(\"Muffins\", 1.25, \"Muffins\"),\n" +
            "\tVALUES(\"Donuts\", 0.99, \"Donuts\")\n" +
            ");";

    static final String MuffinCreate = "CREATE TABLE Muffins(\n" +
            "\tname Text PRIMARY KEY\n" +
            ")";

    static String MuffinItems = "INSERT INTO Muffins(\n" +
            "\tVALUES(\"Chocolate Chip\"),\n" +
            "\tVALUES(\"Wild Blueberry\"),\n" +
            "\tVALUES(\"Fruit Explosion\"),\n" +
            "\tVALUES(\"Carrots with Walnuts\")\n" +
            ");";
    static final String TeaCreate = "CREATE TABLE Tea(\n" +
            "\tname TEXT PRIMARY KEY\n" +
            ");";

    static final String TeaItems = "INSERT INTO Tea(\n" +
            "\tVALUES(\"Green Tea\"),\n" +
            "\tVALUES(\"Honey Lemon\"),\n" +
            "\tVALUES(\"Apple Cinnamon\"),\n" +
            "\tVALUES(\"Chai Tea\"),\n" +
            "\tVALUES(\"Peppermint\"),\n" +
            "\tVALUES(\"Chamomile\"),\n" +
            "\tVALUES(\"Orange Pekoe\"),\n" +
            "\tVALUES(\"Earl Grey\"),\n" +
            "\tVALUES(\"English Breakfast\"),\n" +
            "\tVALUES(\"Decafe Orange Pekoe\")\n" +
            ");";

    static final String DonutCreate = "CREATE TABLE Donuts(\n" +
            "\tname TEXT PRIMARY KEY\n" +
            ");";
    static final String DonutItems = "INSERT INTO Donuts(\n" +
            "\tVALUES(\"Apple Fritter\"),\n" +
            "\tVALUES(\"Chocolate Dip\"),\n" +
            "\tVALUES(\"Honey Dip\"),\n" +
            "\tVALUES(\"Chocolate Glazed\")\n" +
            ");";

    public ItemHelper(Context context){super(context, "Items", null,DATABASE_VERSION);};
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(ItemCreate);
        db.execSQL(DonutCreate);
        db.execSQL(TeaCreate);
        db.execSQL(MuffinCreate);

        db.execSQL(TeaItems);
        db.execSQL(DonutItems);
        db.execSQL(MuffinItems);
        db.execSQL(ItemsItems);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum){

    }

    public ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + ItemTable,null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String name = cursor.getString(0);
                float price = cursor.getFloat(1);
                String desc = cursor.getString(2);
                Item temp = new Item(name,desc,price);
                items.add(temp);
            }
        }
        return items;
    }

    public ArrayList<Item> getSubItems(String table){
        ArrayList<Item> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + table,null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String name = cursor.getString(0);
                float price = cursor.getFloat(1);
                Item temp = new Item(name,price);
                items.add(temp);
            }
        }
        return items;

    }

}
