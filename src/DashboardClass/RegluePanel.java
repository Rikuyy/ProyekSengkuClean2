package project;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegluePanel extends JPanel {

    public RegluePanel(Dashboard dashboard) {
        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1060, 730));

        // Tombol Kembali
        JButton backButton = new JButton(new FlatSVGIcon("SVGClean/Back.svg"));
        backButton.setBounds(30, 20, 55, 55);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
       
        add(backButton);

        // Header backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.showDashboardContent(); // method khusus untuk kembali
            }
        });
        add(backButton);

        // Header
        JLabel header = new JLabel(new FlatSVGIcon("SVGReglue/Reglue.svg"));
        header.setBounds(95, 25, 240, 45);
        add(header);

        // REGULER
        JLabel small = ServiceLabel("SVGReglue/Small.svg", 100, 80, 5);
        add(small);

        // DEEP
        JLabel medium = ServiceLabel("SVGReglue/Medium.svg", 580, 80, 6);
        add(medium);

        // UNYELLOWING
        
        JLabel hard = ServiceLabel("SVGReglue/Hard.svg", 100, 310, 7);
        add(hard);

        // WHITENING
        JLabel glutotal = ServiceLabel("SVGReglue/Total.svg", 580, 310, 8);
        add(glutotal);
    }
    private JLabel ServiceLabel(String iconPath, int x, int y, int serviceIndex) {
        JLabel label = new JLabel(new FlatSVGIcon(iconPath));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(x, y, 440, 200);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SelectedService(serviceIndex);
            }
        });
        return label;
    }
    private void SelectedService(int serviceIndex) {
    AddOrder addOrder = new AddOrder((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
    addOrder.setSelectedServiceIndex(serviceIndex);
    addOrder.setVisible(true); 
    }
}
