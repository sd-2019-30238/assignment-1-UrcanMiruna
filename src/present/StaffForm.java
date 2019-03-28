package present;

import Controller.CartController;
import Controller.ProductController;
import model.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StaffForm extends JFrame{
    JFrame frame = new JFrame();
    private JPanel staff;
    private JButton addNewProductButton;
    private JButton validateOrderButton;
    private JTable table1;
    private JList list1;
    public StaffForm(String username){

        setSize(600,560);
        setVisible(true);
        add(staff);
        String[] columns = {"Name", "Description", "Price", "Type", "Amount"};
        Object[][] data = {{}};
        //for(int i=0, i<)

        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Description", "Amount","Price", "Type"}, 0);
        table1.doLayout();

        ProductController pc = new ProductController();
        List<Product> list = pc.viewDeals();

        for(Product p:list){
            model.addRow(new Object[]{p.getName(), p.getDescription(), p.getAmount(), p.getPrice(), p.getType()});
        }
        CartController cartController = new CartController();
        cartController.getCarts().size();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(staff);
    }
}
