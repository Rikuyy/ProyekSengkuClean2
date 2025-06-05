package Custom;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class PanelShadow extends JPanel {
    private int shadowSize = 2; // Ukuran shadow
    private Color shadowColor = new Color(0, 0, 0, 80); // Warna shadow dengan transparansi

    public PanelShadow() {
        setOpaque(false); // Supaya shadow terlihat
        setPreferredSize(new Dimension(250, 150)); // Ukuran panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Aktifkan anti-aliasing agar lebih halus
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Buat BufferedImage untuk panel dengan shadow
        int width = getWidth();
        int height = getHeight();
        BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gPanel = panelImage.createGraphics();
        gPanel.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gambar panel utama (kotak, tanpa sudut tumpul)
        gPanel.setColor(getBackground());
        gPanel.fillRect(0, 0, width - shadowSize, height);
        gPanel.dispose();

        // Tambahkan efek blur hanya di sisi kanan
        BufferedImage shadowImage = createShadow(panelImage, shadowSize);
        g2.drawImage(shadowImage, 0, 0, null);
        g2.drawImage(panelImage, 0, 0, null);

        g2.dispose();
    }

    // Fungsi untuk membuat shadow dengan efek blur di kanan saja
    private BufferedImage createShadow(BufferedImage panelImage, int shadowSize) {
        int width = panelImage.getWidth();
        int height = panelImage.getHeight();
        BufferedImage shadowImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gShadow = shadowImage.createGraphics();
        gShadow.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gambar shadow hanya di kanan
        gShadow.setColor(shadowColor);
        gShadow.fillRect(width - shadowSize, shadowSize, shadowSize, height - shadowSize * 2);
        gShadow.dispose();

        return blurImage(shadowImage, shadowSize / 2 + 2);
    }

    // Metode untuk blur gambar menggunakan Kernel Gaussian
    private BufferedImage blurImage(BufferedImage image, int radius) {
        int size = radius * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];

        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }

        Kernel kernel = new Kernel(size, size, data);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(image, null);
    }
}