package ua.goit.java.service.interfaces;

import ua.goit.java.models.hibernate.Product;

import java.util.List;

public interface ProductService {
    public void addProduct(Product book);

    public void updateProduct(Product book);

    public void removeProduct(long id);

    public Product getProductById(long id);

    public List<Product> listProducts();
}
