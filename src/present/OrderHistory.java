package present;

import Controller.OrderController;
import Controller.ProductController;
import Controller.UserController;
import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends JFrame {
    JFrame frame = new JFrame();
    private JPanel user;
    private JTable orders;
    private JScrollPane scrollPane;
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;


    public OrderHistory(String username){
        frame.setSize(600, 650);
        frame.setVisible(true);
        frame.setLayout(null);
        //add(user);
        String[] columns = {"Name", "Description", "Price", "Type", "Amount"};
        Object[][] data = {{}};
        //for(int i=0, i<)

        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);

        List<Product> list = new ArrayList<>();

        OrderController orderController= new OrderController();
        UserController user = new UserController();
        List<Order> ordersList =orderController.historyOrders(user.getUser(username));
        System.out.println(user.getUser(username).toString());
        for(Order or:ordersList){
            list.add(or.getProduct());
        }
        for(Product or:list){
            System.out.println(or.toString());
        }


        for(Product p:list){
            model.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
        }

        //back = new JButton("Back");
        //back.setBounds(50, 100, 150, 30);

        orders = new JTable(model){
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
        scrollPane = new JScrollPane(orders);

        scrollPane.setBounds(50, 50, 500, 100);
        //add(scrollPane);
        frame.add(scrollPane);

        info1 = new JLabel("     All delivered orders are displayed. Orders under delivery are not displayed in ");
        info2 = new JLabel("History section. For more information about the products that are under delivery,");
        info3 = new JLabel(" please see the invoice option.");
        info1.setBounds(50, 400, 500,30);
        info2.setBounds(50, 430, 500,30);
        info3.setBounds(50,460,500,30);
        frame.add(info1);
        frame.add(info2);
        frame.add(info3);
        //frame.add(back);
        //this.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
