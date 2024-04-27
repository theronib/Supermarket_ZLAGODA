package DataBaseSection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.sql.*;
import java.util.List;

public class Manager_Query {

    // ПІДКЛЮЧЕННЯ ДО БАЗИ ДАНИХ

    private static final String DB_URL = "jdbc:ucanaccess:///Users/macbookpro/Desktop/Studying/University/Lessons/Second Year/БД/2nd Semester/АІС/Supermarket/DataBase/ZLAGODA.accdb";

    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    // 1. ТОВАРИ - ДОДАВАННЯ ТОВАРУ
    public static void addProduct(int category_num, String product_name, String characteristics_value) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Product (category_number, product_name, characteristics) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, category_num);
                statement.setString(2, product_name);
                statement.setString(3, characteristics_value);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Товар було успішно додано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося додати товар", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 2. ТОВАРИ - ДОДАВАННЯ КАТЕГОРІЇ
    public static void addCategory(int category_num, JTextField textField2) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Category (category_number, category_name) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, category_num);
                statement.setString(2, textField2.getText());

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Категорію товара було успішно додано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося додати категорію", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 3. ТОВАРИ - ДОДАВАННЯ ТОВАРУ В МАГАЗИН
    public static void addProductInStore(String UPC, int id_product, BigDecimal selling_price, int product_num, boolean promotional_product) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String checkSql = "SELECT * FROM Store_Product WHERE id_product = ? AND promotional_product = ?";
            try (PreparedStatement checkStatement = conn.prepareStatement(checkSql)) {
                checkStatement.setInt(1, id_product);
                checkStatement.setBoolean(2, promotional_product);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int existingProductNum = resultSet.getInt("products_number");
                        int newProductNum = existingProductNum + product_num;
                        String updateSql = "UPDATE Store_Product SET products_number = ? WHERE id_product = ? AND promotional_product = ?";
                        try (PreparedStatement updateStatement = conn.prepareStatement(updateSql)) {
                            updateStatement.setInt(1, newProductNum);
                            updateStatement.setInt(2, id_product);
                            updateStatement.setBoolean(3, promotional_product);
                            updateStatement.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(null, "Кількість товару було оновлено в магазині", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        String insertSql = "INSERT INTO Store_Product (UPC, id_product, selling_price, products_number, promotional_product) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
                            insertStatement.setString(1, UPC);
                            insertStatement.setInt(2, id_product);
                            insertStatement.setBigDecimal(3, selling_price);
                            insertStatement.setInt(4, product_num);
                            insertStatement.setBoolean(5, promotional_product);
                            insertStatement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Товар було успішно додано в магазин", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося додати товар в магазин", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 4. ТОВАРИ - РЕДАГУВАННЯ ТОВАРУ В МАГАЗИН
    public static void editProductInStore(String UPC, String UPC_prom, BigDecimal selling_price, int product_num, boolean promotional_product) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Store_Product SET UPC_prom = ?, selling_price = ?, products_number = ?, promotional_product = ? WHERE UPC = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, UPC_prom);
                statement.setBigDecimal(2, selling_price);
                statement.setInt(3, product_num);
                statement.setBoolean(4, promotional_product);
                statement.setString(5, UPC);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Товар було успішно відредаговано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Товар з UPC " + UPC + " не було знайдено", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося відредагувати товар в магазині", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 5. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ТОВАРИ
    public static String[] getProductArray() {
        List<String> resultList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Product";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id_product = resultSet.getString("id_product");
                        String product_name = resultSet.getString("product_name");


                        resultList.add(id_product + ": " + product_name);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список товарів", "Помилка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return resultList.toArray(new String[0]);
    }

    // 6. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ТЕ ЧИ Є ПРОДУКТ АКЦІЙНИЙ
    public static boolean isProductPromotional(String upc) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT promotional_product FROM Store_Product WHERE upc = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, upc);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBoolean("promotional_product");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося дізнатися чи товар є акційним", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // 7. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО КАТЕГОРІЇ ТОВАРІВ
    public static String[] getCategoryArray() {
        List<String> resultList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Category";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String category_id = resultSet.getString("category_number");
                        String category_name = resultSet.getString("category_name");

                        resultList.add(category_id + ": " + category_name);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список категорій товарів", "Помилка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return resultList.toArray(new String[0]);
    }


    // 8. ТОВАРИ - ВИДАЛИТИ ТОВАР
    public static void deleteProduct(int product) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Product WHERE id_product = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, product);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Товар було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося видалити товар", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 9. ТОВАРИ - ВИДАЛИТИ ТОВАР В МАГАЗИНІ
    public static void deleteProductInShop(String upc) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Store_Product WHERE UPC = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, upc);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Товар у магазині було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося видалити товар в магазині", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 10. ТОВАРИ - ВИДАЛИТИ КАТЕГОРІЮ ТОВАРІВ
    public static void deleteCategory(int category) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Category WHERE category_number = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, category);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Категорію було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося видалити категорію товарів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 11. ТОВАРИ - ВИВЕДЕННЯ ТОВАРІВ ДЛЯ ВИДАЛЕННЯ
    public static JScrollPane showProductsToRemove() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_product, product_name FROM Product";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"Номер товару", "Назва"};
                for (String columnName : columnNames) {
                    model.addColumn(columnName);
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося вивести список товарів для видалення", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 12. ТОВАРИ - ВИВЕДЕННЯ ТОВАРІВ ДЛЯ РЕДАГУВАННЯ
    public static JScrollPane showProductsToEdit() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_product, product_name, category_name, characteristics FROM Product\n" +
                    "INNER JOIN Category on Product.category_number = Category.category_number";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"Номер товару", "Назва", "Категорія", "Характеристика"};
                for (String columnName : columnNames) {
                    model.addColumn(columnName);
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося вивести список товарів для редагування", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 13. ТОВАРИ - РЕДАГУВАННЯ ТОВАРІВ
    public static void editProduct(int product_category, String product_name, String product_characteristics, int id_product) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Product SET category_number = ?, product_name = ?, characteristics = ? WHERE id_product =?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, product_category);
                statement.setString(2, product_name);
                statement.setString(3, product_characteristics);
                statement.setInt(4, id_product);

                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Товар успішно було відредаговано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося відредагувати товар", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 14. ТОВАРИ - ВИВЕДЕННЯ КАТЕГОРІЙ ТОВАРІВ
    public static JScrollPane showСatagories() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Category";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"Номер категорії", "Назва категорії"};
                for (String columnName : columnNames) {
                    model.addColumn(columnName);
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список категорій товарів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 15. ТОВАРИ - РЕДАГУВАННЯ КАТЕГОРІЇ ТОВАРІВ
    public static void editCategory(String category_name, int category_id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Category SET category_name=? WHERE category_number=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, category_name);
                statement.setInt(2, category_id);

                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Категорію успішно було відредаговано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося відредагувати категорію товарів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 16. ПРАЦІВНИКИ - ВИВЕДЕННЯ ПРАЦІВНИКІВ
    public static JScrollPane СheckEmployee() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Усі працівники");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name, empl_patronymic, empl_role, date_of_birth, date_of_start, phone_number, city, street, zip_code, salary FROM Employee";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id_employee = resultSet.getString("id_employee");
                        String empl_surname = resultSet.getString("empl_surname");
                        String empl_name = resultSet.getString("empl_name");
                        String empl_patronymic = resultSet.getString("empl_patronymic");
                        String empl_role = resultSet.getString("empl_role");
                        String date_of_birth = resultSet.getString("date_of_birth");
                        String date_of_start = resultSet.getString("date_of_start");
                        String phone_number = resultSet.getString("phone_number");
                        String city = resultSet.getString("city");
                        String street = resultSet.getString("street");
                        String zip_code = resultSet.getString("zip_code");
                        String salary = resultSet.getString("salary");

                        DefaultMutableTreeNode surnameNode = findOrCreateNode(root, empl_surname);
                        DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Працівник № " + id_employee + ". ПІБ: " + empl_surname + " " + empl_name + '\n'
                                + "" + empl_patronymic + ". Роль: " + empl_role + ". Дата народження: " + date_of_birth + ". Дата початку роботи:" + '\n'
                        + date_of_start + ". Номер телефона: " + phone_number + ". Адреса: вул. " + street + ", м. " + city + ", " + zip_code + ". Зарплата: " + salary + ".");

                        surnameNode.add(employeeNode);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список працівників", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        sortTree(root);

        JTree tree = new JTree(root);
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        return scrollPane;
    }

    // 17. ПРАЦІВНИКИ - ВИВЕДЕННЯ ПРАЦІВНИКІВ КАСИРІВ
    public static JScrollPane СheckCashier() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Усі касири");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name, empl_patronymic, empl_role, date_of_birth, date_of_start, phone_number, city, street, zip_code, salary FROM Employee\n" +
                    "WHERE empl_role = 'Касир'";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id_employee = resultSet.getString("id_employee");
                        String empl_surname = resultSet.getString("empl_surname");
                        String empl_name = resultSet.getString("empl_name");
                        String empl_patronymic = resultSet.getString("empl_patronymic");
                        String empl_role = resultSet.getString("empl_role");
                        String date_of_birth = resultSet.getString("date_of_birth");
                        String date_of_start = resultSet.getString("date_of_start");
                        String phone_number = resultSet.getString("phone_number");
                        String city = resultSet.getString("city");
                        String street = resultSet.getString("street");
                        String zip_code = resultSet.getString("zip_code");
                        String salary = resultSet.getString("salary");

                        DefaultMutableTreeNode surnameNode = findOrCreateNode(root, empl_surname);
                        DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Працівник № " + id_employee + ". ПІБ: " + empl_surname + " " + empl_name + '\n'
                                + "" + empl_patronymic + ". Дата народження: " + date_of_birth + ". Дата початку роботи:" + '\n'
                                + date_of_start + ". Номер телефона: " + phone_number + ". Адреса: вул. " + street + ", м. " + city + ", " + zip_code + ". Зарплата: " + salary + ".");

                        surnameNode.add(employeeNode);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список касирів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        sortTree(root);

        JTree tree = new JTree(root);
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        return scrollPane;
    }

    private static DefaultMutableTreeNode findOrCreateNode(DefaultMutableTreeNode root, String surname) {
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
            if (node.getUserObject().equals(surname)) {
                return node;
            }
        }
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(surname);
        root.add(newNode);
        return newNode;
    }

    public static void sortTree(DefaultMutableTreeNode root) {
        Enumeration<TreeNode> children = root.children();

        java.util.List<DefaultMutableTreeNode> list = new java.util.ArrayList<>();
        while (children.hasMoreElements()) {
            list.add((DefaultMutableTreeNode) children.nextElement());
        }

        list.sort((node1, node2) -> {
            String str1 = node1.getUserObject().toString();
            String str2 = node2.getUserObject().toString();
            return str1.compareToIgnoreCase(str2);
        });

        root.removeAllChildren();

        for (DefaultMutableTreeNode node : list) {
            root.add(node);
        }
    }

    // 18. ПРАЦІВНИКИ - ВИВЕДЕННЯ ПРАЦІВНИКІВ ДЛЯ РЕДАГУВАННЯ
    public static JScrollPane СheckEmployeeToEdit() {
        String[] columnNames = {"ID", "Прізвище", "Ім'я", "По батькові", "Роль", "Дата народження", "Дата початку роботи", "Номер телефону", "Місто", "Вулиця", "Індекс", "Зарплата"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name, empl_patronymic, empl_role, date_of_birth, date_of_start, phone_number, city, street, zip_code, salary FROM Employee ORDER BY CAST(id_employee AS BIGINT)";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id_employee = resultSet.getString("id_employee");
                        String empl_surname = resultSet.getString("empl_surname");
                        String empl_name = resultSet.getString("empl_name");
                        String empl_patronymic = resultSet.getString("empl_patronymic");
                        String empl_role = resultSet.getString("empl_role");
                        String date_of_birth = resultSet.getString("date_of_birth");
                        String date_of_start = resultSet.getString("date_of_start");
                        String phone_number = resultSet.getString("phone_number");
                        String city = resultSet.getString("city");
                        String street = resultSet.getString("street");
                        String zip_code = resultSet.getString("zip_code");
                        BigDecimal salary = resultSet.getBigDecimal("salary");

                        model.addRow(new Object[]{id_employee, empl_surname, empl_name, empl_patronymic, empl_role, date_of_birth, date_of_start, phone_number, city, street, zip_code, salary});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося вивести список працівників для редагування", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(120);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        return scrollPane;
    }

    // 19. ПРАЦІВНИКИ - РЕДАГУВАННЯ ПРАЦІВНИКА
    public static void editEmployee(String code, String newSurname, String newName, String newPatronymic, String newRole, String salary, String formatted_birth, String formatted_work_start, String newPhone, String newTown, String newStreet, String newZip) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Employee SET empl_surname = ?, empl_name = ?, empl_patronymic = ?, empl_role = ?, salary = ?, date_of_birth = ?, date_of_start = ?, phone_number = ?, city = ?, street = ?, zip_code = ? WHERE id_employee = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, newSurname);
                statement.setString(2, newName);
                statement.setString(3, newPatronymic);
                statement.setString(4, newRole);
                BigDecimal salaryDecimal = new BigDecimal(salary);
                statement.setBigDecimal(5, salaryDecimal);
                statement.setString(6, formatted_birth);
                statement.setString(7, formatted_work_start);
                statement.setString(8, newPhone);
                statement.setString(9, newTown);
                statement.setString(10, newStreet);
                statement.setString(11, newZip);
                statement.setString(12, code);

                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Працівника успішно було відредаговано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося відредагувати працівника", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    // 20. ПРАЦІВНИКИ - ДОДАВАННЯ ПРАЦІВНИКА
    public static void addEmployee(String id, String surname, String firstName, String patronymic, String role, String salary, String date_birth, String date_start, String phoneNumber, String city, String street, String zipCode) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Employee(id_employee, empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, date_of_start, phone_number, city, street, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                BigDecimal salaryDecimal = new BigDecimal(salary);
                statement.setString(1, id);
                statement.setString(2, surname);
                statement.setString(3, firstName);
                statement.setString(4, patronymic);
                statement.setString(5, role);
                statement.setBigDecimal(6, salaryDecimal);
                statement.setString(7, date_birth);
                statement.setString(8, date_start);
                statement.setString(9, phoneNumber);
                statement.setString(10, city);
                statement.setString(11, street);
                statement.setString(12, zipCode);
                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Працівник успішно доданий до бази даних", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося додати працівника", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    // 21. ПРАЦІВНИКИ - ВИДАЛЕННЯ ПРАЦІВНИКА
    public static void deleteEmployee(long empl_id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Employee WHERE id_employee = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, empl_id);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Працівника було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося видалити працівника", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    // 22. ПРАЦІВНИКИ - ПОШУК ПРАЦІВНИКІВ ЗА ПРІЗВИЩЕМ
    public static JScrollPane searchEmployeeBySurname(JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name, empl_patronymic, empl_role, phone_number, city, street, zip_code\n" +
                    "FROM Employee\n" +
                    "WHERE empl_surname LIKE ?;";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText() + "%");

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"Номер працівника", "Прізвище", "Ім'я", "По батькові", "Роль", "Номер телефону", "Місто", "Вулиця", "ZIP-код"};
                    for (String columnName : columnNames) {
                        model.addColumn(columnName);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти працівника за прізвищем", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 23. ПРОДАЖІ - ЗНАЙТИ СУМУ ЧЕКІВ СТВОРЕНИХ ПЕВНИМ КАСИРОМ ЗА ПЕВНИЙ ПЕРІОД
    public static double findSumOfCheckByCashier(String datetime_start, String datetime_finish, String employee_surname) {
        double totalSales = 0.0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT SUM(Check.sum_total) AS total_sales\n" +
                    "FROM [Check]\n" +
                    "INNER JOIN Employee ON Check.id_employee = Employee.id_employee\n" +
                    "LEFT JOIN Customer_Card ON Check.card_number = Customer_Card.card_number\n" +
                    "WHERE Check.print_date BETWEEN ? AND ?\n" +
                    "AND Employee.empl_surname = ?\n";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, datetime_start);
                statement.setString(2, datetime_finish);
                statement.setString(3, employee_surname);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        totalSales = resultSet.getDouble("total_sales");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти суму чеків касира за період", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return totalSales;
    }

    // 24. ПРОДАЖІ - ЗНАЙТИ СУМУ ЧЕКІВ СТВОРЕНИХ УСІМА КАСИРАМИ ЗА ПЕВНИЙ ПЕРІОД
    public static double findSumOfCheckByEveryone(String datetime_start, String datetime_finish) {
        double totalSales = 0.0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT SUM(Check.sum_total) AS total_sales\n" +
                    "FROM [Check]\n" +
                    "INNER JOIN Employee ON Check.id_employee = Employee.id_employee\n" +
                    "LEFT JOIN Customer_Card ON Check.card_number = Customer_Card.card_number\n" +
                    "WHERE Check.print_date BETWEEN ? AND ?\n";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, datetime_start);
                statement.setString(2, datetime_finish);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        totalSales = resultSet.getDouble("total_sales");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти суму чеків усіх касирів за період", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return totalSales;
    }

    // 25. ПРОДАЖІ - ПЕРЕГЛЯД ЧЕКІВ СТВОРЕНИХ УСІМА КАСИРАМИ ЗА ПЕВНИЙ ПЕРІОД
    public static JScrollPane seeAllChecksForPeriod(String datetime_start, String datetime_finish) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 350));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Check.check_number, Employee.empl_surname, Customer_Card.cust_surname, Check.card_number, Check.print_date, Check.sum_total, Check.vat\n" +
                    "FROM [Check]\n" +
                    "INNER JOIN Employee ON Check.id_employee = Employee.id_employee\n" +
                    "LEFT JOIN Customer_Card ON Check.card_number = Customer_Card.card_number\n" +
                    "WHERE Check.print_date BETWEEN ? AND ?;";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, datetime_start);
                statement.setString(2, datetime_finish);

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"Номер чека", "Касир", "Власник картки", "Номер картки", "Дата створення", "Сума", "ПДВ"};
                    for (String columnName : columnNames) {
                        model.addColumn(columnName);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список чеків створених усіма касирами", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 26. ПРОДАЖІ - ВИВЕДЕННЯ ЧЕКІВ ЯКІ МІСТЯТЬ ПЕВНИЙ ТОВАР ЗА ПЕВНИЙ ПЕРІОД
    public static JScrollPane seeSoldProductsForPeriod(JTextField textField, String datetime_start, String datetime_finish) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Check.check_number, Employee.empl_surname, Customer_Card.cust_surname, Check.card_number, Check.print_date, Check.sum_total, Check.vat " +
                    "FROM Check " +
                    "INNER JOIN Employee ON Check.id_employee = Employee.id_employee " +
                    "INNER JOIN Customer_Card ON Check.card_number = Customer_Card.card_number " +
                    "INNER JOIN Sale ON Check.check_number = Sale.check_number " +
                    "INNER JOIN Store_Product ON Sale.UPC = Store_Product.UPC " +
                    "INNER JOIN Product ON Store_Product.id_product = Product.id_product " +
                    "WHERE Product.product_name = ? AND Check.print_date BETWEEN ? AND ?\n" +
                    "GROUP BY Check.check_number, Employee.empl_surname, Customer_Card.cust_surname, Check.card_number, Check.print_date, Check.sum_total, Check.vat;";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText());
                statement.setString(2, datetime_start);
                statement.setString(3, datetime_finish);

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"Номер чека", "Касир", "Власник картки", "Номер картки", "Дата створення", "Сума", "ПДВ"};
                    for (String columnName : columnNames) {
                        model.addColumn(columnName);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список чеків які містять певний товар", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 27. ПРОДАЖІ - ЗНАЙТИ СУМУ ПРОДАНИХ ТОВАРІВ ЗА ПЕВНИЙ ПЕРІОД
    public static double findSumOfSoldItemsForPeriod(String datetime_start, String datetime_finish, String upc) {
        int total_units_sold = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            String sql = "SELECT SUM(Sale.product_number) AS total_units_sold\n" +
                    "FROM [Sale]\n" +
                    "INNER JOIN Check ON Sale.check_number = Check.check_number AND Check.print_date BETWEEN ? AND ?\n" +
                    "INNER JOIN Store_Product ON Sale.UPC = Store_Product.UPC\n" +
                    "INNER JOIN Product ON Store_Product.id_product = Product.id_product AND Product.product_name = ?\n";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, datetime_start);
                statement.setString(2, datetime_finish);
                statement.setString(3, upc);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        total_units_sold = resultSet.getInt("total_units_sold");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти суму проданих товарів за період", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return total_units_sold;
    }

    // 28. ПРАЦІВНИКИ - ВИВЕДЕННЯ ПРАЦІВНИКІВ ДЛЯ ВИДАЛЕННЯ
    public static JScrollPane showEmployee() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name, empl_patronymic, empl_role\n" +
                    "FROM Employee;";


            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                model.setColumnCount(0);

                String[] columnNames = {"Номер працівника", "Прізвище", "Ім'я", "По-батькові", "Роль"};
                for (String columnName : columnNames) {
                    model.addColumn(columnName);
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список працівників", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 29. КЛІЄНТИ - ВИВЕДЕННЯ КЛІЄНТІВ БЕЗ ПОКУПОК І БЕЗ НОМЕРУ ТЕЛЕФОНА
    public static JScrollPane showCustomersWithNoPurchases() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT card_number, cust_surname, cust_name, cust_patronymic, city, street, zip_code, percent\n" +
                    "FROM Customer_Card\n" +
                    "WHERE card_number NOT IN (SELECT DISTINCT card_number FROM Check)\n" +
                    "AND phone_number IS NULL;\n";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                model.setColumnCount(0);

                String[] columnNames = {"Номер карточки", "Прізвище", "Ім'я", "По-батькові", "Місто", "Вулиця", "ZIP-код", "Процент знижки"};
                for (String columnName : columnNames) {
                    model.addColumn(columnName);
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список клієнтів без покупок і номера телефона", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 30. ПРОДАЖІ - ВИВЕДЕННЯ ЗАГАЛЬНОЇ СУМИ ПРОДАНИХ ТОВАРІВ
    public static JScrollPane totalUnitsOfProductSold(String datetime_start, String datetime_finish, JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 350));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            String sql = "SELECT Store_Product.UPC, Store_Product.UPC_prom, Product.product_name, SUM(Sale.product_number) AS total_units_sold, " +
                    "CASE " +
                    "   WHEN Store_Product.promotional_product = 'true' THEN 'Акційний' " +
                    "   WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний' " +
                    "   ELSE 'Невідомий' " +
                    "END AS promotional_product " +
                    "FROM Sale\n" +
                    "INNER JOIN Check ON Sale.check_number = Check.check_number AND Check.print_date BETWEEN ? AND ?\n" +
                    "INNER JOIN Store_Product ON Sale.UPC = Store_Product.UPC\n" +
                    "INNER JOIN Product ON Store_Product.id_product = Product.id_product AND Product.product_name LIKE ?\n" +
                    "GROUP BY Product.id_product, Product.product_name, Store_Product.UPC;\n";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, datetime_start);
                statement.setString(2, datetime_finish);
                statement.setString(3, textField.getText() + "%");


                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"UPC", "UPC-Prom", "Назва продукту", "Кількість проданих одиниць", "Тип товару"};
                    for (String columnName : columnNames) {
                        model.addColumn(columnName);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти кількість певного проданого товару за період", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 31. ПРОДАЖІ - ВИВЕДЕННЯ НЕ АКЦІЙНИХ ТОВАРІВ ЯКІ НЕ МАЛИ ПРОДАЖІВ
    public static JScrollPane totalUnitsOfNotSoldProduct() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 350));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT sp.UPC, sp.UPC_Prom, p.product_name, c.category_name, sp.products_number, sp.selling_price, p.characteristics\n" +
                    "FROM Store_Product sp\n" +
                    "LEFT JOIN Sale s ON sp.UPC = s.UPC\n" +
                    "LEFT JOIN Product p ON sp.id_product = p.id_product\n" +
                    "LEFT JOIN Category c ON c.category_number = p.category_number\n" +
                    "WHERE NOT (s.UPC IS NOT NULL OR sp.UPC_Prom IS NOT NULL OR sp.promotional_product = true);\n";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"UPC", "UPC-Prom", "Назва товару", "Категорія", "Кількість", "Ціна товару", "Характеристика"};
                    for (String columnName : columnNames) {
                        model.addColumn(columnName);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список неакційних і непроданих товарів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 32. ПРОДАЖІ - СЕРЕДНЯ ЦІНА КОЖНОЇ КАТЕГОРІЇ ТОВАРУ
    public static JScrollPane averageSellingPriceOfCategory(JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 350));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Category.category_number, Category.category_name, AVG(Store_Product.selling_price) AS avg_selling_price\n" +
                    "FROM Category\n" +
                    "INNER JOIN Product ON Category.category_number = Product.category_number\n" +
                    "INNER JOIN Store_Product ON Product.id_product = Store_Product.id_product\n" +
                    "WHERE Category.category_name LIKE ?\n" +
                    "GROUP BY Category.category_number, Category.category_name;";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText() + "%");

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"Номер категорії", "Назва категорії", "Середня ціна"};
                    for (String columnName : columnNames) {
                        model.addColumn(columnName);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти середню ціну певної категорії", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 33. КЛІЄНТИ - ПОШУК КЛІЄНТА ЗА ЗНИЖКОЮ
    public static JScrollPane searchClientsByDiscount(JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent\n" +
                    "FROM Customer_Card\n" +
                    "WHERE percent = ?\n" +
                    "ORDER BY cust_surname;\n";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                int percentValue = Integer.parseInt(textField.getText());
                statement.setInt(1, percentValue);

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"Номер карточки", "Прізвище", "Ім'я", "По-батькові", "Номер телефону", "Місто", "Вулиця", "ZIP-код", "Процент знижки"};
                    for (String columnName : columnNames) {
                        model.addColumn(columnName);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти клієнта по знижці", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 34. ПРОДАЖІ - ВИДАЛИТИ ЧЕК
    public static void deleteCheck(String checkNumber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Check WHERE check_number = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, checkNumber);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Чек було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося видалити чек", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 35. ПРАЦІВНИКИ - ВИДАЛИТИ КЛІЄНТА
    public static void deleteClient(String client_id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Customer_Card WHERE card_number = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, client_id);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Клієнта було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося видалити клієнта", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 36. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО НЕЗАРЕЄСТРОВАНИХ КАСИРІВ
    public static String[] getUnregisteredCashierArray() {
        List<String> resultList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name FROM Employee WHERE empl_role = 'Касир' AND username IS NULL";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String idEmployee = resultSet.getString("id_employee");
                        String empl_surname = resultSet.getString("empl_surname");
                        String empl_name = resultSet.getString("empl_name");

                        resultList.add(idEmployee + ": " + empl_surname + " " + empl_name);


                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати інформацію про незареєстрованих касирів", "Помилка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return resultList.toArray(new String[0]);
    }

    // 37. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО НЕЗАРЕЄСТРОВАНИХ МЕНЕДЖЕРІВ
    public static String[] getUnregisteredManagerArray() {
        List<String> resultList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name FROM Employee WHERE empl_role = 'Менеджер' AND username IS NULL";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String idEmployee = resultSet.getString("id_employee");
                        String empl_surname = resultSet.getString("empl_surname");
                        String empl_name = resultSet.getString("empl_name");

                        resultList.add(idEmployee + ": " + empl_surname + " " + empl_name);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати інформацію про незареєстрованих менеджерів", "Помилка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return resultList.toArray(new String[0]);
    }

    // 38. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ЗАРЕЄСТРОВАНИХ КАСИРІВ
    public static JScrollPane showRegisteredCashierUsers() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, CONCAT(empl_surname, ' ', empl_name, ' ', empl_patronymic) AS full_name, username FROM Employee\n" +
                    "WHERE empl_role = 'Касир' AND username IS NOT NULL";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"Номер працівника", "ПІБ", "Логін"};
                for (String columnName : columnNames) {
                    model.addColumn(columnName);
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати інформацію про зареєстрованих касирів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 39. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ЗАРЕЄСТРОВАНИХ МЕНЕДЖЕРІВ
    public static JScrollPane showRegisteredManagerUsers() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, CONCAT(empl_surname, ' ', empl_name, ' ', empl_patronymic) AS full_name, username FROM Employee\n" +
                    "WHERE empl_role = 'Менеджер' AND username IS NOT NULL";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"Номер працівника", "ПІБ", "Логін"};
                for (String columnName : columnNames) {
                    model.addColumn(columnName);
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати інформацію про зареєстрованих менеджерів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 40. ДОДАТИ ЛОГІН ДО БАЗИ ДАНИХ
    public static void addUsernameInDB(String username, String id_employee) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Employee SET username = ? WHERE id_employee = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, id_employee);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Логін працівника було успішно додано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося додати логін працівника", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static JScrollPane checkInactiveEmployees() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Усі касири, які не працювали протягом місяця");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String sql = "SELECT * FROM Employee\n" +
                    "WHERE id_employee NOT IN (\n" +
                    "    SELECT e.id_employee FROM Employee e\n" +
                    "    LEFT JOIN Check c ON e.id_employee = c.id_employee\n" +
                    "    LEFT JOIN Sale s ON c.check_number = s.check_number\n" +
                    "    WHERE c.check_number IS NOT NULL \n" +
                    "    AND e.empl_role = 'Касир' \n" +
                    "    AND c.print_date >= DATE_SUB(NOW(), INTERVAL 1 MONTH)\n" +
                    ")\n" +
                    "AND empl_role = 'Касир';\n";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id_employee = resultSet.getString("id_employee");
                        String empl_surname = resultSet.getString("empl_surname");
                        String empl_name = resultSet.getString("empl_name");
                        String empl_patronymic = resultSet.getString("empl_patronymic");
                        String date_of_birth = resultSet.getString("date_of_birth");
                        String date_of_start = resultSet.getString("date_of_start");
                        String phone_number = resultSet.getString("phone_number");
                        String city = resultSet.getString("city");
                        String street = resultSet.getString("street");
                        String zip_code = resultSet.getString("zip_code");
                        String salary = resultSet.getString("salary");

                        DefaultMutableTreeNode surnameNode = findOrCreateNode(root, empl_surname);
                        DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Працівник № " + id_employee + ". ПІБ: " + empl_surname + " " + empl_name + '\n'
                                + "" + empl_patronymic + ". Дата народження: " + date_of_birth + ". Дата початку роботи:" + '\n'
                                + date_of_start + ". Номер телефона: " + phone_number + ". Адреса: вул. " + street + ", м. " + city + ", " + zip_code + ". Зарплата: " + salary + ".");

                        surnameNode.add(employeeNode);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список касирів", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        sortTree(root);

        JTree tree = new JTree(root);
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        return scrollPane;
    }

}
