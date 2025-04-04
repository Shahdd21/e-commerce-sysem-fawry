package entity;

public class ShippableProduct extends Product implements Shippable{
    private int weight;

    public ShippableProduct(){

    }

    public ShippableProduct(String name, double price, boolean isExpired, int weight) {
        super(name, price, isExpired);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable(){
        return true;
    }

}
