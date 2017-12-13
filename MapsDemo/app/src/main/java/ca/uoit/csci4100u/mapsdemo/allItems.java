package ca.uoit.csci4100u.mapsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class allItems extends AppCompatActivity {

    ItemHelper ih;
    private ListView lItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        lItem = (ListView)findViewById(R.id.ItemsList);

        ih = new ItemHelper(this);
        ih.createItem("coffee",2.3f,"COFFEE");
        updateItemList();
    }

    private void updateItemList(){
        ArrayList<Item> arr = new ArrayList<>();
        //Item i = new Item("n","2",9.2f);
        //arr.add(i);
        ItemArrayAdapter ad = new ItemArrayAdapter(this,ih.getItems());
        lItem.setAdapter(ad);
    }

    public void addItems(){
        ih.createItem("coffee",2.3f,"COFFEE");
    }
}
