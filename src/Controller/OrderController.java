package Controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderAccess;
import dao.UserAccess;
import model.Order;
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

        public List<Order> invoice(UserAccount user){
            OrderAccess orderAccess = new OrderAccess();
            List<Order> orders = new ArrayList<>();
            orderAccess.selectOrders(orders);
            //System.out.println("///////////////////////////////////////////////////////////////");
            List<Order> list =orders.stream().filter(order -> order.getUser().equals(user)).filter(order -> order.getState().equals("delivering")).collect(Collectors.toList());
            return list;
        }



        public static void main(String[] args){
            OrderController orderController = new OrderController();
            UserAccess userAccess = new UserAccess();
            List<UserAccount> userAccounts = new ArrayList<>();
            userAccess.selectUser(userAccounts);

            List<Order> list = orderController.historyOrders(userAccounts.get(0));
            for(Order ord:list){
               System.out.println(ord.toString());
            }
            System.out.println("/////////////////");
            List<Order> invoices = orderController.invoice(userAccounts.get(0));
            for(Order ord:invoices){
                System.out.println(ord.toString());
            }
        }

}
