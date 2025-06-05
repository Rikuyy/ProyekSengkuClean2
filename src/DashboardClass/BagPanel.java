package project;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel reguler = new JLabel();
        reguler.setIcon(new FlatSVGIcon("SVGRepaint/Small.svg"));
        reguler.setHorizontalAlignment(SwingConstants.CENTER);
        reguler.setBounds(100, 80, 440, 200);
        add(reguler);

        // DEEP
        JLabel deep = new JLabel();
        deep.setIcon(new FlatSVGIcon("SVGRepaint/Medium.svg"));
        deep.setHorizontalAlignment(SwingConstants.CENTER);
        deep.setBounds(580, 80, 440, 200);
        add(deep);

    }
}