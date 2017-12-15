package ca.uoit.csci4100u.mapsdemo.sampledata;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shayne on 2017-12-10.
 */

public class Item implements Parcelable{
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

    public Item(Parcel in)
    {
        name = in.readString();
        price = in.readFloat();
        option = in.readInt();
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

    @Override
    public String toString(){
        return name + ", " + price + "," + description;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeString(name);
        out.writeFloat(price);
        out.writeString(description);
        out.writeInt(option);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>(){
        public Item createFromParcel(Parcel in){
            return new Item(in);
        }

        public Item[] newArray(int size){
            return new Item[size];
        }

    };

    public int describeContents(){return 0;}

}

