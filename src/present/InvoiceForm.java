package present;

import Controller.InvoiceController;
import Controller.UserController;
import model.Invoice;
import model.Product;
import model.UserAccount;

import javax.swing.*;
import java.nio.file.attribute.UserPrincipalLookupService;

public class InvoiceForm extends JFrame {
    private JPanel invoice;
    private JTextArea textArea1;

    public InvoiceForm(String username){
        add(invoice);

        setTitle("Invoice");
        setSize(500, 600);
        UserController userController = new UserController();
        UserAccount user = userController.getUser(username);
        //Invoice inv = new Invoice(user);
        InvoiceController inv= new InvoiceController();
        Invoice invoice = inv.setInvoice(user);
        textArea1.setEditable(false);
        textArea1.append(invoice.getUser().toString()+"\n");
        for(Product p : invoice.getProductList()){
            textArea1.append(p.toString()+"\n");
        }
        textArea1.append("Total price: "+invoice.getTotalPrice()+"");

    }
}
