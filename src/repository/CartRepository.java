package repository;

import entity.Cart;
import entity.Product;

import java.util.HashMap;
import java.util.Map;

public class CartRepository {

    private final Map<String, Cart> cartMap;

    public CartRepository() {
        this.cartMap = new HashMap<>();
    }

    public Cart getCart(String username){
        return cartMap.get(username);
    }

    public void addToCart(String username, Product product, Integer quantity){

        Cart cart = cartMap.get(username);

        cart.getItems().put(product, quantity);
    }

    public void removeFromCart(String username, Product product){

        Cart cart = cartMap.get(username);

        cart.getItems().remove(product);
    }

    public boolean isFound(String username, Product product){
        return cartMap.get(username).getItems().containsKey(product);
    }

    public void assignCustomerToCart(String username){
        cartMap.put(username, new Cart());
    }
}
