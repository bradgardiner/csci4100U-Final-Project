package ca.uoit.csci4100u.mapsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 2017-12-13.
 */

public class orderHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;

    private orderHelper oh;
    private Order order;

    private String DROP_STATEMENT = "DROP TABLE menuItems";
    private String CREATE_STATEMENT = OrderCreate;

    static final String OrderTable = "orderItems";

    static final String OrderCreate = "CREATE TABLE orderItems(\n" +
            "\tusername TEXT PRIMARY KEY,\n" +
            "\tprice TEXT NOT NULL,\n" +
            "\tproducts TEXT NOT NULL \n" +
            "\tlocation TEXT NOT NULL \n" +
            ");";
    /*static final String OrderItems = "INSERT INTO orderItems(\n" +
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
*/


    public orderHelper(Context context){
        super(context, "menuItems", null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(OrderCreate);
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

    public List<Order> getOrders(){
        ArrayList<Order> orders = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"username","price","products","location"};
        String where = "";
        String[] whereArgs = new String[] {};
        Cursor cursor = db.query(OrderTable,columns,where,whereArgs,"","","username");

        cursor.moveToFirst();
        do{
            if(!cursor.isAfterLast()){
                String name = cursor.getString(0);
                float price = cursor.getFloat(1);
                String product = cursor.getString(2);
                String location = cursor.getString(3);
                Order temp = new Order(name,location,product,price);
                orders.add(temp);
            }
            cursor.moveToNext();
        }while(!cursor.isAfterLast());
        return orders;
    }

    //call this when the user click the start order button, including the username
    public Order createOrder(String name){
        Order order = new Order(name);

        SQLiteDatabase db = this.getWritableDatabase();

        /*ContentValues newVals = new ContentValues();
        newVals.put("name","Coffee");
        newVals.put("price","2.4");
        newVals.put("desc","COFFEE");
        long i = db.insert(OrderTable,null,newVals);*/

        return order;
    }

    //use this every time someone adds something to the order, with the product name and price of the product
    public Order addToOrder(String product, int amount, float price)
    {
        //adds to the productnumbers string with product and amount, and then adds the price to the total price
        order.addToOrder(product, amount, price);

        return order;
    }
    //use this when user clicks finalize order
    public Order finalizeOrder()
    {

        //add order to database here

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newVals = new ContentValues();
        newVals.put("username",order.getName());
        newVals.put("price",order.getPrice());
        newVals.put("products",order.getOrder());
        newVals.put("location",order.getLocation());
        long i = db.insert(OrderTable,null,newVals);

        //in case you want the order for later
        return order;
    }

    /*public List<Order> getSubItems(String table){
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

    }*/



}
