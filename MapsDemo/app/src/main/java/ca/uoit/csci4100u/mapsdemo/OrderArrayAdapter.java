package ca.uoit.csci4100u.mapsdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.uoit.csci4100u.mapsdemo.sampledata.Item;

/**
 * Created by Anthony on 2017-12-15.
 */

public class OrderArrayAdapter extends ArrayAdapter<Item> {
    private List<Order> orders;
    private Context context;

    public OrderArrayAdapter(Context context, List<Order> data){
        super(context, R.layout.order_description_layout, data);
        this.context = context;
        orders = data;
    }

    @Override
    public View getView(int position, View reusable, ViewGroup parent){
        Order order = orders.get(position);
        View p = reusable;

        if (reusable == null){
            LayoutInflater inf = LayoutInflater.from(getContext());
            p = inf.inflate(R.layout.item_layout, null);
        }
        TextView txtName = (TextView)p.findViewById(R.id.txtName2);
        txtName.setText(order.getName());

        TextView txtPrice = (TextView)p.findViewById(R.id.txtPrice2);
        txtPrice.setText(order.getPrice()+"");

        TextView txtDesc = (TextView)p.findViewById(R.id.txtDesc);
        txtDesc.setText(order.getOrder());

        TextView txtLocation = (TextView)p.findViewById(R.id.txtLoc);
        txtDesc.setText(order.getLocation());


        return p;
    }
}
