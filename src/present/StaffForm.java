package present;

import Controller.CartController;
import Controller.ProductController;
import model.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class StaffForm extends JFrame{
    JFrame frame = new JFrame();
    private JPanel staff;
    private JTable orders;
    private JScrollPane scrollPane;


    private JButton addProduct;
    private JButton validateOrder;
    private JButton back;
    private JButton changeDeal;


    public StaffForm(String username){

        frame.setSize(800,900);
        frame.setVisible(true);
        frame.setLayout(null);

        //add(staff);
        String[] columns = {"User", "ProductsNo", "Price"};
        Object[][] data = {{}};
        //for(int i=0, i<)

        DefaultTableModel model = new DefaultTableModel(new String[]{"User", "ProductsNo", "Price"}, 0);

        ProductController pc = new ProductController();
        List<Product> list = pc.viewDeals();


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



        scrollPane = new JScrollPane(orders);
        scrollPane.setBounds(300, 50, 500, 100);
        frame.add(scrollPane);

        validateOrder = new JButton("Validate Order");

        //CartController cartController = new CartController();
        //cartController.getCarts().size();

    }
}
