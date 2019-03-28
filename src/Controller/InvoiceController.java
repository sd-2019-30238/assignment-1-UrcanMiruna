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
        OrderAccess orderAccess = new OrderAccess();
        List<Order> orders = new ArrayList<>();
        orderAccess.selectOrders(orders);
        List<Order> list = orders.stream().filter(order -> order.getUser().equals(invoicePrice.getUser())).filter(order -> order.getState().equals("delivering")).collect(Collectors.toList());
        for(Order ord:list){
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
        for(Product p :products){
            System.out.println(p.toString());
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

        for(UserAccount user:userAccounts){
            System.out.println(invoiceController.setInvoicePrice(new Invoice(user))+" ");

        }
    }
}
