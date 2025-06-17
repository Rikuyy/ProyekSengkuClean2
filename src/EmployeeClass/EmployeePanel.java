 package EmployeeClass;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EmployeePanel extends JPanel {
    private final EmployeeData data;
    private final Runnable onExplore;
    private final Runnable onDelete;
    private final JLabel imageLabel;

    public EmployeePanel(EmployeeData data, Runnable onExplore, Runnable onDelete) {
        this.data = data;
        this.onExplore = onExplore;
        this.onDelete = onDelete;

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setOpaque(false);
        setPreferredSize(new Dimension(1050, 150));
        setMaximumSize(new Dimension(1050, 150));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(100, 100));
        imageLabel.setMaximumSize(new Dimension(100, 100));
        imageLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        updateFoto();

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        infoPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(getSafeText(data.getNama()));
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);

        int usia = data.getUsia();
        JLabel usiaLabel = new JLabel(usia > 0 ? usia + " tahun" : "");

        JLabel alamatLabel = new JLabel(getSafeText(data.getAlamat()));
        JLabel teleponLabel = new JLabel(getSafeText(data.getTelepon()));
        JLabel emailLabel = new JLabel(getSafeText(data.getEmail()));

        for (JLabel lbl : new JLabel[]{usiaLabel, alamatLabel, teleponLabel, emailLabel}) {
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lbl.setForeground(Color.WHITE);
        }

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(usiaLabel);
        infoPanel.add(alamatLabel);
        infoPanel.add(teleponLabel);
        infoPanel.add(emailLabel);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(150, 130));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JButton exploreButton = new JButton("Jelajahi ⏩");
        exploreButton.setFocusPainted(false);
        exploreButton.setForeground(Color.WHITE);
        exploreButton.setBackground(new Color(26, 37, 135));
        exploreButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        exploreButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exploreButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exploreButton.addActionListener(e -> onExplore.run());

        JPanel exploreWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exploreWrapper.setOpaque(false);
        exploreWrapper.add(exploreButton);

        JButton deleteButton = new JButton(new FlatSVGIcon("SVGEm/trash.svg", 18, 18));
        deleteButton.setToolTipText("Hapus");
        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setOpaque(false);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> onDelete.run());

        JPanel deleteWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteWrapper.setOpaque(false);
        deleteWrapper.add(deleteButton);

        buttonPanel.add(exploreWrapper, BorderLayout.NORTH);
        buttonPanel.add(deleteWrapper, BorderLayout.SOUTH);

        add(imageLabel);
        add(infoPanel);
        add(Box.createHorizontalGlue());
        add(buttonPanel);
    }

    public void updateFoto() {
        byte[] fotoBytes = data.getFoto();

        if (fotoBytes != null && fotoBytes.length > 0) {
            // Buat ImageIcon dari byte[]
            ImageIcon img = new ImageIcon(fotoBytes);

            // Resize gambar
            Image scaled = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            imageLabel.setIcon(new ImageIcon(scaled));
        } else {
            imageLabel.setIcon(new FlatSVGIcon("SVGEm/User.svg", 100, 100));
        }

        revalidate();
        repaint();
    }

    private String getSafeText(String text) {
        return text != null ? text : "";
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(26, 37, 135));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        g2.dispose();
        super.paintComponent(g);
    }
}