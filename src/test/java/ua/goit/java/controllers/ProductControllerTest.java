package ua.goit.java.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ua.goit.java.TestUtil;
import ua.goit.java.models.ProductBuilder;
import ua.goit.java.models.hibernate.Product;
import ua.goit.java.service.interfaces.ProductService;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Vadim Kozak
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    private static final Long ID = 1L;
    private static final String NAME = "name1";
    private static final String PRODUCER = "producer1";
    private static final String DESCRIPTION = "Lorem ipsum1";
    private static final Float SALARY = 100F;
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    private MockMvc mockMvc;

    @Mock
    private ProductService productServiceMock;

    @Before
    public void setUp() throws Exception {
        ProductController productController = new ProductController();
        productController.setProductService(productServiceMock);

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setViewResolvers(viewResolver())
                .build();
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

        return viewResolver;
    }

/*
    @Test
    public void add_NewTodoEntry_ShouldAddTodoEntryAndRenderViewTodoEntryView() throws Exception {
        Product added = new ProductBuilder()
                .id(ID)
                .name("name1")
                .producer("producer1")
                .salary(100F)
                .description("Lorem ipsum1")
                .build();

        when(productServiceMock.addProduct(isA(Product.class))).thenReturn(added);

        String expectedRedirectViewPath = TestUtil.createRedirectViewPath("/products");

        mockMvc.perform(post("/products/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(WebTestConstants.FORM_FIELD_NAME, "name1")
                .param(WebTestConstants.FORM_FIELD_PRODUCER, "producer1")
                .param(WebTestConstants.FORM_FIELD_SALARY, "100F")
                .param(WebTestConstants.FORM_FIELD_DESCRIPTION, "Lorem ipsum1")
        )
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name(expectedRedirectViewPath));

        ArgumentCaptor<Product> formObjectArgument = ArgumentCaptor.forClass(Product.class);
        verify(productServiceMock, times(1)).addProduct(formObjectArgument.capture());
        verifyNoMoreInteractions(productServiceMock);


    }
*/

    @Test
    public void deleteById_ProductEntryFound_ShouldDeleteTodoEntryAndRenderProductListView() throws Exception {
        Product deleted = new ProductBuilder()
                .id(ID)
                .name("name1")
                .producer("producer1")
                .salary(100F)
                .description("Lorem ipsum1")
                .build();

        when(productServiceMock.removeProduct(ID)).thenReturn(deleted);

        String expectedRedirectViewPath = TestUtil.createRedirectViewPath("/products");

        mockMvc.perform(get("/remove/{id}", ID))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name(expectedRedirectViewPath));

        verify(productServiceMock, times(1)).removeProduct(ID);
        verifyNoMoreInteractions(productServiceMock);
    }

    @Test
    public void findById_ProductEntryFound_ShouldAddTodoEntryToModelAndRenderViewProductEntryView() throws Exception {
        Product found = new ProductBuilder()
                .id(ID)
                .name("name1")
                .producer("producer1")
                .salary(100F)
                .description("Lorem ipsum1")
                .build();

        when(productServiceMock.getProductById(ID)).thenReturn(found);

        mockMvc.perform(get("/productdata/{id}", ID))
                .andExpect(status().isOk())
                .andExpect(view().name("productdata"))
                .andExpect(forwardedUrl("/WEB-INF/views/productdata.jsp"))
                .andExpect(model().attribute("product", hasProperty("id", is(ID))))
                .andExpect(model().attribute("product", hasProperty("name", is("name1"))))
                .andExpect(model().attribute("product", hasProperty("producer", is("producer1"))))
                .andExpect(model().attribute("product", hasProperty("salary", is(100F))))
                .andExpect(model().attribute("product", hasProperty("description", is("Lorem ipsum1"))));

        verify(productServiceMock, times(1)).getProductById(ID);
        verifyNoMoreInteractions(productServiceMock);
    }

    @Test
    public void showUpdateProductForm_ProductEntryFound_ShouldCreateFormObjectAndRenderUpdateProductView() throws Exception {
        Product updated = new ProductBuilder()
            .id(1L)
            .name("name1")
            .producer("producer1")
            .salary(100F)
            .description("Lorem ipsum1")
            .build();

        when(productServiceMock.getProductById(1L)).thenReturn(updated);
        when(productServiceMock.listProducts()).thenReturn(Collections.singletonList(updated));

        mockMvc.perform(get("/edit/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(forwardedUrl("/WEB-INF/views/products.jsp"))
                .andExpect(model().attribute("product", hasProperty("id", is(1L))))
                .andExpect(model().attribute("product", hasProperty("salary", is(100F))))
                .andExpect(model().attribute("product", hasProperty("producer", is("producer1"))))
                .andExpect(model().attribute("product", hasProperty("description", is("Lorem ipsum1"))))
                .andExpect(model().attribute("product", hasProperty("name", is("name1"))))
                .andExpect(model().attribute("listProducts", hasSize(1)))
                .andExpect(model().attribute("listProducts", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("name1")),
                                hasProperty("producer", is("producer1")),
                                hasProperty("salary", is(100F)),
                                hasProperty("description", is("Lorem ipsum1"))
                        )
                )));

        verify(productServiceMock, times(1)).getProductById(1L);
        verify(productServiceMock, times(1)).listProducts();
        verifyNoMoreInteractions(productServiceMock);
    }
    @Test
    public void findAll_ShouldAddProductEntriesToModelAndRenderProductListView() throws Exception {
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

        when(productServiceMock.listProducts()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(forwardedUrl("/WEB-INF/views/products.jsp"))
                .andExpect(model().attribute("product", is(new Product())))
                .andExpect(model().attribute("listProducts", hasSize(2)))
                .andExpect(model().attribute("listProducts", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("name1")),
                                hasProperty("producer", is("producer1")),
                                hasProperty("salary", is(100F)),
                                hasProperty("description", is("Lorem ipsum1"))
                        )
                )))
                .andExpect(model().attribute("listProducts", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("name2")),
                                hasProperty("producer", is("producer2")),
                                hasProperty("salary", is(200F)),
                                hasProperty("description", is("Lorem ipsum 2"))
                        )
                )));

        verify(productServiceMock, times(1)).listProducts();
        verifyNoMoreInteractions(productServiceMock);
    }
}