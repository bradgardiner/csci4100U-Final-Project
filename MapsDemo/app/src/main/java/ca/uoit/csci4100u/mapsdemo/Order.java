package ca.uoit.csci4100u.mapsdemo;

/**
 * Created by Anthony on 2017-12-13.
 */

public class Order {
    String username;
    String price;
    String productNumbers;
    String location;
    private long id;

    public Order(String username,String price, String products, String location){
        this.username = username;
        this.price = price;
        this.productNumbers = products;
        this.location = location;
    }

    public Order(String username){
        this.username = username;
        this.price = "0";
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
    public String getPrice(){return price;}
    public String getLocation() {return location;}

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}