package project;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger; 

public class CleaningPanel extends JPanel {
private AddOrder.OrderListener orderListener;
   
    public void setOrderListener(AddOrder.OrderListener listener) {
        this.orderListener = listener;
        }
    public CleaningPanel(Dashboard dashboard) {
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
        JLabel header = new JLabel(new FlatSVGIcon("SVGClean/Cleaning.svg"));
        header.setBounds(100, 25, 300, 50);
        add(header);

        // REGULER
        JLabel reguler = ServiceLabel("SVGClean/Reguler.svg", 100, 80, 1);
        add(reguler);

        // DEEP - index 2
        JLabel deep = ServiceLabel("SVGClean/Deep.svg", 580, 80, 2);
        add(deep);

        // UNYELLOWING - index 3
        JLabel unyellowing = ServiceLabel("SVGClean/Unyellowing.svg", 100, 310, 3);
        add(unyellowing);

        // WHITENING - index 4
        JLabel whitening = ServiceLabel("SVGClean/Whitening.svg", 580, 310, 4);
        add(whitening);
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
