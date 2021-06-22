package com.example.am_6_zad_1;

public class ExampleItem {
    private String name;
    private String value;
    private String unit;

    public ExampleItem(String n, String v, String u){
        name = n;
        value = v;
        unit = u;
    }

    public String get_name(){
        return name;
    }

    public String get_value(){
        return value;
    }

    public String get_unit(){
        return unit;
    }

    public void set_value(String seted_val){
        value = seted_val;
    }
}
