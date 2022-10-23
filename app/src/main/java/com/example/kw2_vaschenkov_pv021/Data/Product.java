package com.example.kw2_vaschenkov_pv021.Data;

public class Product {
    public int id;
    public String name;
    public double price;
    public int weight;

    public Product(int id, String name, double price, int weight)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
    @Override
    public String toString()
    {
        return "" + this.id + ": " + this.name +
                ": " + this.price + ": " + this.weight;
    }
}
