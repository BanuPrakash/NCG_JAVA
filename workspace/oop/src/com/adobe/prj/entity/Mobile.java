package com.adobe.prj.entity;

// Specialization, inheritance
public class Mobile extends  Product{
    private String connectivity;

    public Mobile() {
    }

    public Mobile(int id, String name, double price, String connectivity) {
        super(id, name, price);
        this.connectivity = connectivity;
    }

    public String getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(String connectivity) {
        this.connectivity = connectivity;
    }

    @Override
    public boolean isExpensive() {
        if("3G".equals(getConnectivity()) &&   getPrice() > 5000) {
            return  true;
        } else if(getConnectivity().equals("4G") && getPrice() > 12000) {
            return  true;
        } else  if("5G".equals(getConnectivity()) && getPrice() > 25000){
            return  true;
        }
        return  false;
    }
}
