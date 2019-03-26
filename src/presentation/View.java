package presentation;

import model.UserAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ActionListener {
    JFrame frame = new JFrame("Furniture Deals");
    private JButton user;
    private JButton staff;
    View(){
        frame.setLayout(null);
        frame.setSize(400, 450);
        frame.setVisible(true);

        user = new JButton("LogIn as User");
        staff = new JButton("LogIn as Administrator");

        frame.getContentPane().add(user);
        frame.getContentPane().add(staff);
        user.setBounds(150,50,100,30);
        staff.setBounds(150,100,100,30);
        user.addActionListener(this);
        staff.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj==user){
            new ViewUser();
        }
        if(obj == staff){
            new ViewStaff();
        }
    }
}
