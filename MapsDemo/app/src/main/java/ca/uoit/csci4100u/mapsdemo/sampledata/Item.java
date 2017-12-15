package ca.uoit.csci4100u.mapsdemo.sampledata;

/**
 * Created by shayne on 2017-12-10.
 */

public class Item {
    String name;
    float price;
    String description;
    int option;

    public Item(String name, String description, float price){
        this.description = description;
        this.name = name;
        this.price = price;
        option = 0;
    }
    public Item(String name, float price,int option){
        this.name = name;
        this.price = price;
        description = "";
        this.option = option;
    }

    public String getName(){return name;}
    public String getDescription(){
        if(description == ""){
            return "";
        }else {
            return description;
        }
    }
    public float getPrice(){return price;}
    public boolean getOption(){return (option == 1);}

}

