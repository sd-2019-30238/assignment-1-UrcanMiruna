package Controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderAccess;
import dao.ProductAccess;
import dao.StaffAccess;
import dao.UserAccess;
import model.Order;
import model.Product;
import model.StaffAccount;
import model.UserAccount;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderController {

        public List<Order> historyOrders(UserAccount user){
            OrderAccess orderAccess = new OrderAccess();
            List<Order> orders = new ArrayList<>();
            orderAccess.selectOrders(orders);

            List<Order> list =  orders.stream().filter(order -> order.getUser().equals(user)).filter(order -> order.getState().equals("paid")).collect(Collectors.toList());
            return list;
        }

        public void insertOrder(Product product, UserAccount userAccount,  int amountOrdered){
            OrderAccess orderAccess = new OrderAccess();
            List<Order> list= new ArrayList<>();
            orderAccess.selectOrders(list);
           // if(product.getAmount() > 0) {
            int nr= list.size()+1;
            Order or = new Order(nr,userAccount, product, "delivering", amountOrdered);
            //if(list.isEmpty()){
            orderAccess.insertOrder(or);
        }

        public void getAllOrders(List<Order> list){
            OrderAccess orderAccess=new OrderAccess();
            orderAccess.selectOrders(list);

        }

        public int getOrdersNumber(UserAccount user){
            List<Order> list = new ArrayList<>();
            OrderAccess orderAccess=new OrderAccess();
            orderAccess.selectOrders(list);
            return list.size();
        }

        public void getAllUsers(List<UserAccount> users){
            List<Order> list = new ArrayList<>();
            OrderAccess orderAccess=new OrderAccess();
            orderAccess.selectOrders(list);
            for(Order order:list) {
              users.add(order.getUser());
            }
            users = users.stream().distinct().collect(Collectors.toList());

        }

        public static void main(String[] args){
            OrderController orderController = new OrderController();
           List<UserAccount> userAccounts= new ArrayList<>();

           UserController user = new UserController();
           UserAccount users = user.getUser("alina@yahoo.cm");

           ProductController productController = new ProductController();
           Product p = productController.viewDeals().get(1);

           orderController.insertOrder(p, users, 1);


        }

}
