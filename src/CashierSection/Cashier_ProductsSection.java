package CashierSection;

import DataBaseSection.Cashier_Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cashier_ProductsSection extends JFrame{

    // 1. Перегляд усіх товарів
    public void OpenCheckProducts() {
        Cashier_ProductsSection.CheckProducts checkProducts= new Cashier_ProductsSection.CheckProducts();
        checkProducts.setVisible(true);
        this.dispose();
    }

    public class CheckProducts extends JFrame {

        public CheckProducts() {
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
            backButton.setBounds(24, 500, 40, 40);

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


            panel.setBackground(new Color(226, 212,175));
            panel.add(backButton);
            panel.add(label);
            add(panel);
            setVisible(true);

        }
    }

    // 2. Перегляд усіх товарів в магазині
    public void OpenCheckAllProductsInShop() {
        Cashier_ProductsSection.CheckAllProductsInShop checkAllProductsInShop = new Cashier_ProductsSection.CheckAllProductsInShop();
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

            JLabel label = new JLabel("Усі товари в магазині відсортовані за назвою");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(226, 212,175));


            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Cashier_Query.showProductsInStore();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);


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


    // 3. Пошук товарів за назвою
    public void OpenSearchByName() {
        Cashier_ProductsSection.SearchByName searchByName = new Cashier_ProductsSection.SearchByName();
        searchByName.setVisible(true);
        this.dispose();
    }

    public class SearchByName extends JFrame {
        public SearchByName() {
            setTitle("Пошук товарів за назвою");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            JLabel label = new JLabel("Пошук товарів за назвою");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JLabel label2 = new JLabel("Введіть назву товару:");
            label2.setBounds(24, 40, 600, 100);
            Font labelFont2 = label.getFont();
            label2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 15));

            JTextField textField = new JTextField(10);
            textField.setBounds(24, 105, 530, 25);
            textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

            JButton search_button = new JButton("Пошук");
            search_button.setBounds(560, 102, 100, 33);

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
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(SearchByName.this,
                                "Для пошуку товару потрібно ввести його назву. Будь ласка, спробуйте ще раз.",
                                "Помилка введення",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JScrollPane) {
                                panel.remove(component);
                            }
                        }

                        JScrollPane scrollPane = Cashier_Query.searchProducts(textField);
                        scrollPane.setBounds(24, 150, 850, 320);
                        panel.add(scrollPane);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            });



            panel.add(label);
            panel.add(textField);
            panel.add(search_button);
            panel.add(label2);
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }


    // 4. Пошук товарів за категорією
    public void OpenSearchProductsByCategory() {
        Cashier_ProductsSection.SearchByCategory searchByCategory = new Cashier_ProductsSection.SearchByCategory();
        searchByCategory.setVisible(true);
        this.dispose();
    }

    public class SearchByCategory extends JFrame {
        public SearchByCategory() {
            setTitle("Пошук товарів за категорією");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

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
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(SearchByCategory.this,
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

    // 5. Перегляд акційних товарів
    public void OpenCheckSaleProducts () {
        Cashier_ProductsSection.CheckSaleProducts checkSaleProducts = new Cashier_ProductsSection.CheckSaleProducts();
        checkSaleProducts.setVisible(true);
        this.dispose();
    }

    public class CheckSaleProducts extends JFrame {
        public CheckSaleProducts() {
            setTitle("Перегляд акційних товарів");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);


            JLabel label = new JLabel("Усі акційні товари у магазині");
            label.setBounds(24, -15, 650, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Cashier_Query.showSaleProducts();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });

            panel.add(label);
            panel.setBackground(new Color(226, 212,175));
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }


    // 6. Перегляд не акційних товарів
    public void OpenCheckNoSaleProducts () {
        Cashier_ProductsSection.CheckNoSaleProducts checkNoSaleProducts = new Cashier_ProductsSection.CheckNoSaleProducts();
        checkNoSaleProducts.setVisible(true);
        this.dispose();
    }

    public class CheckNoSaleProducts extends JFrame {
        public CheckNoSaleProducts() {
            setTitle("Перегляд не акційних товарів");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);

            JLabel label = new JLabel("Усі не акційні товари у магазині");
            label.setBounds(24, -15, 650, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            // КНОПКА ПОВЕРНЕННЯ В ОСНОВНЕ МЕНЮ
            final JButton backButton = new JButton("<-");
            backButton.setBounds(20, 500, 40, 40);

            // ВИВЕДЕННЯ ТАБЛИЧКИ
            JScrollPane scrollPane = Cashier_Query.showNonSaleProducts();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                    frame.dispose();

                }
            });


            panel.add(label);
            panel.setBackground(new Color(226, 212,175));
            panel.add(backButton);
            add(panel);
            setVisible(true);

        }
    }

    // 7. Пошук товару за UPC
    public void OpenCheckProductsByUPC() {
        Cashier_ProductsSection.CheckProductsByUPC checkProductsByUPC = new  Cashier_ProductsSection.CheckProductsByUPC();
        checkProductsByUPC.setVisible(true);
        this.dispose();
    }

    public class CheckProductsByUPC extends JFrame {
        public CheckProductsByUPC() {
            setTitle("Пошук товарів за UPC кодом");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);

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

            panel.setBackground(new Color(226, 212,175));

            search_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(CheckProductsByUPC.this,
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
            add(panel);
            setVisible(true);

        }
    }
}
