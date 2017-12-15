package ca.uoit.csci4100u.mapsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.uoit.csci4100u.mapsdemo.sampledata.ItemArrayAdapter;

/**
 * Created by Brad on 2017-12-14.
 */

public class AllOrders extends AppCompatActivity implements AdapterView.OnItemClickListener{


    orderHelper oh;
    private ListView lOrder;
    OrderArrayAdapter ad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runner_interface);
        lOrder = (ListView)findViewById(R.id.OrdersList);

        oh = new orderHelper(this);
        addOrder();
        updateOrderList();
        lOrder.setOnItemClickListener(this);
    }

    private void updateOrderList(){
        ad = new OrderArrayAdapter(this,oh.getOrders());
        lOrder.setAdapter(ad);
    }

    @Override
    public void onItemClick(AdapterView aView, View source, int pos, long id){
        Intent i = new Intent(this,SubItem.class);
        i.putExtra("item",pos);
        startActivity(i);
    }

    public void addOrder() {
        oh.createOrder("Brad");
        oh.addToOrder("Green Tea", 1, 2.9f);
        oh.finalizeOrder();
    }


}
