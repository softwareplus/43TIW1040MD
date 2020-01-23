package com.example.belisolbokregistratieapp;

public class Order
{
    private String orderNummer;
    private String kantoorNaam;
    private String referentie;
    private String kantoorCode;


    public Order(String orderNummer, String kantoorNaam, String referentie,String kantoorCode)
    {
        this.orderNummer = orderNummer;
        this.kantoorNaam = kantoorNaam;
        this.referentie = referentie;
        this.kantoorCode = kantoorCode;
    }

    public String getOrderNummer() { return orderNummer;}
    public void setOrderNummer(String orderNummer)
    {
        this.orderNummer = orderNummer;
    }

    public String getKantoorNaam() { return kantoorNaam;}
    public void setKantoorNaam(String kantoorNaam)
    {
        this.kantoorNaam = kantoorNaam;
    }

    public String getReferentie() {return referentie;}
    public void setReferentie(String referentie)
    {
        this.referentie = referentie;
    }

    public String getKantoorCode() {return kantoorCode;}
    public void setKantoorCode(String kantoorCode)
    {
        this.kantoorCode = kantoorCode;
    }



}
