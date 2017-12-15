package ca.uoit.csci4100u.mapsdemo.sampledata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.uoit.csci4100u.mapsdemo.R;

/**
 * Created by shayne on 2017-12-14.
 */

public class subItemOptions extends ArrayAdapter<Item>{
    private List<Item> items;
    private Context context;

    public subItemOptions(Context context, List<Item> data){
        super(context, R.layout.sub_item,data);
        this.context = context;
        items = data;
    }

    @Override
    public View getView(int position, View reusable, ViewGroup parent){
        Item item = items.get(position);
        View p = reusable;

        if (reusable == null){
            LayoutInflater inf = LayoutInflater.from(getContext());
            p = inf.inflate(R.layout.item_layout, null);
        }
        if(item.getOption()){
            TextView txtName = (TextView)p.findViewById(R.id.txtName2);
            txtName.setText(item.getName());

            TextView txtPrice = (TextView)p.findViewById(R.id.txtPrice2);
            txtPrice.setText(item.getPrice()+"");
        }

        return p;
    }
}
