package present;

import Controller.StaffController;
import Controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInVIew extends JFrame{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel logIn;
    private JButton logInButton;
    private JButton back;
    private static int loggedUser=0;
    private static int loggedStaff =0;

    public LogInVIew(){

        add(logIn);
        setTitle("Furniture Deals");
        setSize(400, 450);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                char[] password = passwordField1.getPassword();
                String pass = "";
                for(char a:password){
                    pass+=a;
                }
                UserController userController = new UserController();
                if(userController.validateLogin(username, pass) && loggedUser==0){
                    loggedUser =1;
                    new UserForm(username);
                    //JOptionPane.showMessageDialog(null, "User logged");
                    dispose();

                }
                else{
                    StaffController sc = new StaffController();
                    if(sc.validateLogin(username, pass)&& loggedStaff==0){
                        loggedStaff=1;
                         new StaffForm(username);
                        //JOptionPane.showMessageDialog(null, "Staff loggged");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Oh! Something went wrong. Please try again!");
                    }
                }

            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView mv = new mainView();
                mv.setVisible(true);
                dispose();
            }
        });
    }
}
