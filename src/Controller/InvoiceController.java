package Controller;

import com.sun.deploy.util.OrderedHashSet;
import com.sun.javafx.UnmodifiableArrayList;
import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderAccess;
import dao.UserAccess;
import model.Invoice;
import model.Order;
import model.Product;
import model.UserAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceController {

    public float setInvoicePrice(Invoice invoicePrice){
        float price=0;
        OrderController orderController = new OrderController();
        List<Order> orders = new ArrayList<>();
        orderController.getAllOrders(orders);
        List<Order> list = orders.stream().filter(order -> order.getUser().equals(invoicePrice.getUser())).filter(order -> order.getState().equals("delivering")).collect(Collectors.toList());
        for(Order ord:list){
            System.out.println(ord.toString());
            price+=ord.getProduct().getPrice()*ord.getAmountOrdered();
        }
        invoicePrice.setTotalPrice(price);
        return price;
    }

    public List<Invoice> getAllInvoices(){
        UserAccess userAccess = new UserAccess();
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccess.selectUser(userAccounts);
        List<Invoice> invoices = new ArrayList<>();
        for(UserAccount user: userAccounts){
            invoices.add(new Invoice(user));
        }
        return invoices;
    }

    public Invoice setInvoice(UserAccount user){
        OrderAccess ord = new OrderAccess();
        List<Order> orders = new ArrayList<>();
        ord.selectOrders(orders);

        List<Order> o = orders.stream().filter(order -> order.getUser().equals(user)).filter(order -> order.getState().equals("delivering")).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        for(Order order:o){
            products.add(order.getProduct());
        }

        Invoice invoice= new Invoice(user, products);
        this.setInvoicePrice(invoice);
        return invoice;
    }

    public static void main(String args[]){
        InvoiceController invoiceController=new InvoiceController();

        UserAccess userAccess=new UserAccess();
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccess.selectUser(userAccounts);
        UserAccount user = userAccess.userbyUsername("alina@yahoo.com");

       Invoice invoice = new Invoice(user);
       invoiceController.setInvoicePrice(invoice);
       System.out.println(invoice.getTotalPrice()+"");
    }
}
