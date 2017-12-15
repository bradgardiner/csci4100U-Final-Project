package ca.uoit.csci4100u.mapsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Brad on 2017-12-14.
 */

public class OrderActivity extends AppCompatActivity {

    orderHelper oh;
    long orderID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_description_layout);

        oh = new orderHelper(this);
        List<Order> orders = oh.getOrders();

        int data = getIntent().getIntExtra("item", 0);

        Order order = null;
        order = orders.get(data);
        orderID = order.getId();

        TextView name = (TextView) findViewById(R.id.txtName2);
        TextView price = (TextView) findViewById(R.id.txtPrice2);
        TextView description = (TextView) findViewById(R.id.txtDesc);
        TextView location = (TextView) findViewById(R.id.txtLoc);

        name.setText(order.getName());
        price.setText(order.getPrice());
        description.setText(order.getOrder());
        location.setText(order.getLocation());


    }


    public void computeOrder(View view){


        oh.deleteOrder(orderID);
        finish();

    }

}
