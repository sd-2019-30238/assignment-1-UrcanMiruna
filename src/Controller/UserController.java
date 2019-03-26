package Controller;

import dao.ProductAccess;
import dao.StaffAccess;
import dao.UserAccess;
import javafx.beans.property.ListProperty;
import model.Person;
import model.Product;
import model.StaffAccount;
import model.UserAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {
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

        for(UserAccount us: userList){
            if(us.getUsername().equals(username) && us.getPassword().equals(password)){
                System.out.println("2");
                return true;
            }
        }
        return false;
    }
/*
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
    }*/
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

    public void createAccount(Person person, int id, String username, String password) {
        if (validateEmail(username) && validatePAssword(password)) {
            if ( verifyStaff(username) ){
                UserAccess userAccess = new UserAccess();
                List<UserAccount> userAccounts = new ArrayList<>();
                userAccess.selectUser(userAccounts);
                userAccess.insertUser(new UserAccount( person.getName(), person.getAge(), person.getAddress(), username, password));
            }else{
               System.out.println("Username existent");
            }
        }
    }

    public void deleteAccount(String username, String password){
        if(validateLogin(username, password)){
            UserAccess userAccess = new UserAccess();
            List<UserAccount> userAccounts = new ArrayList<>();
            userAccess.selectUser(userAccounts);
            UserAccount acc = null;
            for(UserAccount user: userAccounts){
                if(user.getUsername().equals(username)){
                    acc = user;
                }
            }
            userAccess.deleteUser(acc.getId());
        }else{
            System.out.println("Wrong username or password ");
        }
    }


    public static void main(String[] args){
        UserController acc = new UserController();
        //System.out.println(acc.validateEmail("urcan.miruna@yahoo.com"));
        // System.out.println(acc.validatePAssword("ana"));
        Person person = new Person("ana", 56, "Oradea");
        //acc.createAccount(person, 1,"ada@yahoo.com", "mirustefi");
       acc.deleteAccount("carmen@haha.com", "edrfb");
       // acc.createAccount(person, 2,"carmen@haha.com", "edrfb");
       // System.out.println(acc.validateLogin("miruna@google.com", "haha"));
        //System.out.println(acc.verifyStaff("ana@yahoo.com"));
    }

}
