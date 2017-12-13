package ca.uoit.csci4100u.mapsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SubItem extends AppCompatActivity {

    ItemArrayAdapter iaa;
    ItemHelper ih;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_item);

        Intent n = getIntent();
        int pos = n.getIntExtra("item",0);
        ih = new ItemHelper(this);
        list = (ListView) findViewById(R.id.listView);
        List<Item> items = ih.getItems();
        Item Table = items.get(pos);

        TextView table = (TextView)findViewById(R.id.txtTable);
        table.setText(Table.getName());

    }

    public void createSandwichVals(){

    }
    public void createBreakfastVals(){

    }
}
