package Controller;

import dao.ProductAccess;
import model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductController {

    public List<Product> viewDeals(){
         List<Product> products = new ArrayList<>();
        ProductAccess productAccess = new ProductAccess();
        productAccess.selectProduct(products);
        return products;
    }



    public List<Product> filterByName(){
        List<Product> products = new ArrayList<>();
        ProductAccess productAccess = new ProductAccess();
        productAccess.selectProduct(products);
        return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());


    }
    public List<Product> filterByPrice(){
        List<Product> products = new ArrayList<>();
        ProductAccess productAccess = new ProductAccess();
        productAccess.selectProduct(products);
        return products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
    }

    public List<Product> filterByType(String type){
        List<Product> products = new ArrayList<>();
        ProductAccess productAccess = new ProductAccess();
        productAccess.selectProduct(products);
        return products.stream().filter(product -> product.getType().equals(type)).collect(Collectors.toList());
    }

    public void addProduct(Product product){
        ProductAccess productAccess = new ProductAccess();
        productAccess.insertProduct(product);
    }
    public static void main(String[] args){
        ProductAccess pa = new ProductAccess();
        ProductController pc = new ProductController();
        List<Product> name = new ArrayList<>();

       name = pc.filterByName();
        for(Product p:name){
            System.out.println(p.toString());
        }
        System.out.println("////////////////////");
        List<Product> list = new ArrayList<>();
        list = pc.filterByPrice();
        for(Product p:list){
            System.out.println(p.toString());
        }
        System.out.println("////////////////////");
        List<Product> types = new ArrayList<>();
        types = pc.filterByType("dfgvb");
        for(Product p:types){
            System.out.println(p.toString());
        }
        Product p1 = new Product("chair", "wood", 2, 4.5f, "desk");
        pa.insertProduct(p1);
    }
}
