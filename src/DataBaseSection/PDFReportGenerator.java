package DataBaseSection;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


public class PDFReportGenerator {

    private static final String DB_URL = "jdbc:ucanaccess:///Users/macbookpro/Desktop/Studying/University/Lessons/Second Year/БД/2nd Semester/АІС/Supermarket/DataBase/ZLAGODA.accdb";

    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static final int ROWS_PER_PAGE = 36;

    // СТВОРЕННЯ ЗВІТІВ ДЛЯ ПРОДУКТІВ І КАТЕГОРІЇ ПРОДУКТІВ
    public static void generateProductReport(DefaultTableModel model, String filePath, String name_of_table) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDFont font = PDType0Font.load(document, PDFReportGenerator.class.getResourceAsStream("/ArialUnicodeMS.ttf"));

            // Додання верхнього колонтитулу з назвою таблиці
            PDPageContentStream upperContentStream = new PDPageContentStream(document, page);
            upperContentStream.beginText();
            upperContentStream.setFont(font, 12);
            upperContentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 20);
            upperContentStream.showText(name_of_table);
            upperContentStream.endText();
            upperContentStream.close();

            int rowCount = model.getRowCount();
            int currentPage = 1;
            int currentRow = 0;

            while (currentRow < rowCount) {
                PDPageContentStream tableContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
                float yStart = page.getMediaBox().getHeight() - 50;
                drawTable(tableContentStream, page, yStart, model, font, currentRow);
                tableContentStream.close();

                addFooter(document, page, currentPage, 500);

                document.addPage(new PDPage());
                page = document.getPage(document.getNumberOfPages() - 1);

                currentPage++;
                currentRow += ROWS_PER_PAGE;
            }

            document.save(filePath);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void drawTable(PDPageContentStream contentStream, PDPage page, float yStart, DefaultTableModel model, PDFont font, int startRow) throws Exception {
        float margin = 50;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float bottomMargin = 70;
        float rowHeight = 20;
        int numberOfRows = model.getRowCount();
        int numberOfColumns = model.getColumnCount();
        float[] columnWidths = new float[numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            columnWidths[i] = 1.0f / numberOfColumns;
        }

        float yPosition = yStart;

        // Відображення назв колонок
        for (int column = 0; column < numberOfColumns; column++) {
            String columnName = model.getColumnName(column);
            contentStream.beginText();
            contentStream.setFont(font, 10);
            contentStream.newLineAtOffset(margin + column * (tableWidth / numberOfColumns), yPosition);
            contentStream.showText(columnName);
            contentStream.endText();
        }
        yPosition -= rowHeight;

        // Відображення вмісту таблиці
        for (int row = startRow; row < Math.min(startRow + ROWS_PER_PAGE, numberOfRows); row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                String text = model.getValueAt(row, column).toString();
                contentStream.beginText();
                contentStream.setFont(font, 10);
                contentStream.newLineAtOffset(margin + column * (tableWidth / numberOfColumns), yPosition);
                contentStream.showText(text);
                contentStream.endText();
            }
            yPosition -= rowHeight;
        }
    }

    private static void addFooter(PDDocument document, PDPage page, int currentPage, int x) throws IOException {
        PDFont font = PDType0Font.load(document, PDFReportGenerator.class.getResourceAsStream("/ArialUnicodeMS.ttf"));
        PDPageContentStream footerContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
        footerContentStream.beginText();
        footerContentStream.setFont(font, 10);
        footerContentStream.newLineAtOffset(x, 15);
        footerContentStream.showText("Стор. " + currentPage);
        footerContentStream.endText();
        footerContentStream.close();
    }

    //////////////////////////////////////////


    // СТВОРЕННЯ ЗВІТІВ ДЛЯ ПРАЦІВНИКІВ, ПОСТІЙНИХ КЛІЄНТІВ, ЧЕКІВ, ПРОДУКТІВ У МАГАЗИНІ
    public static void generateHorizontalReport(DefaultTableModel model, String filePath, String name_of_table, float scaleFactor) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(new PDRectangle(PDRectangle.A3.getHeight(), PDRectangle.A3.getWidth())); // Збільшення ширини сторінки до A3
            document.addPage(page);

            PDFont font = PDType0Font.load(document, PDFReportGenerator.class.getResourceAsStream("/ArialUnicodeMS.ttf"));

            PDPageContentStream upperContentStream = new PDPageContentStream(document, page);
            upperContentStream.beginText();
            upperContentStream.setFont(font, 12);
            upperContentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 20);
            upperContentStream.showText(name_of_table);
            upperContentStream.endText();
            upperContentStream.close();

            int rowCount = model.getRowCount();
            int currentPage = 1;
            int currentRow = 0;

            while (currentRow < rowCount) {
                PDPageContentStream tableContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
                float xStart = 50;
                float yStart = page.getMediaBox().getHeight() - 50;
                drawHorizontalTable(tableContentStream, page, xStart, yStart, model, font, currentRow, scaleFactor);
                tableContentStream.close();

                addFooter(document, page, currentPage, 1150);

                document.addPage(new PDPage(new PDRectangle(PDRectangle.A3.getHeight(), PDRectangle.A3.getWidth()))); // Додавання нової сторінки A3
                page = document.getPage(document.getNumberOfPages() - 1);

                currentPage++;
                currentRow += ROWS_PER_PAGE;
            }

            document.save(filePath);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void drawHorizontalTable(PDPageContentStream contentStream, PDPage page, float xStart, float yStart, DefaultTableModel model, PDFont font, int startRow, float scaleFactor) throws Exception {
        float margin = 50;
        float tableHeight = page.getMediaBox().getHeight() - 2 * margin;
        float bottomMargin = 70;
        float columnWidth = ((page.getMediaBox().getWidth() - 2 * margin) / model.getColumnCount()) * scaleFactor; // Збільшення ширини колонок на 20%
        float rowHeight = 20;
        int numberOfRows = model.getRowCount();
        int numberOfColumns = model.getColumnCount();

        float xPosition = xStart;
        float yPosition = yStart;

        for (int column = 0; column < numberOfColumns; column++) {
            String columnName = model.getColumnName(column);
            contentStream.beginText();
            contentStream.setFont(font, 10);
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(columnName);
            contentStream.endText();
            xPosition += columnWidth;
        }
        yPosition -= rowHeight;

        for (int row = startRow; row < Math.min(startRow + ROWS_PER_PAGE, numberOfRows); row++) {
            xPosition = xStart; // Повернення до початкової позиції X
            for (int column = 0; column < numberOfColumns; column++) {
                String text = model.getValueAt(row, column).toString();
                contentStream.beginText();
                contentStream.setFont(font, 10);
                contentStream.newLineAtOffset(xPosition, yPosition);
                contentStream.showText(text);
                contentStream.endText();
                xPosition += columnWidth;
            }
            yPosition -= rowHeight;
        }
    }

    //////////////////////////////////////////


    // СТВОРЕННЯ ЗВІТІВ ДЛЯ ОКРЕМОГО ЧЕКА
    public static void generatePDFFromJTree(JTree tree, String filePath) {
        try (PDDocument document = new PDDocument()) {
            float width = 900;
            float height = 692;
            PDRectangle pageSize = new PDRectangle(width, height);

            PDPage page = new PDPage(pageSize);

            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDFont font = PDType0Font.load(document, PDFReportGenerator.class.getResourceAsStream("/ArialUnicodeMS.ttf"));

            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 500);

            DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
            traverseTree(root, contentStream);

            contentStream.endText();
            contentStream.close();

            document.save(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void traverseTree(DefaultMutableTreeNode node, PDPageContentStream contentStream) throws IOException {
        if (node != null) {
            contentStream.showText(node.getUserObject().toString());
            contentStream.newLine();
            for (int i = 0; i < node.getChildCount(); i++) {
                traverseTree((DefaultMutableTreeNode) node.getChildAt(i), contentStream);
            }
        }
    }


    //////////////////////////////////////////

    // 1. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ТОВАРИ
    private static DefaultTableModel getProductTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Product.id_product, Product.product_name, Category.category_name\n" +
                    "FROM Product\n" +
                    "INNER JOIN Category ON Product.category_number = Category.category_number;";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setColumnCount(0);

                String[] columnNames = {"Номер товару", "Назва", "Категорія"};
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

        return model;
    }

    // 2. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ТОВАРИ В МАГАЗИНІ
    public static DefaultTableModel getProductsInStoreTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Store_Product.UPC, Store_Product.UPC_prom, Product.product_name, Category.category_name, Store_Product.products_number, Store_Product.selling_price, Product.characteristics, " +
                    "CASE " +
                    "   WHEN Store_Product.promotional_product = 'true' THEN 'Акційний' " +
                    "   WHEN Store_Product.promotional_product = 'false' THEN 'Звичайний' " +
                    "   ELSE 'Невідомий' " +
                    "END AS promotional_product " +
                    "FROM ((Product " +
                    "INNER JOIN Store_Product ON Product.id_product = Store_Product.id_product) " +
                    "INNER JOIN Category ON Product.category_number = Category.category_number)";



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
                        Object value = resultSet.getObject(i);
                        row[i - 1] = value != null ? value.toString() : "";
                    }
                    model.addRow(row);
                }
            }
        }
        return model;
    }

    // 3. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО КАТЕГОРІЇ
    public static DefaultTableModel getCategoryTableModel() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Category";

            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

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
        }

        return model;
    }

    // 4. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ПРАЦІВНИКІВ
    public static DefaultTableModel getEmployeeTableModel() throws SQLException {
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
                        String date_of_birth = resultSet.getString("date_of_birth").split(" ")[0]; // Відкидаємо частину "00:00:00" з дати народження
                        String date_of_start = resultSet.getString("date_of_start").split(" ")[0]; // Відкидаємо частину "00:00:00" з дати початку роботи
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
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }

    // 5. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО КЛІЄНТІВ
    public static DefaultTableModel getClientsTableModel() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

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
                        Object value = resultSet.getObject(i);
                        row[i - 1] = value != null ? value.toString() : "";
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }

    // 6. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ЧЕКИ
    public static DefaultTableModel getChecksModel() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT Check.check_number, Employee.empl_surname, Customer_Card.cust_surname, Check.card_number, Check.print_date, Check.sum_total, Check.vat\n" +
                    "FROM [Check]\n" +
                    "INNER JOIN Employee ON Check.id_employee = Employee.id_employee\n" +
                    "LEFT JOIN Customer_Card ON Check.card_number = Customer_Card.card_number";


            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

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
                        Object value = resultSet.getObject(i);
                        row[i - 1] = value != null ? value.toString() : "";
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }

    // 7. ОТРИМАННЯ ІНФОРМАЦІЇ ПРО ОДИН ЧЕК
    public static JTree getCheck(long checkId) throws SQLException {
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
                    "WHERE Check.check_number=?;";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, checkId);

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
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }

        JTree tree = new JTree(root);
        expandAll(tree, new TreePath(root));

        return tree;
    }

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



    // 1. ГЕНЕРАЦІЯ ЗВІТУ ДЛЯ ПРОДУКТІВ - DONE
    public void GenerateReportForProducts(){
        String filePath = "Звіти/product_report.pdf";
        try {
            DefaultTableModel model = getProductTable();
            generateProductReport(model, filePath, "Звіт по продуктам");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 2. ГЕНЕРАЦІЯ ЗВІТУ ДЛЯ ПРОДУКТІВ У МАГАЗИНІ - DONE
    public void GenerateReportForProductsInStore(){
        String filePath = "Звіти/product_in_store_report.pdf";
        try {
            DefaultTableModel model = getProductsInStoreTable();
            generateHorizontalReport(model, filePath, "Звіт по продуктам у магазині", 1.1f);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 3. ГЕНЕРАЦІЯ ЗВІТУ ДЛЯ КАТЕГОРІЇ ПРОДУКТІВ - DONE
    public void GenerateReportForProductCategory(){
        String filePath = "Звіти/product_category.pdf";
        try {
            DefaultTableModel model = getCategoryTableModel();
            generateProductReport(model, filePath, "Звіт по категоріям товарів");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 4. ГЕНЕРАЦІЯ ЗВІТУ ПРО ПРАЦІВНИКІВ - GOOD
    public void GenerateReportForEmployee(){
        String filePath = "Звіти/employee.pdf";
        try {
            DefaultTableModel model = getEmployeeTableModel();
            generateHorizontalReport(model, filePath, "Звіт по працівникам", 1.08f);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 5. ГЕНЕРАЦІЯ ЗВІТУ ПРО ПОСТІЙНИХ КЛІЄНТІВ
    public void GenerateReportForClients(){
        String filePath = "Звіти/customers_with_card.pdf";
        try {
            DefaultTableModel model = getClientsTableModel();
            generateHorizontalReport(model, filePath, "Звіт по клієнтам з карткою", 1.1f);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 6. ГЕНЕРАЦІЯ ЗВІТУ ПРО ВСІ СТВОРЕНІ ЧЕКИ
    public void GenerateReportForChecks(){
        String filePath = "Звіти/created_checks.pdf";
        try {
            DefaultTableModel model = getChecksModel();
            generateHorizontalReport(model, filePath, "Звіт по чекам", 1.1f);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 7. ГЕНЕРАЦІЯ ЧЕКУ
    public void GenerateCheck(long checkId){
        String filePath = "Чеки/check_" + checkId + ".pdf";
        try {
            JTree tree = getCheck(checkId);
            generatePDFFromJTree(tree, filePath);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
