package ua.goit.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.ProductDao;
import ua.goit.java.models.hibernate.Product;
import ua.goit.java.service.interfaces.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product model = getProductById(product.getId());
        productDao.save(product);
        return model;
    }

    @Override
    @Transactional
    public Product removeProduct(Long id) {
        Product deleted = getProductById(id);
        productDao.delete(id);

        return deleted;
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        return productDao.findOne(id);
    }

    @Override
    @Transactional
    public List<Product> listProducts() {
        return productDao.findAll();
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
