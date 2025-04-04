import entity.*;
import exception.EmptyCartException;
import exception.ExceptionHandler;
import exception.ExpiredProductException;
import repository.CartRepository;
import repository.ProductRepository;
import repository.WalletRepository;
import service.CartService;
import service.ProductService;
import service.WalletService;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static ProductService productService = new ProductService(new ProductRepository());
    public static WalletService walletService = new WalletService(new WalletRepository());
    public static CartService cartService = new CartService(new CartRepository(), productService);

    public static void main(String[] args) {

        try{
            run();
        } catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }

    public static void run(){
        //system initialization
        Product milk = new Product("Juhayna", 50, true);
        Product cheese = new ShippableProduct("Domty", 30, false, 200);
        Product lgTv = new ShippableProduct("LG", 3000, false, 1000);
        Product mobile = new Product("Samsung", 5000, false);
        Product laptop = new Product("Lenovo", 15000, false);
        Product lotteryTicket = new Product("lottery", 100, false);

        productService.addProduct(cheese, 20);
        productService.addProduct(lgTv, 10);
        productService.addProduct(mobile, 5);
        productService.addProduct(laptop, 10);
        productService.addProduct(milk, 10);
        productService.addProduct(lotteryTicket, 0);

        //customer input
        Customer shahd = new Customer("Shahd Mahmoud", "shahd",
                "0135648970", "shahd@gmail.com", "Alexandria");
        walletService.assignWalletToUser(shahd.getUsername());
        walletService.fund(shahd.getUsername(), 10000);
        cartService.assignCustomerToCart(shahd.getUsername());

        //cartService.addToCart(shahd.getUsername(), cheese, 20); // more than available
        //cartService.addToCart(shahd.getUsername(), laptop, 1); // insufficient balance
        cartService.addToCart(shahd.getUsername(), milk, 1); // expired
        cartService.addToCart(shahd.getUsername(), mobile, 1);
        cartService.addToCart(shahd.getUsername(), lotteryTicket, 1); // out of stock
        cartService.addToCart(shahd.getUsername(), lgTv, 1);


        checkout(shahd);
    }


    public static void checkout(Customer customer){

        String username = customer.getUsername();
        Cart cart = cartService.getCart(username);

        if(cart.getItems().isEmpty()){
            throw new EmptyCartException("Failed Checkout. Empty Cart");
        }

        System.out.println("\n** Checkout Receipt **");

        Map<ShippableProduct, Integer> shippableProducts = new HashMap<>();

        for(Map.Entry<Product, Integer> entry : cart.getItems().entrySet()){

            if(entry.getKey().isShippable()) shippableProducts.put((ShippableProduct) entry.getKey(), entry.getValue());

            System.out.println(entry.getValue() +"x  "+ entry.getKey().getName()+ "\t"+
                    entry.getKey().getPrice()* entry.getValue());

        }

        System.out.println("-------------------------------");

        if(!shippableProducts.isEmpty()){
            System.out.println("** Shipped Items **");

            double totalWeight = 0;
            for(Map.Entry<ShippableProduct, Integer> entry : shippableProducts.entrySet()){

                double weight = entry.getKey().getWeight()* entry.getValue();
                totalWeight += weight;

                System.out.print(entry.getValue() +"x  "+ entry.getKey().getName()+
                        "\t");

                System.out.println(weight >= 1000 ? (weight/1000) +" kg" : weight +" g");
            }
            System.out.println("Total Weight" +"\t"+ (totalWeight/1000) +" kg");
        }

        System.out.println("------------------------------------");

        double subTotal = cartService.getTotalPrice(customer.getUsername());
        double shipping = subTotal*0.1;
        double total = subTotal+shipping;

        System.out.println("Subtotal"+"\t" + subTotal);
        System.out.println("Shipping"+"\t" + shipping);
        System.out.println("Total"+ "\t" + total);

        System.out.println("---------------------------------------");

        walletService.withdraw(username, total);
        System.out.println("Current Balance"+ "\t" + walletService.getBalance(username));
    }
}