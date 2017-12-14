package ca.uoit.csci4100u.mapsdemo.sampledata;

/**
 * Created by shayne on 2017-12-14.
 */
public class subItem{
    private String name;
    private float price;
    private int option;
    public subItem(String name,float price, int option){
        this.name = name;
        this.price = price;
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public boolean isOption() {
        return (option == 1);
    }
}
