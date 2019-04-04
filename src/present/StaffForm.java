package present;

import Controller.*;
import dao.OrderAccess;
import model.Order;
import model.Product;
import model.StaffAccount;
import model.UserAccount;
import sun.swing.UIAction;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StaffForm extends JFrame{
    JFrame frame = new JFrame();
    private JPanel staff;
    private JTable orders;
    private JScrollPane scrollPane;


    private JButton addProduct;
    private JButton validateOrder;
    private JButton back;
    private JButton changeDeal;


    private JTextField nume;
    private JTextField amount;
    private JTextField price;
    private JTextField description;
    private JComboBox type;

    public StaffForm(String username){

        frame.setSize(800,900);
        frame.setVisible(true);
        frame.setLayout(null);

        //add(staff);
        Object[][] data = {{}};
        //for(int i=0, i<)

        DefaultTableModel model = new DefaultTableModel(new String[]{"User", "ProductsNo", "Amount ordered"}, 0);

        ProductController pc = new ProductController();
        List<Product> list = pc.viewDeals();
        OrderController orderController = new OrderController();
        List<UserAccount> users = new ArrayList<>();
        orderController.getAllUsers(users);
        List<Order> order = new ArrayList<>();
        orderController.getAllOrders(order);

        for(Order ord: order){
            model.addRow(new Object[]{ord.getUser().getUsername(), ord.getProduct().getName(), ord.getAmountOrdered()});
        }

        orders = new JTable(model){
            public boolean isCellEditable(int data, int column){return false;}
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

        validateOrder = new JButton("Validate Order");
        validateOrder.setBounds(50, 100, 150, 30);
        validateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   int p = orders.getSelectedRow();
                   OrderController orderController1 = new OrderController();
                   List<Order> orders = new ArrayList<>();
                   orderController1.getAllOrders(orders);
                   StaffController staffController = new StaffController();
                   staffController.setStateOrder(orders.get(p), "paid");
                   System.out.println(order.get(p).toString());
               }catch(Exception s){
                   System.out.println(s.getMessage());
               }

            }
        });
        frame.add(validateOrder);

        scrollPane = new JScrollPane(orders);
        scrollPane.setBounds(300, 50, 500, 100);
        frame.add(scrollPane);

        back = new JButton("Back");
        back.setBounds(50,500,150,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInVIew log =new LogInVIew();
                log.setVisible(true);
                frame.dispose();

            }
        });
        frame.add(back);


        nume = new JTextField("name", 20);
        description = new JTextField("Description", 20);
        nume.setBounds(300,500,150,30);
        description.setBounds(500,500,150,30);
        price = new JTextField("price", 20);
        amount = new JTextField("amount", 20);
        price.setBounds(300, 550, 150, 30);
        amount.setBounds(500, 550, 150, 30);

        String[] types = {"bedroom", "kitchen", "living room", "office"};

        type = new JComboBox(types);
        type.setSelectedIndex(0);
        type.setEditable(false);
        type.setBounds(300,600,150,30);
        frame.add(nume);
        frame.add(description);
        frame.add(price);
        frame.add(amount);
        frame.add(type);
        validateOrder.setBounds(50, 100, 150, 30);

        addProduct = new JButton("Add product");
        addProduct.setBounds(50,150,150,30);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = nume.getText();
                    String desc = description.getText();
                    float pret = Float.parseFloat( price.getText().toString());
                    int amt = Integer.parseInt(amount.getText());
                    String tip = type.getSelectedItem().toString();
                    Product p = new Product(name, desc, amt, pret, tip);

                    StaffController staff = new StaffController();
                    staff.addProduct(p);
                }catch(Exception t){
                    System.out.println(t.getMessage());
                }
            }
        });

    frame.add(addProduct);
        //CartController cartController = new CartController();
        //cartController.getCarts().size();

    }
}
