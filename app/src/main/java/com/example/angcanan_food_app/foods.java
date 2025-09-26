package com.example.angcanan_food_app;

public class foods {
    String name;
    int quantity;
    double price;
    int image;


    public foods(String name, double price, int image) {
        this.quantity = 0;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getImage() {
        return image;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
