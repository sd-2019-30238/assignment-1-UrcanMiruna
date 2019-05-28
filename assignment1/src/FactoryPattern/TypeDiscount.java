package FactoryPattern;

import dao.ProductAccess;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

public class TypeDiscount implements Discount {
    @Override
    public void applyDiscount() {
        ProductAccess productAccess=new ProductAccess();
        List<Product> products = new ArrayList<>();
        productAccess.selectProduct(products);
        List<Product> typeProducts = products.stream().filter(product -> product.getType().equalsIgnoreCase("kitchen")).collect(Collectors.toList());
        for(Product product:products){
            System.out.println(product.getPrice().toString());
        }
        float discount = 20;
        for(Product product:typeProducts){
            float pr = product.getPrice()-(discount/100)*product.getPrice();
            product.setPrice(pr);
            productAccess.updateProduct(product.getId(), product);
        }
        for(Product product:products){
            System.out.println(product.toString());
        }
    }

}
