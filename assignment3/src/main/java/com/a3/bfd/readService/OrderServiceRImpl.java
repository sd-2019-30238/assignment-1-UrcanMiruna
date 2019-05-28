package com.a3.bfd.readService;

import com.a3.bfd.model.Order;
import com.a3.bfd.model.Product;
import com.a3.bfd.model.OrderRepository;
import com.a3.bfd.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceRImpl implements OrderServiceR {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductServiceR productService;
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders=orderRepository.getOrderByUsername(username);
        return orders;
    }

    @Override
    public Float gettotalPrice(String username) {
        List<Order> orders = this.getOrdersByUsername(username);
        float price=0.0f;
        for(Order order:orders){
            Optional<Product> product=productRepository.findById(order.getIdProduct());
            price+=order.getAmountOrdered()*product.get().getPrice();
        }
        return price;
    }

    @Override
    public Order getOrderByID(Integer id) {
        Order order= orderRepository.getOrderById(id);
        return order;
    }

    @Override
    public Optional<Order> getOrderId(Integer id) {
        return orderRepository.findById(id);
    }
}
