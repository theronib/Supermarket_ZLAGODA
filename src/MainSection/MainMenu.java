package MainSection;

import CashierSection.Cashier_Interface;
import CashierSection.Cashier_Login;
import CashierSection.Cashier_PasswordEdit;
import ManagerSection.Manager_Interface;
import ManagerSection.Manager_Login;
import ManagerSection.Manager_PasswordEdit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    // ОСНОВНЕ МЕНЮ: ВИБІР РОЛІ (МЕНЕДЖЕР І КАСИР)
    public MainMenu() {
        setTitle("Продуктовий міні-супермаркет «ZLAGODA»");
        setResizable(false);
        setSize(400, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(201, 212,193));

        JButton managerButton = new JButton("Менеджер");
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginInterface();
            }
        });

        JButton cashierButton = new JButton("Касир");
        cashierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCashierLoginInterface();
            }
        });

        JButton exitButton = new JButton("Вихід");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLabel label = new JLabel("Авторизація");
        JLabel label2 = new JLabel("«ZLAGODA»");
        JLabel label3 = new JLabel("Оберіть тип користувача: ");
        JLabel label4 = new JLabel("або");

        managerButton.setBounds(75, 150, 250, 80);
        cashierButton.setBounds(75, 260, 250, 80);
        exitButton.setBounds(160, 360, 80, 30);

        label.setBounds(125, 35, 300, 100);
        label2.setBounds(160, -25, 300, 100);
        label3.setBounds(80, 85, 300, 100);
        label4.setBounds(185, 195, 300, 100);
        Font labelFont = label.getFont();

        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));

        panel.add(managerButton);
        panel.add(cashierButton);
        panel.add(exitButton);

        add(label);
        add(label2);
        add(label3);
        add(label4);
        add(panel);

        setLocationRelativeTo(null);
    }

    protected void openLoginInterface() {
        Manager_Login ml = new Manager_Login();
        ml.setVisible(true);
        this.dispose();
    }

    protected void openCashierLoginInterface() {
        Cashier_Login cl = new Cashier_Login();
        cl.setVisible(true);
        this.dispose();
    }

    public void openCashierPasswordEdit() {
        Cashier_PasswordEdit cp = new Cashier_PasswordEdit();
        cp.setVisible(true);
        this.dispose();
    }

    public void openManagerPasswordEdit() {
        Manager_PasswordEdit cp = new Manager_PasswordEdit();
        cp.setVisible(true);
        this.dispose();
    }

    public void openManagerInterface() {
        Manager_Interface managerInterface = new Manager_Interface();
        managerInterface.setVisible(true);
        this.dispose();
    }

    public void openCashierInterface() {
        Cashier_Interface cashierInterface = new Cashier_Interface();
        cashierInterface.setVisible(true);
        this.dispose();
    }


    // Окремий клас для слухача подій
    public static class CustomActionListener implements ActionListener {
        private String buttonLabel;

        public CustomActionListener(String buttonLabel) {
            this.buttonLabel = buttonLabel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    public static void main(String[] args) {
            MainMenu userRoleSelection = new MainMenu();
            userRoleSelection.setVisible(true);
    }
}
