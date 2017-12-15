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
        ih.createItem("coffee",2.3f,"Coffee Options inside");
        ih.createItem("Tea",2.3f,"Tea types found inside");
        ih.createItem("Donuts",2.3f,"Donut types found inside");
        ih.createItem("Sandwiches",2.3f,"Sandwiches types found inside");
        ih.createItem("Breakfast",2.3f,"Breakfast types found inside");
        ih.createItem("Cookies",2.3f,"Cookie types found inside");
        ih.createOtherItem("Coffee","Defcafe",2.3f,"0");
        ih.createOtherItem("Coffee","Defca",2.3f,"1");
    }
}
