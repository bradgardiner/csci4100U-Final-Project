package ca.uoit.csci4100u.mapsdemo;

/**
 * Created by Anthony on 2017-12-13.
 */

public class Order {
    String username;
    float price;
    String productNumbers;
    String location;

    public Order(String username, String location, String productNumbers, float price){
        this.username = username;
        this.price = price;
        this.productNumbers = productNumbers;
        this.location = location;
    }

    public Order(String username){
        this.username = username;
        this.price = 0;
        this.productNumbers = "";
        this.location = "";
    }

    public void addToOrder(String product, int amount, float price)
    {
        this.productNumbers += product + " " + amount + "\n";
        this.price += price;
    }

    public void computeOrder(){
        //This is where we will compile the order and send it to the db and set its status to open

    }

    public String getName(){return username;}
    public String getOrder(){return productNumbers;}
    public float getPrice(){return price;}
    public String getLocation() {return location;}


}