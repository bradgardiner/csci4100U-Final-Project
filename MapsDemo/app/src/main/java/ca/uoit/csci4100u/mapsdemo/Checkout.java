//Shayne Taylor
package ca.uoit.csci4100u.mapsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ca.uoit.csci4100u.mapsdemo.sampledata.Item;
import ca.uoit.csci4100u.mapsdemo.sampledata.ItemArrayAdapter;


public class Checkout extends AppCompatActivity {

    private List<Item> items;
    private ItemHelper ih;
    private ItemArrayAdapter iaa;
    private Bundle bun;
    private float price;
    private String order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Log.i("HEREK@K@@K","Starting Checkout");
        bun = new Bundle();
        ih = new ItemHelper(this);
        TextView txtItems = (TextView) findViewById(R.id.txtOrder);
        TextView txtPrice = (TextView) findViewById(R.id.txtPrice);
        Log.i("HEJQKMSN","making arraylist");
        //u
        items = ih.getOrderItems();
        Log.i("ArraySize",items.size() + "");
        for(int i =0;i <= items.size()-1;i++){
            Item item = items.get(i);
            Log.i("ArraySize",items.get(i).getName() + "");
            order += item.getName() + "\n";
            price += item.getPrice();
        }

        txtItems.setText(order);
        txtPrice.setText(price + "");
    }

    public void addOrder(View view){
        orderHelper oh = new orderHelper(this);
        oh.createOrder("user123",order,order,"location");
        finish();
        ih.dropTable("Order");
    }
}
