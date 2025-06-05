package project;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel reguler = new JLabel();
        reguler.setIcon(new FlatSVGIcon("SVGReglue/Small.svg"));
        reguler.setHorizontalAlignment(SwingConstants.CENTER);
        reguler.setBounds(100, 80, 440, 200);
        add(reguler);

        // DEEP
        JLabel deep = new JLabel();
        deep.setIcon(new FlatSVGIcon("SVGReglue/Medium.svg"));
        deep.setHorizontalAlignment(SwingConstants.CENTER);
        deep.setBounds(580, 80, 440, 200);
        add(deep);

        // UNYELLOWING
        JLabel unyellowing = new JLabel();
        unyellowing.setIcon(new FlatSVGIcon("SVGReglue/Hard.svg"));
        unyellowing.setHorizontalAlignment(SwingConstants.CENTER);
        unyellowing.setBounds(100, 310, 440, 200);
        add(unyellowing);

        // WHITENING
        JLabel whitening = new JLabel();
        whitening.setIcon(new FlatSVGIcon("SVGReglue/Total.svg"));
        whitening.setHorizontalAlignment(SwingConstants.CENTER);
        whitening.setBounds(580, 310, 440, 200);
        add(whitening);
    }
}
