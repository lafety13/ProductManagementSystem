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
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.save(product);
    }

    @Override
    @Transactional
    public void removeProduct(long id) {
        productDao.delete(id);
    }

    @Override
    @Transactional
    public Product getProductById(long id) {
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
