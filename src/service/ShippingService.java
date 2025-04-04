package service;

import entity.ShippableProduct;

import java.util.Map;

public class ShippingService {

    private Map<ShippableProduct, Integer> shippedProducts;

    public ShippingService(){

    }

    public void ship(Map<ShippableProduct, Integer> shippableProducts){
        this.shippedProducts = shippableProducts;

        System.out.println("** Shipped Items **");

        double totalWeight = 0;
        for(Map.Entry<ShippableProduct, Integer> entry : shippableProducts.entrySet()){

            double weight = entry.getKey().getWeight()* entry.getValue();
            totalWeight += weight;

            System.out.print(entry.getValue() +"x  "+ entry.getKey().getName()+
                    "\t");

            System.out.println(weight >= 1000 ? (weight/1000) +" kg" : weight +" g");
        }
        System.out.println("Total Weight" +"\t"+ (totalWeight >= 1000 ? (totalWeight/1000) +" kg" : totalWeight + " g"));
        System.out.println("------------------------------------");
    }
}
