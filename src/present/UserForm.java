package present;

import Controller.OrderController;
import Controller.ProductController;
import Controller.UserController;
import com.sun.org.apache.xpath.internal.operations.Or;
import model.Invoice;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderHistory(username);

            }
        });
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

    }

}
