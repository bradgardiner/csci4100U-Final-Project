package ca.uoit.csci4100u.mapsdemo;

/**
 * Created by Anthony on 2017-12-13.
 */

public class Order {
    String username;
    float price;
    String productNumbers;
    String location;

    public Order(String username, String location){
        this.username = username;
        this.price = 0;
        this.productNumbers = "";
        this.location = location;
    }

    public void addToOrder(String product, int amount, float price)
    {
        this.productNumbers += product + " " + amount + ", ";
        this.price += price;
    }

    public String getName(){return username;}
    public float getPrice(){return price;}


}