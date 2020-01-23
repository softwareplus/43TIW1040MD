package com.example.belisolbokregistratieapp;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;


//Later deze in een Model folder steken
public class Client
{
    String id,name,phone,address,kantoorCode, land;
    String opmerking="";

    public Client(String id , String name, String phone, String address, String kantoorCode, String land, String opmerking ) {
        this.id = id;

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.kantoorCode = kantoorCode;
        this.land = land;
        this.opmerking = opmerking;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddress(){
        return address;
    }

    public String getKantoorCode() {return kantoorCode;}

    public String getLand() {return land;}

    public String getLandPath()
    {
        return land + ".png";
    }

    public String getOpmerking() {return opmerking;}


}
