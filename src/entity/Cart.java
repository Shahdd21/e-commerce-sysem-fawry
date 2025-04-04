package entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> items;

    public Cart(){
        items = new HashMap<>();
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}
