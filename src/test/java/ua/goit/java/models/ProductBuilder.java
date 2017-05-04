package ua.goit.java.models;

import ua.goit.java.models.hibernate.Product;

/**
 * @author Vadim Kozak
 */
public class ProductBuilder {
    private Product product;

    public ProductBuilder() {
        this.product = new Product();
    }

    public ProductBuilder id(long id) {
        product.setId(id);
        return this;
    }

    public ProductBuilder name(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder salary(float salary) {
        product.setSalary(salary);
        return this;
    }

    public ProductBuilder producer(String producer) {
        product.setProducer(producer);
        return this;
    }

    public ProductBuilder description(String description) {
        product.setDescription(description);
        return this;
    }

    public Product build() {
        return product;
    }
}
