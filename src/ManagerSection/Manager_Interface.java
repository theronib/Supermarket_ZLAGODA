package ManagerSection;

import MainSection.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager_Interface extends JFrame {

    //////////////////
    // МЕНЕДЖЕР
    //////////////////
    public Manager_Interface() {
        setResizable(false);
        setTitle("Менеджер");
        setSize(320, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(null);
        JLabel label = new JLabel("Оберіть категорію");

        JButton salesButton = new JButton("Продажі");
        salesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSalesSection();
            }
        });


        JButton productsButton = new JButton("Товари");
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProductsSection();
            }
        });

        JButton employeesButton = new JButton("Працівники");
        employeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEmployeesSection();
            }
        });

        JButton clientsButton = new JButton("Постійні клієнти");
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openClientSection();
            }
        });


        JButton discountButton = new JButton("Акційні товари");
        discountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDiscountSection();
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
                cp.openManagerPasswordEdit();
            }
        });

        salesButton.setBounds(35, 140, 250, 80);
        employeesButton.setBounds(35, 320, 250, 80);
        clientsButton.setBounds(35, 230, 250, 80);
        productsButton.setBounds(35, 50, 250, 80);
        discountButton.setBounds(35, 410, 250, 80);

        exitButton.setBounds(30, 500, 80, 30);
        changePassword.setBounds(140, 500, 150, 30);

        label.setBounds(84, -20, 300, 100);
        Font labelFont = label.getFont();

        label.setFont(new Font(labelFont.getName(), Font.BOLD, 15));

        add(mainPanel);
        mainPanel.setBackground(new Color(141, 178,196));

        mainPanel.add(salesButton);
        mainPanel.add(employeesButton);
        mainPanel.add(clientsButton);
        mainPanel.add(exitButton);
        mainPanel.add(discountButton);
        mainPanel.add(productsButton);
        mainPanel.add(changePassword);

        mainPanel.add(label);
        setLocationRelativeTo(null);
    }

    public void getProductsSection(){
        openProductsSection();
    }

    public void getSalesSection(){
        openSalesSection();
    }

    public void getEmployeeSection(){
        openEmployeesSection();
    }

    public void getClientsSection(){
        openClientSection();
    }



    ////////////////////
    /// СЕКЦІЯ: ПРОДАЖІ
    ///////////////////
    private void openSalesSection() {
        JFrame frame = new JFrame("Продажі");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));

        JPanel salesPanel = createCategoryPanel("Продажі",
                "Перегляд чеків створених касиром за певний період",
                "Перегляд чеків створених усіма касирами за певний період",
                "Загальна кількість проданих одиниць товару за період",
                "Cередня ціна продажу за категоріями товарів",
                "Перегляд продажів певного товару за період",
                "Перегляд не куплених товарів",
                "Звіт про чеки"
        );


        // 1. Перегляд чеків створених касиром за певний період
        JButton cashierReceipt = (JButton) salesPanel.getComponent(0);
        cashierReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_SalesSection sc = new Manager_SalesSection();
                sc.openCashierReceipt();
            }
        });

        // 2. Перегляд чеків створених усіма касирами за певний період
        JButton AllCashierReceipt = (JButton) salesPanel.getComponent(1);
        AllCashierReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_SalesSection sc = new Manager_SalesSection();
                sc.openAllСashierReceipt();
            }
        });


        // 3. Загальна кількість проданих одиниць товару за період
        JButton SumOfItemSold = (JButton) salesPanel.getComponent(2);
        SumOfItemSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_SalesSection sc = new Manager_SalesSection();
                sc.SumOfItemSold();
            }
        });

        // 4. Cередня ціна продажу за категоріями товарів
        JButton AvgPriceOfCategory = (JButton) salesPanel.getComponent(3);
        AvgPriceOfCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_SalesSection sc = new Manager_SalesSection();
                sc.AvgPriceOfCategory();
            }
        });

        // 5. Перегляд продажів певного товару за період
        JButton ProductSalesForPeriod = (JButton) salesPanel.getComponent(4);
        ProductSalesForPeriod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_SalesSection sc = new Manager_SalesSection();
                sc.OpenProductSalesForPeriod();

            }
        });

        // 6. Перегляд не проданих товарів
        JButton NotSoldProducts = (JButton) salesPanel.getComponent(5);
        NotSoldProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_SalesSection sc = new Manager_SalesSection();
                sc.OpenNotSoldProducts();

            }
        });

        // 7. Надрукувати звіт про чеки
        JButton printCheckReport = (JButton) salesPanel.getComponent(6);
        printCheckReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_SalesSection sc = new Manager_SalesSection();
                sc.OpenPrintCheckReport();
                frame.setVisible(false);


            }
        });

        mainPanel.add(salesPanel);
        add(mainPanel);

        frame.setContentPane(mainPanel);

    }


    ///////////////////////
    /// СЕКЦІЯ: ТОВАРИ
    //////////////////////
    private void openProductsSection() {
        JFrame frame = new JFrame("Товари");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(2, 5));

        // 1. ТОВАРИ
        JPanel productDataPanel = createCategoryPanel("Управління товарами",
                "Додати товар",
                "Редагувати товар",
                "Видалити товар",
                "Звіт про товари");

        // 1. Додати нові дані про товар
        JButton AddProduct = (JButton) productDataPanel.getComponent(0);
        AddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenAddProduct();
                frame.setVisible(false);

            }
        });

        // 2. Редагувати дані про товар
        JButton EditProduct = (JButton) productDataPanel.getComponent(1);
        EditProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenEditProduct();
            }
        });

        // 3. Видалити дані про товар
        JButton DeleteProduct = (JButton) productDataPanel.getComponent(2);
        DeleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenDeleteProduct();
                frame.setVisible(false);
            }
        });

        // 4. Надрукувати звіт про товари
        JButton PrintProductReport = (JButton) productDataPanel.getComponent(3);
        PrintProductReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenPrintProductReport();
                frame.setVisible(false);

            }
        });

        // 2. ТОВАРИ У МАГАЗИНІ
        JPanel shopProductDataPanel = createCategoryPanel("Управління даними про товари у магазині",
                "Додати нові дані про товар у магазині",
                "Редагувати дані про товар у магазині",
                "Видалити дані про товар у магазині",
                "Звіт про товари у магазині");


        // 1. Додати нові дані про товар у магазині
        JButton AddProductInShop = (JButton) shopProductDataPanel.getComponent(0);
        AddProductInShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenAddProductInShopInfo();
                frame.setVisible(false);

            }
        });

        // 2. Редагувати дані про товар у магазині
        JButton EditProductInShop = (JButton) shopProductDataPanel.getComponent(1);
        EditProductInShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenEditProductInShop();
                frame.setVisible(false);

            }
        });

        // 3. Видалити дані про товар у магазині
        JButton DeleteProductInShop = (JButton) shopProductDataPanel.getComponent(2);
        DeleteProductInShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenDeleteProductInShop();
                frame.setVisible(false);

            }
        });

        // 4. Надрукувати звіт про товари у магазині
        JButton PrintProductInShopReport = (JButton) shopProductDataPanel.getComponent(3);
        PrintProductInShopReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenPrintProductInShopReport();
                frame.setVisible(false);
            }
        });


        // 3. КАТЕГОРІЇ ТОВАРІВ
        JPanel categoryPanel = createCategoryPanel("Управління категоріями товарів",
                "Додати категорію товарів",
                "Редагувати категорію товарів",
                "Видалити категорію товарів",
                "Звіт про категорії товарів");

        // 1. Додати нові дані про категорії товарів
        JButton AddProductCategory = (JButton) categoryPanel.getComponent(0);
        AddProductCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenAddProductCategory();
                frame.setVisible(false);
            }
        });

        // 2. Редагувати дані про категорії товарів
        JButton EditProductCategory = (JButton) categoryPanel.getComponent(1);
        EditProductCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenEditProductCategory();
                frame.setVisible(false);
            }
        });

        // 3. Видалити дані про категорії товарів
        JButton DeleteProductCategory = (JButton) categoryPanel.getComponent(2);
        DeleteProductCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenDeleteProductCategory();
                frame.setVisible(false);
            }
        });

        // 4. Надрукувати звіт про категорії товарів
        JButton PrintProductCategoryReport = (JButton) categoryPanel.getComponent(3);
        PrintProductCategoryReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenPrintProductCategoryReport();
                frame.setVisible(false);

            }
        });

        // 4. ІНФОРМАЦІЯ ПРО ТОВАРИ
        JPanel productInfoPanel = createCategoryPanel("Інформація про товари",
                "Перегляд усіх категорій",
                "Перегляд усіх товарів",
                "Перегляд усіх товарів в магазині",
                "Пошук товарів за категорією",
                "Пошук товарів за UPC кодом");

        // 1. Перегляд усіх категорій
        JButton CheckAllCategories = (JButton) productInfoPanel.getComponent(0);
        CheckAllCategories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenCheckAllCatagories();
            }
        });

        // 2. Перегляд усіх товарів
        JButton CheckAllProducts = (JButton) productInfoPanel.getComponent(1);
        CheckAllProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenCheckAllProducts();
            }
        });

        // 3. Перегляд усіх товарів в магазині
        JButton CheckAllProductsInShop = (JButton) productInfoPanel.getComponent(2);
        CheckAllProductsInShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenCheckAllProductsInShop();
            }
        });

        // 4. Пошук товарів за категорією
        JButton SearchProductsByCatagory = (JButton) productInfoPanel.getComponent(3);
        SearchProductsByCatagory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenSearchProductsByCategory();
            }
        });

        // 5. Інформація за UPC-товару
        JButton SearchProductsByUPC = (JButton) productInfoPanel.getComponent(4);
        SearchProductsByUPC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_ProductsSection ps = new Manager_ProductsSection();
                ps.OpenSearchProductsByUPC();
            }
        });

        mainPanel.add(productDataPanel);
        mainPanel.add(shopProductDataPanel);
        mainPanel.add(categoryPanel);
        mainPanel.add(productInfoPanel);
        add(mainPanel);

        frame.setContentPane(mainPanel);

    }

    ///////////////////////
    /// СЕКЦІЯ: ПРАЦІВНИКИ
    //////////////////////
    private void openEmployeesSection() {
        JFrame frame = new JFrame("Працівники");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));

        JPanel employeesPanel = createCategoryPanel("Працівники",
                "Додати працівника",
                "Редагувати працівника",
                "Видалити працівника",
                "Перегляд працівників",
                "Перегляд працівників касирів",
                "Перегляд касирів, які не працювали протягом одного місяця і не видавали чеків",
                "Пошук контактної інформації працівника",
                "Звіт про усіх працівників",
                "Додати/Видалити аккаунт для касира",
                "Додати/Видалити аккаунт для менеджера"
        );

        // 1. Додати працівника
        JButton addEmployee = (JButton) employeesPanel.getComponent(0);
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenAddEmployee();
            }
        });

        // 2. Редагувати працівника
        JButton editEmployee = (JButton) employeesPanel.getComponent(1);
        editEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenEditEmployee();
            }
        });

        // 3. Видалити працівника
        JButton removeEmployee = (JButton) employeesPanel.getComponent(2);
        removeEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenDeleteEmployee();

            }
        });

        // 4. Перегляд працівників за прізвищем
        JButton checkEmployeeBySurname = (JButton) employeesPanel.getComponent(3);
        checkEmployeeBySurname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenCheckEmployeeBySurname();

            }
        });

        // 5. Перегляд працівників касирів
        JButton checkEmployeeCashiers = (JButton) employeesPanel.getComponent(4);
        checkEmployeeCashiers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenCheckEmployeeCashiers();

            }
        });

        JButton checkEmployeeCashiersThatDoSomething = (JButton) employeesPanel.getComponent(5);
        checkEmployeeCashiersThatDoSomething.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenCheckInActiveEmployee();

            }
        });

        // 7. Знайти контактну інформацію працівника
        JButton checkContactInfo = (JButton) employeesPanel.getComponent(6);
        checkContactInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenCheckEmployeeContactInfo();

            }
        });

        // 8. Надрукувати звіт про усіх працівників
        JButton printEmployeeReport = (JButton) employeesPanel.getComponent(7);
        printEmployeeReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenPrintEmployeeReport();
                frame.setVisible(false);


            }
        });

        // 9. Додати новий аккаунт для касира
        JButton makeCashierAccount = (JButton) employeesPanel.getComponent(8);
        makeCashierAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenAddNewCashierAccount();
            }
        });

        // 10. Додати новий аккаунт для менеджера
        JButton makeManagerAccount = (JButton) employeesPanel.getComponent(9);
        makeManagerAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_EmployeesSection es = new Manager_EmployeesSection();
                es.OpenAddNewManagerAccount();
            }
        });


        mainPanel.add(employeesPanel);
        add(mainPanel);
        frame.setContentPane(mainPanel);
    }



    ////////////////////////////
    /// СЕКЦІЯ: ПОСТІЙНІ КЛІЄНТИ
    ////////////////////////////
    private void openClientSection() {
        JFrame frame = new JFrame("Постійні клієнти");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));

        JPanel clientsPanel = createCategoryPanel("Постійні клієнти",
                "Додати постійного клієнта",
                "Редагувати постійного клієнта",
                "Видалити постійного клієнта",
                "Перегляд постійних клієнтів",
                "Пошук постійних клієнтів за певним відсотком знижки",
                "Перегляд клієнтів без покупок і номеру телефона",
                "Звіт про усіх постійних клієнтів"
        );

        // 1. Додати постійного клієнта
        JButton AddClient = (JButton) clientsPanel.getComponent(0);
        AddClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_СlientsSection cs = new Manager_СlientsSection();
                cs.OpenAddClient();
            }
        });

        // 2. Редагувати постійного клієнта
        JButton EditClient = (JButton) clientsPanel.getComponent(1);
        EditClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_СlientsSection cs = new Manager_СlientsSection();
                cs.OpenEditClient();
            }
        });

        // 3. Видалити постійного клієнта
        JButton RemoveClient = (JButton) clientsPanel.getComponent(2);
        RemoveClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_СlientsSection cs = new Manager_СlientsSection();
                cs.OpenRemoveClient();
            }
        });

        // 4. Перегляд клієнтів за прізвищем
        JButton CheckClientsBySurname = (JButton) clientsPanel.getComponent(3);
        CheckClientsBySurname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_СlientsSection cs = new Manager_СlientsSection();
                cs.OpenCheckClientsBySurname();
            }
        });

        // 5. Пошук постійних клієнтів за певним відсотком знижки
        JButton CheckClientsWithCard = (JButton) clientsPanel.getComponent(4);
        CheckClientsWithCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_СlientsSection cs = new Manager_СlientsSection();
                cs.OpenCheckClientsWithCard();
            }
        });

        // 6. Перегляд клієнтів без покупок і номеру телефона
        JButton ClientsWithNoPurchases = (JButton) clientsPanel.getComponent(5);
        ClientsWithNoPurchases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_СlientsSection cs = new Manager_СlientsSection();
                cs.OpenClientsWithNoPurchases();

            }
        });

        // 7. Надрукувати звіт про усіх постійних клієнтів
        JButton PrintClientReport = (JButton) clientsPanel.getComponent(6);
        PrintClientReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_СlientsSection cs = new Manager_СlientsSection();
                cs.OpenPrintClientReport();
                frame.setVisible(false);

            }
        });


        mainPanel.add(clientsPanel);
        add(mainPanel);
        frame.setContentPane(mainPanel);
    }


    ///////////////////////////
    /// СЕКЦІЯ: АКЦІЙНІ ТОВАРИ
    //////////////////////////
    private void openDiscountSection() {
        JFrame frame = new JFrame("Акційні товари");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));

        JPanel discountPanel = createCategoryPanel("Акційні товари",
                "Перегляд акційних товарів",
                "Перегляд не акційних товарів"
        );

        // 1. Усі акційні товари
        JButton CheckDiscountProducts = (JButton) discountPanel.getComponent(0);
        CheckDiscountProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_DiscountSection ds = new Manager_DiscountSection();
                ds.OpenDiscountProducts();
            }
        });

        // 2. Усі не акційні товари
        JButton CheckNoneDiscountProducts = (JButton) discountPanel.getComponent(1);
        CheckNoneDiscountProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_DiscountSection ds = new Manager_DiscountSection();
                ds.OpenNoneDiscountProducts();
            }
        });


        mainPanel.add(discountPanel);
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
