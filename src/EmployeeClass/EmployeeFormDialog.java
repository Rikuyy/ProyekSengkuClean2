package EmployeeClass;

import DAO.EmployeeDAO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class EmployeeFormDialog extends JDialog {
    private EmployeeData employeeData;
    private final File[] fotoFile = new File[1];

    public EmployeeFormDialog(JFrame parent) {
        super(parent, "Input Data Karyawan", true);

        setSize(400, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField tfNama = new JTextField(20);
        JTextField tfEmail = new JTextField(20);
        JTextField tfAlamat = new JTextField(20);
        JTextField tfTelepon = new JTextField(20);
        JTextField tfUsia = new JTextField(20);
        JButton btnUpload = new JButton("Upload File");

        btnUpload.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Gambar", "jpg", "jpeg", "png"));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                fotoFile[0] = chooser.getSelectedFile();
                btnUpload.setText("✔ " + fotoFile[0].getName());
            }
        });

        int y = 0;
        addLabelAndField(formPanel, gbc, "Nama Lengkap:", tfNama, y++);
        addLabelAndField(formPanel, gbc, "Email:", tfEmail, y++);
        addLabelAndField(formPanel, gbc, "Alamat:", tfAlamat, y++);
        addLabelAndField(formPanel, gbc, "No Telepon:", tfTelepon, y++);
        addLabelAndField(formPanel, gbc, "Usia:", tfUsia, y++);
        addLabelAndField(formPanel, gbc, "Foto Profile:", btnUpload, y++);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        btnOK.setBackground(new Color(26, 37, 135));
        btnOK.setForeground(Color.WHITE);
        btnOK.setFocusPainted(false);
        btnCancel.setFocusPainted(false);

        btnOK.addActionListener(e -> handleSave(tfNama, tfEmail, tfAlamat, tfTelepon, tfUsia));
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnOK);
        buttonPanel.add(btnCancel);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.WHITE);
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void handleSave(JTextField tfNama, JTextField tfEmail, JTextField tfAlamat,
                            JTextField tfTelepon, JTextField tfUsia) {
        String nama = tfNama.getText().trim();
        String email = tfEmail.getText().trim();
        String alamat = tfAlamat.getText().trim();
        String telepon = tfTelepon.getText().trim();
        String usiaStr = tfUsia.getText().trim();

        if (nama.isEmpty() || email.isEmpty() || alamat.isEmpty() || telepon.isEmpty() || usiaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!usiaStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Usia harus berupa angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int usia = Integer.parseInt(usiaStr);

        byte[] fotoBytes = null;

        if (fotoFile[0] != null) {
            if (fotoFile[0].length() > 5 * 1024 * 1024) {
                JOptionPane.showMessageDialog(this, "Ukuran file terlalu besar (maks 5MB).", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                fotoBytes = java.nio.file.Files.readAllBytes(fotoFile[0].toPath());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal membaca file foto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Buat EmployeeData, tapi **JANGAN INSERT DI SINI**
        employeeData = new EmployeeData(nama, usia, alamat, telepon, email, fotoBytes);
        dispose();
    }

    public EmployeeData getEmployeeData() {
        return employeeData;
    }
}

