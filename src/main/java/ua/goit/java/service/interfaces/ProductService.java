package ua.goit.java.service.interfaces;

import ua.goit.java.models.hibernate.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product book);

    public Product updateProduct(Product book);

    public Product removeProduct(Long id);

    public Product getProductById(Long id);

    public List<Product> listProducts();
}
