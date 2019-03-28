package present;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainView extends JFrame{
    private JPanel furnitureDeals;
    private JButton logInButton;
    private JButton createAccountButton;

    public mainView() {

        add(furnitureDeals);

        setTitle("Furniture Deals");
        setSize(400, 450);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInVIew logIn = new LogInVIew();
                logIn.setVisible(true);
                dispose();
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccount createAccount=new CreateAccount();
                createAccount.setVisible(true);
            }
        });
    }
    public static void main(String args[]){
        mainView mv = new mainView();
        mv.setVisible(true);
    }
}
