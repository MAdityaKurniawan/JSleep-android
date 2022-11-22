package com.adityaJSleepFN.model;
/*
@author M. Aditya Kurniawan
@version JDK 11   
*/
import java.util.*;
public class Room extends Serializable
{
    public Price price;
    public String address;
    public int accountId;
    public int size;
    public Facility facility;
    public BedType bedType;
    public City city;
    public String name;
    public ArrayList<Date> booked;
}
