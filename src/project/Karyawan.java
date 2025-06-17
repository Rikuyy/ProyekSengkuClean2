package project;

import DAO.EmployeeDAO;
import EmployeeClass.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Karyawan extends JPanel {

    private final RoundedAddPanel addPanel;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final EmployeeDAO employeeDAO;

    private static final int FIXED_WIDTH = 900;

    public Karyawan(Connection conn) {
        this.employeeDAO = new EmployeeDAO(conn);

        // Inisialisasi komponen utama
        Scroll = new JScrollPane();
        Content = new JPanel();
        addPanel = new RoundedAddPanel(this::handleAddEmployee);

        // Konfigurasi panel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));
        Content.setBackground(Color.WHITE);

        Scroll.setViewportView(Content);
        Scroll.setBorder(null);
        Scroll.setBackground(Color.WHITE);
        Scroll.getVerticalScrollBar().setUnitIncrement(16);

        // Header dengan ikon
        JLabel titleLabel = new JLabel(new FlatSVGIcon("SVGEm/Employee.svg", 210, 45));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 0));
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Panel utama list + scroll
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(Color.WHITE);
        listPanel.add(headerPanel, BorderLayout.NORTH);
        listPanel.add(Scroll, BorderLayout.CENTER);

        // Panel dengan CardLayout (list/detail)
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.add(listPanel, "list");

        add(cardPanel, BorderLayout.CENTER);

        // Load awal data karyawan
        reloadEmployeeList();
    }

    private void handleAddEmployee(EmployeeData data) {
        try {
            if (employeeDAO.emailExists(data.getEmail())) {
                JOptionPane.showMessageDialog(this, "Email sudah terdaftar!", "Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (employeeDAO.insertEmployee(data)) {
                reloadEmployeeList();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambah data.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saat menyimpan data: " + ex.getMessage());
        }
    }

    private void reloadEmployeeList() {
        try {
            Content.removeAll();
            Content.add(Box.createVerticalStrut(20));

            // Panel tambah data
            addPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            addPanel.setMaximumSize(new Dimension(FIXED_WIDTH, addPanel.getPreferredSize().height));
            Content.add(addPanel);
            Content.add(Box.createVerticalStrut(20));

            List<EmployeeData> list = employeeDAO.getAllEmployees();
            for (EmployeeData data : list) {
                EmployeePanel empPanel = createEmployeePanel(data);
                Content.add(empPanel);
                Content.add(Box.createVerticalStrut(15));
            }

            Content.revalidate();
            Content.repaint();

            SwingUtilities.invokeLater(() -> Scroll.getVerticalScrollBar().setValue(0)); // Auto-scroll ke atas
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private EmployeePanel createEmployeePanel(EmployeeData data) {
        EmployeePanel[] empRef = new EmployeePanel[1];
        empRef[0] = new EmployeePanel(
            data,
            () -> showDetailPanel(data, empRef[0]),
            () -> handleDeleteEmployee(data)
        );

        empRef[0].setAlignmentX(Component.CENTER_ALIGNMENT);
        empRef[0].setMaximumSize(new Dimension(FIXED_WIDTH, empRef[0].getPreferredSize().height));

        return empRef[0];
    }

    private void handleDeleteEmployee(EmployeeData data) {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus karyawan ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (employeeDAO.hapusData(data.getId())) {
                    reloadEmployeeList();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void showDetailPanel(EmployeeData data, EmployeePanel empPanel) {
        // Bersihkan panel detail sebelumnya jika ada
        if (cardPanel.getComponentCount() > 1) {
            Component c = cardPanel.getComponent(1);
            if (c instanceof JScrollPane || c instanceof DetailPanel) {
                cardPanel.remove(c);
            }
        }

        DetailPanel detail = new DetailPanel(data, empPanel, () -> {
            cardLayout.show(cardPanel, "list");
            reloadEmployeeList();
        });

        JScrollPane detailScroll = new JScrollPane(detail);
        detailScroll.setBorder(null);
        detailScroll.getVerticalScrollBar().setUnitIncrement(16);

        cardPanel.add(detailScroll, "detail");
        cardLayout.show(cardPanel, "detail");
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Scroll = new javax.swing.JScrollPane();
        Content = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        Scroll.setBackground(new java.awt.Color(255, 255, 255));
        Scroll.setBorder(null);
        Scroll.setViewportView(Content);

        Content.setBackground(new java.awt.Color(255, 255, 255));
        Content.setPreferredSize(new java.awt.Dimension(0, 2000));
        Content.setLayout(new javax.swing.BoxLayout(Content, javax.swing.BoxLayout.Y_AXIS));
        Scroll.setViewportView(Content);

        add(Scroll, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JScrollPane Scroll;
    // End of variables declaration//GEN-END:variables
}
