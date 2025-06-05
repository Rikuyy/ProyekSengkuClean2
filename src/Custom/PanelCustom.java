package Custom;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PanelCustom extends JPanel {

    public PanelCustom() {
        setOpaque(false); // Membuat panel transparan agar efek gradasi terlihat`
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Gradasi dari biru tua ke putih
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(20, 20, 150), // Warna awal (Biru tua)
                getWidth(), 0, Color.WHITE   // Warna akhir (Putih)
        );

        g2d.setPaint(gradient);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Sudut membulat
    }
}
