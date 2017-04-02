package ua.goit.java.dao;

import org.springframework.data.repository.CrudRepository;
import ua.goit.java.models.hibernate.Product;

import java.util.List;

public interface ProductDao extends CrudRepository<Product, Long> {
    List<Product> findAll();
    <S extends Product> S save(S s);
/*    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET c.address = :address WHERE c.id = :companyId")
    public void updateProduct(Product book);

    public void removeProduct(long id);

    public Product getProductById(long id);*/

}
