package present;

import Controller.CartController;
import Controller.OrderController;
import Controller.ProductController;
import Controller.UserController;
import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderAccess;
import dao.StaffAccess;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserForm extends JFrame {
    JFrame frame = new JFrame();
    private JPanel user;
    private JTable products;
    private JScrollPane scrollPane;
    private JButton history;
    private JButton invoice;
    private JButton addToCart;
    private JButton logOut;

    private JTable carttable;
    private JLabel cartLabel;
    private JScrollPane cartScroll;

    private JButton order;



    public UserForm(String username){
        frame.setSize(800, 900);
        frame.setVisible(true);
        frame.setLayout(null);
        //add(user);
        String[] columns = {"Name", "Description", "Price", "Type", "Amount"};
        Object[][] data = {{}};
        //for(int i=0, i<)

        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

        ProductController pc = new ProductController();
        List<Product> list = pc.viewDeals();

        for(Product p:list){
            model.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
        }

        history = new JButton("Orders History");
        history.setBounds(50, 100, 150, 30);

        products = new JTable(model){
            public boolean isCellEditable(int data, int columns){
                return false;
            }

           public Component prepareRenderer(TableCellRenderer r, int data, int columns){
                Component c = super.prepareRenderer(r, data, columns);
                if(data%2==0){
                    c.setBackground(Color.WHITE);
                }else {
                    c.setBackground(Color.LIGHT_GRAY);
                }
                if(isCellSelected(data, columns)){
                    c.setBackground(Color.GRAY);
                }
               return c;

           }
        };
        //user.add(history);
        scrollPane = new JScrollPane(products);

        scrollPane.setBounds(300, 50, 500, 100);
        //add(scrollPane);
        frame.add(scrollPane);
        DefaultTableModel model1 = new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);


        carttable = new JTable(model1){
            public boolean isCellEditable(int data, int columns){
                if(columns==2){
                    return true;
                }
                return false;
            }

            public Component prepareRenderer(TableCellRenderer r, int data, int columns){
                Component c = super.prepareRenderer(r, data, columns);
                if(data%2==0){
                    c.setBackground(Color.WHITE);
                }else {
                    c.setBackground(Color.LIGHT_GRAY);
                }
                if(isCellSelected(data, columns)){
                    c.setBackground(Color.GRAY);
                }
                return c;

            }
        };


        cartScroll = new JScrollPane(carttable);
        cartScroll.setBounds(300, 250, 500, 100);
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderHistory(username);

            }
        });
        frame.add(cartScroll);
        frame.add(history);
        //this.pack();

        invoice = new JButton("Invoice");
        invoice.setBounds(50, 150, 150, 30);
        invoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvoiceForm invoice = new InvoiceForm(username);
                invoice.setVisible(true);
            }
        });
        frame.add(invoice);

        UserController userController = new UserController();
        UserAccount user = userController.getUser(username);
        Cart cart = new Cart(user);

        addToCart = new JButton("Add to cart");
        cartLabel = new JLabel("Cart");
        cartLabel.setBounds(300, 200, 100, 30);

        addToCart.setBounds(50, 200, 150, 30);
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p = products.getSelectedRow();
                ProductController pc = new ProductController();
                List<Product>list = pc.viewDeals();
                cart.addProduct(list.get(p));
                Product pr = list.get(p);
                //for(Product pr : cart.getProductList()){
                    model1.addRow(new Object[]{pr.getName(), pr.getDescription(), pr.getAmount(), pr.getPrice(), pr.getType()});

                //}

            }
        });
        frame.add(addToCart);
        frame.add(cartLabel);

        order = new JButton("Order");
        order.setBounds(50,250,150,30);
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderController orderController = new OrderController();
                for(Product p:cart.getProductList()){
                    System.out.println(p.toString());
                    orderController.insertOrder( p,user, p.getAmount());
                }
                for(int p=0; p<carttable.getRowCount(); p++)
                    model1.removeRow(p);
            }
        });
        frame.add(order);
    }


}
