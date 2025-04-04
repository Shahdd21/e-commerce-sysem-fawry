package service;

import entity.Product;
import repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product, Integer quantity){
        productRepository.addProduct(product.getName().toLowerCase(),quantity);
    }

    public boolean isFound(Product product){
        return productRepository.isFound(product.getName().toLowerCase());
    }

    public Integer getQuantity(Product product){
        return productRepository.getQuantity(product.getName().toLowerCase());
    }

    public void removeProduct(Product product, Integer quantity){
        productRepository.removeProduct(product.getName().toLowerCase(), quantity);
    }
}
