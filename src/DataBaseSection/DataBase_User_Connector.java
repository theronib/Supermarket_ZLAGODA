package DataBaseSection;

import javax.swing.*;
import java.sql.*;

public class DataBase_User_Connector {
    private static final String DB_URL = "jdbc:ucanaccess:///Users/macbookpro/Desktop/Studying/University/Lessons/Second Year/БД/2nd Semester/АІС/Supermarket/DataBase/ZLAGODA_Users.accdb";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";


    // 1. ПЕРЕВІРКА НА ЗАРЕЄСТРОВАНОГО МЕНЕДЖЕРА
    public static boolean checkManagerLogin(String login, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Managers WHERE username = ? AND password_hash = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. ПЕРЕВІРКА НА ЗАРЕЄСТРОВАНОГО КАСИРА
    public static boolean checkCashierLogin(String login, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Cashiers WHERE username = ? AND password_hash = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. ЗМІНА ПАРОЛЮ ДЛЯ КАСИРА
    public static boolean changeCashierPassword(String password, String login) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Cashiers\n" +
                    "SET password_hash = ? \n" +
                    "WHERE username = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, password);
                statement.setString(2, login);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. ЗМІНА ПАРОЛЮ ДЛЯ МЕНЕДЖЕРА
    public static boolean changeManagerPassword(String password, String login) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Managers\n" +
                    "SET password_hash = ? \n" +
                    "WHERE username = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, password);
                statement.setString(2, login);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. ЗАРЕЄСТРУВАТИ КАСИРА
    public static void RegisterCashierUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Cashiers(username, password_hash) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Касир був успішно зареєстрований", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 6. ЗАРЕЄСТРУВАТИ МЕНЕДЖЕРА
    public static void RegisterManagerUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Managers(username, password_hash) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Менеджер був успішно зареєстрований", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 7. ВИДАЛИТИ КАСИРА З БАЗИ ДАНИХ
    public static void DeleteCashierAccountFromDB(String cashier_username) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Cashiers WHERE username = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cashier_username);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Акаунт касира було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 8. ВИДАЛИТИ МЕНЕДЖЕРА З БАЗИ ДАНИХ
    public static void DeleteManagerAccountFromDB(String manager_username) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Managers WHERE username = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, manager_username);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Акаунт менеджера було успішно видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка підключення або виконання запиту", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

}
