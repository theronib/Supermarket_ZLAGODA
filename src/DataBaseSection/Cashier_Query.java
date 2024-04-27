package DataBaseSection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

public class Cashier_Query {

    // ПІДКЛЮЧЕННЯ ДО БАЗИ ДАНИХ

    private static final String DB_URL = "jdbc:ucanaccess:///Users/macbookpro/Desktop/Studying/University/Lessons/Second Year/БД/2nd Semester/АІС/Supermarket/DataBase/ZLAGODA.accdb";

    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";


    // 1. ТОВАРИ - ВИВЕДЕННЯ УСІХ ТОВАРІВ
    public static JScrollPane showProducts() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        fillProductTable(model);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    private static void fillProductTable(DefaultTableModel model) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Product.id_product, Product.product_name, Category.category_name, Product.characteristics\n" +
                    "FROM Product\n" +
                    "INNER JOIN Category ON Product.category_number = Category.category_number;";

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
            JOptionPane.showMessageDialog(null, "Не вдалося відкрити таблицю з товарами", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 2. ТОВАРИ - ПОШУК ТОВАРІВ ЗА НАЗВОЮ
    public static JScrollPane searchProducts(JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Store_Product.UPC, Store_Product.UPC_prom, Product.product_name, Category.category_name, Store_Product.products_number, Store_Product.selling_price, Product.characteristics, " +
                    "CASE " +
                    "   WHEN Store_Product.promotional_product = 'true' THEN 'Акційний' " +
                    "   WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний' " +
                    "   ELSE 'Невідомий' " +
                    "END AS promotional_product " +
                    "FROM Product " +
                    "INNER JOIN Category ON Product.category_number = Category.category_number\n" +
                    "INNER JOIN Store_Product ON Product.id_product = Store_Product.id_product " +
                    "WHERE product_name LIKE ?";


            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText() + "%");

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"UPC", "UPC-Prom", "Назва товару", "Категорія", "Кількість", "Ціна товару", "Характеристика", "Тип товару"};
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
            JOptionPane.showMessageDialog(null, "Не вдалося здійснити пошук товару", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }


    // 3. ТОВАРИ - ВИВЕДЕННЯ УСІХ ТОВАРІВ В МАГАЗИНІ
    public static JScrollPane showProductsInStore() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 3 || columnIndex == 4) {
                    return Integer.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }
        };
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT \n" +
                    "    Store_Product.UPC, \n" +
                    "    Store_Product.UPC_prom, \n" +
                    "    Product.product_name, \n" +
                    "    Category.category_name, \n" +
                    "    Store_Product.products_number, \n" +
                    "    Store_Product.selling_price, \n" +
                    "    Product.characteristics, \n" +
                    "    CASE \n" +
                    "        WHEN Store_Product.promotional_product = 'true' THEN 'Акційний'\n" +
                    "        WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний'\n" +
                    "        ELSE 'Невідомий'\n" +
                    "    END AS promotional_product\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "INNER JOIN \n" +
                    "    Store_Product ON Product.id_product = Store_Product.id_product\n" +
                    "INNER JOIN \n" +
                    "    Category ON Product.category_number = Category.category_number;\n";


            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"UPC", "UPC-Prom", "Назва товару", "Категорія", "Кількість", "Ціна товару", "Характеристика", "Тип товару"};
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
            JOptionPane.showMessageDialog(null, "Не вдалося відкрити таблицю з товарами в магазині", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);


        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 4. ТОВАРИ - ПОШУК ТОВАРІВ ЗА КАТЕГОРІЄЮ
    public static JScrollPane searchProductsByCategory(JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT \n" +
                    "    Store_Product.UPC, \n" +
                    "    Store_Product.UPC_prom, \n" +
                    "    Product.product_name, \n" +
                    "    Category.category_name, \n" +
                    "    Store_Product.products_number, \n" +
                    "    Store_Product.selling_price, \n" +
                    "    Product.characteristics, \n" +
                    "    CASE \n" +
                    "        WHEN Store_Product.promotional_product = 'true' THEN 'Акційний'\n" +
                    "        WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний'\n" +
                    "        ELSE 'Невідомий'\n" +
                    "    END AS promotional_product\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "INNER JOIN \n" +
                    "    Store_Product ON Product.id_product = Store_Product.id_product\n" +
                    "INNER JOIN \n" +
                    "    Category ON Product.category_number = Category.category_number\n" +
                    "WHERE category_name LIKE ?";



            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText() + "%");

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"UPC", "UPC-Prom", "Назва товару", "Категорія", "Кількість", "Ціна товару", "Характеристика", "Тип товару"};
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
            JOptionPane.showMessageDialog(null, "Не вдалося здійснити пошук товару по категорії", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 5. ТОВАРИ - ПОШУК ТОВАРІВ ЗА НОМЕРОМ UPC
    public static JScrollPane searchProductsByUPC(JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Store_Product.UPC, Store_Product.UPC_prom, Product.product_name, Category.category_name, Store_Product.products_number, Store_Product.selling_price, Product.characteristics, " +
                    "CASE " +
                    "   WHEN Store_Product.promotional_product = 'true' THEN 'Акційний' " +
                    "   WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний' " +
                    "   ELSE 'Невідомий' " +
                    "END AS promotional_product " +
                    "FROM Product " +
                    "INNER JOIN Category ON Product.category_number = Category.category_number\n" +
                    "INNER JOIN Store_Product ON Product.id_product = Store_Product.id_product " +
                    "WHERE Store_Product.UPC LIKE ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText() + "%");

                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    model.setColumnCount(0);

                    String[] columnNames = {"UPC", "UPC-Prom", "Назва товару", "Категорія", "Кількість", "Ціна товару", "Характеристика", "Тип товару"};
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
            JOptionPane.showMessageDialog(null, "Не вдалося здійснити пошук товару по UPC коду", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 6. ТОВАРИ - ВИВЕДЕННЯ УСІХ АКЦІЙНИХ ТОВАРІВ В МАГАЗИНІ
    public static JScrollPane showSaleProducts() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 3 || columnIndex == 4) {
                    return Integer.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }
        };
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT \n" +
                    "    Store_Product.UPC, \n" +
                    "    Store_Product.UPC_prom, \n" +
                    "    Product.product_name, \n" +
                    "    Category.category_name, \n" +
                    "    Store_Product.products_number, \n" +
                    "    Store_Product.selling_price, \n" +
                    "    Product.characteristics, \n" +
                    "    CASE \n" +
                    "        WHEN Store_Product.promotional_product = 'true' THEN 'Акційний'\n" +
                    "        WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний'\n" +
                    "        ELSE 'Невідомий'\n" +
                    "    END AS promotional_product\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "INNER JOIN \n" +
                    "    Store_Product ON Product.id_product = Store_Product.id_product\n" +
                    "INNER JOIN \n" +
                    "    Category ON Product.category_number = Category.category_number\n" +
                    "WHERE \n" +
                    "    Store_Product.promotional_product = 'true' AND Store_Product.UPC_prom IS NULL;\n";


            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"UPC", "UPC-Prom", "Назва товару", "Категорія", "Кількість", "Ціна товару", "Характеристика", "Тип товару"};
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
            JOptionPane.showMessageDialog(null, "Не вдалося відкрити таблицю з акційними товарами\n", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 7. ТОВАРИ - ВИВЕДЕННЯ УСІХ НЕ АКЦІЙНИХ ТОВАРІВ В МАГАЗИНІ
    public static JScrollPane showNonSaleProducts() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 3 || columnIndex == 4) {
                    return Integer.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }
        };
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT \n" +
                    "    Store_Product.UPC, \n" +
                    "    Store_Product.UPC_prom, \n" +
                    "    Product.product_name, \n" +
                    "    Category.category_name, \n" +
                    "    Store_Product.products_number, \n" +
                    "    Store_Product.selling_price, \n" +
                    "    Product.characteristics, \n" +
                    "    CASE \n" +
                    "        WHEN Store_Product.promotional_product = 'true' THEN 'Акційний'\n" +
                    "        WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний'\n" +
                    "        ELSE 'Невідомий'\n" +
                    "    END AS promotional_product\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "INNER JOIN \n" +
                    "    Store_Product ON Product.id_product = Store_Product.id_product\n" +
                    "INNER JOIN \n" +
                    "    Category ON Product.category_number = Category.category_number\n" +
                    "WHERE \n" +
                    "    Store_Product.promotional_product = 'false' AND Store_Product.UPC_prom IS NULL;\n";


            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"UPC", "UPC-Prom", "Назва товару", "Категорія", "Кількість", "Ціна товару", "Характеристика", "Тип товару"};
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
            JOptionPane.showMessageDialog(null, "Не вдалося відкрити таблицю з не акційними товарами\n", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }

    // 8. ПРОДАЖІ - ДОДАВАННЯ ПРОДАЖІ ДО ЧЕКУ
    public static void addSaleToCheck(String UPC, String check_number, int product_number, BigDecimal selling_price) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Sale (check_number, UPC, product_number, selling_price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, check_number);
                statement.setString(2, UPC);
                statement.setInt(3, product_number);
                statement.setBigDecimal(4, selling_price);

                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Продажа товару була успішно здійснена", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося здійснити продажу товару", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        updateCheckTotal(check_number);
        removeItemFromStock(product_number, UPC);

    }

    // 9. ПРОДАЖІ - ДОДАВАННЯ ПРОДАЖІ ДО ЧЕКУ, ЯКЩО ТОВАР В НАЯВНОСТІ
    public static void addSaleToCheckIfAvailable(String UPC, String check_number, int product_number, BigDecimal selling_price) {
        if (product_number <= 0) {
            JOptionPane.showMessageDialog(null, "Неправильне значення кількості товару", "Увага", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int stock_quantity = getStockQuantityFromDatabase(UPC);

        if (product_number > 0 && product_number <= stock_quantity) {
            addSaleToCheck(UPC, check_number, product_number, selling_price);
        } else if (stock_quantity == 0) {
            JOptionPane.showMessageDialog(null, "Товар відсутній на складі", "Увага", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Кількість товару, що додається, перевищує кількість товару на складі", "Увага", JOptionPane.WARNING_MESSAGE);
        }
    }

    // 10. ПРОДАЖІ - ОТРИМАННЯ ЗНИЖКИ КЛІЄНТА
    public static int GetClientsPercent(String card_number){
        int percent = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT percent FROM Customer_Card WHERE card_number = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, card_number);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        percent = resultSet.getInt("percent");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти процентну знижку клієнта", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return percent;
    }

    // 11. ПРОДАЖІ - ОНОВЛЕННЯ СУМИ ЧЕКА
    public static double UpdateSumOfCheck(String check_number) {
        double total = 0.0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT SUM(selling_price) AS total FROM Sale WHERE check_number = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, check_number);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        total = resultSet.getDouble("total");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося оновити суму чека", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return total;
    }


    // 12. ПРОДАЖІ - ОТРИМАННЯ КІЛЬКОСТІ ПРОДУКЦІЇ ПЕВНОГО ТОВАРУ
    private static int getStockQuantityFromDatabase(String UPC) {
        int stock_quantity = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT products_number FROM Store_Product WHERE UPC = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, UPC);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        stock_quantity = resultSet.getInt("products_number");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти кількість цього продукту в магазині", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return stock_quantity;
    }

    // 13. ПРОДАЖІ - ОНОВЛЕННЯ СУМИ ЧЕКА
    private static void updateCheckTotal(String checkNumber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Check SET sum_total = (SELECT SUM(selling_price) FROM Sale WHERE check_number = ?), vat = (SELECT SUM(selling_price) * 0.2 FROM Sale WHERE check_number = ?) WHERE check_number = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, checkNumber);
                statement.setString(2, checkNumber);
                statement.setString(3, checkNumber);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 14. ПРОДАЖІ - ВИДАЛЕННЯ ПРОДУКТУ ЗІ СКЛАДУ
    private static void removeItemFromStock(int product_number, String UPC) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Store_Product SET products_number = CASE WHEN (products_number - ?) < 0 THEN 0 ELSE (products_number - ?) END WHERE UPC = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, product_number);
                statement.setInt(2, product_number);
                statement.setString(3, UPC);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 15. ПРОДАЖІ - ДОДАВАННЯ ЧЕКУ
    public static void addCheck(String check_number, String id_employee, String cardNumber, String datetime) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Check (check_number, id_employee, card_number, print_date, sum_total, vat)  VALUES (?, ?, ?, ?, 0.0, 0.0)";

            String datetimeWithDefaultTime = datetime + " 00:00:00";
            Timestamp timestamp = Timestamp.valueOf(datetimeWithDefaultTime);

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, check_number);
                statement.setString(2, id_employee);
                statement.setString(3, cardNumber);
                statement.setTimestamp(4, timestamp);


                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Чек був успішно створений", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося створити чек", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 16. ПРОДАЖІ - ДОДАВАННЯ ЧЕКУ БЕЗ ПОСТІЙНОГО КЛІЄНТА
    public static void addCheckWithoutCustomer(String check_number, String id_employee, String datetime) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Check (check_number, id_employee, print_date, sum_total, vat)  VALUES (?, ?, ?, 0.0, 0.0)";

            String datetimeWithDefaultTime = datetime + " 00:00:00";
            Timestamp timestamp = Timestamp.valueOf(datetimeWithDefaultTime);

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, check_number);
                statement.setString(2, id_employee);
                statement.setTimestamp(3, timestamp);


                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Чек був успішно створений", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося створити чек", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 17. ПРОДАЖІ - ПОШУК ЧЕКА СТВОРЕНОГО ПЕВНИМ КАСИРОМ ЗА ДЕНЬ
    public static JScrollPane seeChecksForDay(JTextField textField, String datetime) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = " SELECT Check.check_number, Employee.empl_surname, Customer_Card.cust_surname, Check.card_number, Check.print_date, Check.sum_total, Check.vat\n" +
                    "FROM [Check]\n" +
                    "INNER JOIN Employee ON Check.id_employee = Employee.id_employee\n" +
                    "LEFT JOIN Customer_Card ON Check.card_number = Customer_Card.card_number\n" +
                    "WHERE Employee.empl_surname = ? AND Check.print_date = ?;";


            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText());
                statement.setString(2, datetime);

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
            JOptionPane.showMessageDialog(null, "Не вдалося знайти чеки за певний день", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 18. ПРОДАЖІ - ПОШУК ЧЕКА СТВОРЕНОГО ПЕВНИМ КАСИРОМ ЗА ПЕВНИЙ ПЕРІОД
    public static JScrollPane seeChecksForPeriod(JTextField textField, String datetime_start, String datetime_finish) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Check.check_number, Employee.empl_surname, Customer_Card.cust_surname, Check.card_number, Check.print_date, Check.sum_total, Check.vat\n" +
                    "FROM [Check]\n" +
                    "INNER JOIN Employee ON Check.id_employee = Employee.id_employee\n" +
                    "LEFT JOIN Customer_Card ON Check.card_number = Customer_Card.card_number\n" +
                    "WHERE Employee.empl_surname = ? AND Check.print_date BETWEEN ? AND ?;\n";

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
            JOptionPane.showMessageDialog(null, "Не вдалося знайти чеки за певний період часу", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 19. ПРОДАЖІ - ПОШУК ЧЕКА ЗА UPC
    public static JScrollPane searchCheckByNumber(JTextField textField) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Деталі чеку");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * " +
                    "FROM " +
                    "    Check LEFT JOIN  Employee ON Check.id_employee = Employee.id_employee " +
                    "           LEFT JOIN  Customer_Card ON Check.card_number = Customer_Card.card_number " +
                    "           LEFT JOIN Sale ON Check.check_number = Sale.check_number " +
                    "LEFT JOIN Store_Product ON Sale.UPC = Store_Product.UPC " +
                    "LEFT JOIN Product ON Product.id_product = Store_Product.id_product " +
                    "LEFT JOIN Category ON Product.category_number = Category.category_number " +
                    "WHERE Check.check_number = ?;";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        DefaultMutableTreeNode checkNode = findCheckNode(root, resultSet.getString("check_number"));

                        if (checkNode == null) {
                            checkNode = new DefaultMutableTreeNode("Номер чека: " + resultSet.getString("check_number") +
                                    " - Дата створення чека: " + resultSet.getString("print_date") +
                                    ". Сума: " + resultSet.getString("sum_total") +
                                    ". ПДВ: " + resultSet.getString("vat"));
                            root.add(checkNode);

                            DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Касир: " + resultSet.getString("empl_surname") +
                                    " " + resultSet.getString("empl_name") +
                                    ". Номер касира: " + resultSet.getString("id_employee"));
                            checkNode.add(employeeNode);

                            DefaultMutableTreeNode customerNode = new DefaultMutableTreeNode("Клієнт: " + resultSet.getString("cust_surname") +
                                    " " + resultSet.getString("cust_name") +
                                    ". Номер карточки клієнта: " + resultSet.getString("card_number") +
                                    ". Знижка клієнта: " + resultSet.getString("percent") + "%");
                            checkNode.add(customerNode);
                        }

                        DefaultMutableTreeNode saleNode = new DefaultMutableTreeNode("Товар: " + resultSet.getString("product_name") +
                                " - UPC-код: " + resultSet.getString("UPC") +
                                ". Ціна: " + resultSet.getString("selling_price") +
                                ". Кількість товару: " + resultSet.getString("product_number") +
                                ". Характеристика: " + resultSet.getString("characteristics") +
                                ". Тип товару: " + (resultSet.getString("promotional_product") != null && resultSet.getString("promotional_product").equals("TRUE") ? "Акційний" : resultSet.getString("promotional_product") != null && resultSet.getString("promotional_product").equals("FALSE") ? "Звичайний" : "null"));
                        checkNode.add(saleNode);

                        DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode("Категорія товару: " + resultSet.getString("category_name")
                                + ". Номер категорії: " + resultSet.getString("category_number"));
                        checkNode.add(categoryNode);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти чек", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        JTree tree = new JTree(root);
        expandAll(tree, new TreePath(root));
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        return scrollPane;
    }

    // Метод для пошуку вузла чеку за номером чеку
    private static DefaultMutableTreeNode findCheckNode(DefaultMutableTreeNode root, String checkNumber) {
        Enumeration<?> e = root.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (node.getUserObject().toString().startsWith("Номер чека: " + checkNumber)) {
                return node;
            }
        }
        return null;
    }

    private static void expandAll(JTree tree, TreePath parent) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();

        if (node.getChildCount() >= 0) {
            for (Enumeration<?> e = node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path);
            }
        }
        tree.expandPath(parent);
    }


    // 20. КЛІЄНТИ - ПЕРЕГЛЯД ПОСТІЙНИХ КЛІЄНТІВ
    public static JScrollPane showClients() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent\n" +
                    "FROM Customer_Card;";


            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

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
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося відкрити таблицю з клієнтами", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);


        return scrollPane;
    }


    // 21. КЛІЄНТИ - ПОШУК ПОСТІЙНИХ КЛІЄНТІВ ЗА ПРІЗВИЩЕМ
    public static JScrollPane searchClientsBySurname(JTextField textField) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent\n" +
                    "FROM Customer_Card\n" +
                    "WHERE cust_surname LIKE ?;";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText() + "%");

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
            JOptionPane.showMessageDialog(null, "Не вдалося здійснити пошук клієнтів за прізвищем", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        return scrollPane;
    }

    // 22. КЛІЄНТИ - ДОДАТИ КЛІЄНТА
    public static void addClientToDatabase(String cardNumber, String surname, String firstName, String lastName, String phoneNumber, String city, String street, String zipCode, int discount) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Customer_Card(card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cardNumber);
                statement.setString(2, surname);
                statement.setString(3, firstName);
                statement.setString(4, lastName);
                statement.setString(5, phoneNumber);
                statement.setString(6, city);
                statement.setString(7, street);
                statement.setString(8, zipCode);
                statement.setInt(9, discount);
                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Клієнт успішно доданий до бази даних", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося додати клієнта до бази даних", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 23. КЛІЄНТИ - ДОДАТИ КЛІЄНТА БЕЗ НОМЕРА ТЕЛЕФОНУ
    public static void addClientWithoutPhoneToDatabase(String cardNumber, String surname, String firstName, String lastName, String city, String street, String zipCode, int discount) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "INSERT INTO Customer_Card(card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cardNumber);
            statement.setString(2, surname);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setNull(5, java.sql.Types.VARCHAR);
            statement.setString(6, city);
            statement.setString(7, street);
            statement.setString(8, zipCode);
            statement.setInt(9, discount);
            statement.executeUpdate();
        }
        JOptionPane.showMessageDialog(null, "Клієнт успішно доданий до бази даних", "Успіх", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Не вдалося додати клієнта до бази даних", "Помилка", JOptionPane.ERROR_MESSAGE);
    }
}

    // 24. КЛІЄНТИ - РЕДАГУВАТИ КЛІЄНТА
    public static void editClient(String code, String newSurname, String newName, String newPatronymic,
                                  String newPhone, String newTown, String newStreet, String newZip, int newDiscount) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Customer_Card SET cust_surname=?, cust_name=?, cust_patronymic=?, " +
                    "phone_number=?, city=?, street=?, zip_code=?, percent=? WHERE card_number=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, newSurname);
                statement.setString(2, newName);
                statement.setString(3, newPatronymic);
                if (newPhone == null || newPhone.isEmpty()) {
                    statement.setNull(4, Types.VARCHAR);
                } else {
                    statement.setString(4, newPhone);
                }
                statement.setString(5, newTown);
                statement.setString(6, newStreet);
                statement.setString(7, newZip);
                statement.setInt(8, newDiscount);
                statement.setString(9, code);
                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Клієнт успішно був відредагований", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося відредагувати клієнта", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }




    // 25. ІНФОРМАЦІЯ ПРО СЕБЕ (ПРО КАСИРА)
    public static JScrollPane showInfoAboutMe(JTextField textField) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Інформація про касира");
        DefaultTreeModel model = new DefaultTreeModel(root);
        JTree tree = new JTree(model);
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name, empl_patronymic, date_of_birth, date_of_start, phone_number, city, street, zip_code, salary\n" +
                    "FROM Employee\n" +
                    "WHERE username = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, textField.getText());

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


                        DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Касир №" + id_employee);
                        employeeNode.add(new DefaultMutableTreeNode("Прізвище: " + empl_surname));
                        employeeNode.add(new DefaultMutableTreeNode("Ім'я: " + empl_name));
                        employeeNode.add(new DefaultMutableTreeNode("Ім'я по батькові: " + empl_patronymic));
                        employeeNode.add(new DefaultMutableTreeNode("Дата народження: " + date_of_birth));
                        employeeNode.add(new DefaultMutableTreeNode("Дата початку роботи: " + date_of_start));
                        employeeNode.add(new DefaultMutableTreeNode("Номер телефона: " + phone_number));
                        employeeNode.add(new DefaultMutableTreeNode("Місто: " + city));
                        employeeNode.add(new DefaultMutableTreeNode("Вулиця: " + street));
                        employeeNode.add(new DefaultMutableTreeNode("ZIP-код: " + zip_code));
                        employeeNode.add(new DefaultMutableTreeNode("Зарплата: " + salary));

                        root.add(employeeNode);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти інформацію про касира", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        tree = new JTree(root);
        expandAll(tree, new TreePath(root));
        scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        return scrollPane;
    }

    // 26. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО КАСИРІВ
    public static String[] getCashierArray() {
        List<String> resultList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id_employee, empl_surname, empl_name FROM Employee WHERE empl_role = 'Касир'";
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
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список касирів", "Помилка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return resultList.toArray(new String[0]);
    }

    // 27. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО КЛІЄНТІВ
    public static String[] getClientsArray() {
        List<String> resultList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT card_number, cust_surname, cust_name, percent FROM Customer_Card";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String card_number = resultSet.getString("card_number");
                        String cust_surname = resultSet.getString("cust_surname");
                        String cust_name = resultSet.getString("cust_name");
                        int percent = resultSet.getInt("percent");

                        resultList.add(card_number + ": " + cust_surname + " " + cust_name + ". Знижка: " + percent + "%");

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося отримати список клієнтів", "Помилка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return resultList.toArray(new String[0]);
    }

    // 28. ЗНАХОДЖЕННЯ ЦІНИ ПЕВНОГО ТОВАРУ
    public static double findProductPrice(String UPC, String card_number) {
        double productPrice = 0.0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT selling_price FROM Store_Product WHERE UPC = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, UPC);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        productPrice = resultSet.getDouble("selling_price");

                        int clientPercent = GetClientsPercent(card_number);

                        double discount = productPrice * clientPercent / 100.0;

                        productPrice -= discount;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не вдалося знайти ціну товару", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return productPrice;
    }
}




