package ManagerSection;


import DataBaseSection.Cashier_Query;
import DataBaseSection.Manager_Query;
import DataBaseSection.PDFReportGenerator;
import DataBaseSection.PDFViewer;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.Random;
import java.util.prefs.Preferences;

public class Manager_ProductsSection extends JFrame {

    //////////////////////
    // СЕКЦІЯ: "ТОВАРИ"
    //////////////////////

    // 1. Додати нові дані про товар
    public void OpenAddProduct() {
        AddProduct addProduct = new AddProduct();
        addProduct.setVisible(true);
        this.dispose();
    }

    public class AddProduct extends JFrame {

        public AddProduct() {
            setTitle("Додати товар");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            JLabel label = new JLabel("Додати товар");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть назву товару:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 370, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Виберіть номер категорії товару:");
            label3.setBounds(24, 106, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            String[] choices = Manager_Query.getCategoryArray();
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(20, 160, 370, 50);
            panel.add(comboBox);

            JLabel label4 = new JLabel("Надайте характеристику товару:");
            label4.setBounds(24, 172, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(24, 237, 370, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton addButton = new JButton("Додати товар");
            addButton.setBounds(130, 310, 170, 60);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("") || textField2.getText().equals("")) {
                        JOptionPane.showMessageDialog(AddProduct.this,
                                "Для додавання товару потрібно заповнити всі необхідні поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String selectedText = (String) comboBox.getSelectedItem();
                        String[] parts = selectedText.split(":");
                        int category_number = Integer.parseInt(parts[0].trim());

                        String characteristics = textField2.getText();
                        Manager_Query.addProduct(category_number, textField.getText(), characteristics);
                    }
                }
            });


            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();

                }
            });

            panel.add(backButton);
            panel.add(label);
            panel.add(label2);
            panel.add(textField);
            panel.add(textField2);
            panel.add(addButton);
            panel.add(label3);
            panel.add(label4);

            add(panel);
            setVisible(true);

        }
    }

    // 2. Редагувати дані про товар
    public void OpenEditProduct() {
        EditProduct editProduct = new EditProduct();
        editProduct.setVisible(true);
        this.dispose();
    }

    public class EditProduct extends JFrame {

        public EditProduct() {
            setTitle("Редагувати товар");
            setSize(1050, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));

            JLabel label = new JLabel("Редагувати товар");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JScrollPane scrollPane = Manager_Query.showProductsToEdit();
            scrollPane.setBounds(24, 70, 600, 350);
            panel.add(scrollPane);

            JTextField textField_product = new JTextField(10);
            textField_product.setBounds(24, 450, 600, 20);
            textField_product.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label_product = new JLabel("Товар, який потрібно редагувати: ");
            label_product.setBounds(24, 385, 600, 100);
            Font labelFont_product = label.getFont();
            label_product.setFont(new Font(labelFont_product.getName(), Font.PLAIN, 12));


            JLabel label3 = new JLabel("Виберіть нову категорію товара:");
            label3.setBounds(654, 30, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            String[] choices = Manager_Query.getCategoryArray();
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(650, 84, 380, 50);
            panel.add(comboBox);


            JLabel label4 = new JLabel("Введіть нове ім'я товару:");
            label4.setBounds(654, 96, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(654, 165, 370, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label5 = new JLabel("Надайте нову характеристику товару:");
            label5.setBounds(654, 172, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(654, 241, 370, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));


            JButton editButton = new JButton("Редагувати товар");
            editButton.setBounds(740, 330, 200, 60);

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField_product.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditProduct.this,
                                "Для редагування товару спочатку потрібно його вибрати в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditProduct.this,
                                "Для редагування товару потрібно надати йому нову назву. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String product = textField_product.getText();
                        String[] product_str = product.split(":", 2);
                        int product_id = Integer.parseInt(product_str[0]);

                        String category = (String) comboBox.getSelectedItem();
                        String[] category_id_str = category.split(":", 2);
                        int category_id = Integer.parseInt(category_id_str[0].trim());

                        String new_product_name = textField.getText();
                        String new_characteristics = textField2.getText();
                        Manager_Query.editProduct(category_id, new_product_name, new_characteristics, product_id);

                    }
                }
            });

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });


            panel.add(backButton);
            panel.add(textField_product);
            panel.add(textField);
            panel.add(textField2);
            panel.add(label_product);
            panel.add(label);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(editButton);
            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String product_id = table.getValueAt(selectedRow, 0).toString();
                            String product_name = table.getValueAt(selectedRow, 1).toString();

                            textField_product.setText(product_id + ": " + product_name);
                        }
                    }
                }
            });
        }
    }

    // 3. Видалити дані про товар
    public void OpenDeleteProduct() {
        DeleteProduct deleteProduct = new DeleteProduct();
        deleteProduct.setVisible(true);
        this.dispose();
    }

    public class DeleteProduct extends JFrame {

        public DeleteProduct() {
            setTitle("Видалити товар");
            setSize(450, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            JLabel label = new JLabel("Видалити товар");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Manager_Query.showProductsToRemove();
            scrollPane.setBounds(24, 70, 405, 350);
            panel.add(scrollPane);

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 450, 400, 20);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));


            JLabel label2 = new JLabel("Товар, який потрібно видалити: ");
            label2.setBounds(24, 385, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 12));

            JButton deleteButton = new JButton("Видалити");
            deleteButton.setBounds(330, 475, 100, 30);

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(DeleteProduct.this,
                                "Для видалення товару спочатку потрібно вибрати його в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                            String product = textField.getText();
                            String[] product_num = product.split(":", 2);
                            int productId = Integer.parseInt(product_num[0]);
                            Manager_Query.deleteProduct(productId);
                    }
                }
            });




            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();
                }
            });

            panel.setBackground(new Color(141, 178, 196));
            panel.add(backButton);
            panel.add(textField);
            panel.add(label);
            panel.add(deleteButton);
            panel.add(label2);
            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String productId = table.getValueAt(selectedRow, 0).toString();
                            String productName = table.getValueAt(selectedRow, 1).toString();
                            textField.setText(productId + ": " + productName);
                        }
                    }
                }
            });
        }
    }

    // 4. Надрукувати звіт про товари
    public void OpenPrintProductReport() {
        ReportProductReport printPrReport = new ReportProductReport();
        printPrReport.setVisible(true);
        this.dispose();
    }

    public class ReportProductReport extends JFrame {

        public ReportProductReport() {
            setTitle("Звіт про товари");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Товари:");
            label.setBounds(24, 5, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Переглянути попередній звіт:");
            label2.setBounds(24, 100, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JButton checkButton = new JButton("Переглянути звіт");
            checkButton.setBounds(24, 165, 180, 60);
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String filePath = "Звіти/product_report.pdf";
                    File file = new File(filePath);

                    if (file.exists() && !file.isDirectory()) {
                        PDFViewer pdfViewer = new PDFViewer("Звіт по продуктам", 800);
                        pdfViewer.openPDF(filePath);
                        pdfViewer.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Такого звіту не існує", "Помилка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            JLabel label3 = new JLabel("Згенерувати новий звіт:");
            label3.setBounds(24, 196, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JButton printButton = new JButton("Новий звіт");
            printButton.setBounds(24, 261, 180, 60);
            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PDFReportGenerator pdf = new PDFReportGenerator();
                    pdf.GenerateReportForProducts();
                    JOptionPane.showMessageDialog(ReportProductReport.this,
                            "Звіт збережено у файлі product_report.pdf",
                            "Звіт збережено",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();
                }
            });

            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(checkButton);
            panel.add(backButton);
            panel.add(printButton);
            add(panel);
            setVisible(true);
        }
    }


    // 5. Додати нові дані про товар у магазині
    public void OpenAddProductInShopInfo() {
        AddProductInShopInfo addProductInShopInfo = new AddProductInShopInfo();
        addProductInShopInfo.setVisible(true);
        this.dispose();
    }

    public class AddProductInShopInfo extends JFrame {

        public AddProductInShopInfo() {

            final boolean[] isPromotionalProduct = {false};

            setTitle("Додати нові дані про товар у магазині");
            setSize(755, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));

            JLabel label = new JLabel("Додати нові дані про товар у магазині");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть UPC товару:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 300, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton randomButton = new JButton("Згенерувати номер");
            randomButton.setBounds(18, 135, 150, 25);

            JLabel label3 = new JLabel("Виберіть тип товару:");
            label3.setBounds(24, 138, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JRadioButton r1 =new JRadioButton("а) Звичайний");
            JRadioButton r2 =new JRadioButton("б) Акційний");

            ButtonGroup group = new ButtonGroup();
            group.add(r1);
            group.add(r2);

            r1.setBounds(20,200,140,30);
            r2.setBounds(150,200,140,30);



            randomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String randomNum = generateRandom13DigitNumber();
                    textField.setText(randomNum);
                }
            });


            r1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (r1.isSelected()) {
                        isPromotionalProduct[0] = false;
                    }
                }
            });

            r2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (r2.isSelected()) {
                        isPromotionalProduct[0] = true;
                    }
                }
            });


            JLabel label5 = new JLabel("Виберіть товар:");
            label5.setBounds(424, 40, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            String[] choices = Manager_Query.getProductArray();
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(420, 94, 300, 50);
            panel.add(comboBox);


            JLabel label6 = new JLabel("Введіть ціну товара (за 1 шт):");
            label6.setBounds(424, 138, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JTextField textField6 = new JTextField(10);
            textField6.setBounds(424, 203, 300, 25);
            textField6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label7 = new JLabel("Введіть кількість товару:");
            label7.setBounds(424, 206, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JTextField textField7 = new JTextField(10);
            textField7.setBounds(424, 271, 300, 25);
            textField7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton addButton = new JButton("Додати товар до магазину");
            addButton.setBounds(270, 380, 210, 60);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("") ||
                            textField6.getText().equals("") ||
                            textField7.getText().equals("")) {
                        JOptionPane.showMessageDialog(AddProductInShopInfo.this,
                                "Для додавання товару у магазин необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!r1.isSelected() && !r2.isSelected()){
                        JOptionPane.showMessageDialog(AddProductInShopInfo.this,
                                "Для додавання товару у магазин необхідно обрати тип товару. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String upc = textField.getText();

                        String selectedText = (String) comboBox.getSelectedItem();
                        String[] parts = selectedText.split(":");
                        int category_number = Integer.parseInt(parts[0].trim());
                        BigDecimal selling_price = new BigDecimal(textField6.getText());
                        int product_number = Integer.parseInt(textField7.getText());

                        Manager_Query.addProductInStore(upc, category_number, selling_price, product_number, isPromotionalProduct[0]);
                    }
                }
            });

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();

                }
            });

            panel.add(backButton);
            panel.add(addButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
            panel.add(textField);
            panel.add(textField6);
            panel.add(textField7);
            panel.add(randomButton);
            panel.add(r1);
            panel.add(r2);
            add(panel);
            setVisible(true);

        }


        private static String generateRandom13DigitNumber() {
            Random random = new Random();
            StringBuilder sb = new StringBuilder(12);
            sb.append((char) ('1' + random.nextInt(9)));
            for (int i = 0; i < 11; i++) {
                sb.append((char) ('0' + random.nextInt(10)));
            }
            return sb.toString();
        }

    }

    // 6. Редагувати нові дані про товар у магазині
    public void OpenEditProductInShop() {
        EditProductInShopInfo editProductInShopInfo = new EditProductInShopInfo();
        editProductInShopInfo.setVisible(true);
        this.dispose();
    }

    public class EditProductInShopInfo extends JFrame {

        public EditProductInShopInfo() {

            final boolean[] isPromotionalProduct = {false};

            setTitle("Редагувати дані про товар у магазині");
            setSize(980, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JScrollPane scrollPane = Cashier_Query.showProductsInStore();
            scrollPane.setBounds(24, 70, 600, 350);
            panel.add(scrollPane);

            JLabel label = new JLabel("Редагувати дані про товар у магазині");
            label.setBounds(20, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 21));

            JTextField textField_product = new JTextField(10);
            textField_product.setBounds(24, 450, 600, 20);
            textField_product.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label_pr = new JLabel("Товар, який потрібно редагувати: ");
            label_pr.setBounds(24, 385, 600, 100);
            Font labelFont_pr = label.getFont();
            label_pr.setFont(new Font(labelFont_pr.getName(), Font.PLAIN, 12));

            JLabel label_pr_code = new JLabel("UPC товару, який потрібно редагувати: ");
            label_pr_code.setBounds(24, 435, 600, 100);
            Font labelFont_pr_code = label.getFont();
            label_pr_code.setFont(new Font(labelFont_pr_code.getName(), Font.PLAIN, 12));

            JTextField textField_pr_code = new JTextField(10);
            textField_pr_code.setBounds(270, 476, 355, 19);
            textField_pr_code.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            ///////////

            JLabel label3 = new JLabel("Виберіть новий тип товару:");
            label3.setBounds(654, 30, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JRadioButton r1 =new JRadioButton("а) Звичайний");
            JRadioButton r2 =new JRadioButton("б) Акційний");

            ButtonGroup group = new ButtonGroup();
            group.add(r1);
            group.add(r2);

            r1.setBounds(650,92,140,30);
            r2.setBounds(780,92,140,30);

            r1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (r1.isSelected()) {
                        isPromotionalProduct[0] = false;
                    }
                }
            });

            r2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (r2.isSelected()) {
                        isPromotionalProduct[0] = true;
                    }
                }
            });



            JLabel label4 = new JLabel("Введіть UPC-Prom товару:");
            label4.setBounds(654, 127, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField5 = new JTextField(10);
            textField5.setBounds(654, 192, 300, 25);
            textField5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label6 = new JLabel("Введіть нову ціну товара (за 1 шт):");
            label6.setBounds(654, 195, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JTextField textField6 = new JTextField(10);
            textField6.setBounds(654, 260, 300, 25);
            textField6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label7 = new JLabel("Введіть нову кількість товару:");
            label7.setBounds(654, 263, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JTextField textField7 = new JTextField(10);
            textField7.setBounds(654, 328, 300, 25);
            textField7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton editButton = new JButton("Редагувати товар у магазині");
            editButton.setBounds(705, 370, 200, 60);

            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField_product.getText().equals("") || textField_pr_code.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditProductInShopInfo.this,
                                "Для редагування товара у магазині спочатку потрібно обрати його в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (textField6.getText().equals("") ||
                               textField7.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditProductInShopInfo.this,
                                "Для редагування товара у магазині необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String upc = textField_pr_code.getText();
                        String upc_prom = textField5.getText().isEmpty() ? null : textField5.getText();

                        BigDecimal selling_price = new BigDecimal(textField6.getText());
                        int product_number = Integer.parseInt(textField7.getText());

                        Manager_Query.editProductInStore(upc, upc_prom, selling_price, product_number, isPromotionalProduct[0]);
                    }
                }
            });


            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();


                }
            });

            panel.add(backButton);
            panel.add(label);
            panel.add(label3);
            panel.add(label_pr_code);
            panel.add(label_pr);
            panel.add(label4);
            panel.add(label6);
            panel.add(label7);
            panel.add(textField5);
            panel.add(textField6);
            panel.add(textField7);
            panel.add(editButton);
            panel.add(textField_pr_code);
            panel.add(textField_product);
            panel.add(r1);
            panel.add(r2);

            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String upc = table.getValueAt(selectedRow, 0).toString();
                            boolean isPromotional = Manager_Query.isProductPromotional(upc);
                            String product_name = table.getValueAt(selectedRow, 2).toString();
                            String category = table.getValueAt(selectedRow, 3).toString();
                            String amount = table.getValueAt(selectedRow, 4).toString();
                            String price = table.getValueAt(selectedRow, 5).toString();
                            String type = table.getValueAt(selectedRow, 7).toString();

                            if (isPromotional) {
                                label4.setEnabled(false);
                                textField5.setEnabled(false);
                                r2.setSelected(true);
                                r1.setSelected(false);
                            } else {
                                label4.setEnabled(true);
                                textField5.setEnabled(true);
                                r2.setSelected(false);
                                r1.setSelected(true);
                            }

                            textField_product.setText("[" + category + "] " + product_name + ". Кількість: " + amount + ". Ціна: " + price + " грн за 1 шт. Тип: " + type);
                            textField_pr_code.setText(upc);
                        }
                    }
                }
            });
        }
    }

    // 7. Видалити нові дані про товар у магазині
    public void OpenDeleteProductInShop() {
        DeleteProductInShopInfo deleteProductInShopInfo = new DeleteProductInShopInfo();
        deleteProductInShopInfo.setVisible(true);
        this.dispose();
    }

    public class DeleteProductInShopInfo extends JFrame {

        public DeleteProductInShopInfo() {
            setTitle("Видалити дані про товар у магазині");
            setSize(900, 630);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Видалити дані про товар у магазині");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Товар, який потрібно видалити: ");
            label2.setBounds(24, 385, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 12));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 450, 850, 20);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("UPC товару: ");
            label3.setBounds(24, 435, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 12));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(24, 500, 200, 20);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton deleteButton = new JButton("Видалити");
            deleteButton.setBounds(230, 495, 100, 30);

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Cashier_Query.showProductsInStore();
            scrollPane.setBounds(24, 70, 850, 350);
            panel.add(scrollPane);

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("") || textField2.getText().equals("")) {
                        JOptionPane.showMessageDialog(DeleteProductInShopInfo.this,
                                "Для видалення товару спочатку потрібно вибрати його в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String upc_product = textField2.getText();
                        Manager_Query.deleteProductInShop(upc_product);
                    }
                }
            });

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 550, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();


                }
            });

            panel.add(backButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(textField);
            panel.add(textField2);
            panel.add(deleteButton);
            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String upc = table.getValueAt(selectedRow, 0).toString();
                            String product_name = table.getValueAt(selectedRow, 2).toString();
                            String category = table.getValueAt(selectedRow, 3).toString();
                            String amount = table.getValueAt(selectedRow, 4).toString();
                            String price = table.getValueAt(selectedRow, 5).toString();
                            String type = table.getValueAt(selectedRow, 7).toString();


                            textField.setText("[" + category + "] " + product_name + ". Кількість: " + amount + ". Ціна: " + price + " грн за 1 шт. Тип: " + type);
                            textField2.setText(upc);
                        }
                    }
                }
            });

        }
    }

    // 8. Надрукувати звіт про товари у магазині
    public void OpenPrintProductInShopReport() {
        PrintProductInShopReport printProductInShopReport = new PrintProductInShopReport();
        printProductInShopReport.setVisible(true);
        this.dispose();
    }

    public class PrintProductInShopReport extends JFrame {

        public PrintProductInShopReport() {
            setTitle("Звіт про товари у магазині");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Товари у магазині:");
            label.setBounds(24, 5, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Переглянути попередній звіт:");
            label2.setBounds(24, 100, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JButton checkButton = new JButton("Переглянути звіт");
            checkButton.setBounds(24, 165, 180, 60);
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String filePath = "Звіти/product_in_store_report.pdf";
                    File file = new File(filePath);

                    if (file.exists() && !file.isDirectory()) {
                        PDFViewer pdfViewer = new PDFViewer("Звіт по продуктам у магазині", 1500);
                        pdfViewer.openPDF(filePath);
                        pdfViewer.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Такого звіту не існує", "Помилка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            JLabel label3 = new JLabel("Згенерувати новий звіт:");
            label3.setBounds(24, 196, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JButton printButton = new JButton("Новий звіт");
            printButton.setBounds(24, 261, 180, 60);
            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PDFReportGenerator pdf = new PDFReportGenerator();
                    pdf.GenerateReportForProductsInStore();
                    JOptionPane.showMessageDialog(PrintProductInShopReport.this,
                            "Звіт збережено у файлі product_in_store_report.pdf",
                            "Звіт збережено",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });


            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();

                }
            });

            panel.add(backButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(checkButton);
            panel.add(printButton);
            add(panel);
            setVisible(true);

        }
    }

    // 9. Додати нові дані про категорії товарів
    public void OpenAddProductCategory() {
        AddProductCategory addProductCategory = new AddProductCategory();
        addProductCategory.setVisible(true);
        this.dispose();
    }


    public class AddProductCategory extends JFrame {
        private JTextField textField;
        private int initialValue;

        public AddProductCategory() {
            setTitle("Додати категорію товарів");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));

            JLabel label = new JLabel("Додати категорію товарів");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть номер категорії:");
            label2.setBounds(24, 60, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            textField = new JTextField(10);
            textField.setBounds(24, 125, 370, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Введіть назву категорії:");
            label3.setBounds(24, 128, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(24, 193, 370, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton addButton = new JButton("Додати категорію");
            addButton.setBounds(130, 258, 170, 60);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("") || textField2.getText().equals("")) {
                        JOptionPane.showMessageDialog(AddProductCategory.this,
                                "Для додавання категорії потрібно заповнити необхідні поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int category_number = Integer.parseInt(textField.getText());
                        Manager_Query.addCategory(category_number, textField2);
                        category_number++; // Збільшуємо значення на 1
                        textField.setText(String.valueOf(category_number));
                        textField2.setText("");
                        saveInitialValue(category_number);
                    }
                }
            });


            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();
                }
            });

            initialValue = loadInitialValue();
            textField.setText(String.valueOf(initialValue));

            panel.add(backButton);
            panel.add(addButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(textField);
            panel.add(textField2);
            add(panel);
            setVisible(true);
        }

        private void saveInitialValue(int value) {
            Preferences prefs = Preferences.userNodeForPackage(AddProductCategory.class);
            prefs.putInt("categoryNumber", value);
        }

        private int loadInitialValue() {
            Preferences prefs = Preferences.userNodeForPackage(AddProductCategory.class);
            return prefs.getInt("categoryNumber", 11);
        }

    }

    // 10. Редагувати дані про категорії товарів
    public void OpenEditProductCategory() {
        EditProductCategory editProductCategory = new EditProductCategory();
        editProductCategory.setVisible(true);
        this.dispose();
    }

    public class EditProductCategory extends JFrame {

        public EditProductCategory() {
            setTitle("Редагувати категорію товарів");
            setSize(450, 600);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Редагувати категорію товарів");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JScrollPane scrollPane = Manager_Query.showСatagories();
            scrollPane.setBounds(24, 70, 405, 250);
            panel.add(scrollPane);

            JLabel label_category = new JLabel("Категорія, яку потрібно редагувати: ");
            label_category.setBounds(24, 290, 600, 100);
            Font labelFont_category = label.getFont();
            label_category.setFont(new Font(labelFont_category.getName(), Font.PLAIN, 15));

            JTextField textField_category = new JTextField(10);
            textField_category.setBounds(26, 360, 400, 23);
            textField_category.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label_new = new JLabel("Введіть нову назву категорії: ");
            label_new.setBounds(24, 365, 600, 100);
            Font labelFont_new_category = label.getFont();
            label_new.setFont(new Font(labelFont_new_category.getName(), Font.PLAIN, 15));

            JTextField textField_new = new JTextField(10);
            textField_new.setBounds(26, 435, 400, 23);
            textField_new.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton editButton = new JButton("Редагувати");
            editButton.setBounds(150, 470, 150, 50);

            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField_category.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditProductCategory.this,
                                "Для редагування категорії спочатку потрібно її вибрати в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (textField_new.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditProductCategory.this,
                                "Для редагування категорії потрібно надати їй нову назву. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String category = textField_category.getText();
                        String[] category_str = category.split(":", 2);
                        int category_id = Integer.parseInt(category_str[0]);

                        String new_category_name = textField_new.getText();
                        Manager_Query.editCategory(new_category_name, category_id);

                    }
                }
            });

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 520, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();

                }
            });

            panel.add(backButton);
            panel.add(label);
            panel.add(label_category);
            panel.add(textField_category);
            panel.add(label_new);
            panel.add(textField_new);
            panel.add(editButton);

            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String categoryId = table.getValueAt(selectedRow, 0).toString();
                            String categoryName = table.getValueAt(selectedRow, 1).toString();
                            textField_category.setText(categoryId + ": " + categoryName);
                        }
                    }
                }
            });
        }
    }

    // 11. Видалити дані про категорії товарів
    public void OpenDeleteProductCategory() {
        DeleteProductCategory deleteProductCategory = new DeleteProductCategory();
        deleteProductCategory.setVisible(true);
        this.dispose();
    }

    public class DeleteProductCategory extends JFrame {

        public DeleteProductCategory() {
            setTitle("Видалити категорію товарів");
            setSize(450, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));

            JLabel label = new JLabel("Видалити категорію товарів");
            label.setBounds(20, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 21));

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Manager_Query.showСatagories();
            scrollPane.setBounds(24, 70, 405, 350);
            panel.add(scrollPane);

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 450, 400, 20);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label2 = new JLabel("Категорію, яку потрібно видалити: ");
            label2.setBounds(24, 385, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 12));

            JButton deleteButton = new JButton("Видалити");
            deleteButton.setBounds(330, 475, 100, 30);

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(DeleteProductCategory.this,
                                "Для видалення категорії спочатку потрібно її вибрати в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String category = textField.getText();
                        String[] category_num = category.split(":", 2);
                        int categoryId = Integer.parseInt(category_num[0]);
                        Manager_Query.deleteCategory(categoryId);
                    }
                }
            });


            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();

                }
            });

            panel.add(backButton);
            panel.add(textField);
            panel.add(label);
            panel.add(label2);
            panel.add(deleteButton);

            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String productId = table.getValueAt(selectedRow, 0).toString();
                            String productName = table.getValueAt(selectedRow, 1).toString();
                            textField.setText(productId + ": " + productName);
                        }
                    }
                }
            });
        }
    }

    // 12. Надрукувати звіт про категорії товарів
    public void OpenPrintProductCategoryReport() {
        PrintProductCategoryReport printProductCategoryReport = new PrintProductCategoryReport();
        printProductCategoryReport.setVisible(true);
        this.dispose();
    }

    public class PrintProductCategoryReport extends JFrame {

        public PrintProductCategoryReport() {
            setTitle("Звіт про категорії товарів");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Категорії товарів:");
            label.setBounds(24, 5, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Переглянути попередній звіт:");
            label2.setBounds(24, 100, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JButton checkButton = new JButton("Переглянути звіт");
            checkButton.setBounds(24, 165, 180, 60);
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String filePath = "Звіти/product_category.pdf";
                    File file = new File(filePath);

                    if (file.exists() && !file.isDirectory()) {
                        PDFViewer pdfViewer = new PDFViewer("Звіт по категоріям товарів", 800);
                        pdfViewer.openPDF(filePath);
                        pdfViewer.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Такого звіту не існує", "Помилка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            JLabel label3 = new JLabel("Згенерувати новий звіт:");
            label3.setBounds(24, 196, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JButton printButton = new JButton("Новий звіт");
            printButton.setBounds(24, 261, 180, 60);
            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PDFReportGenerator pdf = new PDFReportGenerator();
                    pdf.GenerateReportForProductCategory();
                    JOptionPane.showMessageDialog(PrintProductCategoryReport.this,
                            "Звіт збережено у файлі product_category.pdf",
                            "Звіт збережено",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                    Manager_Interface ml = new Manager_Interface();
                    ml.getProductsSection();
                }
            });

            panel.add(backButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(checkButton);
            panel.add(printButton);
            add(panel);
            setVisible(true);

        }
    }

    // 13. Перегляд усіх категорій
    public void OpenCheckAllCatagories() {
        CheckAllCatagories checkAllCatagories = new CheckAllCatagories();
        checkAllCatagories.setVisible(true);
        this.dispose();
    }

    public class CheckAllCatagories extends JFrame {

        public CheckAllCatagories() {
            setTitle("Перегляд усіх категорій");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Усі категорії в магазині");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Manager_Query.showСatagories();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);


            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            panel.add(label);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }

    // 14. Перегляд усіх товарів
    public void OpenCheckAllProducts() {
        CheckAllProducts checkAllProducts = new CheckAllProducts();
        checkAllProducts.setVisible(true);
        this.dispose();
    }

    public class CheckAllProducts extends JFrame {

        public CheckAllProducts() {
            setTitle("Перегляд усіх товарів");
            JLabel label = new JLabel("Усі товари відсортовані за назвою");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));
            JPanel panel = new JPanel(null);


            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Cashier_Query.showProducts();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);


            panel.setBackground(new Color(141, 178,196));
            panel.add(backButton);
            panel.add(label);
            add(panel);
            setVisible(true);

        }

    }

    // 15. Перегляд усіх товарів в магазині
    public void OpenCheckAllProductsInShop() {
        CheckAllProductsInShop checkAllProductsInShop = new CheckAllProductsInShop();
        checkAllProductsInShop.setVisible(true);
        this.dispose();
    }

    public class CheckAllProductsInShop extends JFrame {

        public CheckAllProductsInShop() {
            setTitle("Перегляд усіх товарів в магазині");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Усі товари в магазині відсортовані за назвою");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Cashier_Query.showProductsInStore();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            panel.add(label);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }

    // 16. Пошук товарів за категорією
    public void OpenSearchProductsByCategory() {
        SearchProductsByCategory searchProductsByCategory = new SearchProductsByCategory();
        searchProductsByCategory.setVisible(true);
        this.dispose();
    }

    public class SearchProductsByCategory extends JFrame {

        public SearchProductsByCategory() {
            setTitle("Пошук товарів за категорією");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Пошук товарів за категорією");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть назву категорії:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 530, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton search_button = new JButton("Пошук");
            search_button.setBounds(560, 102, 100, 33);

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            search_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(SearchProductsByCategory.this,
                                "Для пошуку товару потрібно ввести його категорію. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }

                        JScrollPane scrollPane = Cashier_Query.searchProductsByCategory(textField);
                        scrollPane.setBounds(24, 150, 850, 320);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            });

            panel.add(label);
            panel.add(label2);
            panel.add(textField);
            panel.add(search_button);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }

    }

    // 17. Інформація за UPC-товару
    public void OpenSearchProductsByUPC() {
        SearchProductsByUPC searchProductsByUPC = new SearchProductsByUPC();
        searchProductsByUPC.setVisible(true);
        this.dispose();
    }

    public class SearchProductsByUPC extends JFrame {

        public SearchProductsByUPC() {
            setTitle("Пошук товарів за UPC кодом");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Пошук товарів за UPC кодом");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть UPC код:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 530, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton search_button = new JButton("Пошук");
            search_button.setBounds(560, 102, 100, 33);

            search_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(SearchProductsByUPC.this,
                                "Для пошуку товару потрібно ввести його UPC код. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }

                        JScrollPane scrollPane = Cashier_Query.searchProductsByUPC(textField);
                        scrollPane.setBounds(24, 150, 850, 320);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            });

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            panel.add(label);
            panel.add(label2);
            panel.add(textField);
            panel.add(search_button);
            panel.add(backButton);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }

    }

}
