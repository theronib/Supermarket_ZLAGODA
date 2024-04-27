package DataBaseSection;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDFViewer extends JFrame {
    private JPanel panel;
    private float scale = 1.1f;

    public PDFViewer(String title, int width) {
        setTitle(title);
        setSize(width, 900);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());
        getContentPane().add(new JScrollPane(panel));
    }

    public void openPDF(String filePath) {
        try {
            File file = new File(filePath);
            PDDocument document = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();
            JPanel imagePanel = new JPanel(new GridLayout(pageCount, 1));
            for (int i = 0; i < pageCount; i++) {
                RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                BufferedImage image = renderer.renderImage(i, scale, ImageType.RGB);
                JLabel label = new JLabel(new ImageIcon(image));
                imagePanel.add(label);
            }
            document.close();
            panel.add(imagePanel, BorderLayout.NORTH);
            revalidate();
            repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка відкриття PDF файлу", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
}