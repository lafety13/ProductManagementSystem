package ua.goit.java.models.hibernate;

import org.junit.Test;
import ua.goit.java.models.ProductBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ProductTest {

    @Test
    public void build_MandatoryInformationGiven_ShouldCreateNewObjectAndSetMandatoryInformation() {
        Product product = new ProductBuilder()
                .name("name")
                .producer("producer")
                .salary(100F)
                .build();

        assertNull(product.getId());
        assertNull(product.getDescription());
        assertEquals("name", product.getName());
        assertEquals("producer", product.getProducer());
        assertEquals(100F, product.getSalary(), product.getSalary());
    }

    @Test
    public void build_AllInformationGiven_ShouldCreateNewObjectAndSetAllInformation() {
        Product product = new ProductBuilder()
                .name("name")
                .producer("producer")
                .salary(100F)
                .description("description")
                .build();

        assertNull(product.getId());
        assertEquals("name", product.getName());
        assertEquals("producer", product.getProducer());
        assertEquals("description", product.getDescription());
        assertEquals(100F, product.getSalary(), product.getSalary());

    }
}