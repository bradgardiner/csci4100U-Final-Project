package ca.uoit.csci4100u.mapsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ca.uoit.csci4100u.mapsdemo.sampledata.Item;
import ca.uoit.csci4100u.mapsdemo.sampledata.ItemArrayAdapter;
import ca.uoit.csci4100u.mapsdemo.sampledata.subItem;
import ca.uoit.csci4100u.mapsdemo.sampledata.subItemArrayAdapter;
import ca.uoit.csci4100u.mapsdemo.sampledata.subItemOptions;

public class SubItem extends AppCompatActivity {

    ItemArrayAdapter iaa;
    subItemOptions sio;
    ItemHelper ih;
    ListView list;
    ListView optionL;
    Item Table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_item);

        Intent n = getIntent();
        int pos = n.getIntExtra("item",0);
        ih = new ItemHelper(this);
        list = (ListView) findViewById(R.id.itemList);
        optionL = (ListView)findViewById(R.id.optionsList);
        List<Item> items = ih.getItems();
        Table = items.get(pos);

        TextView table = (TextView)findViewById(R.id.txtTable);
        table.setText(Table.getName());

        updateItemList();
    }

    private void updateItemList(){
        List<Item> items = ih.getSubItems(Table.getName());
        ArrayList<Item> main = new ArrayList<>();
        ArrayList<Item> sub = new ArrayList<>();
        for (int i =0; i <= items.size()-1; i++){
            if (items.get(i).getOption()){
                sub.add(items.get(i));
            }else{
                main.add(items.get(i));
            }
        }
        iaa = new ItemArrayAdapter(this,main);
        sio = new subItemOptions(this,sub);
        list.setAdapter(iaa);
        optionL.setAdapter(sio);
    }

    public void createSandwichVals(){

    }
    public void createBreakfastVals(){

    }
}
