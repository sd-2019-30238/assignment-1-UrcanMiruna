package Controller;

import dao.ProductAccess;
import model.Order;
import model.Product;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
        int k=0;
        ProductAccess productAccess= new ProductAccess();
        List<Product> products = new ArrayList<>();
        productAccess.selectProduct(products);
        int nr = products.size();
        productAccess.insertProduct(new Product(nr+1, product.getName(), product.getDescription(), product.getAmount(), product.getPrice(), product.getType()));

    }

    public void deleteProduct(Product p){
        ProductAccess productAccess= new ProductAccess();
        List<Product> list = new ArrayList<>();
        productAccess.selectProduct(list);
        productAccess.deleteProduct(p.getId());
    }

    public void updateProduct(Product p, int amount){
        ProductAccess productAccess = new ProductAccess();
        List<Product> productList = new ArrayList<>();
        productAccess.selectProduct(productList);
        Product pnou = new Product(p.getId(), p.getName(), p.getDescription(), p.getAmount()-amount, p.getPrice(), p.getType());
        productAccess.updateProduct(p.getId(), pnou);
    }

    public void updateAll(Product p1,Product p2){
        ProductAccess productAccess = new ProductAccess();
        productAccess.updateProduct(p1.getId(), p2);

    }

    public static void main(String[] args){
        ProductAccess pa = new ProductAccess();
        ProductController pc = new ProductController();
        List<Product> name = new ArrayList<>();
/*
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
        }*/
        Product p1 = new Product("chair", "hahaha", 4, 45.5f, "office");
        pc.addProduct(p1);
        //pa.insertProduct(p1);
       // ProductAccess ppa = new ProductAccess();
       pa.selectProduct(name);
        for(Product p:name){
            System.out.println(p.toString());
        }
    }
}
