package EmployeeClass;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.File;
import java.sql.*;

public class DetailPanel extends JPanel {
    private final JTextField tfId, tfNama, tfEmail, tfAlamat, tfTelepon, tfUsia;
    private final JLabel fotoLabel;
    private final EmployeePanel employeePanel;

    public DetailPanel(EmployeeData data, EmployeePanel employeePanel, Runnable onBack) {
        this.employeePanel = employeePanel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // === TOP PANEL ===
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnBack = new JButton("← Kembali");
        btnBack.setFocusPainted(false);
        btnBack.setBackground(new Color(30, 44, 144));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnBack.setPreferredSize(new Dimension(110, 35));
        btnBack.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnBack.addActionListener(e -> onBack.run());

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(Color.WHITE);
        backPanel.add(btnBack);

        JLabel title = new JLabel("Detail Karyawan");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(26, 37, 135));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(backPanel, BorderLayout.WEST);
        topPanel.add(title, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // === FOTO PANEL ===
        fotoLabel = new JLabel(new FlatSVGIcon("SVGEm/User.svg", 200, 200), SwingConstants.CENTER);
        fotoLabel.setOpaque(true);
        fotoLabel.setBackground(Color.WHITE);
        fotoLabel.setPreferredSize(new Dimension(200, 200));
        updateFoto(data.getFoto());

        JButton btnUpload = new JButton("Upload Foto");
        btnUpload.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnUpload.setFocusPainted(false);
        btnUpload.setBackground(new Color(30, 44, 144));
        btnUpload.setForeground(Color.WHITE);
        btnUpload.setPreferredSize(new Dimension(150, 35));
        btnUpload.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                try {
                    byte[] fotoBytes = java.nio.file.Files.readAllBytes(selectedFile.toPath());
                    updateFoto(fotoBytes);
                    data.setFoto(fotoBytes);
                    if (employeePanel != null) {
                        employeePanel.updateFoto();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Gagal membaca file foto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.Y_AXIS));
        photoPanel.setBackground(Color.WHITE);
        photoPanel.add(fotoLabel);
        photoPanel.add(Box.createVerticalStrut(10));

        JPanel btnUploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        btnUploadPanel.setBackground(Color.WHITE);
        btnUploadPanel.add(btnUpload);
        photoPanel.add(btnUploadPanel);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.add(photoPanel);

        // === FORM PANEL ===
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfId = createTextField(data.getId());
        tfNama = createTextField(data.getNama());
        tfEmail = createTextField(data.getEmail());
        tfAlamat = createTextField(data.getAlamat());
        tfTelepon = createTextField(data.getTelepon());
        tfUsia = createTextField(String.valueOf(data.getUsia()));

        int row = 0;
        addRow(formPanel, gbc, row++, "ID", tfId);
        addRow(formPanel, gbc, row++, "Nama", tfNama);
        addRow(formPanel, gbc, row++, "Email", tfEmail);
        addRow(formPanel, gbc, row++, "Alamat", tfAlamat);
        addRow(formPanel, gbc, row++, "No Telepon", tfTelepon);
        addRow(formPanel, gbc, row++, "Usia", tfUsia);

        // Center both photo and form
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        contentPanel.add(Box.createHorizontalGlue());
        contentPanel.add(leftPanel);
        contentPanel.add(Box.createHorizontalStrut(40));
        contentPanel.add(formPanel);
        contentPanel.add(Box.createHorizontalGlue());

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(contentPanel, BorderLayout.CENTER);

        // === TABEL RIWAYAT SERVIS ===
        String[] columnNames = {"No", "Nama Pelanggan", "No HP", "Merk", "Waktu", "Tipe"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sengkuclean2", "root", "");
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT t.id_transaksi AS id, p.nama_pelanggan AS nama_pelanggan, " +
                "pe.no_hp AS no_hp, p.nama_barang AS barang, p.nama_barang AS nama_barang, " +
                "t.waktu AS waktu, p.layanan AS tipe " +
                "FROM pesanan p " +
                "LEFT JOIN transaksipemesanan t ON t.id_transaksi = p.id_transaksi " +
                "LEFT JOIN pelanggan pe ON p.id_pelanggan = pe.id_pelanggan " +
                "LEFT JOIN karyawan k ON t.id_karyawan = k.id_karyawan " +
                "WHERE t.id_karyawan = ? " +
                "ORDER BY t.id_transaksi"
             )
        ) {
            stmt.setString(1, data.getId());
            ResultSet rs = stmt.executeQuery();
            int no = 1;
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    no++,
                    rs.getString("nama_pelanggan"),
                    rs.getString("no_hp"),                    
                    rs.getString("nama_barang"),                    
                    rs.getString("waktu"),
                    rs.getString("tipe")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal mengambil riwayat servis.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        JTable riwayatTable = new JTable(tableModel);
        riwayatTable.setRowHeight(28);
        riwayatTable.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JTableHeader header = riwayatTable.getTableHeader();
        header.setBackground(new Color(26, 37, 135));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < riwayatTable.getColumnCount(); i++) {
            riwayatTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScroll = new JScrollPane(riwayatTable);
        tableScroll.setPreferredSize(new Dimension(850, 200));
        tableScroll.setBorder(BorderFactory.createTitledBorder("Riwayat Servis"));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(infoPanel, BorderLayout.NORTH);
        centerPanel.add(tableScroll, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void updateFoto(byte[] fotoBytes) {
        if (fotoBytes != null && fotoBytes.length > 0) {
            ImageIcon icon = new ImageIcon(fotoBytes);
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            fotoLabel.setIcon(new ImageIcon(scaled));
            fotoLabel.setText("");
        } else {
            fotoLabel.setIcon(new FlatSVGIcon("SVGEm/User.svg", 200, 200));
            fotoLabel.setText("");
        }
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField tf) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(tf, gbc);
    }

    private JTextField createTextField(String value) {
        JTextField tf = new JTextField(value);
        tf.setEditable(false);
        tf.setPreferredSize(new Dimension(250, 30));
        tf.setBackground(new Color(245, 245, 245));
        tf.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return tf;
    }
}
