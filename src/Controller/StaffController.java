package Controller;

import dao.OrderAccess;
import dao.StaffAccess;
import dao.UserAccess;
import model.Order;
import model.Person;
import model.StaffAccount;
import model.UserAccount;

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
    public void createAccount(Person person, int id, String username, String password, String cat) {
        if (validateEmail(username) && validatePAssword(password)) {
            if(cat.equals("staff") && verifyUser(username)){
                StaffAccess staffAccess = new StaffAccess();
                List<StaffAccount> staffAccounts = new ArrayList<>();
                staffAccess.selectStaff(staffAccounts);

                staffAccess.insertStaff(new StaffAccount(person.getName(), person.getAge(), person.getAddress(), username, password));
            }
            else System.out.println("Username existent");
        }
    }
    public void seStateOrder(Order order, String state){
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

}
