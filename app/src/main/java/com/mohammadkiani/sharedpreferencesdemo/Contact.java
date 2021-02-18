package com.mohammadkiani.sharedpreferencesdemo;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private int number;
    private String occupation;

    public Contact(String name, int number, String occupation) {
        this.name = name;
        this.number = number;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getOccupation() {
        return occupation;
    }
}
