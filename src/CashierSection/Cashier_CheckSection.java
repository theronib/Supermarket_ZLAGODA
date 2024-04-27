package CashierSection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import DataBaseSection.Cashier_Query;
import DataBaseSection.PDFReportGenerator;
import DataBaseSection.PDFViewer;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class Cashier_CheckSection extends JFrame {


    // 1. Додавання чеку
    public void OpenAddCheck() {
        Cashier_CheckSection.AddCheck addCheck = new Cashier_CheckSection.AddCheck();
        addCheck.setVisible(true);
        this.dispose();
    }

    public class AddCheck extends JFrame {

        public AddCheck() {
            setTitle("Додавання чека");
            setSize(1135, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            JLabel label = new JLabel("Додати чек");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JScrollPane scrollPane = Cashier_Query.showProductsInStore();
            scrollPane.setBounds(24, 70, 600, 350);
            panel.add(scrollPane);

            JLabel label2 = new JLabel("Створити чек");
            label2.setBounds(654, -15, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.BOLD, 23));

            JTextField textField_product = new JTextField(10);
            textField_product.setBounds(24, 450, 600, 20);
            textField_product.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label_product = new JLabel("Інформація про товар: ");
            label_product.setBounds(24, 385, 600, 100);
            Font labelFont_product = label.getFont();
            label_product.setFont(new Font(labelFont_product.getName(), Font.PLAIN, 12));

            JLabel label_upc = new JLabel("UPC товару: ");
            label_upc.setBounds(24, 435, 600, 100);
            Font labelFont_upc = label.getFont();
            label_upc.setFont(new Font(labelFont_upc.getName(), Font.PLAIN, 12));

            JTextField textField_upc = new JTextField(10);
            textField_upc.setBounds(100, 476, 525, 19);
            textField_upc.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));


            JLabel label3 = new JLabel("Номер чека:");
            label3.setBounds(654, 295, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(750, 330, 290, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton randomButton = new JButton("Згенерувати номер");
            randomButton.setBounds(647, 95, 150, 25);

            JLabel label4 = new JLabel("Введіть кількість товару:");
            label4.setBounds(654, 328, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField4 = new JTextField(10);
            textField4.setBounds(845, 362, 195, 25);
            textField4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label5 = new JLabel("Введіть ціну товару:");
            label5.setBounds(654, 361, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField5 = new JTextField(10);
            textField5.setBounds(810, 394, 230, 25);
            textField5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton addButton = new JButton("Додати товар");
            addButton.setBounds(880, 430, 160, 40);

            JButton cleanButton = new JButton("Очистити");
            cleanButton.setBounds(878, 470, 165, 30);

            JLabel label6 = new JLabel("Додати товар у чек");
            label6.setBounds(654, 250, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.BOLD, 23));

            JLabel label7 = new JLabel("Введіть номер чека:");
            label7.setBounds(654, 30, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JTextField textField6 = new JTextField(10);
            textField6.setBounds(810, 67, 300, 25);
            textField6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label8 = new JLabel("Виберіть номер касира:");
            label8.setBounds(654, 85, 600, 100);
            Font labelFont8 = label.getFont();
            label8.setFont(new Font(labelFont8.getName(), Font.PLAIN, 15));

            String[] choices = Cashier_Query.getCashierArray();
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(828, 124, 285, 27);
            panel.add(comboBox);

            String[] choices2 = Cashier_Query.getClientsArray();
            JComboBox comboBox2 = new JComboBox(choices2);
            comboBox2.addItem("");
            comboBox2.setBounds(830, 160, 282, 25);
            panel.add(comboBox2);


            JLabel label9 = new JLabel("Виберіть номер клієнта:");
            label9.setBounds(654, 120, 600, 100);
            Font labelFont9 = label.getFont();
            label9.setFont(new Font(labelFont9.getName(), Font.PLAIN, 15));

            JLabel label10 = new JLabel("Виберіть дату створення чека:");
            label10.setBounds(654, 155, 600, 100);
            Font labelFont10 = label.getFont();
            label10.setFont(new Font(labelFont10.getName(), Font.PLAIN, 15));

            JLabel label11 = new JLabel("Сума чеку:");
            label11.setBounds(654, 420, 600, 100);
            Font labelFont11 = label.getFont();
            label11.setFont(new Font(labelFont11.getName(), Font.PLAIN, 15));

            JTextField textField7 = new JTextField(10);
            textField7.setBounds(740, 460, 100, 25);
            textField7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");

            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePicker());
            datePicker.setBounds(885, 192, 225, 25);
            datePicker.setBackground(Color.WHITE);


            JButton addCheckButton = new JButton("Створити чек");
            addCheckButton.setBounds(950, 230, 160, 40);


            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String upc = textField_upc.getText();
                    String check_number = textField.getText();
                    int product_number = Integer.parseInt(textField4.getText());
                    BigDecimal selling_price = new BigDecimal(textField5.getText());

                    Cashier_Query.addSaleToCheckIfAvailable(upc, check_number, product_number, selling_price);
                    double total = Cashier_Query.UpdateSumOfCheck(check_number);
                    textField7.setText(String.valueOf(total));

                }
            });

            cleanButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField4.setText("");
                    textField5.setText("");
                }
            });

            addCheckButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String check_number = textField6.getText();

                    String selectedText = (String) comboBox.getSelectedItem();
                    String[] parts = selectedText.split(":");
                    String id_employee = parts[0].trim();

                    String selectedCard = (String) comboBox2.getSelectedItem();
                    String[] separator = selectedCard.split(":");
                    String card_number = separator[0].trim();

                    UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
                    Date selectedDate = dateModel.getValue();

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = formatter.format(selectedDate);

                    if(card_number.equals("")){
                        Cashier_Query.addCheckWithoutCustomer(check_number, id_employee, formattedDate);
                    }
                    else{
                        Cashier_Query.addCheck(check_number, id_employee, card_number, formattedDate);
                    }
                }
            });


            // КНОПКА ГЕНЕРАЦІЇ НОМЕРА КАРТКА
            randomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(textField_upc.getText().equals("") || textField_product.getText().equals("")){
                        JOptionPane.showMessageDialog(AddCheck.this,
                                "Для додавання чека спочатку потрібно обрати товар з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String randomNum = generateRandom13DigitNumber();
                        textField.setText(randomNum);
                        textField6.setText(randomNum);

                        textField4.setText("");
                        textField5.setText("");
                        textField7.setText("");

                    }
                }
            });

            textField4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String upc = textField_upc.getText();
                    int quantity = Integer.parseInt(textField4.getText());

                    String selectedCard = (String) comboBox2.getSelectedItem();
                    String[] separator = selectedCard.split(":");
                    String card_number = separator[0].trim();

                    if (!upc.isEmpty() && quantity > 0) {
                        double productPrice = Cashier_Query.findProductPrice(upc, card_number) * quantity;
                        textField5.setText(String.valueOf(productPrice));
                    }
                }
            });


            panel.setBackground(new Color(226, 212,175));
            panel.add(backButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label_product);
            panel.add(label_upc);
            panel.add(scrollPane);
            panel.add(textField);
            panel.add(textField_product);
            panel.add(textField_upc);
            panel.add(textField4);
            panel.add(textField5);
            panel.add(textField6);
            panel.add(textField7);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
            panel.add(label8);
            panel.add(label9);
            panel.add(label10);
            panel.add(label11);
            panel.add(randomButton);
            panel.add(addCheckButton);
            panel.add(cleanButton);
            panel.add(addButton);
            panel.add(datePicker);

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
                            String price = table.getValueAt(selectedRow, 5).toString();
                            String type = table.getValueAt(selectedRow, 7).toString();

                            textField_product.setText("[" + category + "] " + product_name + ". Ціна: " + price + " грн за 1 шт. Тип: " + type);
                            textField_upc.setText(upc);
                            textField4.setText("");
                            textField5.setText("");

                        }
                    }
                }
            });
        }

        private static String generateRandom13DigitNumber() {
            Random random = new Random();
            StringBuilder sb = new StringBuilder(10);
            sb.append((char) ('1' + random.nextInt(9)));
            for (int i = 0; i < 9; i++) {
                sb.append((char) ('0' + random.nextInt(10)));
            }
            return sb.toString();
        }

    }


    // 2. Перегляд усіх чеків за день
    public void OpenSeeAllChecksForDay() {
        Cashier_CheckSection.SeeAllChecksForDay seeAllChecksForDay = new Cashier_CheckSection.SeeAllChecksForDay ();
        seeAllChecksForDay.setVisible(true);
        this.dispose();
    }

    public class SeeAllChecksForDay extends JFrame {
        private JTable table;
        private long check_generated_Id;
        private String filename;
        public SeeAllChecksForDay() {
            setTitle("Перегляд чеків створених касиром за певний день");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            this.check_generated_Id = -1;
            table = new JTable();

            JLabel label = new JLabel("Перегляд чеків створених касиром за певний день");
            label.setBounds(24, -15, 650, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть прізвище касира:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 230, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Виберіть дату створення чека:");
            label3.setBounds(314, 40, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JLabel label4 = new JLabel("Номер чека:");
            label4.setBounds(660, 440, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(760, 480, 115, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            // СТВОРЕННЯ КАЛЕНДАРЯ
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");

            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePicker());
            datePicker.setBounds(314, 105, 300, 25);
            datePicker.setBackground(Color.WHITE);

            JButton search_button = new JButton("Пошук");
            search_button.setBounds(660, 102, 100, 33);

            JButton open_button = new JButton("Відкрити");
            open_button.setBounds(757, 510, 100, 33);

            JButton print_check_button = new JButton("Згенерувати чек");
            print_check_button.setBounds(20, 138, 133, 33);

            JButton check_printed_check_button = new JButton("Переглянути згенерований чек");
            check_printed_check_button.setBounds(170, 138, 240, 33);


            panel.setBackground(new Color(226, 212,175));

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
                    if (textField.getText().equals("") || datePicker.getJFormattedTextField().getText().equals("")) {
                        JOptionPane.showMessageDialog(SeeAllChecksForDay.this,
                                "Для пошуку чека потрібно ввести прізвище касира і обрати дату. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
                        Date selectedDate = dateModel.getValue();

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = formatter.format(selectedDate);

                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }
                        JScrollPane scrollPane = Cashier_Query.seeChecksForDay(textField, formattedDate);
                        scrollPane.setBounds(24, 180, 850, 290);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();

                        table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
                        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent event) {
                                if (!event.getValueIsAdjusting()) {
                                    int selectedRow = table.getSelectedRow();
                                    if (selectedRow != -1) {
                                        String checkId = table.getValueAt(selectedRow, 0).toString();
                                        check_generated_Id = Long.parseLong(checkId);
                                        textField2.setText(checkId);
                                    }
                                }
                            }
                        });
                    }
                }
            });

            print_check_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (check_generated_Id != -1) {
                        filename = "check_" + check_generated_Id + ".pdf";
                        PDFReportGenerator pdf = new PDFReportGenerator();
                        pdf.GenerateCheck(check_generated_Id);
                        JOptionPane.showMessageDialog(SeeAllChecksForDay.this,
                                "Чек збережено у файлі " + filename,
                                "Чек збережено",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SeeAllChecksForDay.this,
                                "Спочатку виберіть чек для генерації",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            check_printed_check_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (check_generated_Id != -1) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String checkId = table.getValueAt(selectedRow, 0).toString();
                            long selectedCheckId = Long.parseLong(checkId);
                            if (selectedCheckId == check_generated_Id) {
                                String filePath = "Чеки/" + "check_" + check_generated_Id + ".pdf";
                                File file = new File(filePath);

                                if (file.exists() && !file.isDirectory()) {
                                    PDFViewer pdfViewer = new PDFViewer("Чек № " + check_generated_Id, 1000);
                                    pdfViewer.openPDF(filePath);
                                    pdfViewer.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Чек № " + check_generated_Id + " не був згенерований", "Помилка", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Виберіть чек, який потрібно переглянути", "Помилка", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Виберіть чек для перегляду", "Помилка", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Спочатку виберіть чек для генерації", "Помилка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            open_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField2.getText().equals("")){
                        JOptionPane.showMessageDialog(SeeAllChecksForDay.this,
                                "Для перегляду чека спочатку потрібно його обрати з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        new CheckDetails(textField2);
                    }

                }
            });

            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(search_button);
            panel.add(open_button);
            panel.add(textField);
            panel.add(textField2);
            panel.add(datePicker);
            panel.add(backButton);
            panel.add(print_check_button);
            panel.add(check_printed_check_button);
            add(panel);
            setVisible(true);

        }
    }

    public static class CheckDetails extends JFrame {
        public CheckDetails(JTextField textField) {
            setTitle("Деталі чеку");
            setSize(900, 300);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());

            JScrollPane scrollPane = Cashier_Query.searchCheckByNumber(textField);
            panel.add(scrollPane, BorderLayout.CENTER);

            add(panel);
            setVisible(true);
        }
    }



    // 3. Перегляд усіх чеків за певний період
    public void OpenSeeAllChecksForPeriod() {
        Cashier_CheckSection.SeeAllChecksForPeriod seeAllChecksForPeriod = new Cashier_CheckSection.SeeAllChecksForPeriod();
        seeAllChecksForPeriod.setVisible(true);
        this.dispose();
    }

    public class SeeAllChecksForPeriod extends JFrame {
        private JTable table;
        private long check_generated_Id;
        private String filename;
        public SeeAllChecksForPeriod() {
            setTitle("Перегляд чеків створених касиром за певний період");
            setSize(1000, 580);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.check_generated_Id = -1;

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(226, 212,175));

            table = new JTable();

            JLabel label = new JLabel("Перегляд чеків створених касиром за певний період");
            label.setBounds(24, -15, 650, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть прізвище касира:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 230, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Виберіть початкову дату створення чека:");
            label3.setBounds(314, 40, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JLabel label4 = new JLabel("Виберіть кінцеву дату створення чека:");
            label4.setBounds(664, 40, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JLabel label5 = new JLabel("Номер чека:");
            label5.setBounds(630, 451, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(730, 491, 115, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton open_button = new JButton("Відкрити");
            open_button.setBounds(865, 487, 100, 33);

            JButton print_check_button = new JButton("Згенерувати чек");
            print_check_button.setBounds(20, 138, 133, 33);

            JButton check_printed_check_button = new JButton("Переглянути згенерований чек");
            check_printed_check_button.setBounds(170, 138, 240, 33);


            // СТВОРЕННЯ КАЛЕНДАРЯ 1
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");

            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePicker());
            datePicker.setBounds(314, 105, 300, 25);
            datePicker.setBackground(Color.WHITE);

            // СТВОРЕННЯ КАЛЕНДАРЯ 2
            UtilDateModel model2 = new UtilDateModel();

            JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
            JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DatePicker());
            datePicker2.setBounds(664, 105, 300, 25);
            datePicker2.setBackground(Color.WHITE);

            JButton search_button = new JButton("Пошук");
            search_button.setBounds(868, 138, 100, 33);

            search_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("") || datePicker.getJFormattedTextField().getText().equals("") || datePicker2.getJFormattedTextField().getText().equals("")) {
                        JOptionPane.showMessageDialog(SeeAllChecksForPeriod.this,
                                "Для пошуку чека потрібно ввести прізвище касира і обрати початкову/кінцеву дату. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
                        Date selectedDate = dateModel.getValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = formatter.format(selectedDate);

                        UtilDateModel dateModel2 = (UtilDateModel) datePicker2.getModel();
                        Date selectedDate2 = dateModel2.getValue();
                        String formattedDate2 = formatter.format(selectedDate2);

                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }
                        JScrollPane scrollPane = Cashier_Query.seeChecksForPeriod(textField, formattedDate, formattedDate2);
                        scrollPane.setBounds(24, 180, 940, 300);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();

                        table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
                        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent event) {
                                if (!event.getValueIsAdjusting()) {
                                    int selectedRow = table.getSelectedRow();
                                    if (selectedRow != -1) {
                                        String checkId = table.getValueAt(selectedRow, 0).toString();
                                        check_generated_Id = Long.parseLong(checkId);
                                        textField2.setText(checkId);
                                    }
                                }
                            }
                        });
                    }
                }
            });

            print_check_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (check_generated_Id != -1) {
                        filename = "check_" + check_generated_Id + ".pdf";
                        PDFReportGenerator pdf = new PDFReportGenerator();
                        pdf.GenerateCheck(check_generated_Id);
                        JOptionPane.showMessageDialog(SeeAllChecksForPeriod.this,
                                "Чек збережено у файлі " + filename,
                                "Чек збережено",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SeeAllChecksForPeriod.this,
                                "Спочатку виберіть чек для генерації",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            check_printed_check_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (check_generated_Id != -1) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String checkId = table.getValueAt(selectedRow, 0).toString();
                            long selectedCheckId = Long.parseLong(checkId);
                            if (selectedCheckId == check_generated_Id) {
                                String filePath = "Чеки/" + "check_" + check_generated_Id + ".pdf";
                                File file = new File(filePath);

                                if (file.exists() && !file.isDirectory()) {
                                    PDFViewer pdfViewer = new PDFViewer("Чек № " + check_generated_Id, 1000);
                                    pdfViewer.openPDF(filePath);
                                    pdfViewer.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Чек № " + check_generated_Id + " не був згенерований", "Помилка", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Виберіть чек, який потрібно переглянути", "Помилка", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Виберіть чек для перегляду", "Помилка", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Спочатку виберіть чек для генерації", "Помилка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            open_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField2.getText().equals("")){
                        JOptionPane.showMessageDialog(SeeAllChecksForPeriod.this,
                                "Для перегляду чека спочатку потрібно його обрати з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        new CheckDetails(textField2);
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
            panel.add(label3);
            panel.add(label4);
            panel.add(textField2);
            panel.add(label5);
            panel.add(search_button);
            panel.add(print_check_button);
            panel.add(check_printed_check_button);
            panel.add(textField);
            panel.add(datePicker);
            panel.add(datePicker2);
            panel.add(backButton);
            panel.add(open_button);
            add(panel);
            setVisible(true);

        }
    }


    // 4. Перегляд чеку за його номером
    public void OpenSeeCheckByNumber() {
        Cashier_CheckSection.SeeCheckByNumber seeCheckByNumber = new Cashier_CheckSection.SeeCheckByNumber();
        seeCheckByNumber.setVisible(true);
        this.dispose();
    }

    public class SeeCheckByNumber extends JFrame {
        public SeeCheckByNumber() {
            setTitle("Перегляд чеку за його номером");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            JLabel label = new JLabel("Перегляд усієї інформації про чек за його номером");
            label.setBounds(24, -15, 800, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть номер чеку:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 190, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton search_button = new JButton("Пошук");
            search_button.setBounds(230, 102, 100, 33);

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
                        JOptionPane.showMessageDialog(SeeCheckByNumber.this,
                                "Для пошуку чека потрібно ввести його номер. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }

                        JScrollPane scrollPane = Cashier_Query.searchCheckByNumber(textField);
                        scrollPane.setBounds(24, 150, 850, 320);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            });

            panel.setBackground(new Color(226, 212,175));

            panel.add(label);
            panel.add(label2);
            panel.add(textField);
            panel.add(search_button);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }
}


