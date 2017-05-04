package ua.goit.java.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.goit.java.dao.ProductDao;
import ua.goit.java.models.ProductBuilder;
import ua.goit.java.models.hibernate.Product;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_UPDATED = "updatedDescription";
    public static final String TITLE = "title";
    public static final String TITLE_UPDATED = "updatedTitle";

    private ProductServiceImpl service;

    private ProductDao repositoryMock;

    @Before
    public void setUp() {
        repositoryMock = mock(ProductDao.class);
        service = new ProductServiceImpl();
        service.setProductDao(repositoryMock);
    }

    @Test
    public void add_NewProductEntry_ShouldSaveProducEntry() {
        Product product = new ProductBuilder()
                .name("name1")
                .producer("producer1")
                .salary(100F)
                .description("Lorem ipsum1")
                .build();

        service.addProduct(product);

        ArgumentCaptor<Product> toDoArgument = ArgumentCaptor.forClass(Product.class);
        verify(repositoryMock, times(1)).save(toDoArgument.capture());
        verifyNoMoreInteractions(repositoryMock);

        Product model = toDoArgument.getValue();

        assertNull(model.getId());
        assertThat(model.getName(), is(product.getName()));
        assertThat(model.getProducer(), is(product.getProducer()));
        assertThat(model.getSalary(), is(product.getSalary()));
        assertThat(model.getDescription(), is(product.getDescription()));
    }

    @Test
    public void deleteById_ProductEntryFound_ShouldDeleteProductEntryAndReturnIt() {
        Product product = new ProductBuilder()
                .id(ID)
                .name("name1")
                .producer("producer1")
                .salary(100F)
                .description("Lorem ipsum1")
                .build();

        when(repositoryMock.findOne(ID)).thenReturn(product);

        Product actual = service.removeProduct(ID);

        verify(repositoryMock, times(1)).findOne(ID);
        verify(repositoryMock, times(1)).delete(1L);
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(product));
    }

    @Test
    public void findAll_ShouldReturnListOfProductEntries() {
        List<Product> models = new ArrayList<>();
        when(repositoryMock.findAll()).thenReturn(models);

        List<Product> actual = service.listProducts();

        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(models));
    }

    @Test
    public void findById_ProductEntryFound_ShouldReturnFoundProductEntry() {
        Product product = new ProductBuilder()
                .id(ID)
                .name("name1")
                .producer("producer1")
                .salary(100F)
                .description("Lorem ipsum1")
                .build();

        when(repositoryMock.findOne(ID)).thenReturn(product);

        Product actual = service.getProductById(ID);

        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(product));
    }

    @Test
    public void update_ProductEntryFound_ShouldUpdateProductEntry() {
        Product first = new ProductBuilder()
                .id(1L)
                .name("name1")
                .producer("producer1")
                .salary(100F)
                .description("Lorem ipsum1")
                .build();

        Product second = new ProductBuilder()
                .id(2L)
                .name("name2")
                .producer("producer2")
                .salary(200F)
                .description("Lorem ipsum 2")
                .build();

        when(repositoryMock.findOne(first.getId())).thenReturn(second);

        Product actual = service.updateProduct(first);

        //verify(repositoryMock, times(1)).findOne(first.getId());
       // verifyNoMoreInteractions(repositoryMock);

//        assertThat(second.getId(), is(first.getId()));
//        assertThat(second.getName(), is(first.getName()));
//        assertThat(second.getProducer(), is(first.getProducer()));
//        assertThat(second.getSalary(), is(first.getSalary()));
//        assertThat(second.getDescription(), is(first.getDescription()));
    }

}