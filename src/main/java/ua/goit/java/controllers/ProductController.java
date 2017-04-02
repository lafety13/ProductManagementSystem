package ua.goit.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.java.models.hibernate.Product;
import ua.goit.java.service.interfaces.ProductService;

@Controller
public class ProductController {
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String product(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("listProducts", productService.listProducts());

        return "products";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product) {
        if(product.getId() == 0) {
            this.productService.addProduct(product);
        } else {
            this.productService.updateProduct(product);
        }

        return "redirect:/products";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") int id) {
        this.productService.removeProduct(id);

        return "redirect:/products";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("listProducts", this.productService.listProducts());

        return "products";
    }

    @RequestMapping("productdata/{id}")
    public String productData(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", this.productService.getProductById(id));

        return "productdata";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
