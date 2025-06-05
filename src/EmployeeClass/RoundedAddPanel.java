package EmployeeClass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.function.Consumer;

public class RoundedAddPanel extends JPanel {
    private final Consumer<EmployeeData> onAddCallback;

    public RoundedAddPanel(Consumer<EmployeeData> onAddCallback) {
        this.onAddCallback = onAddCallback;

        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(1050, 150));
        setMaximumSize(new Dimension(1050, 150));
        setBackground(new Color(26, 37, 135));
        setLayout(new GridBagLayout());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showFormDialog();
            }
        });
    }

    private void showFormDialog() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        EmployeeFormDialog dialog = new EmployeeFormDialog(parentFrame);
        dialog.setVisible(true);
        EmployeeData data = dialog.getEmployeeData();
        if (data != null) {
            try {
                onAddCallback.accept(data);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + ex.getMessage());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(31, 44, 150));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(new Color(200, 200, 255));
        g2.setFont(getFont().deriveFont(Font.PLAIN, 40f));
        FontMetrics fm = g2.getFontMetrics();
        String plus = "+";
        int x = (getWidth() - fm.stringWidth(plus)) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 5;
        g2.drawString(plus, x, y);
        g2.dispose();
    }
}
