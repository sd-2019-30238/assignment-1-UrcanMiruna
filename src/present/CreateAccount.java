package present;

import Controller.StaffController;
import Controller.UserController;
import model.Person;
import model.UserAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount extends JFrame{
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addfield;
    private JTextField usernameField;
    private JPasswordField pass;
    private JPasswordField confirmPass;
    private JRadioButton userR;
    private JRadioButton staffR;
    private JButton confirm;
    private JButton backButton;
    private JPanel panel;

    public CreateAccount(){

        add(panel);
        //add(backButton);
        setSize(700,800);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String address = addfield.getText();
                    String username = usernameField.getText();
                    char[] password = pass.getPassword();
                    String passw = "";
                    for(char a:password){
                        passw+=a;
                    }
                    char[] confirmePass = confirmPass.getPassword();
                    String cPass="";
                    for(char c:confirmePass){
                        cPass+=c;
                    }
                    int sel=0;
                    if(userR.isSelected()){
                        sel = 1;
                    }else{
                        if(staffR.isSelected()){
                            sel=2;
                        }
                    }
                    Person person = new Person();
                    if(age > 18 && age < 80){
                        if(!name.equals(null)){
                            if(!address.equals(null)){
                                person=new Person(name, age,address);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Incorrect address");

                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Incorrect name");

                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid age");

                    }

                    if(sel==1){
                        UserController userController = new UserController();
                        if(userController.validateEmail(username) && userController.validatePAssword(passw) && passw.equals(cPass)){
                            userController.createAccount(person, username, passw);
                        }
                    }else{
                        if(sel==2){
                            StaffController controller = new StaffController();
                            if(controller.validateEmail(username) && controller.validatePAssword(passw) && passw.equals(cPass)){
                                controller.createAccount(person, username, passw, "staff");
                            }
                        }
                    }



            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
