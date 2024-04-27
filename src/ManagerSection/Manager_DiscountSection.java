package ManagerSection;

import DataBaseSection.Cashier_Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager_DiscountSection extends JFrame {

    //////////////////////
    // СЕКЦІЯ: "Акційні товари"
    //////////////////////

    // 1. Усі акційні товари
    public void OpenDiscountProducts() {
        DiscountProducts discountProducts = new DiscountProducts();
        discountProducts.setVisible(true);
        this.dispose();
    }

    public class DiscountProducts extends JFrame {

        public DiscountProducts() {
            setTitle("Перегляд акційних товарів");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Усі акційні товари у магазині");
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

            JScrollPane scrollPane = Cashier_Query.showSaleProducts();
            scrollPane.setBounds(24, 70, 850, 400);
            panel.add(scrollPane);

            panel.add(backButton);
            panel.add(label);
            add(panel);
            setVisible(true);

        }
    }

    // 2. Усі не акційні товари
    public void OpenNoneDiscountProducts() {
        NoneDiscountProducts noneDiscountProducts = new NoneDiscountProducts();
        noneDiscountProducts.setVisible(true);
        this.dispose();
    }

    public class NoneDiscountProducts extends JFrame {

        public NoneDiscountProducts() {
            setTitle("Перегляд не акційних товарів");
            setSize(900, 580);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(null);
            panel.setBackground(new Color(141, 178,196));

            JLabel label = new JLabel("Усі не акційні товари у магазині");
            label.setBounds(24, -15, 600, 100);
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 23));

            JScrollPane scrollPane = Cashier_Query.showNonSaleProducts();
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
}
