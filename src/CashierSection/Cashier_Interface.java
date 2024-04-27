package CashierSection;

import DataBaseSection.Cashier_Query;
import MainSection.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cashier_Interface extends JFrame {

    //////////////////
    // КАСИР
    //////////////////
    public Cashier_Interface() {
        setResizable(false);
        setTitle("Касир");
        setSize(320, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(null);
        JLabel label = new JLabel("Оберіть категорію");

        JButton checkButton = new JButton("Продажі");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCheckSection();
            }
        });

        JButton productsButton = new JButton("Товари");
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProductsSection();
            }
        });

        JButton aboutMeButton = new JButton("Про себе");
        aboutMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAboutMeSection();
            }
        });

        JButton clientsButton = new JButton("Постійні клієнти");
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openClientSection();
            }
        });

        JButton exitButton = new JButton("Вихід");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu userRoleSelection = new MainMenu();
                userRoleSelection.setVisible(true);
            }
        });

        JButton changePassword = new JButton("Змінити пароль");
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu cp = new MainMenu();
                cp.openCashierPasswordEdit();
            }
        });

        checkButton.setBounds(35, 140, 250, 80);
        aboutMeButton.setBounds(35, 320, 250, 80);
        clientsButton.setBounds(35, 230, 250, 80);
        productsButton.setBounds(35, 50, 250, 80);

        exitButton.setBounds(30, 410, 80, 30);
        changePassword.setBounds(140, 410, 150, 30);


        label.setBounds(84, -20, 300, 100);
        Font labelFont = label.getFont();

        label.setFont(new Font(labelFont.getName(), Font.BOLD, 15));

        add(mainPanel);
        mainPanel.setBackground(new Color(226, 212,175));

        mainPanel.add(checkButton);
        mainPanel.add(aboutMeButton);
        mainPanel.add(clientsButton);
        mainPanel.add(exitButton);
        mainPanel.add(productsButton);
        mainPanel.add(label);
        mainPanel.add(changePassword);

        setLocationRelativeTo(null);
    }

    ///////////////////
    /// СЕКЦІЯ: ТОВАРИ
    //////////////////
    private void openProductsSection(){
        JFrame frame = new JFrame("Товари");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));

        JPanel productsPanel = createCategoryPanel("Товари",
                "Перегляд усіх товарів",
                "Перегляд усіх товарів в магазині",
                "Пошук товарів за назвою",
                "Пошук товарів за категорією",
                "Перегляд акційних товарів",
                "Перегляд не акційних товарів",
                "Пошук товарів за UPC кодом"
        );

        // 1. Перегляд усіх товарів
        JButton checkProducts = (JButton) productsPanel.getComponent(0);
        checkProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ProductsSection ps = new Cashier_ProductsSection();
                ps.OpenCheckProducts();

            }
        });

        // 2. Перегляд усіх товарів в магазині
        JButton checkAllProductsInShop = (JButton) productsPanel.getComponent(1);
        checkAllProductsInShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ProductsSection ps = new Cashier_ProductsSection();
                ps.OpenCheckAllProductsInShop();

            }
        });

        // 3. Пошук товарів за назвою
        JButton searchProductsByName = (JButton) productsPanel.getComponent(2);
        searchProductsByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ProductsSection ps = new Cashier_ProductsSection();
                ps.OpenSearchByName();

            }
        });

        // 4. Пошук товарів за категорією
        JButton searchProductsByCategory = (JButton) productsPanel.getComponent(3);
        searchProductsByCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ProductsSection ps = new Cashier_ProductsSection();
                ps.OpenSearchProductsByCategory();

            }
        });

        // 5. Перегляд акційних товарів
        JButton checkSaleProducts = (JButton) productsPanel.getComponent(4);
        checkSaleProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ProductsSection ps = new Cashier_ProductsSection();
                ps.OpenCheckSaleProducts();

            }
        });

        // 6. Перегляд не акційних товарів
        JButton checkNoSaleProducts = (JButton) productsPanel.getComponent(5);
        checkNoSaleProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ProductsSection ps = new Cashier_ProductsSection();
                ps.OpenCheckNoSaleProducts();

            }
        });

        // 7. Пошук товару за UPC
        JButton checkProductsByUPC = (JButton) productsPanel.getComponent(6);
        checkProductsByUPC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ProductsSection ps = new Cashier_ProductsSection();
                ps.OpenCheckProductsByUPC();

            }
        });


        mainPanel.add(productsPanel);
        add(mainPanel);

        frame.setContentPane(mainPanel);
    }

    ////////////////////
    /// СЕКЦІЯ: ПРОДАЖІ
    ///////////////////
    private void openCheckSection(){
        JFrame frame = new JFrame("Продажі");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));

        JPanel checkPanel = createCategoryPanel("Продажі",
                "Додавання чеку",
                "Перегляд чеків створених касиром за певний день",
                "Перегляд чеків створених касиром за певний період",
                "Перегляд чеку за його номером"

        );

        // 1. Додавання чеку
        JButton addCheck = (JButton) checkPanel.getComponent(0);
        addCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_CheckSection cs = new Cashier_CheckSection();
                cs.OpenAddCheck();

            }
        });

        // 2. Перегляд чеків створених касиром за певний день
        JButton seeAllChecksForDay = (JButton) checkPanel.getComponent(1);
        seeAllChecksForDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_CheckSection cs = new Cashier_CheckSection();
                cs.OpenSeeAllChecksForDay();

            }
        });

        // 3. Перегляд чеків створених касиром за певний період
        JButton seeAllChecksForPeriod = (JButton) checkPanel.getComponent(2);
        seeAllChecksForPeriod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_CheckSection cs = new Cashier_CheckSection();
                cs.OpenSeeAllChecksForPeriod();

            }
        });

        // 4. Перегляд чеку за його номером
        JButton seeCheckByNumber = (JButton) checkPanel.getComponent(3);
        seeCheckByNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_CheckSection cs = new Cashier_CheckSection();
                cs.OpenSeeCheckByNumber();

            }
        });

        mainPanel.add(checkPanel);
        add(mainPanel);

        frame.setContentPane(mainPanel);

    }

    /////////////////////
    /// СЕКЦІЯ: ПРО СЕБЕ
    ////////////////////
    private void openAboutMeSection(){
        JFrame frame = new JFrame("Про себе");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Інформація про себе");
        label.setBounds(24, -15, 600, 100);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));


        JPanel mainPanel = new JPanel(null);
        final JButton backButton = new JButton("<-");
        backButton.setBounds(20, 500, 40, 40);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                frame.dispose();
            }
        });

        JTextField loginField = Cashier_Login.getLoginField();
        JScrollPane scrollPane = Cashier_Query.showInfoAboutMe(loginField);
        scrollPane.setBounds(24, 70, 850, 400);
        mainPanel.add(scrollPane);

        mainPanel.setBackground(new Color(226, 212,175));
        mainPanel.add(backButton);
        mainPanel.add(label);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }


    ////////////////////////////
    /// СЕКЦІЯ: ПОСТІЙНІ КЛІЄНТИ
    ////////////////////////////
    private void openClientSection(){
        JFrame frame = new JFrame("Постійні клієнти");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));

        JPanel clientPanel = createCategoryPanel("Постійні клієнти",
                "Додати постійного клієнта",
                "Редагувати постійного клієнта",
                "Перегляд постійних клієнтів",
                "Пошук постійних клієнтів за прізвищем"
        );

        // 1. Додати постійного клієнта
        JButton addClient = (JButton) clientPanel.getComponent(0);
        addClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ClientsSection cs = new Cashier_ClientsSection();
                cs.OpenAddClient();

            }
        });

        // 2. Редагувати постійного клієнта
        JButton editClient = (JButton) clientPanel.getComponent(1);
        editClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ClientsSection cs = new Cashier_ClientsSection();
                cs.OpenEditClient();

            }
        });

        // 3. Перегляд постійних клієнтів
        JButton checkClients = (JButton) clientPanel.getComponent(2);
        checkClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ClientsSection cs = new Cashier_ClientsSection();
                cs.OpenCheckClients();

            }
        });

        // 4. Пошук постійних клієнтів за прізвищем
        JButton searchClientsBySurname = (JButton) clientPanel.getComponent(3);
        searchClientsBySurname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier_ClientsSection cs = new Cashier_ClientsSection();
                cs.OpenSearchClientsBySurname();

            }
        });

        mainPanel.add(clientPanel);
        add(mainPanel);

        frame.setContentPane(mainPanel);
    }

    private JPanel createCategoryPanel(String categoryName, String... buttonLabels) {
        JPanel categoryPanel = new JPanel(new GridLayout(buttonLabels.length + 1, 1));
        categoryPanel.setBorder(BorderFactory.createTitledBorder(categoryName));

        for (String buttonLabel : buttonLabels) {
            JButton button = new JButton(buttonLabel);
            button.setPreferredSize(new Dimension(200, 40));

            button.addActionListener(new MainMenu.CustomActionListener(buttonLabel));

            categoryPanel.add(button);
        }

        return categoryPanel;
    }

}