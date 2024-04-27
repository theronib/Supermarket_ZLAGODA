package ManagerSection;

import CashierSection.Cashier_CheckSection;
import CashierSection.DatePicker;
import DataBaseSection.Cashier_Query;
import DataBaseSection.Manager_Query;
import DataBaseSection.PDFReportGenerator;
import DataBaseSection.PDFViewer;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Manager_SalesSection extends JFrame {

    //////////////////////
    // СЕКЦІЯ: "ПРОДАЖІ"
    //////////////////////


    // 1. Чеки касира за період
    public void openCashierReceipt() {
        CheckCashierReceipt checkCashierReceipt = new CheckCashierReceipt();
        checkCashierReceipt.setVisible(true);
        this.dispose();
    }

    public class CheckCashierReceipt extends JFrame {
        private JTable table;
        private long check_generated_Id;
        private String filename;

        public CheckCashierReceipt() {
            setTitle("Перегляд чеків створених касиром за певний період");
            setSize(1000, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.check_generated_Id = -1;

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

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

            JLabel label6 = new JLabel("Загальна сума проданих товарів з чеків:");
            label6.setBounds(570, 415, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JTextField textField3 = new JTextField(10);
            textField3.setBounds(880, 453, 80, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton open_button = new JButton("Відкрити");
            open_button.setBounds(865, 487, 100, 33);

            JButton delete_check_button = new JButton("\uD83D\uDDD1   Видалити");
            delete_check_button.setBounds(430, 138, 123, 33);


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
                        JOptionPane.showMessageDialog(CheckCashierReceipt.this,
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
                        scrollPane.setBounds(24, 180, 940, 260);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();

                        double total_sales = Manager_Query.findSumOfCheckByCashier(formattedDate, formattedDate2, textField.getText());
                        textField3.setText(String.valueOf(total_sales));

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
                                    else {
                                        textField2.setText("");
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
                        JOptionPane.showMessageDialog(CheckCashierReceipt.this,
                                "Чек збережено у файлі " + filename,
                                "Чек збережено",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(CheckCashierReceipt.this,
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


            delete_check_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField2.getText().equals("")) {
                        JOptionPane.showMessageDialog(CheckCashierReceipt.this,
                                "Для видалення чека потрібно обрати його з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String value = textField2.getText();
                            Manager_Query.deleteCheck(value);
                        } else {
                            JOptionPane.showMessageDialog(CheckCashierReceipt.this,
                                    "Для видалення чека потрібно обрати його з таблиці. Будь ласка, спробуйте ще раз.",
                                    "Помилка введення",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            open_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField2.getText().equals("")){
                        JOptionPane.showMessageDialog(CheckCashierReceipt.this,
                                "Для перегляду чека спочатку потрібно його обрати з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        new Cashier_CheckSection.CheckDetails(textField2);
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
            panel.add(label6);
            panel.add(textField2);
            panel.add(textField3);
            panel.add(label5);
            panel.add(search_button);
            panel.add(textField);
            panel.add(datePicker);
            panel.add(delete_check_button);
            panel.add(print_check_button);
            panel.add(check_printed_check_button);
            panel.add(datePicker2);
            panel.add(open_button);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }

    }

    // 2. Cередня ціна продажу за категорією товару
    public void AvgPriceOfCategory () {
        AvgPriceOfCategory avgPriceOfCategory = new AvgPriceOfCategory();
        avgPriceOfCategory.setVisible(true);
        this.dispose();
    }


    public class AvgPriceOfCategory extends JFrame {
        public AvgPriceOfCategory() {
            setTitle("Cередня ціна продажу за категорією товару");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            JLabel label = new JLabel("Cередня ціна продажу за категорією товару");
            label.setBounds(24, -15, 650, 100);
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

            panel.setBackground(new Color(226, 212,175));

            search_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(AvgPriceOfCategory.this,
                                "Для знаходження середньої ціни продажу за категорією товару необхідно ввести назву категорії. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }

                        JScrollPane scrollPane = Manager_Query.averageSellingPriceOfCategory(textField);
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
            panel.setBackground(new Color(141, 178,196));
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }

    }

    ////////////////////////////


    // 3. Чеки усіх касирів за період
    public void openAllСashierReceipt() {
        CheckAllCashierReceipt checkAllCashierReceipt = new CheckAllCashierReceipt();
        checkAllCashierReceipt.setVisible(true);
        this.dispose();
    }

    public class CheckAllCashierReceipt extends JFrame {
        private JTable table;
        private long check_generated_Id;
        private String filename;

        public CheckAllCashierReceipt() {
            setTitle("Перегляд чеків створених усіма касирами за певний період");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.check_generated_Id = -1;

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            table = new JTable();

            JLabel label = new JLabel("Перегляд чеків створених усіма касирами за певний період");
            label.setBounds(24, -15, 750, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Виберіть початкову дату створення чека:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JLabel label3 = new JLabel("Виберіть кінцеву дату створення чека:");
            label3.setBounds(364, 40, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JLabel label4 = new JLabel("Номер чека:");
            label4.setBounds(660, 440, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JButton search_button = new JButton("Пошук");
            search_button.setBounds(690, 101, 100, 33);

            JTextField textField = new JTextField(10);
            textField.setBounds(760, 480, 115, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton open_button = new JButton("Відкрити");
            open_button.setBounds(757, 510, 100, 33);

            JLabel label6 = new JLabel("Загальна сума проданих товарів з чеків:");
            label6.setBounds(223, 440, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JTextField textField3 = new JTextField(10);
            textField3.setBounds(533, 480, 80, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton delete_check_button = new JButton("\uD83D\uDDD1   Видалити");
            delete_check_button.setBounds(430, 138, 123, 33);

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
            datePicker.setBounds(24, 105, 300, 25);
            datePicker.setBackground(Color.WHITE);

            // СТВОРЕННЯ КАЛЕНДАРЯ 2
            UtilDateModel model2 = new UtilDateModel();

            JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
            JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DatePicker());
            datePicker2.setBounds(364, 105, 300, 25);
            datePicker2.setBackground(Color.WHITE);

            search_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (datePicker.getJFormattedTextField().getText().equals("") || datePicker2.getJFormattedTextField().getText().equals("")) {
                        JOptionPane.showMessageDialog(CheckAllCashierReceipt.this,
                                "Для пошуку чека потрібно обрати початкову і кінцеву дату. Будь ласка, спробуйте ще раз.",
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
                        JScrollPane scrollPane = Manager_Query.seeAllChecksForPeriod(formattedDate, formattedDate2);
                        scrollPane.setBounds(24, 180, 850, 280);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();

                        double total_sales = Manager_Query.findSumOfCheckByEveryone(formattedDate, formattedDate2);
                        textField3.setText(String.valueOf(total_sales));

                        table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
                        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent event) {
                                if (!event.getValueIsAdjusting()) {
                                    int selectedRow = table.getSelectedRow();
                                    if (selectedRow != -1) {
                                        String checkId = table.getValueAt(selectedRow, 0).toString();
                                        check_generated_Id = Long.parseLong(checkId);
                                        textField.setText(checkId);
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
                        JOptionPane.showMessageDialog(CheckAllCashierReceipt.this,
                                "Чек збережено у файлі " + filename,
                                "Чек збережено",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(CheckAllCashierReceipt.this,
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


            delete_check_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(CheckAllCashierReceipt.this,
                                "Для видалення чека потрібно обрати його з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String value = textField.getText();
                            Manager_Query.deleteCheck(value);
                        } else {
                            JOptionPane.showMessageDialog(CheckAllCashierReceipt.this,
                                    "Для видалення чека потрібно обрати його з таблиці. Будь ласка, спробуйте ще раз.",
                                    "Помилка введення",
                                    JOptionPane.ERROR_MESSAGE);
                        }
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

            open_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")){
                        JOptionPane.showMessageDialog(CheckAllCashierReceipt.this,
                                "Для перегляду чека спочатку потрібно його обрати з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        new Cashier_CheckSection.CheckDetails(textField);
                    }

                }
            });

            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(datePicker);
            panel.add(datePicker2);
            panel.add(open_button);
            panel.add(search_button);
            panel.add(delete_check_button);
            panel.add(print_check_button);
            panel.add(check_printed_check_button);
            panel.add(backButton);
            panel.add(textField);
            panel.add(label6);
            panel.add(textField3);
            add(panel);
            setVisible(true);

        }
    }

    ////////////////////////////

    // 4. Загальна кількість проданих одиниць товару за період
    public void SumOfItemSold() {
        CheckSumOfItemSold checkSumOfItemSold = new CheckSumOfItemSold();
        checkSumOfItemSold.setVisible(true);
        this.dispose();
    }

    public class CheckSumOfItemSold extends JFrame {
        public CheckSumOfItemSold() {
            setTitle("Загальна кількість проданих одиниць товару за період");
            setSize(1000, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Загальна кількість проданих одиниць товару за період");
            label.setBounds(24, -15, 700, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть назву товару:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 230, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Виберіть початкову дату продажі:");
            label3.setBounds(314, 40, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JLabel label4 = new JLabel("Виберіть кінцеву дату продажі:");
            label4.setBounds(664, 40, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

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
                        JOptionPane.showMessageDialog(CheckSumOfItemSold.this,
                                "Для визначення загальної кількості одиниць певного товару потрібнл ввести його назву і обрати початкову/кінцеву дату. Будь ласка, спробуйте ще раз.",
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
                        JScrollPane scrollPane = Manager_Query.totalUnitsOfProductSold(formattedDate, formattedDate2, textField);
                        scrollPane.setBounds(24, 180, 940, 300);
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
            panel.add(label3);
            panel.add(label4);
            panel.add(search_button);
            panel.add(textField);
            panel.add(datePicker);
            panel.add(datePicker2);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }

    // 5. Надрукувати звіт про чеки
    public void OpenPrintCheckReport() {
        PrintCheckReport printCheckReportCheck = new PrintCheckReport();
        printCheckReportCheck.setVisible(true);
        this.dispose();
    }

    public class PrintCheckReport extends JFrame {

        public PrintCheckReport() {
            setTitle("Звіт про чеки");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Чеки:");
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
                    String filePath = "Звіти/created_checks.pdf";
                    File file = new File(filePath);

                    if (file.exists() && !file.isDirectory()) {
                        PDFViewer pdfViewer = new PDFViewer("Звіт по чекам", 1500);
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
                    pdf.GenerateReportForChecks();
                    JOptionPane.showMessageDialog(PrintCheckReport.this,
                            "Звіт збережено у файлі created_checks.pdf",
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
                    ml.getSalesSection();
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

    // 6. Перегляд продажів певного товару за період
    public void OpenProductSalesForPeriod() {
        ProductSalesForPeriod productSalesForPeriod = new ProductSalesForPeriod();
        productSalesForPeriod.setVisible(true);
        this.dispose();
    }

    public class ProductSalesForPeriod extends JFrame {

        public ProductSalesForPeriod() {
            setTitle("Перегляд продажів певного товару за період");
            setSize(1000, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Перегляд продажів певного товару за період");
            label.setBounds(24, -15, 650, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть назву товару:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 230, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Виберіть початкову дату продажу товару:");
            label3.setBounds(314, 40, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JLabel label4 = new JLabel("Виберіть кінцеву дату продажу товару:");
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

            JLabel label6 = new JLabel("Загальна кількість проданих товарів з чеків:");
            label6.setBounds(200, 451, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JTextField textField3 = new JTextField(10);
            textField3.setBounds(533, 491, 80, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));


            JButton open_button = new JButton("Відкрити");
            open_button.setBounds(865, 487, 100, 33);

            // СТВОРЕННЯ КАЛЕНДАРЯ 1
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");

            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePicker());
            datePicker.setBounds(314, 105, 315, 25);
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
                        JOptionPane.showMessageDialog(ProductSalesForPeriod.this,
                                "Для пошуку проданого товару потрібно ввести його назву і обрати початкову/кінцеву дату. Будь ласка, спробуйте ще раз.",
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
                        JScrollPane scrollPane = Manager_Query.seeSoldProductsForPeriod(textField, formattedDate, formattedDate2);
                        scrollPane.setBounds(24, 180, 940, 300);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();

                        double total_sales = Manager_Query.findSumOfSoldItemsForPeriod(formattedDate, formattedDate2, textField.getText());
                        textField3.setText(String.valueOf(total_sales));


                        JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
                        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent event) {
                                if (!event.getValueIsAdjusting()) {
                                    int selectedRow = table.getSelectedRow();
                                    if (selectedRow != -1) {
                                        String checkId = table.getValueAt(selectedRow, 0).toString();
                                        textField2.setText(checkId);
                                    }
                                    else {
                                        textField2.setText("");
                                    }
                                }
                            }
                        });

                    }
                }
            });

            open_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField2.getText().equals("")){
                        JOptionPane.showMessageDialog(ProductSalesForPeriod.this,
                                "Для перегляду чека спочатку потрібно його обрати з таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        new Cashier_CheckSection.CheckDetails(textField2);
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
            panel.add(textField3);
            panel.add(label5);
            panel.add(label6);
            panel.add(search_button);
            panel.add(textField);
            panel.add(datePicker);
            panel.add(datePicker2);
            panel.add(open_button);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }

    // 7. Перегляд не куплених товарів
    public void OpenNotSoldProducts() {
        NotSoldProducts openNotSoldProducts = new NotSoldProducts();
        openNotSoldProducts.setVisible(true);
        this.dispose();
    }

    public class NotSoldProducts extends JFrame {

        public NotSoldProducts() {
            setTitle("Перегляд не куплених товарів");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Перегляд не акційних товарів, які не мали жодних продажів");
            label.setBounds(24, -15, 750, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JScrollPane scrollPane = Manager_Query.totalUnitsOfNotSoldProduct();
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

}
