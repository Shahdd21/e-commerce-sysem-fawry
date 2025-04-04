package repository;

import entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private final Map<String, Integer> productsQuantity;


    public ProductRepository() {
        this.productsQuantity = new HashMap<>();
    }

    public void addProduct(String productName, Integer quantity){

        productsQuantity.put(productName,
                productsQuantity.getOrDefault(productName, 0)+quantity);
    }

    public boolean isFound(String productName){
        return productsQuantity.containsKey(productName);
    }

    public Integer getQuantity(String productName){
        return productsQuantity.get(productName);
    }

    public void removeProduct(String productName, Integer quantity){

        if(isFound(productName) && getQuantity(productName) - quantity > 0){
            productsQuantity.put(productName,
                    getQuantity(productName)-quantity);
        }

        else
         productsQuantity.put(productName,0);
    }
}
