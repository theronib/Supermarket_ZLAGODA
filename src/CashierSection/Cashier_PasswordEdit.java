package CashierSection;

import DataBaseSection.DataBase_User_Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cashier_PasswordEdit extends JFrame {
    String login = Cashier_Login.getLoginField().getText();

    // МЕНЮ ДЛЯ РЕДАГУВАННЯ ПАРОЛЯ (КАСИР)
    public Cashier_PasswordEdit() {
        setResizable(false);
        setTitle("Редагування пароля для " + login);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    protected void initUI() {
        JPanel panel = new JPanel(null);

        JLabel label = new JLabel("Старий пароль:");
        JLabel label2 = new JLabel("Новий пароль:");
        JLabel label3 = new JLabel("Підтвердити пароль:");
        final JPasswordField passwordField = new JPasswordField();
        final JPasswordField passwordField1 = new JPasswordField();
        final JPasswordField passwordField2 = new JPasswordField();
        JButton editButton = new JButton("Змінити");
        JButton exitButton = new JButton("Вихід");

        label.setBounds(30, 30, 100, 25);
        label2.setBounds(30, 60, 100, 25);
        label3.setBounds(30, 90, 140, 25);
        passwordField.setBounds(140, 30, 200, 25);
        passwordField1.setBounds(140, 60, 200, 25);
        passwordField2.setBounds(170, 90, 170, 25);

        editButton.setBounds(295, 135, 100, 25);
        exitButton.setBounds(7, 135, 80, 30);


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String old_password = new String(passwordField.getPassword());
                String password1 = new String(passwordField1.getPassword());
                String password2 = new String(passwordField2.getPassword());

                if (old_password.equals("")) {
                    JOptionPane.showMessageDialog(Cashier_PasswordEdit.this,
                            "Для зміни пароля необхідно ввести старий пароль. Будь ласка, спробуйте ще раз.",
                            "Помилка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (password1.equals("") || password2.equals("")){
                    JOptionPane.showMessageDialog(Cashier_PasswordEdit.this,
                            "Для зміни пароля необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                            "Помилка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (!password1.equals(password2)){
                    JOptionPane.showMessageDialog(Cashier_PasswordEdit.this,
                            "Помилка невідповідності нового пароля. Будь ласка, спробуйте ще раз.",
                            "Помилка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (!DataBaseSection.DataBase_User_Connector.checkCashierLogin(login, old_password)){
                    JOptionPane.showMessageDialog(Cashier_PasswordEdit.this,
                            "Введено неправильний старий пароль. Будь ласка, спробуйте ще раз.",
                            "Помилка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    DataBase_User_Connector.changeCashierPassword(password2, login);
                    JOptionPane.showMessageDialog(Cashier_PasswordEdit.this,
                            "Зміна пароля пройшла успішно",
                            "Успіх",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainSection.MainMenu cashierInterface = new MainSection.MainMenu();
                cashierInterface.openCashierInterface();
            }
        });

        panel.setBackground(new Color(226, 212,175));
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(passwordField);
        panel.add(passwordField1);
        panel.add(passwordField2);
        panel.add(editButton);
        panel.add(exitButton);

        add(panel);

        setLocationRelativeTo(null);
    }

}