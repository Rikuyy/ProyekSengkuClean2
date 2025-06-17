package project;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BagPanel extends JPanel {

    public BagPanel(Dashboard dashboard) {
        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1060, 730));

        // Tombol Kembali
        JButton backButton = new JButton(new FlatSVGIcon("SVGClean/Back.svg"));
        backButton.setBounds(30, 20, 55, 55);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.showDashboardContent(); // method khusus untuk kembali
            }
        });
        add(backButton);

        // Header
        JLabel header = new JLabel(new FlatSVGIcon("SVGRepaint/Bag.svg"));
        header.setBounds(100, 28, 150, 45);
        add(header);

        // REGULER
        JLabel bagsmal = ServiceLabel("SVGRepaint/Small.svg", 100, 80, 11);
        add(bagsmal);

        // DEEP
        JLabel bagmed = ServiceLabel("SVGRepaint/Medium.svg", 580, 80, 12);
        add(bagmed);

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
//JLabel medium = ServiceLabel("SVGReglue/Medium.svg", 580, 80, 6);