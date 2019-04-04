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
    private JButton discount;


    private JTextField nume;
    private JTextField amount;
    private JTextField price;
    private JTextField description;
    private JComboBox type;

    private JTable products ;
    private JScrollPane scrollPane1;
    private JTextArea infos;
    private JButton delete;


    public StaffForm(String username){

        frame.setSize(800,1100);
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
        back.setBounds(50,350,150,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInVIew log =new LogInVIew();
                log.setVisible(true);
                frame.dispose();

            }
        });
        frame.add(back);

        delete = new JButton("Delete");
        delete.setBounds(50,300, 150, 30);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ProductController productController  = new ProductController();
                    List<Product> productList = productController.viewDeals();
                    int p = products.getSelectedRow();
                    Product product = productList.get(p);
                    StaffController staff = new StaffController();
                    staff.deleteProduct(product);
                    JOptionPane.showMessageDialog(null,"Product deleted successfully");
                    frame.repaint();
                }catch(Exception d){
                    System.out.println(d.getMessage());
                }
            }
        });
        frame.add(delete);

        nume = new JTextField("name", 20);
        description = new JTextField("Description", 20);
        nume.setBounds(300,350,150,30);
        description.setBounds(500,350,150,30);
        price = new JTextField("price", 20);
        amount = new JTextField("amount", 20);
        price.setBounds(300, 400, 150, 30);
        amount.setBounds(500, 400, 150, 30);

        String[] types = {"bedroom", "kitchen", "living room", "office"};

        type = new JComboBox(types);
        type.setSelectedIndex(0);
        type.setEditable(false);
        type.setBounds(300,450,150,30);
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
                    JOptionPane.showMessageDialog(null, "Product added successfully");
                }catch(Exception t){
                    System.out.println(t.getMessage());
                }
            }
        });


        ProductController productController  = new ProductController();
        List<Product> productList = productController.viewDeals();
        DefaultTableModel model1 = new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

        for(Product p:productList){
            model1.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
        }
        products = new JTable(model1){

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
        scrollPane1 = new JScrollPane(products);

        scrollPane1.setBounds(300, 200, 500, 100);
        //add(scrollPane);
        frame.add(scrollPane1);

        changeDeal = new JButton("Update product");
        changeDeal.setBounds(50,200,150,30);
        changeDeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ProductController productController  = new ProductController();
                    List<Product> productList = productController.viewDeals();
                    int p = products.getSelectedRow();
                    Product product = productList.get(p);
                    String nm = products.getValueAt(p, 0).toString();
                    String desc = products.getValueAt(p, 1).toString();
                    int am = Integer.parseInt(products.getValueAt(p, 2).toString());
                    float pr = Float.parseFloat(products.getValueAt(p, 3).toString());
                    String tips = type.getSelectedItem().toString();
                    StaffController staffController = new StaffController();

                    Product product1 = new Product(nm, desc, am, pr, tips);
                    staffController.updateProduct(product, product1);
                }catch(Exception s){
                    System.out.println(s.getMessage());
                }

            }
        });
        frame.add(changeDeal);

        discount = new JButton("Discount");
        discount.setBounds(50,250, 150, 30);
        discount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int p = products.getSelectedRow();
                    Product product = productList.get(p);

                    String tips = type.getSelectedItem().toString();
                    StaffController staffController = new StaffController();
                    staffController.setDiscount(tips);
                }catch(Exception s){
                    System.out.println(s.getMessage());
                }
            }
        });
        frame.add(discount);

        frame.add(addProduct);

        infos = new JTextArea("");
        infos.setEditable(false);
        infos.setBounds(50, 500, 700, 400);
        infos.append("Tips: \n  If you want validate an order, please select the order from the first table and  press the 'Validate order' button.\n");
        infos.append("  To make changes for a product, please select the product from the second table, double click on the field you want to make \n changes and write new data\n");
        infos.append("  To add a product, please complete ALL fields from the second half of the window and press the button.\n");
        infos.append("  In order to apply some discount, select the category from the last box and press the Discount button. \n          Thank you for choosing us!");

        frame.add(infos);
        //CartController cartController = new CartController();
        //cartController.getCarts().size();

    }
}
