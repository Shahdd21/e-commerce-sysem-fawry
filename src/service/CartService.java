package service;

import entity.Cart;
import entity.Product;
import repository.CartRepository;

import java.util.Map;

public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public void addToCart(String username, Product product, Integer quantity){

        if(quantity > productService.getQuantity(product)){
            System.out.println("Failed to add to cart. Unavailable quantity"); // i'll throw an exception here
        }

        else if(product.isExpired()){
            System.out.println("Failed to add to cart. Expired Product");
        }

        else if(productService.getQuantity(product) == 0){
            System.out.println("Failed to add to cart. Out of stock");
        }

        else{
            cartRepository.addToCart(username, product, quantity);
        }
    }

    public void removeFromCart(String username, Product product){
        cartRepository.removeFromCart(username, product);
    }

    public double getTotalPrice(String username){

        Map<Product, Integer> cartItems = cartRepository.getCart(username).getItems();

        double totalPrice = cartItems.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue() )
                .sum();

        return totalPrice;
    }

    public void assignCustomerToCart(String username){
        cartRepository.assignCustomerToCart(username);
    }

    public Cart getCart(String username){
        return cartRepository.getCart(username);
    }
}
