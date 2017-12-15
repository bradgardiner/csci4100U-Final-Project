package ca.uoit.csci4100u.mapsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ca.uoit.csci4100u.mapsdemo.sampledata.Item;
import ca.uoit.csci4100u.mapsdemo.sampledata.subItem;

/**
 * Created by shayne on 2017-12-10.
 */

public class ItemHelper extends SQLiteOpenHelper{
    static final int DATABASE_VERSION = 10;


    private String DROP_STATEMENT = "DROP TABLE menuItems\n" +
                                    "DROP TABLE Coffee\n" +
                                    "DROP TABLE Tea\n" +
                                    "DROP TABLE Muffin\n" +
                                    "DROP TABLE Sandwich\n" +
                                    "DROP TABLE Breakfast\n" +
                                    "DROP TABLE Cookie\n" +
                                    "DROP TABLE Orders";

    static final String ItemTable = "menuItems";
    static final String CoffeeTable = "Coffee";

    static final String CoffeeCreate = "CREATE TABLE Coffee(\n" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\toption INTEGER NOT NULL \n" +
            "\n" +
            ");";
    static final String OrderCreate = "CREATE TABLE Orders(\n" +
            "\tname TEXT NOT NULL,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\toption INTEGER NOT NULL,\n" +
            "\t_id INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "\n" +
            ");";

    static final String ItemCreate = "CREATE TABLE menuItems(\n" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\tdesc TEXT NOT NULL \n" +
            "\n" +
            ");";

    static final String DonutCreate = "CREATE TABLE Donut(" +
        "\tname TEXT PRIMARY KEY,\n" +
        "\tprice REAL NOT NULL,\n" +
        "\toption INTEGER NOT NULL \n" +
        "\n" +
        ");";

    static final String TeaCreate =  "CREATE TABLE Tea(" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\toption INTEGER NOT NULL \n" +
            "\n" +
            ");";

    static final String MuffinCreate =  "CREATE TABLE Muffin(" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\toption INTEGER NOT NULL \n" +
            "\n" +
            ");";

    static final String CookieCreate =  "CREATE TABLE Cookie(" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\toption INTEGER NOT NULL \n" +
            "\n" +
            ");";

    static final String BreakFastCreate =  "CREATE TABLE Breakfast(" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\toption INTEGER NOT NULL \n" +
            "\n" +
            ");";
    static final String SandwichCreate=  "CREATE TABLE Sandwich(" +
            "\tname TEXT PRIMARY KEY,\n" +
            "\tprice REAL NOT NULL,\n" +
            "\toption INTEGER NOT NULL \n" +
            "\n" +
            ");";




    public ItemHelper(Context context){
        super(context, "menuItems", null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(ItemCreate);
        Log.i("ItemCreate","eeeeeeee");
        db.execSQL(CoffeeCreate);
        db.execSQL(TeaCreate);
        db.execSQL(SandwichCreate);
        db.execSQL(MuffinCreate);
        db.execSQL(CookieCreate);
        db.execSQL(BreakFastCreate);
        db.execSQL(DonutCreate);
        db.execSQL(OrderCreate);
        Log.i("Coffee","wesdfs");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum){
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        onCreate(db);
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
        ArrayList<Item> items = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"name","price","option"};
        String where = "";
        String[] whereArgs = new String[] {};
        Cursor cursor = db.query(table,columns,where,whereArgs,"","","name");

        cursor.moveToFirst();
        do{
            if(!cursor.isAfterLast()){
                String name = cursor.getString(0);
                float price = Float.parseFloat(cursor.getString(1));
                int option = cursor.getInt(2);
                Item temp = new Item(name,price,option);
                items.add(temp);
            }
            cursor.moveToNext();
        }while(!cursor.isAfterLast());
        return items;

    }

    public Item createOtherItem(String tableName, String name,float price, int options){
        Item item = new Item(name,price,options);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newVals = new ContentValues();
        newVals.put("name",name);
        newVals.put("price",price);
        newVals.put("option",options);
        long i = db.insert(tableName,null,newVals);

        return item;
    }

    public List<Item> addOrderItem(Item item){
        int option  =0;
        if (item.getOption()){
            option = 1;
        }
        createOtherItem("Orders",item.getName(),item.getPrice(),option);
        return getSubItems("Orders");
    }
}
