package ca.uoit.csci4100u.mapsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ca.uoit.csci4100u.mapsdemo.sampledata.Item;
import ca.uoit.csci4100u.mapsdemo.sampledata.ItemArrayAdapter;

public class allItems extends AppCompatActivity
                        implements AdapterView.OnItemClickListener{

    ItemHelper ih;
    private ListView lItem;
    ItemArrayAdapter ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        lItem = (ListView)findViewById(R.id.ItemsList);

        ih = new ItemHelper(this);
        addItems();
        updateItemList();
        lItem.setOnItemClickListener(this);
    }

    private void updateItemList(){
        ad = new ItemArrayAdapter(this,ih.getItems());
        lItem.setAdapter(ad);
    }

    @Override
    public void onItemClick(AdapterView aView, View source, int pos, long id){
        Intent i = new Intent(this,SubItem.class);
        i.putExtra("item",pos);
        startActivity(i);
    }

    public void addItems(){
        ih.createItem("Coffee",2.3f,"Coffee Options inside");
        ih.createItem("Tea",2.3f,"Tea types found inside");
        ih.createItem("Donuts",2.3f,"Donut types found inside");
        ih.createItem("Sandwich",2.3f,"Sandwiches types found inside");
        ih.createItem("Breakfast",2.3f,"Breakfast types found inside");
        ih.createItem("Cookies",2.3f,"Cookie types found inside");
        ih.createOtherItem("Coffee","Defcafe",2.3f,0);
        ih.createOtherItem("Coffee","Milk",0f,1);
        ih.createOtherItem("Coffee","Cream",0f,1);
        ih.createOtherItem("Coffee","Sugar",0f,1);
        ih.createOtherItem("Coffee","Sweatener",0f,1);
        ih.createOtherItem("Coffee","Dark Roast",2.3f,0);
        ih.createOtherItem("Tea","Green",2.9f,0);
        ih.createOtherItem("Tea","Orange Pekoe",2.9f,0);
        ih.createOtherItem("Tea","Milk",0f,1);
        ih.createOtherItem("Tea","Cream",0f,1);
        ih.createOtherItem("Tea","Sugar",0f,1);
        ih.createOtherItem("Tea","Sweatener",0f,1);
        ih.createOtherItem("Donut","Chocolate Dip",1.00f,0);
        ih.createOtherItem("Donut","Vanilla Dip",1.00f,0);
        ih.createOtherItem("Sandwich","Sesame Seed Bagel",1.50f,0);
        ih.createOtherItem("Sandwich","Everything Bagel",1.50f,0);
        ih.createOtherItem("Sandwich","Cream Cheese",0.25f,1);
        ih.createOtherItem("Sandwich","Peanut Butter",0.25f,1);
        ih.createOtherItem("Breakfast","HashBrown",1.50f,0);
        ih.createOtherItem("Cookie","Chocolate Chip",0.75f,0);
        ih.createOtherItem("Cookie","Oatmeal Rasin",0.75f,0);

        //ih.createOtherItem("","",,);
    }
}
