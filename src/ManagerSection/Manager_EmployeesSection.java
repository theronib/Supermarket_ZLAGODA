package ManagerSection;

import CashierSection.DatePicker;
import DataBaseSection.*;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class Manager_EmployeesSection extends JFrame {

    //////////////////////
    // СЕКЦІЯ: "ПРАЦІВНИКИ"
    //////////////////////

    // 1. Додати працівника
    public void OpenAddEmployee() {
        AddEmployee addEmployee = new AddEmployee();
        addEmployee.setVisible(true);
        this.dispose();
    }

    public class AddEmployee extends JFrame {

        public AddEmployee() {
            setTitle("Додати працівника");
            setSize(755, 650);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178, 196));

            JLabel label = new JLabel("Додати працівника");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть номер працівника:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 300, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton randomButton = new JButton("Згенерувати номер");
            randomButton.setBounds(18, 135, 150, 25);

            JLabel label3 = new JLabel("Введіть прізвище працівника:");
            label3.setBounds(24, 138, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(24, 203, 300, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label4 = new JLabel("Введіть ім'я працівника:");
            label4.setBounds(24, 206, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField3 = new JTextField(10);
            textField3.setBounds(24, 271, 300, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label5 = new JLabel("Введіть ім'я по батькові працівника:");
            label5.setBounds(24, 274, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField4 = new JTextField(10);
            textField4.setBounds(24, 339, 300, 25);
            textField4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label6 = new JLabel("Виберіть роль працівника:");
            label6.setBounds(24, 342, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            String[] choices = {"Касир", "Менеджер", "Прибиральник"};
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(20, 407, 310, 25);
            panel.add(comboBox);

            JLabel label7 = new JLabel("Введіть зарплату працівника:");
            label7.setBounds(24, 410, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JTextField textField5 = new JTextField(10);
            textField5.setBounds(24, 475, 300, 25);
            textField5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label8 = new JLabel("Виберіть дату народження працівника:");
            label8.setBounds(424, 40, 600, 100);
            Font labelFont8 = label.getFont();
            label8.setFont(new Font(labelFont8.getName(), Font.PLAIN, 15));

            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");

            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePicker());
            datePicker.setBounds(424, 105, 300, 25);
            datePicker.setBackground(Color.WHITE);

            JLabel label9 = new JLabel("Виберіть дату влаштування працівника:");
            label9.setBounds(424, 108, 600, 100);
            Font labelFont9 = label.getFont();
            label9.setFont(new Font(labelFont9.getName(), Font.PLAIN, 15));

            // СТВОРЕННЯ КАЛЕНДАРЯ 2
            UtilDateModel model2 = new UtilDateModel();

            JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
            JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DatePicker());
            datePicker2.setBounds(424, 173, 300, 25);
            datePicker2.setBackground(Color.WHITE);

            JLabel label10 = new JLabel("Введіть номер телефона працівника:");
            label10.setBounds(424, 176, 600, 100);
            Font labelFont10 = label.getFont();
            label10.setFont(new Font(labelFont10.getName(), Font.PLAIN, 15));

            JTextField textField6 = new JTextField(10);
            textField6.setBounds(424, 241, 300, 25);
            textField6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label11 = new JLabel("Введіть місто проживання працівника:");
            label11.setBounds(424, 244, 600, 100);
            Font labelFont11 = label.getFont();
            label11.setFont(new Font(labelFont11.getName(), Font.PLAIN, 15));

            JTextField textField7 = new JTextField(10);
            textField7.setBounds(424, 309, 300, 25);
            textField7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label12 = new JLabel("Введіть вулицю проживання працівника:");
            label12.setBounds(424, 312, 600, 100);
            Font labelFont12 = label.getFont();
            label12.setFont(new Font(labelFont12.getName(), Font.PLAIN, 15));

            JTextField textField8 = new JTextField(10);
            textField8.setBounds(424, 377, 300, 25);
            textField8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label13 = new JLabel("Введіть ZIP-код працівника:");
            label13.setBounds(424, 380, 600, 100);
            Font labelFont13 = label.getFont();
            label13.setFont(new Font(labelFont13.getName(), Font.PLAIN, 15));

            JTextField textField9 = new JTextField(10);
            textField9.setBounds(424, 445, 300, 25);
            textField9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton addButton = new JButton("Додати працівника");
            addButton.setBounds(470, 520, 200, 60);

            // КНОПКА ГЕНЕРАЦІЇ НОМЕРА КЛІЄНТА
            randomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String randomNum = generateRandom10DigitNumber();
                    textField.setText(randomNum);
                }
            });

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("") ||
                            textField2.getText().equals("") ||
                            textField3.getText().equals("") ||
                            textField4.getText().equals("") ||
                            textField5.getText().equals("") ||
                            textField6.getText().equals("") ||
                            textField7.getText().equals("") ||
                            textField8.getText().equals("") ||
                            textField9.getText().equals("")) {
                        JOptionPane.showMessageDialog(AddEmployee.this,
                                "Для додавання працівника необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String empl_id = textField.getText();
                        String surname = textField2.getText();
                        String firstName = textField3.getText();
                        String patronymic = textField4.getText();
                        String selectedRole = (String) comboBox.getSelectedItem();
                        String salary = textField5.getText();

                        UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
                        Date selectedDate = dateModel.getValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String formatted_birth = formatter.format(selectedDate);

                        UtilDateModel dateModel2 = (UtilDateModel) datePicker2.getModel();
                        Date selectedDate2 = dateModel2.getValue();
                        String formatted_work_start = formatter.format(selectedDate2);

                        String phone_num = textField6.getText();
                        String city = textField7.getText();
                        String street = textField8.getText();
                        String zipCode = textField9.getText();

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.YEAR, -18);
                        Date minimumDateOfBirth = calendar.getTime();
                        Date selectedDateOfBirth = null;

                        try {
                            selectedDateOfBirth = (Date) datePicker.getModel().getValue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        if (selectedDateOfBirth != null && selectedDateOfBirth.after(minimumDateOfBirth)) {
                            JOptionPane.showMessageDialog(AddEmployee.this,
                                    "Працівник повинен бути щонайменше 18 років. Будь ласка, виберіть коректну дату народження.",
                                    "Помилка введення",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        Manager_Query.addEmployee(empl_id, surname, firstName, patronymic, selectedRole, salary, formatted_birth, formatted_work_start, phone_num, city, street, zipCode);
                    }
                }
            });

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 570, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            panel.add(backButton);
            panel.add(randomButton);
            panel.add(addButton);
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
            panel.add(label11);
            panel.add(label12);
            panel.add(label13);
            panel.add(textField);
            panel.add(textField2);
            panel.add(textField3);
            panel.add(textField4);
            panel.add(textField5);
            panel.add(textField6);
            panel.add(textField7);
            panel.add(textField8);
            panel.add(textField9);
            panel.add(comboBox);
            panel.add(datePicker);
            panel.add(datePicker2);
            add(panel);
            setVisible(true);

        }
    }

    private static String generateRandom10DigitNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(9);
        sb.append((char) ('1' + random.nextInt(9)));
        for (int i = 0; i < 9; i++) {
            sb.append((char) ('0' + random.nextInt(10)));
        }
        return sb.toString();
    }

    // 2. Редагувати працівника
    public void OpenEditEmployee() {
        EditEmployee editEmployee = new EditEmployee();
        editEmployee.setVisible(true);
        this.dispose();
    }

    public class EditEmployee extends JFrame {
        public EditEmployee() {
            setTitle("Редагувати працівника");
            setSize(1220, 620);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            JLabel label = new JLabel("Редагувати працівника");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));
            panel.setBackground(new Color(141, 178, 196));

            JScrollPane scrollPane = Manager_Query.СheckEmployeeToEdit();
            scrollPane.setBounds(24, 70, 600, 350);

            JTextField textField_empl = new JTextField(10);
            textField_empl.setBounds(24, 450, 600, 20);
            textField_empl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label_empl = new JLabel("Працівника, якого потрібно редагувати: ");
            label_empl.setBounds(24, 385, 600, 100);
            Font labelFont_empl = label.getFont();
            label_empl.setFont(new Font(labelFont_empl.getName(), Font.PLAIN, 12));

            JLabel label_emplcode = new JLabel("Номер працівника, якого потрібно редагувати: ");
            label_emplcode.setBounds(24, 435, 600, 100);
            Font labelFont_emplcode = label.getFont();
            label_emplcode.setFont(new Font(labelFont_emplcode.getName(), Font.PLAIN, 12));

            JTextField textField_emplcode = new JTextField(10);
            textField_emplcode.setBounds(320, 476, 305, 19);
            textField_emplcode.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label3 = new JLabel("Введіть нове прізвище працівника:");
            label3.setBounds(654, 30, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField2 = new JTextField(10);
            textField2.setBounds(920, 67, 270, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label4 = new JLabel("Введіть нове ім'я працівника:");
            label4.setBounds(654, 70, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 15));

            JTextField textField3 = new JTextField(10);
            textField3.setBounds(875, 109, 315, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label5 = new JLabel("Введіть нове ім'я по батькові працівника:");
            label5.setBounds(654, 110, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField4 = new JTextField(10);
            textField4.setBounds(965, 148, 225, 25);
            textField4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label6 = new JLabel("Виберіть нову роль працівника:");
            label6.setBounds(654, 150, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            String[] choices = {"Касир", "Менеджер", "Прибиральник"};
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(890, 187, 305, 25);
            panel.add(comboBox);

            JLabel label7 = new JLabel("Введіть нову зарплату працівника:");
            label7.setBounds(654, 190, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JTextField textField6 = new JTextField(10);
            textField6.setBounds(918, 229, 270, 25);
            textField6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label8 = new JLabel("Виберіть нову дату народження працівника:");
            label8.setBounds(654, 230, 600, 100);
            Font labelFont8 = label.getFont();
            label8.setFont(new Font(labelFont8.getName(), Font.PLAIN, 15));

            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");

            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePicker());
            datePicker.setBounds(988, 267, 202, 25);
            datePicker.setBackground(Color.WHITE);

            JTextField textField7 = new JTextField(10);
            textField7.setBounds(875, 267, 310, 25);
            textField7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label9 = new JLabel("Виберіть нову дату влаштування працівника:");
            label9.setBounds(654, 270, 600, 100);
            Font labelFont9 = label.getFont();
            label9.setFont(new Font(labelFont9.getName(), Font.PLAIN, 15));

            // СТВОРЕННЯ КАЛЕНДАРЯ 2
            UtilDateModel model2 = new UtilDateModel();

            JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
            JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DatePicker());
            datePicker2.setBounds(992, 307, 198, 25);
            datePicker2.setBackground(Color.WHITE);

            JTextField textField8 = new JTextField(10);
            textField8.setBounds(840, 308, 345, 25);
            textField8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label10 = new JLabel("Введіть новий номер телефона працівника:");
            label10.setBounds(654, 310, 600, 100);
            Font labelFont10 = label.getFont();
            label10.setFont(new Font(labelFont10.getName(), Font.PLAIN, 15));

            JTextField textField9 = new JTextField(10);
            textField9.setBounds(983, 349, 205, 25);
            textField9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label11 = new JLabel("Введіть нове місто проживання працівника:");
            label11.setBounds(654, 350, 600, 100);
            Font labelFont11 = label.getFont();
            label11.setFont(new Font(labelFont11.getName(), Font.PLAIN, 15));

            JTextField textField10 = new JTextField(10);
            textField10.setBounds(983, 390, 205, 25);
            textField10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label12 = new JLabel("Введіть нову вулицю проживання працівника:");
            label12.setBounds(654, 390, 600, 100);
            Font labelFont12 = label.getFont();
            label12.setFont(new Font(labelFont12.getName(), Font.PLAIN, 15));

            JTextField textField11 = new JTextField(10);
            textField11.setBounds(1000, 427, 190, 25);
            textField11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label13 = new JLabel("Введіть новий ZIP-код працівника:");
            label13.setBounds(654, 430, 600, 100);
            Font labelFont13 = label.getFont();
            label13.setFont(new Font(labelFont13.getName(), Font.PLAIN, 15));

            JTextField textField12 = new JTextField(10);
            textField12.setBounds(920, 464, 270, 25);
            textField12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton editButton = new JButton("Редагувати працівника");
            editButton.setBounds(840, 510, 200, 60);

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 540, 40, 40);

            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField2.getText().equals("") ||
                            textField3.getText().equals("") ||
                            textField4.getText().equals("") ||
                            textField6.getText().equals("") ||
                            model.getValue() == null ||
                            model2.getValue() == null ||
                            textField9.getText().equals("") ||
                            textField10.getText().equals("") ||
                            textField11.getText().equals("") ||
                            textField12.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditEmployee.this,
                                "Для редагування працівника необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (textField_emplcode.getText().equals("") || textField_empl.getText().equals("")) {
                        JOptionPane.showMessageDialog(EditEmployee.this,
                                "Для редагування працівника спочатку потрібно обрати його в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String code = textField_emplcode.getText();
                        String newSurname = textField2.getText();
                        String newName = textField3.getText();
                        String newPatronymic = textField4.getText();
                        String newRole = (String) comboBox.getSelectedItem();
                        String newSalary = textField6.getText();

                        UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
                        Date selectedDate = dateModel.getValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String formatted_birth = formatter.format(selectedDate);

                        UtilDateModel dateModel2 = (UtilDateModel) datePicker2.getModel();
                        Date selectedDate2 = dateModel2.getValue();
                        String formatted_work_start = formatter.format(selectedDate2);

                        String newPhone = textField9.getText();
                        String newTown = textField10.getText();
                        String newStreet = textField11.getText();
                        String newZip = textField12.getText();

                        Manager_Query.editEmployee(code, newSurname, newName, newPatronymic, newRole, newSalary, formatted_birth, formatted_work_start, newPhone, newTown, newStreet, newZip);
                    }
                }
            });

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            panel.add(scrollPane);
            panel.add(textField_empl);
            panel.add(textField_emplcode);
            panel.add(label_empl);
            panel.add(label_emplcode);
            panel.add(label);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
            panel.add(label8);
            panel.add(label9);
            panel.add(label10);
            panel.add(label11);
            panel.add(label12);
            panel.add(label13);
            panel.add(textField2);
            panel.add(textField3);
            panel.add(textField4);
            panel.add(textField6);
            panel.add(textField9);
            panel.add(textField10);
            panel.add(textField11);
            panel.add(textField12);
            panel.add(datePicker);
            panel.add(datePicker2);
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
                            String id = table.getValueAt(selectedRow, 0).toString();
                            String surname = table.getValueAt(selectedRow, 1).toString();
                            String name = table.getValueAt(selectedRow, 2).toString();
                            String patronymic = table.getValueAt(selectedRow, 3).toString();
                            String role = table.getValueAt(selectedRow, 4).toString();
                            String phone = table.getValueAt(selectedRow, 7).toString();


                            textField_empl.setText(surname + " " + name + " " + patronymic + ". " + role + ". Номер телефона: " + phone);
                            textField_emplcode.setText(id);
                        }
                    }
                }
            });
        }
    }

    // 3. Видалити працівника
    public void OpenDeleteEmployee() {
        DeleteEmployee deleteEmployee = new DeleteEmployee();
        deleteEmployee.setVisible(true);
        this.dispose();
    }

    public class DeleteEmployee extends JFrame {
        public DeleteEmployee() {
            setTitle("Видалити працівника");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            JLabel label = new JLabel("Видалити працівника");
            label.setBounds(24, -15, 650, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label5 = new JLabel("Номер працівника:");
            label5.setBounds(475, 451, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(625, 491, 115, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton delete_empl_button = new JButton("\uD83D\uDDD1   Видалити");
            delete_empl_button.setBounds(755, 487, 123, 33);

            JScrollPane scrollPane = Manager_Query.showEmployee();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            delete_empl_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(DeleteEmployee.this,
                                "Для видалення працівника спочатку потрібно вибрати його в таблиці. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String empl = textField.getText();
                        long empl_id = Long.parseLong(empl);
                        Manager_Query.deleteEmployee(empl_id);

                    }
                }
            });

            panel.add(backButton);
            panel.add(label);
            panel.add(label5);
            panel.add(textField);
            panel.add(delete_empl_button);
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

    // 4. Надрукувати звіт про усіх працівників
    public void OpenPrintEmployeeReport() {
        PrintEmployeeReport printEmployeeReport = new PrintEmployeeReport();
        printEmployeeReport.setVisible(true);
        this.dispose();
    }

    public class PrintEmployeeReport extends JFrame {
        public PrintEmployeeReport() {
            setTitle("Звіт про усіх працівників");
            setSize(430, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Працівники:");
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
                    String filePath = "Звіти/employee.pdf";
                    File file = new File(filePath);

                    if (file.exists() && !file.isDirectory()) {
                        PDFViewer pdfViewer = new PDFViewer("Звіт по працівникам", 1500);
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
                    pdf.GenerateReportForEmployee();
                    JOptionPane.showMessageDialog(PrintEmployeeReport.this,
                            "Звіт збережено у файлі employee.pdf",
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
                    ml.getEmployeeSection();

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

    // 5. Перегляд працівників за прізвищем
    public void OpenCheckEmployeeBySurname() {
        CheckEmployeeBySurname checkEmployeeBySurname = new CheckEmployeeBySurname();
        checkEmployeeBySurname.setVisible(true);
        this.dispose();
    }

    public class CheckEmployeeBySurname extends JFrame {
        public CheckEmployeeBySurname() {
            setTitle("Перегляд працівників");
            setSize(1200, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Усі працівники відсортовані за прізвищем");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            JScrollPane scrollPane = Manager_Query.СheckEmployee();
            scrollPane.setBounds(24, 70, 1150, 400);
            panel.add(scrollPane);


            panel.add(backButton);
            panel.add(label);
            add(panel);
            setVisible(true);

        }
    }

    // 6. Перегляд працівників касирів
    public void OpenCheckEmployeeCashiers() {
        CheckEmployeeCashiers checkEmployeeCashiers = new CheckEmployeeCashiers();
        checkEmployeeCashiers.setVisible(true);
        this.dispose();
    }

    public class CheckEmployeeCashiers extends JFrame {
        public CheckEmployeeCashiers() {
            setTitle("Перегляд працівників касирів");
            setSize(1200, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Усі касири відсортовані за прізвищем");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            JScrollPane scrollPane = Manager_Query.СheckCashier();
            scrollPane.setBounds(24, 70, 1150, 400);
            panel.add(scrollPane);

            panel.add(label);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }

    // 7. Знайти контактну інформацію працівника
    public void OpenCheckEmployeeContactInfo() {
        CheckEmployeeContactInfo checkEmployeeContactInfo = new CheckEmployeeContactInfo();
        checkEmployeeContactInfo.setVisible(true);
        this.dispose();
    }

    public class CheckEmployeeContactInfo extends JFrame {
        public CheckEmployeeContactInfo() {
            setTitle("Пошук контактної інформації працівника");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Пошук контактної інформації працівника за прізвищем");
            label.setBounds(24, -15, 800, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 21));

            JLabel label2 = new JLabel("Введіть прізвище працівника:");
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
                        JOptionPane.showMessageDialog(CheckEmployeeContactInfo.this,
                                "Для пошуку працівника потрібно ввести його прізвище. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }

                        JScrollPane scrollPane = Manager_Query.searchEmployeeBySurname(textField);
                        scrollPane.setBounds(24, 150, 850, 320);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            });



            panel.add(textField);
            panel.add(backButton);
            panel.add(label);
            panel.add(label2);
            panel.add(search_button);
            add(panel);
            setVisible(true);

        }
    }

    // 8. Додати новий акаунт для касира
    public void OpenAddNewCashierAccount() {
        AddNewCashierAccount addNewAccount = new AddNewCashierAccount();
        addNewAccount.setVisible(true);
        this.dispose();
    }

    public class AddNewCashierAccount extends JFrame {
        private boolean passwordShown = false;
        public AddNewCashierAccount() {
            setTitle("Додати/Видалити акаунт для касира");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Додати/Видалити акаунт для касира");
            label.setBounds(24, -15, 800, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 21));

            JLabel label2 = new JLabel("Оберіть працівника для якого створюється акаунт:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            String[] choices = Manager_Query.getUnregisteredCashierArray();
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(20, 105, 430, 27);
            panel.add(comboBox);

            JLabel label3 = new JLabel("Введіть ім'я користувача для працівника: ");
            label3.setBounds(24, 105, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 170, 420, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label4 = new JLabel("Наприклад: kathy_johnson25 ");
            label4.setBounds(24, 160, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 12));

            JLabel label5 = new JLabel("Створіть пароль для працівника: ");
            label5.setBounds(24, 190, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JPasswordField textField2 = new JPasswordField(10);
            textField2.setBounds(24, 255, 420, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton randomButton = new JButton("Згенерувати пароль");
            randomButton.setBounds(18, 285, 180, 25);

            JButton showButton = new JButton("Показати пароль");
            showButton.setBounds(200, 285, 180, 25);

            JLabel label6 = new JLabel("Підтвердіть новий пароль: ");
            label6.setBounds(24, 288, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JPasswordField textField3 = new JPasswordField(10);
            textField3.setBounds(24, 353, 420, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            randomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String randomPass = generateRandomPassword(20);
                    textField2.setText(randomPass);
                    textField3.setText(randomPass);
                }
            });

            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField2.setEchoChar((char) 0);
                    textField3.setEchoChar((char) 0);

                    showButton.setText("Сховати пароль");

                    if (passwordShown) {
                        textField2.setEchoChar('●');
                        textField3.setEchoChar('●');
                        showButton.setText("Показати пароль");
                        passwordShown = false;
                    } else {
                        textField2.setEchoChar((char) 0);
                        textField3.setEchoChar((char) 0);
                        showButton.setText("Сховати пароль");
                        passwordShown = true;
                    }
                }
            });

            JButton addButton = new JButton("Додати акаунт");
            addButton.setBounds(120, 400, 200, 60);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = textField.getText();
                    String password = new String(textField2.getPassword());
                    String password_confirm = new String(textField3.getPassword());

                    if (username.equals("") || password.equals("") || password_confirm.equals("")) {
                        JOptionPane.showMessageDialog(AddNewCashierAccount.this,
                                "Для створення акаунта необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!password.equals(password_confirm)){
                        JOptionPane.showMessageDialog(AddNewCashierAccount.this,
                                "Помилка невідповідності нового пароля. Будь ласка, спробуйте ще раз.",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String selectedText = (String) comboBox.getSelectedItem();
                        String[] parts = selectedText.split(":");
                        String id_employee = parts[0].trim();

                        Manager_Query.addUsernameInDB(username, id_employee);
                        DataBase_User_Connector.RegisterCashierUser(username, password);
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

            JLabel label7 = new JLabel("Створені акаунти: ");
            label7.setBounds(500, 40, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JScrollPane scrollPane = Manager_Query.showRegisteredCashierUsers();
            scrollPane.setBounds(500, 105, 375, 350);
            panel.add(scrollPane);

            JLabel label8 = new JLabel("Логін акаунта:");
            label8.setBounds(500, 425, 600, 100);
            Font labelFont8 = label.getFont();
            label8.setFont(new Font(labelFont8.getName(), Font.PLAIN, 15));

            JTextField textField4 = new JTextField(10);
            textField4.setBounds(610, 463, 160, 25);
            textField4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton deleteButton = new JButton("Видалити");
            deleteButton.setBounds(778, 460, 100, 35);

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username_to_delete = textField4.getText();

                    if (username_to_delete.equals("")) {
                        JOptionPane.showMessageDialog(AddNewCashierAccount.this,
                                "Для видалення акаунта необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        DataBase_User_Connector.DeleteCashierAccountFromDB(username_to_delete);
                    }
                }
            });

            panel.add(backButton);
            panel.add(randomButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
            panel.add(label8);
            panel.add(textField);
            panel.add(textField2);
            panel.add(textField3);
            panel.add(textField4);
            panel.add(addButton);
            panel.add(showButton);
            panel.add(deleteButton);
            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String username = table.getValueAt(selectedRow, 2).toString();
                            textField4.setText(username);
                        }
                    }
                }
            });
        }
    }

    // 9. Додати новий акаунт для менеджера
    public void OpenAddNewManagerAccount() {
        AddNewManagerAccount addNewAccount = new AddNewManagerAccount();
        addNewAccount.setVisible(true);
        this.dispose();
    }

    public class AddNewManagerAccount extends JFrame {
        private boolean passwordShown = false;
        public AddNewManagerAccount() {
            setTitle("Додати/Видалити акаунт для менеджера");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Додати/Видалити акаунт для менеджера");
            label.setBounds(24, -15, 800, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 21));

            JLabel label2 = new JLabel("Оберіть працівника для якого створюється акаунт:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            String[] choices = Manager_Query.getUnregisteredManagerArray();
            JComboBox comboBox = new JComboBox(choices);
            comboBox.setBounds(20, 105, 430, 27);
            panel.add(comboBox);

            JLabel label3 = new JLabel("Введіть ім'я користувача для працівника: ");
            label3.setBounds(24, 105, 600, 100);
            Font labelFont3 = label.getFont();
            label3.setFont(new Font(labelFont3.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 170, 420, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JLabel label4 = new JLabel("Наприклад: kathy_johnson25 ");
            label4.setBounds(24, 160, 600, 100);
            Font labelFont4 = label.getFont();
            label4.setFont(new Font(labelFont4.getName(), Font.PLAIN, 12));

            JLabel label5 = new JLabel("Створіть пароль для працівника: ");
            label5.setBounds(24, 190, 600, 100);
            Font labelFont5 = label.getFont();
            label5.setFont(new Font(labelFont5.getName(), Font.PLAIN, 15));

            JPasswordField textField2 = new JPasswordField(10);
            textField2.setBounds(24, 255, 420, 25);
            textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton randomButton = new JButton("Згенерувати пароль");
            randomButton.setBounds(18, 285, 180, 25);

            JButton showButton = new JButton("Показати пароль");
            showButton.setBounds(200, 285, 180, 25);

            JLabel label6 = new JLabel("Підтвердіть новий пароль: ");
            label6.setBounds(24, 288, 600, 100);
            Font labelFont6 = label.getFont();
            label6.setFont(new Font(labelFont6.getName(), Font.PLAIN, 15));

            JPasswordField textField3 = new JPasswordField(10);
            textField3.setBounds(24, 353, 420, 25);
            textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            randomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String randomPass = generateRandomPassword(20);
                    textField2.setText(randomPass);
                    textField3.setText(randomPass);
                }
            });

            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField2.setEchoChar((char) 0);
                    textField3.setEchoChar((char) 0);

                    showButton.setText("Сховати пароль");

                    if (passwordShown) {
                        textField2.setEchoChar('●');
                        textField3.setEchoChar('●');
                        showButton.setText("Показати пароль");
                        passwordShown = false;
                    } else {
                        textField2.setEchoChar((char) 0);
                        textField3.setEchoChar((char) 0);
                        showButton.setText("Сховати пароль");
                        passwordShown = true;
                    }
                }
            });

            JButton addButton = new JButton("Додати акаунт");
            addButton.setBounds(120, 400, 200, 60);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = textField.getText();
                    String password = new String(textField2.getPassword());
                    String password_confirm = new String(textField3.getPassword());

                    if (username.equals("") || password.equals("") || password_confirm.equals("")) {
                        JOptionPane.showMessageDialog(AddNewManagerAccount.this,
                                "Для створення акаунта необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!password.equals(password_confirm)){
                        JOptionPane.showMessageDialog(AddNewManagerAccount.this,
                                "Помилка невідповідності нового пароля. Будь ласка, спробуйте ще раз.",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String selectedText = (String) comboBox.getSelectedItem();
                        String[] parts = selectedText.split(":");
                        String id_employee = parts[0].trim();

                        Manager_Query.addUsernameInDB(username, id_employee);
                        DataBase_User_Connector.RegisterManagerUser(username, password);
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

            JLabel label7 = new JLabel("Створені акаунти: ");
            label7.setBounds(500, 40, 600, 100);
            Font labelFont7 = label.getFont();
            label7.setFont(new Font(labelFont7.getName(), Font.PLAIN, 15));

            JScrollPane scrollPane = Manager_Query.showRegisteredManagerUsers();
            scrollPane.setBounds(500, 105, 375, 350);
            panel.add(scrollPane);

            JLabel label8 = new JLabel("Логін акаунта:");
            label8.setBounds(500, 425, 600, 100);
            Font labelFont8 = label.getFont();
            label8.setFont(new Font(labelFont8.getName(), Font.PLAIN, 15));

            JTextField textField4 = new JTextField(10);
            textField4.setBounds(610, 463, 160, 25);
            textField4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton deleteButton = new JButton("Видалити");
            deleteButton.setBounds(778, 460, 100, 35);

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username_to_delete = textField4.getText();

                    if (username_to_delete.equals("")) {
                        JOptionPane.showMessageDialog(AddNewManagerAccount.this,
                                "Для видалення акаунта необхідно заповнити всі поля. Будь ласка, спробуйте ще раз.",
                                "Помилка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        DataBase_User_Connector.DeleteManagerAccountFromDB(username_to_delete);
                    }
                }
            });

            panel.add(backButton);
            panel.add(randomButton);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            panel.add(label7);
            panel.add(label8);
            panel.add(textField);
            panel.add(textField2);
            panel.add(textField3);
            panel.add(textField4);
            panel.add(addButton);
            panel.add(showButton);
            panel.add(deleteButton);
            add(panel);
            setVisible(true);

            JTable table = ((JTable)((JViewport)scrollPane.getComponents()[0]).getView());
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String username = table.getValueAt(selectedRow, 2).toString();
                            textField4.setText(username);
                        }
                    }
                }
            });

        }
    }

    // Метод для генерації паролів
    private static String generateRandomPassword(int length) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String allCharacters = upperCaseLetters + lowerCaseLetters + numbers;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        sb.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        sb.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        sb.append(numbers.charAt(random.nextInt(numbers.length())));
        for (int i = 3; i < length; i++) {
            sb.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(length);
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(randomIndex));
            sb.setCharAt(randomIndex, temp);
        }
        return sb.toString();
    }

    // 5. Перегляд працівників за прізвищем
        public void OpenCheckInActiveEmployee() {
        CheckInactiveEmployee checkInactiveEmployee = new CheckInactiveEmployee();
        checkInactiveEmployee.setVisible(true);
        this.dispose();
    }

    public class CheckInactiveEmployee extends JFrame {
        public CheckInactiveEmployee() {
            setTitle("Перегляд касирів, які не працювали протягом одного місяця і не видавали чеків");
            setSize(1200, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Усі касири, які не працювали протягом одного місяця і не видавали чеків");
            label.setBounds(24, -15, 950, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            JScrollPane scrollPane = Manager_Query.checkInactiveEmployees();
            scrollPane.setBounds(24, 70, 1150, 400);
            panel.add(scrollPane);

            panel.add(backButton);
            panel.add(label);
            add(panel);
            setVisible(true);

        }
    }
}