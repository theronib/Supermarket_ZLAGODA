package ManagerSection;

import CashierSection.Cashier_ClientsSection;
import DataBaseSection.Cashier_Query;
import DataBaseSection.Manager_Query;
import DataBaseSection.PDFReportGenerator;
import DataBaseSection.PDFViewer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class Manager_СlientsSection extends JFrame {

    //////////////////////
    // СЕКЦІЯ: "КАСИРИ"
    //////////////////////

    // 1. Додати постійного клієнта
    public void OpenAddClient() {
        AddClient addClient = new AddClient();
        addClient.setVisible(true);
        this.dispose();
    }

    public class AddClient extends JFrame {

        public AddClient() {
            setTitle("Додати постійного клієнта");
            setSize(755, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));

            JLabel label = new JLabel("Додати постійного клієнта");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть номер картки:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 300, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton randomButton = new JButton("Згенерувати номер");
            randomButton.setBounds(18, 135, 150, 25);

            JLabel label3 = new JLabel("Введіть прізвище клієнта:");
            label3.setBounds(24, 138, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(24, 203, 300, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label4 = new JLabel("Введіть ім'я клієнта:");
            label4.setBounds(24, 206, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField3 = new JTextField(10);
            textField3.setBounds(24, 271, 300, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label5 = new JLabel("Введіть ім'я по батькові клієнта:");
            label5.setBounds(24, 274, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField4 = new JTextField(10);
            textField4.setBounds(24, 339, 300, 25);
            textField4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label6 = new JLabel("Введіть номер телефона клієнта:");
            label6.setBounds(24, 342, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JTextField textField5 = new JTextField(10);
            textField5.setBounds(24, 407, 300, 25);
            textField5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label7 = new JLabel("Введіть місто проживання клієнта:");
            label7.setBounds(424, 70, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JTextField textField6 = new JTextField(10);
            textField6.setBounds(424, 135, 300, 25);
            textField6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label8 = new JLabel("Введіть вулицю клієнта:");
            label8.setBounds(424, 138, 600, 100);
            Font labelFont8 = label.getFont();
            label8.setFont(new Font(labelFont8.getName(), Font.PLAIN, 15));

            JTextField textField7 = new JTextField(10);
            textField7.setBounds(424, 203, 300, 25);
            textField7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label9 = new JLabel("Введіть zip-код клієнта:");
            label9.setBounds(424, 206, 600, 100);
            Font labelFont9 = label.getFont();
            label9.setFont(new Font(labelFont9.getName(), Font.PLAIN, 15));

            JTextField textField8 = new JTextField(10);
            textField8.setBounds(424, 271, 300, 25);
            textField8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label10 = new JLabel("Введіть відсоток знижки клієнта:");
            label10.setBounds(424, 274, 600, 100);
            Font labelFont10 = label.getFont();
            label10.setFont(new Font(labelFont10.getName(), Font.PLAIN, 15));

            JTextField textField9 = new JTextField(10);
            textField9.setBounds(424, 339, 300, 25);
            textField9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));


            JButton addButton = new JButton("Додати клієнта");
            addButton.setBounds(470, 440, 200, 60);

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            // КНОПКА ГЕНЕРАЦІЇ НОМЕРА КАРТКА
            randomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String randomNum = generateRandom13DigitNumber();
                    textField.setText(randomNum);
                }
            });

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("") ||
                            textField2.getText().equals("") ||
                            textField3.getText().equals("") ||
                            textField4.getText().equals("") ||
                            textField6.getText().equals("") ||
                            textField7.getText().equals("") ||
                            textField8.getText().equals("") ||
                            textField9.getText().equals("")) {
                        JOptionPane.showMessageDialog(AddClient.this,
                                "Для додавання клієнта необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String cardNumber = textField.getText();
                        String surname = textField2.getText();
                        String firstName = textField3.getText();
                        String lastName = textField4.getText();
                        String phoneNumber = textField5.getText();
                        String city = textField6.getText();
                        String street = textField7.getText();
                        String zipCode = textField8.getText();
                        int discount = Integer.parseInt(textField9.getText());

                        if (phoneNumber.equals("")) {
                            Cashier_Query.addClientWithoutPhoneToDatabase(cardNumber, surname, firstName, lastName, city, street, zipCode, discount);
                        } else {
                            Cashier_Query.addClientToDatabase(cardNumber, surname, firstName, lastName, phoneNumber, city, street, zipCode, discount);
                        }
                    }
                }
            });


            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
            panel.add(label8);
            panel.add(label9);
            panel.add(label10);
            panel.add(textField);
            panel.add(textField2);
            panel.add(textField3);
            panel.add(textField4);
            panel.add(textField5);
            panel.add(textField6);
            panel.add(textField7);
            panel.add(textField8);
            panel.add(textField9);
            panel.add(randomButton);
            panel.add(addButton);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }

    }

    private static String generateRandom13DigitNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(13);
        sb.append((char) ('1' + random.nextInt(9)));
        for (int i = 0; i < 12; i++) {
            sb.append((char) ('0' + random.nextInt(10)));
        }
        return sb.toString();
    }

    // 2. Редагувати постійного клієнта
    public void OpenEditClient() {
        EditClient editClient = new EditClient();
        editClient.setVisible(true);
        this.dispose();
    }

    public class EditClient extends JFrame {

        public EditClient() {
            setTitle("Редагувати постійного клієнта");
            setSize(1220, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));


            JLabel label = new JLabel("Редагувати постійного клієнта");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JScrollPane scrollPane = Cashier_Query.showClients();
            scrollPane.setBounds(24, 70, 600, 350);
            panel.add(scrollPane);

            JTextField textField_client = new JTextField(10);
            textField_client.setBounds(24, 450, 600, 20);
            textField_client.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label_client = new JLabel("Клієнта, якого потрібно редагувати: ");
            label_client.setBounds(24, 385, 600, 100);
            Font labelFont_client = label.getFont();
            label_client.setFont(new Font(labelFont_client.getName(), Font.PLAIN, 12));

            JLabel label_clientcode = new JLabel("Номер клієнта, якого потрібно редагувати: ");
            label_clientcode.setBounds(24, 435, 600, 100);
            Font labelFont_clientcode = label.getFont();
            label_clientcode.setFont(new Font(labelFont_clientcode.getName(), Font.PLAIN, 12));

            JTextField textField_clientcode = new JTextField(10);
            textField_clientcode.setBounds(290, 476, 335, 19);
            textField_clientcode.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Введіть нове прізвище клієнта:");
            label3.setBounds(654, 30, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(887, 67, 300, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label4 = new JLabel("Введіть нове ім'я клієнта:");
            label4.setBounds(654, 70, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField3 = new JTextField(10);
            textField3.setBounds(845, 109, 340, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label5 = new JLabel("Введіть нове ім'я по батькові клієнта:");
            label5.setBounds(654, 110, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField4 = new JTextField(10);
            textField4.setBounds(935, 148, 250, 25);
            textField4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label6 = new JLabel("Введіть новий номер телефона клієнта:");
            label6.setBounds(654, 150, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JTextField textField5 = new JTextField(10);
            textField5.setBounds(950, 187, 235, 25);
            textField5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label7 = new JLabel("Введіть нове місто проживання клієнта:");
            label7.setBounds(654, 190, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JTextField textField6 = new JTextField(10);
            textField6.setBounds(951, 229, 234, 25);
            textField6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label8 = new JLabel("Введіть нову вулицю клієнта:");
            label8.setBounds(654, 230, 600, 100);
            Font labelFont8 = label.getFont();
            label8.setFont(new Font(labelFont8.getName(), Font.PLAIN, 15));

            JTextField textField7 = new JTextField(10);
            textField7.setBounds(875, 267, 310, 25);
            textField7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label9 = new JLabel("Введіть zip-код клієнта:");
            label9.setBounds(654, 270, 600, 100);
            Font labelFont9 = label.getFont();
            label9.setFont(new Font(labelFont9.getName(), Font.PLAIN, 15));

            JTextField textField8 = new JTextField(10);
            textField8.setBounds(840, 308, 345, 25);
            textField8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label10 = new JLabel("Введіть новий відсоток знижки клієнта:");
            label10.setBounds(654, 310, 600, 100);
            Font labelFont10 = label.getFont();
            label10.setFont(new Font(labelFont10.getName(), Font.PLAIN, 15));

            JTextField textField9 = new JTextField(10);
            textField9.setBounds(950, 349, 235, 25);
            textField9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));


            JButton editButton = new JButton("Редагувати клієнта");
            editButton.setBounds(840, 410, 200, 60);

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();
                }
            });

            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField2.getText().equals("") ||
                            textField3.getText().equals("") ||
                            textField4.getText().equals("") ||
                            textField6.getText().equals("") ||
                            textField7.getText().equals("") ||
                            textField8.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditClient.this,
                                "Для редагування клієнта необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (textField_clientcode.getText().equals("") || textField_client.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditClient.this,
                                "Для редагування клієнта спочатку потрібно обрати його в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String code = textField_clientcode.getText();
                        String newSurname = textField2.getText();
                        String newName = textField3.getText();
                        String newPatronymic = textField4.getText();
                        String newPhone = textField5.getText();
                        String newTown = textField6.getText();
                        String newStreet = textField7.getText();
                        String newZip = textField8.getText();
                        int newDiscount = Integer.parseInt(textField9.getText());
                        Cashier_Query.editClient(code, newSurname, newName, newPatronymic, newPhone, newTown, newStreet, newZip, newDiscount);
                    }
                }
            });

            panel.add(scrollPane);
            panel.add(textField_client);
            panel.add(textField_clientcode);
            panel.add(label_client);
            panel.add(label_clientcode);
            panel.add(label);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
            panel.add(label8);
            panel.add(label9);
            panel.add(label10);
            panel.add(textField2);
            panel.add(textField3);
            panel.add(textField4);
            panel.add(textField5);
            panel.add(textField6);
            panel.add(textField7);
            panel.add(textField8);
            panel.add(textField9);
            panel.add(editButton);
            panel.add(backButton);
            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String code = table.getValueAt(selectedRow, 0).toString();
                            String surname = table.getValueAt(selectedRow, 1).toString();
                            String name = table.getValueAt(selectedRow, 2).toString();
                            String patronymic = table.getValueAt(selectedRow, 3).toString();
                            String phone = table.getValueAt(selectedRow, 4) != null ? table.getValueAt(selectedRow, 4).toString() : "Невідомий";
                            String town = table.getValueAt(selectedRow, 5).toString();
                            String street = table.getValueAt(selectedRow, 6).toString();
                            String zip = table.getValueAt(selectedRow, 7).toString();

                            textField_client.setText(surname + " " + name + " " + patronymic + ". " + phone + ". " + town + ", вул. " + street + ", " + zip);
                            textField_clientcode.setText(code);
                        }
                    }
                }
            });
        }
    }

    // 3. Видалити постійного клієнта
    public void OpenRemoveClient() {
        RemoveClient removeClient = new RemoveClient();
        removeClient.setVisible(true);
        this.dispose();
    }

    public class RemoveClient extends JFrame {

        public RemoveClient() {
            setTitle("Видалити постійного клієнта");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Видалити постійного клієнта");
            label.setBounds(24, -15, 650, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Номер клієнта:");
            label2.setBounds(505, 451, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(625, 491, 115, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton delete_empl_button = new JButton("\uD83D\uDDD1   Видалити");
            delete_empl_button.setBounds(755, 487, 123, 33);

            JScrollPane scrollPane = Cashier_Query.showClients();
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

            delete_empl_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(RemoveClient.this,
                                "Для видалення клієнта спочатку потрібно вибрати його в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String cust_id = textField.getText();
                        Manager_Query.deleteClient(cust_id);
                    }
                }
            });

            panel.add(backButton);
            panel.add(delete_empl_button);
            panel.add(label);
            panel.add(label2);
            panel.add(textField);
            add(panel);
            setVisible(true);


            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String emplId = table.getValueAt(selectedRow, 0).toString();
                            textField.setText(emplId);
                        }
                    }
                }
            });
        }

    }

    // 4. Надрукувати звіт про усіх постійних клієнтів
    public void OpenPrintClientReport() {
        PrintClientReport printClientReport = new PrintClientReport();
        printClientReport.setVisible(true);
        this.dispose();
    }

    public class PrintClientReport extends JFrame {

        public PrintClientReport() {
            setTitle("Надрукувати звіт про усіх постійних клієнтів");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Постійні клієнти:");
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
                    String filePath = "Звіти/customers_with_card.pdf";
                    File file = new File(filePath);

                    if (file.exists() && !file.isDirectory()) {
                        PDFViewer pdfViewer = new PDFViewer("Звіт по клієнтам з карткою", 1500);
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
                    pdf.GenerateReportForClients();
                    JOptionPane.showMessageDialog(PrintClientReport.this,
                            "Звіт збережено у файлі customers_with_card.pdf",
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
                    ml.getClientsSection();
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

    // 5. Перегляд клієнтів за прізвищем
    public void OpenCheckClientsBySurname() {
        CheckClientsBySurname checkClientsBySurname = new CheckClientsBySurname();
        checkClientsBySurname.setVisible(true);
        this.dispose();
    }

    public class CheckClientsBySurname extends JFrame {

        public CheckClientsBySurname() {
            setTitle("Перегляд постійних клієнтів");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));


            JLabel label = new JLabel("Усі постійні клієнти відсортовані за прізвищем");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Cashier_Query.showClients();
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

            panel.add(backButton);
            panel.add(label);
            add(panel);
            setVisible(true);


        }

    }

    // 6. Перегляд клієнтів з карткою клієнта
    public void OpenCheckClientsWithCard() {
        ClientsWithCard clientsWithCard = new ClientsWithCard();
        clientsWithCard.setVisible(true);
        this.dispose();
    }

    public class ClientsWithCard extends JFrame {

        public ClientsWithCard() {
            setTitle("Пошук постійних клієнтів за певним відсотком знижки");
            setResizable(false);
            setSize(900, 580);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));

            JLabel label = new JLabel("Пошук постійних клієнтів за певним відсотком знижки");
            label.setBounds(24, -15, 800, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 21));

            JLabel label2 = new JLabel("Введіть відсоток знижки клієнта:");
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
                        JOptionPane.showMessageDialog(ClientsWithCard.this,
                                "Для пошуку клієнта потрібно ввести відсоток знижки. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }

                        JScrollPane scrollPane = Manager_Query.searchClientsByDiscount(textField);
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

            panel.add(backButton);
            panel.add(label);
            panel.add(label2);
            panel.add(textField);
            panel.add(search_button);
            add(panel);
            setVisible(true);


        }

    }

    public void OpenClientsWithNoPurchases() {
        ClientsWithNoPurchases clientsWithNoPurchases = new ClientsWithNoPurchases();
        clientsWithNoPurchases.setVisible(true);
        this.dispose();
    }

    public class ClientsWithNoPurchases extends JFrame {
        public ClientsWithNoPurchases() {
            setTitle("Перегляд клієнтів без покупок і номеру телефона");
            setSize(1200, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Перегляд клієнтів без покупок і номеру телефона");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 21));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            JScrollPane scrollPane = Manager_Query.showCustomersWithNoPurchases();
            scrollPane.setBounds(24, 70, 1150, 400);
            panel.add(scrollPane);

            panel.add(label);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }

    }

}
