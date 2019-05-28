package tests;

import Controller.ProductController;
import dao.ProductAccess;
import model.Product;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InsertProducttest {
    @Test
    public  void insertProducttest() throws Exception{
        ProductController productController = new ProductController();
        List<Product> products = new ArrayList<>();
        products=productController.viewDeals();
        int initSize = products.size();
        Product p = new Product("coffetable", "white", 4, 25.3f, "livingroom");
       productController.addProduct(p);
       products=productController.viewDeals();
        int afterSize = products.size();
        Assert.assertEquals(initSize+1, afterSize);

    }
}
