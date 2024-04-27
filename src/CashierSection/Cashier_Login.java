package CashierSection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cashier_Login extends JFrame {

    static JTextField loginField;

    public Cashier_Login() {
        setResizable(false);
        setTitle("Логін для касира");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    protected void initUI() {
        JPanel panel = new JPanel(null);

        JLabel label = new JLabel("Логін:");
        JLabel label2 = new JLabel("Пароль:");
        loginField = new JTextField();
        final JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Увійти");
        JButton exitButton = new JButton("Вихід");

        label.setBounds(50, 30, 80, 25);
        label2.setBounds(50, 60, 80, 25);
        loginField.setBounds(140, 30, 200, 25);
        passwordField.setBounds(140, 60, 200, 25);
        loginButton.setBounds(135, 100, 100, 25);
        exitButton.setBounds(7, 135, 80, 30);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                if (DataBaseSection.DataBase_User_Connector.checkCashierLogin(login, password)) {
                    openCashierInterface();
                } else {
                    JOptionPane.showMessageDialog(CashierSection.Cashier_Login.this,
                            "Невірний логін або пароль. Будь ласка, спробуйте ще раз.",
                            "Помилка входу",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainSection.MainMenu userRoleSelection = new MainSection.MainMenu();
                userRoleSelection.setVisible(true);
            }
        });

        panel.setBackground(new Color(226, 212, 175));

        panel.add(label);
        panel.add(label2);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton);

        add(panel);

        setLocationRelativeTo(null);
    }

    protected void openCashierInterface() {
        MainSection.MainMenu cashierInterface = new MainSection.MainMenu();
        cashierInterface.openCashierInterface();
        this.dispose();
    }

    public static JTextField getLoginField() {
        return loginField;
    }
}
