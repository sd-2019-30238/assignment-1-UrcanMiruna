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

import javax.management.OperationsException;
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
            orderAccess.insertOrder(new Order(userAccount,product, "delivering", amountOrdered));
        }

        public static void main(String[] args){
            OrderController orderController = new OrderController();
            UserAccess userAccess = new UserAccess();
            List<UserAccount> userAccounts = new ArrayList<>();
            userAccess.selectUser(userAccounts);

            ProductAccess productAccess = new ProductAccess();
            List<Product> list = new ArrayList<>();
            productAccess.selectProduct(list);

            StaffAccess staffAccess = new StaffAccess();
            List<StaffAccount> list1 = new ArrayList<>();
            staffAccess.selectStaff(list1);
            OrderAccess orderAccess = new OrderAccess();
            List<Order> orders = new ArrayList<>();
            //orderAccess.insertOrder(new Order(userAccounts.get(0),list.get(2), "paid", 2));
            orderAccess.selectOrders(orders);
            for(Order order:orders){
                System.out.println(order.getUser().toString());
            }

            //List<Order> list = orderController.historyOrders(userAccounts.get(1));
          //  for(Order ord:list){
           //    System.out.println(ord.toString());
           // }

           // orderController.insertOrder(new Product("chair", ""));

        }

}
