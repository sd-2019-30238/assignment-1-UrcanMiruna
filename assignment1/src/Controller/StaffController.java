package Controller;

import FactoryPattern.Discount;
import FactoryPattern.DiscountFactory;
import dao.OrderAccess;
import dao.ProductAccess;
import dao.StaffAccess;
import dao.UserAccess;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffController {
    public Boolean validateEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        System.out.println(email+" : "+ matcher.matches());
        return matcher.matches();
    }
    public Boolean validatePAssword(String password){
        if(password.equals("") || password.equals(" ")){
            return false;
        }else return true;
    }
    public boolean validateLogin(String username, String password){
        UserAccess user = new UserAccess();
        List<UserAccount> userList = new ArrayList<>();
        user.selectUser(userList);

        StaffAccess staff = new StaffAccess();
        List<StaffAccount> staffAccountList = new ArrayList<>();
        staff.selectStaff(staffAccountList);

        for(StaffAccount st:staffAccountList){
            if(st.getUsername().equals(username) && st.getPassword().equals(password)){
                System.out.println("1");
                return true;
            }
        }
        for(UserAccount us: userList){
            if(us.getUsername().equals(username) && us.getPassword().equals(password)){
                System.out.println("2");
                return true;
            }
        }
        return false;
    }
    public boolean verifyUser(String username){
        UserAccess userAccess = new UserAccess();
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccess.selectUser(userAccounts);
        for(UserAccount userAccount: userAccounts){
            if(userAccount.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
    public boolean verifyStaff(String username){
        StaffAccess staffAccess = new StaffAccess();
        List<StaffAccount> staffAccounts = new ArrayList<>();
        staffAccess.selectStaff(staffAccounts);
        for(StaffAccount staffAccount: staffAccounts){
            if(staffAccount.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
    public void createAccount(Person person,  String username, String password, String cat) {
        if (validateEmail(username) && validatePAssword(password)) {
            if(cat.equals("staff") && verifyUser(username)){
                StaffAccess staffAccess = new StaffAccess();
                List<StaffAccount> staffAccounts = new ArrayList<>();
                staffAccess.selectStaff(staffAccounts);
                int nr = staffAccounts.size() + 1;
                staffAccess.insertStaff(new StaffAccount(nr, person.getName(), person.getAge(), person.getAddress(), username, password));

            }
            else System.out.println("Username existent");
        }
    }
    public void setStateOrder(Order order, String state){
        OrderAccess orderAccess=new OrderAccess();
        List<Order> orders = new ArrayList<>();
        orderAccess.selectOrders(orders);
        for(Order ord:orders){
            if(ord.equals(order)){
                ord.setState(state);
                orderAccess.updateOrder(ord.getId(), ord);
            }
        }
    }

    public void setDiscount(String type){
        DiscountFactory discountFactory = new DiscountFactory();
        Discount discount = discountFactory.getDiscount(type);
        discount.applyDiscount();
    }
    public void addProduct(Product product){
        ProductController productController = new ProductController();
        productController.addProduct(product);
    }

    public void updateProduct(Product  pr, Product product){
        ProductController productController=new ProductController();
        Product pt = new Product(pr.getId(), product.getName(), product.getDescription(), product.getAmount(), product.getPrice(), product.getType());
        productController.updateAll(pr, pt);
    }

    public void deleteProduct(Product p){
        ProductController productController= new ProductController();
        OrderController orderController  = new OrderController();
        List<Order>orders = new ArrayList<>();
        orderController.getAllOrders(orders);
        int k=0;
        for(Order ord:orders){
            if(ord.getProduct().equals(p)){
                k=1;
            }
        }
        if(k==0){
            productController.deleteProduct(p);
        }
    }

    public static void main(String[] args){

       StaffController staff = new StaffController();
       //staff.createAccount(new Person("ALina", 33, "Cluj"), "alina@gm.com", "alina", "staff");
       //StaffAccess st = new StaffAccess();
      // List<StaffAccount> list = new ArrayList<>();
       //st.selectStaff(list);
        OrderAccess ord = new OrderAccess();
        List<Order> list = new ArrayList<>();
        ord.selectOrders(list);
        staff.setStateOrder(list.get(0), "paid");

        System.out.println(list.get(1).getId());
    }
}
