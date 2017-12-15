package ca.uoit.csci4100u.mapsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ca.uoit.csci4100u.mapsdemo.sampledata.Item;
import ca.uoit.csci4100u.mapsdemo.sampledata.ItemArrayAdapter;


public class Checkout extends AppCompatActivity {

    private ArrayList<Item> items;
    private ListView lItems;
    private ItemHelper ih;
    private ItemArrayAdapter iaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        lItems = (ListView) findViewById(R.id.itemList);
        items = savedInstanceState.getParcelableArrayList("order");

    }

    public void updateList(){
        iaa = new ItemArrayAdapter(this,items);
        lItems.setAdapter(iaa);
    }
}
