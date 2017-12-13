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
            "\tprice REAL NOT NULL,\n" +
            "\tdesc TEXT NOT NULL \n" +
            ");";

    static final String DonutCreate = "CREATE TABLE Donuts(\n" +
            "\tname TEXT PRIMARY KEY\n" +
            ");";

    static final String TeaCreate = "CREATE TABLE Tea(\n" +
            "\tname TEXT PRIMARY KEY\n" +
            ");";

    static final String MuffinCreate = "CREATE TABLE Muffins(\n" +
            "\tname Text PRIMARY KEY\n" +
            ")";

    static final String CookieCreate = "CREATE TABLE Cookies(\n "+
            "name TEXT PRIMARY KEY\n)";

    static final String BreakFastCreate = "CREATE TABLE Breakfast(\n "+
            "name TEXT PRIMARY KEY\n)";
    static final String SandwichCreate= "CREATE TABLE Sandwich(\n "+
            "name TEXT PRIMARY KEY\n)";



    public ItemHelper(Context context){
        super(context, "menuItems", null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(ItemCreate);
        db.execSQL(DonutCreate);
        db.execSQL(TeaCreate);
        db.execSQL(MuffinCreate);
        db.execSQL(CookieCreate);
        db.execSQL(BreakFastCreate);
        db.execSQL(SandwichCreate);
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
        newVals.put("name",name);
        newVals.put("price",price);
        newVals.put("desc",desc);
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

public Item createOtherItem(String tableName, String name){
    Item item = new Item(name,2.4f);

    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues newVals = new ContentValues();
    newVals.put("name",name);
    long i = db.insert(tableName,null,newVals);

    return item;
}

}
