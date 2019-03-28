package FactoryPattern;

import dao.ProductAccess;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfficeDiscount implements Discount {
    @Override
    public void applyDiscount() {
        ProductAccess productAccess=new ProductAccess();
        List<Product> products = new ArrayList<>();
        productAccess.selectProduct(products);

        List<Product> typeProducts = products.stream().filter(product -> product.getType().equalsIgnoreCase("office")).collect(Collectors.toList());

        for(Product product:products){
            System.out.println(product.getPrice().toString());
        }
        float discount = 15;
        for(Product product:typeProducts){
            float pr = product.getPrice()-(discount/100)*product.getPrice();
            System.out.println(pr+" ");
            product.setPrice(pr);
            productAccess.updateProduct(product.getId(), product);
        }

        //return typeProducts;
    }
}
