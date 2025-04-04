package entity;

public class Product {

    private String name;
    private double price;
    private boolean isExpired;

    public Product(){

    }

    public Product(String name, double price, boolean isExpired) {
        this.name = name;
        this.price = price;
        this.isExpired = isExpired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public boolean isShippable(){
        return false;
    }

}
