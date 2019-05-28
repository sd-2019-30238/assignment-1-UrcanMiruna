package com.a3.bfd.writeService;

import com.a3.bfd.model.Observer;
import com.a3.bfd.model.OrderRepository;
import com.a3.bfd.model.Product;
import com.a3.bfd.readService.ProductServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import com.a3.bfd.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceWImpl extends Observer implements OrderServiceW {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductServiceR productServiceR;

    @Autowired
    private ProductServiceW productServiceW;

    @Override
    public void addOrder(Order order) {
        Iterable<Product> products = productServiceR.getAllProducts();
        Product product=null;
        for(Product p:products){
            if(p.getId().equals(order.getIdProduct())){
                product=p;
            }
        }
        if(order.getAmountOrdered()<product.getAmount()){
            orderRepository.save(order);
            product.setAmount(product.getAmount()-order.getAmountOrdered());
            productServiceW.updateProduct(product);
        }
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void validateOrder(Order order) {
        if(order.getState().equalsIgnoreCase("delivering")){
            order.register(this);
            order.setState("paid");
            orderRepository.save(order);
        }
    }

    @Override
    public void update(Order order) {
        Optional<Product> product=productServiceR.getProductbyId(order.getIdProduct());
        System.out.println("The order  for username "+order.getUsername()+" for the product "+order.getIdProduct()
        );
    }
}
