package ca.uoit.csci4100u.mapsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ca.uoit.csci4100u.mapsdemo.sampledata.Item;
import ca.uoit.csci4100u.mapsdemo.sampledata.ItemArrayAdapter;


public class Checkout extends AppCompatActivity {

    private List<Item> items;
    private ListView lItems;
    private ItemHelper ih;
    private ItemArrayAdapter iaa;
    private Bundle bun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Log.i("HEREK@K@@K","Starting Checkout");
        bun = new Bundle();
        ih = new ItemHelper(this);
        lItems = (ListView) findViewById(R.id.itemList);
        Log.i("HEJQKMSN","making arraylist");
        updateList();

    }

    public void updateList(){
        iaa = new ItemArrayAdapter(this,ih.getSubItems("Orders"));
        lItems.setAdapter(iaa);
    }
}
