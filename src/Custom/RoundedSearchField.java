/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * @author OZ
 */
public class RoundedSearchField extends JTextField {
    private Color bgColor = new Color(255, 255, 255); 

    public RoundedSearchField(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background with rounded corners
        Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(bgColor);
        g2.fill(round);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Optional: draw rounded border
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 20, 20));
        g2.dispose();
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }
}
