package com.example.belisolbokregistratieapp;

public class ExpeditionItem
{
    String Id;
    String OrderNumber;
    Integer ColliQty;
    String RegistrationDate;
    String content;
    String details;

    public ExpeditionItem(String id, String orderNumber,Integer colliQty,String registrationDate,String content,String details)
    {
        this.Id=id;
        this.OrderNumber = orderNumber;
        this.ColliQty = colliQty;
        this.RegistrationDate = registrationDate;
        this.content="CONTENT";
        this.details="DETAILS";
    }

    public String GetId()
    {
        return this.Id;
    }

    public String GetOrderNumber()
    {
        return this.OrderNumber;
    }

    public Integer GetColliQty()
    {
        return this.ColliQty;
    }

    public String GetRegistrationDate()
    {
        return this.RegistrationDate;
    }


}
