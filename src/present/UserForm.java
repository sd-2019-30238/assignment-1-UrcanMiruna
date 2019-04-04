package present;

import Controller.CartController;
import Controller.OrderController;
import Controller.ProductController;
import Controller.UserController;
import com.sun.deploy.ui.ProgressDialog;
import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderAccess;
import dao.ProductAccess;
import dao.StaffAccess;
import model.*;

import javax.jws.soap.SOAPBinding;
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
    private JButton viewAll;

    private JTable carttable;
    private JLabel cartLabel;
    private JScrollPane cartScroll;
    private JButton filterByName;
    private JButton bedroom;
    private JButton filterByPrice;
    private JButton kitchen;
    private JButton office;
    private JButton living;
    private JButton clean;
    private JButton order;

    private JButton back;

    public UserForm(String username){
        frame.setSize(800, 900);
        frame.setVisible(true);
        frame.setLayout(null);
        //add(user);
        String[] columns = {"Name", "Description", "Price", "Type", "Amount"};
        Object[][] data = {{}};
        //for(int i=0, i<)
        CartController cartController = new CartController();
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


        UserController userController = new UserController();
        UserAccount user = userController.getUser(username);
        Cart cart = new Cart(user);

        Cart newCart = new Cart(user);
        List<Product> ord = new ArrayList<>();

        invoice = new JButton("Invoice");
        invoice.setBounds(50, 150, 150, 30);
        invoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvoiceForm invoice = new InvoiceForm(username, cart);
                invoice.setVisible(true);
            }
        });
        frame.add(invoice);


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
                for(Product pw: cart.getProductList()){
                    System.out.println(pw.toString());
                }
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
                ProductController pc = new ProductController();
               List <Product> productList = pc.viewDeals();
                for(int i=0; i<cart.getProductList().size(); i++) {
                    if (cart.getProductList().get(i).getAmount() >= Integer.parseInt(model1.getValueAt(i, 2).toString())) {
                        orderController.insertOrder(cart.getProductList().get(i), user, Integer.parseInt(model1.getValueAt(i, 2).toString()));

                        for (Product p : productList) {
                            if (p.equals(cart.getProductList().get(i))) {
                                //p.setPrice(p.getPrice()-Integer.parseInt(model1.getValueAt(i, 2).toString()));
                                pc.updateProduct(p, Integer.parseInt(model1.getValueAt(i, 2).toString()));
                                //frame.repaint();

                            }
                        }
                        //model1.removeRow(i);
                    }
                }
                cart.deleteAll();


            }
        });

        frame.add(order);


        filterByName = new JButton("Filter by name");
        filterByName.setBounds(50, 300, 150, 30);
        filterByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductController productController= new ProductController();
                List<Product> productList=productController.filterByName();
                DefaultTableModel model2 =   new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

                for(Product p : productList){
                    model2.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
                }

                products.setModel(model2);

            }
        });
        frame.add(filterByName);

        filterByPrice = new JButton("Filter by price");
        filterByPrice.setBounds(50, 350,150, 30);
        filterByPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductController productController= new ProductController();
                List<Product> productList=productController.filterByPrice();
                DefaultTableModel model2 =   new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

                for(Product p : productList){
                    model2.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
                }

                products.setModel(model2);
            }
        });
        frame.add(filterByPrice);

        bedroom = new JButton("Bedroom products");
        bedroom.setBounds(50, 400, 150, 30);
        bedroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductController productController= new ProductController();
                List<Product> productList=productController.filterByType("bedroom");
                DefaultTableModel model3 =   new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

                for(Product p : productList){
                    model3.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
                }

                products.setModel(model3);
            }
        });
        frame.add(bedroom);
        kitchen = new JButton("Kitchen products");
        kitchen.setBounds(230, 400, 150, 30);
        kitchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductController productController= new ProductController();
                List<Product> productList=productController.filterByType("kitchen");
                DefaultTableModel model3 =   new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

                for(Product p : productList){
                    model3.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
                }

                products.setModel(model3);
            }
        });
        frame.add(kitchen);

        office = new JButton("Office products");
        office.setBounds(400, 400, 150, 30);
        office.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductController productController= new ProductController();
                List<Product> productList=productController.filterByType("office");
                DefaultTableModel model3 =   new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

                for(Product p : productList){
                    model3.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
                }

                products.setModel(model3);
            }
        });
        frame.add(office);

        living = new JButton("Living room products");
        living.setBounds(580, 400, 150, 30);
        living.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductController productController= new ProductController();
                List<Product> productList=productController.filterByType("living");
                DefaultTableModel model3 =   new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

                for(Product p : productList){
                    model3.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
                }

                products.setModel(model3);
            }
        });
        frame.add(living);



        viewAll = new JButton("View all products");
        viewAll.setBounds(50, 450, 150, 30);
        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductController productController = new ProductController();
                List<Product> productList = productController.viewDeals();
                DefaultTableModel model4 = new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);
                for(Product p : productList){
                    model4.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
                }

                products.setModel(model4);
            }
        });

        frame.add(viewAll);
        back = new JButton("Back");
        back.setBounds(50,500,150,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInVIew log =new LogInVIew();
                log.setVisible(true);
                dispose();

            }
        });
        frame.add(back);
    }


}
