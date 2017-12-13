package ca.uoit.csci4100u.mapsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shayne on 2017-12-10.
 */

public class ItemHelper extends SQLiteOpenHelper{
    static final int DATABASE_VERSION = 1;

    private ItemHelper ih;

    private String DROP_STATEMENT = "DROP TABLE menuItems";
    private String CREATE_STATEMENT = ItemCreate;

    static final String ItemTable = "menuItems";

    static final String ItemCreate = "CREATE TABLE menuItems(\n" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice TEXT NOT NULL,\n" +
            "\tdesc TEXT NOT NULL \n" +
            ");";
    static final String ItemsItems = "INSERT INTO menuItems(\n" +
            "\tVALUES (\"Coffee\", 1.99, \"Tim Hortons Original Blend Coffee: Small, Medium, and Large\"),\n" +
            "\tVALUES (\"Dark Roast Coffee\", 1.99, \"Tim Hortons Dark Roast Coffee: Small, Medium, and Large\"),\n" +
            "\tVALUES (\"Decaf Coffee\", 1.99, \"Tim Hortons Decaf Coffee: Small, Medium, and Large\"),\n" +
            "\tVALUES (\"Specialty Tea\", 1.99, \"Specialty Teas\"),\n" +
            "\tVALUES(\"Hot Breakfast Sandwich\", 2.99, \"Hot Breakfast Sandwich\"),\n" +
            "\tVALUES(\"Bagel B.E.L.T\", 3.99, \"BELT\"),\n" +
            "\tVALUES(\"HashBrown\", 1.99, \"HashBrown\"),\n" +
            "\tVALUES(\"Grilled Panini\", 3.99, \"Grilled Panini\"),\n" +
            "\tVALUES(\"Crispy Chicken\", 3,99, \"Crispy Chicken\"),\n" +
            "\tVALUES(\"Soup\", 2.99, \"Soup\"),\n" +
            "\tVALUES(\"Chili\", 1.99 , \"Chili\"),\n" +
            "\tVALUES(\"Muffins\", 1.25, \"Muffins\"),\n" +
            "\tVALUES(\"Donuts\", 0.99, \"Donuts\")\n" +
            ");";



    public ItemHelper(Context context){
        super(context, "menuItems", null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(ItemCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum){
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        db.execSQL(CREATE_STATEMENT);
    }

    public List<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"name","price","desc"};
        String where = "";
        String[] whereArgs = new String[] {};
        Cursor cursor = db.query(ItemTable,columns,where,whereArgs,"","","name");

        cursor.moveToFirst();
        do{
            if(!cursor.isAfterLast()){
                String name = cursor.getString(0);
                float price = cursor.getFloat(1);
                String desc = cursor.getString(2);
                Item temp = new Item(name,desc,price);
                items.add(temp);
            }
            cursor.moveToNext();
        }while(!cursor.isAfterLast());
        return items;
    }

    public Item createItem(String name, float price, String desc){
        Item item = new Item(name,desc,price);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newVals = new ContentValues();
        newVals.put("name","Coffee");
        newVals.put("price","2.4");
        newVals.put("desc","COFFEE");
        long i = db.insert(ItemTable,null,newVals);

        return item;
    }

    public List<Item> getSubItems(String table){
        List<Item> items = new ArrayList<>();
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
