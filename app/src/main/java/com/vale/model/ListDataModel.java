package com.vale.model;

import android.location.Address;

import androidx.lifecycle.ViewModel;

public class ListDataModel {

    private String name;
    private String Address;

    public ListDataModel(String name, String address) {
        this.name = name;
        Address = address;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
