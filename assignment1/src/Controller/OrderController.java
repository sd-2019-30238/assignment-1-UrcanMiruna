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
            List<Order> l1 = list.stream().filter(order -> order.getUser().equals(user)).filter(order -> order.getState().equals("delivering")).collect(Collectors.toList());
            return l1.size();
        }
        public float getPriceOrder(UserAccount userAccount){
            List<Order> list = new ArrayList<>();
            OrderAccess orderAccess=new OrderAccess();
            orderAccess.selectOrders(list);
            List<Order> l1 = list.stream().filter(order -> order.getUser().equals(userAccount)).filter(order -> order.getState().equals("delivering")).collect(Collectors.toList());
            float price = 0;
            for(Order order:list){
                if(order.getUser().equals(userAccount)){
                    price += order.getAmountOrdered() * order.getProduct().getPrice();

                }
            }
            return price;
        }

        public void getAllUsers(List<UserAccount> users){
            List<Order> list = new ArrayList<>();
            OrderAccess orderAccess=new OrderAccess();
            List<UserAccount> user1 = new ArrayList<>();

            orderAccess.selectOrders(list);
            for(Order order:list) {
              user1.add(order.getUser());
            }
            for(UserAccount userAccount:user1){
                if(users.contains(userAccount)){

                }
                else{
                    users.add(userAccount);
                }
            }

        }

        public static void main(String[] args){
            OrderController orderController = new OrderController();
           List<UserAccount> userAccounts= new ArrayList<>();

           UserController user = new UserController();
           UserAccount users = user.getUser("miruna@yahoo.com");

           ProductController productController = new ProductController();
           Product p = productController.viewDeals().get(0);

          // orderController.insertOrder(p, users, 1);
            orderController.getAllUsers(userAccounts);


           System.out.println( orderController.getOrdersNumber(userAccounts.get(1)));
             System.out.println(orderController.getPriceOrder(userAccounts.get(0)));

        }

}
