package ca.uoit.csci4100u.mapsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class allItems extends AppCompatActivity {

    ItemHelper ih;
    private ListView lItem;
    private ArrayList<Item> items;
    private ArrayList<Item> Muffins;
    private ArrayList<Item> Donuts;
    private ArrayList<Item> Teas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        lItem = (ListView)findViewById(R.id.ItemsList);

        ih = new ItemHelper(this);
        items = ih.getItems();
    }

    private void updateItemList(){
        ItemArrayAdapter ad = new ItemArrayAdapter(this,items);
        lItem.setAdapter(ad);
    }
}
