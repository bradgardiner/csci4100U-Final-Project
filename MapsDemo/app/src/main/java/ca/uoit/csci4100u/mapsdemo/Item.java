package ca.uoit.csci4100u.mapsdemo;

/**
 * Created by shayne on 2017-12-10.
 */

public class Item {
    String name;
    float price;
    String description;

    public Item(String name, String description, float price){
        this.description = description;
        this.name = name;
        this.price = price;
    }
    public Item(String name, float price){
        this.name = name;
        this.price = price;
        description = "";
    }

    public String getName(){return name;}
    public String getDescription(){return description;}
    public float getPrice(){return price;}


}
