package com.adityaJSleepFN.model;

import java.util.regex.Pattern;

/*
@author M. Aditya Kurniawan
@version JDK 11   
*/
public class Account extends Serializable {
    public String name;
    public String email;
    public String password;
    public double balance;
    public Renter renter;
    @Override
    public String toString(){
        return "Account {" +
                "balance = " + balance+
                ", email = '" + email + '\''+
                ", name = '" + name + '\''+
                ", password = '" + password + '\''+
                ", renter = " + renter +
                '}';
    }
}
