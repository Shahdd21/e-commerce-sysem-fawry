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

        if(quantity < 0) throw new IllegalArgumentException("Invalid quantity value.");

        if(productService.getQuantity(product) == 0){
            System.out.println("Failed to add "+ product.getName() +
                    " to cart. Out of stock");

            //throw new OutOfStockException("Out of stock exception");
            // i see it more convenient not to throw exception and continue adding
            //other items and pay for what can be added
        }

        else if(quantity > productService.getQuantity(product)){
            System.out.println("Failed to add "+ product.getName() +
                    " to cart. Quantity provided is more than the available");
        }

        else if(product.isExpired()){
            System.out.println("Failed to add "+ product.getName() +
                    " to cart. Expired Product");

            //throw new ExpiredProductException("Out of stock exception");
            // i see it more convenient not to throw exception and continue adding
            //other items and pay for what can be added
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
