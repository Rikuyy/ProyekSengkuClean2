package project;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;


public class Signup extends javax.swing.JFrame {

    private ImageIcon originalIcon;
    private Timer resizeTimer;
    private int targetWidth, targetHeight;
    private static final int ANIMATION_DURATION = 100;
    private static final int FRAME_RATE = 60;
    
    public Signup() {
        initComponents();
        setSize(800, 600); 
        setLocationRelativeTo(null);
        
        setIconImage(new FlatSVGIcon("SVGLogin/lgSCG.svg").getImage());
        
        txUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txCfpass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirm Password");
        txEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        txRfid.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nomor KTM / RFID");
        
        // Membuat panel transparan
        jpSignup.setOpaque(false); // Membuat hanya panel yang transparan
        jpSignup.setBackground(new java.awt.Color(0, 0, 0, 0)); // Transparansi penuh        
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                smoothResize(getWidth(), getHeight());
            }
        });
        
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpSignup = new javax.swing.JPanel();
        btLogin = new javax.swing.JLabel();
        txRfid = new javax.swing.JTextField();
        txEmail = new javax.swing.JTextField();
        txCfpass = new javax.swing.JPasswordField();
        txPass = new javax.swing.JPasswordField();
        txUser = new javax.swing.JTextField();
        btSignup = new javax.swing.JLabel();
        Signup = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        Signup1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jpSignup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btLogin.setIcon(new FlatSVGIcon("SVGSignup/btLogin.svg"));
        btLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btLoginMouseExited(evt);
            }
        });
        jpSignup.add(btLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 75, 30));

        txRfid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txRfid.setBorder(null);
        txRfid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txRfidActionPerformed(evt);
            }
        });
        txRfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txRfidKeyPressed(evt);
            }
        });
        jpSignup.add(txRfid, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 233, 150, 30));

        txEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txEmail.setBorder(null);
        jpSignup.add(txEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 195, 150, 30));

        txCfpass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txCfpass.setBorder(null);
        jpSignup.add(txCfpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 158, 150, 30));

        txPass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txPass.setBorder(null);
        jpSignup.add(txPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 150, 30));

        txUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txUser.setBorder(null);
        jpSignup.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 79, 150, 30));

        btSignup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btSignup.setIcon(new FlatSVGIcon("SVGSignup/btSign.svg"));
        btSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btSignupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btSignupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btSignupMouseExited(evt);
            }
        });
        jpSignup.add(btSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 290, 127, 33));

        Signup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Signup.setIcon(new FlatSVGIcon("SVGSignup/SU.svg"));
        Signup.setPreferredSize(new java.awt.Dimension(700, 400));
        jpSignup.add(Signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 400));

        getContentPane().add(jpSignup);
        jpSignup.setBounds(430, 250, 700, 400);

        Background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Desain/1.jpg"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1580, 1080);

        Signup1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Signup1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Desain/SU.png"))); // NOI18N
        getContentPane().add(Signup1);
        Signup1.setBounds(0, 0, 700, 400);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSignupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSignupMouseEntered
        btSignup.setIcon(new FlatSVGIcon("SVGSignup/btSignEnter.svg"));
    }//GEN-LAST:event_btSignupMouseEntered

    private void btSignupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSignupMouseExited
        btSignup.setIcon(new FlatSVGIcon("SVGSignup/btSign.svg"));
    }//GEN-LAST:event_btSignupMouseExited

    private void btLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLoginMouseExited
        btLogin.setIcon(new FlatSVGIcon("SVGSignup/btLogin.svg"));
    }//GEN-LAST:event_btLoginMouseExited

    private void btLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLoginMouseEntered
        btLogin.setIcon(new FlatSVGIcon("SVGSignup/btLogEnter.svg"));
    }//GEN-LAST:event_btLoginMouseEntered

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        originalIcon = (ImageIcon) Background.getIcon();
        txRfid.requestFocusInWindow();
        centerImage();
    }//GEN-LAST:event_formWindowOpened

    private void btSignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSignupMouseClicked
        if (!isFormValid()) return;

        String username = txUser.getText().trim();
        String password = new String(txPass.getPassword());
        String email = txEmail.getText().trim().toLowerCase();
        String rfid = txRfid.getText().trim();

        try (Connection conn = Custom.Connector.getkoneksi()) {
            String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Username sudah digunakan, pilih yang lain.");
                return;
            }
            
            String checkRfidQuery = "SELECT COUNT(*) FROM users WHERE rfid = ?";
            PreparedStatement checkRfidStmt = conn.prepareStatement(checkRfidQuery);
            checkRfidStmt.setString(1, rfid);
            ResultSet rsRfid = checkRfidStmt.executeQuery();
            if (rsRfid.next() && rsRfid.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Nomor kartu mahasiswa (RFID) sudah terdaftar.");
                return;
            }

            String sql = "INSERT INTO users (username, password, email, rfid) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);            
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, rfid);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registrasi Berhasil!");
            this.dispose();
            new Login().setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btSignupMouseClicked

    private void btLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLoginMouseClicked
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btLoginMouseClicked

    private void txRfidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txRfidActionPerformed

    }//GEN-LAST:event_txRfidActionPerformed

    private void txRfidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txRfidKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {            
            String rfid = txRfid.getText().trim();
            System.out.println("RFID Scanned: " + rfid);            
        }
    }//GEN-LAST:event_txRfidKeyPressed

    public static void main(String args[]) {
        FlatLightLaf.setup();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Signup;
    private javax.swing.JLabel Signup1;
    private javax.swing.JLabel btLogin;
    private javax.swing.JLabel btSignup;
    private javax.swing.JPanel jpSignup;
    private javax.swing.JPasswordField txCfpass;
    private javax.swing.JTextField txEmail;
    private javax.swing.JPasswordField txPass;
    private javax.swing.JTextField txRfid;
    private javax.swing.JTextField txUser;
    // End of variables declaration//GEN-END:variables
    private void smoothResize(int newWidth, int newHeight) {
        if (resizeTimer != null && resizeTimer.isRunning()) {
            resizeTimer.stop();
        }

        targetWidth = newWidth;
        targetHeight = newHeight;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + ANIMATION_DURATION;

        resizeTimer = new Timer(1000 / FRAME_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                float rawProgress = Math.min(1.0f, (float) (currentTime - startTime) / ANIMATION_DURATION);

                // Gunakan easing (cubic ease-out)
                float progress = 1 - (float) Math.pow(1 - rawProgress, 3);

                int interpolatedWidth = (int) (getWidth() + (targetWidth - getWidth()) * progress);
                int interpolatedHeight = (int) (getHeight() + (targetHeight - getHeight()) * progress);

                setSize(interpolatedWidth, interpolatedHeight);
                centerPanel();
                centerImage();

                if (currentTime >= endTime) {
                    setSize(targetWidth, targetHeight);
                    resizeTimer.stop();
                }
            }
        });

        resizeTimer.start();
    }

    private void centerImage() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        if (originalIcon == null) return;

        int imgWidth = originalIcon.getIconWidth();
        int imgHeight = originalIcon.getIconHeight();

        double scale = Math.max((double) frameWidth / imgWidth, (double) frameHeight / imgHeight);
        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);

        Image img = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Background.setIcon(new ImageIcon(img));

        int x = (frameWidth - newWidth) / 2;
        int y = (frameHeight - newHeight) / 2;
        Background.setBounds(x, y, newWidth, newHeight);
    }

    private void centerPanel() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int panelWidth = jpSignup.getWidth();
        int panelHeight = jpSignup.getHeight();

        int x = (frameWidth - panelWidth) / 2;
        int y = (frameHeight - panelHeight) / 2;

        jpSignup.setBounds(x, y, panelWidth, panelHeight);
    }
    
   private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

    private boolean isRFIDValid(String rfid) {
        return rfid.matches("^\\d{10,12}$");
}

    private boolean isUsernameValid(String username) {
        return username != null && username.length() >= 5;
    }

    private boolean doPasswordsMatch(String pass, String confirmPass) {
        return pass.equals(confirmPass);
    }

    private boolean isFormValid() {
        String username = txUser.getText().trim();
        String pass = new String(txPass.getPassword());
        String cfpass = new String(txCfpass.getPassword());
        String email = txEmail.getText().trim();
        String rfid = txRfid.getText().trim().replaceAll("\\s", "");
        
        System.out.println("RFID input: [" + rfid + "]");

        if (!isUsernameValid(username)) {
            JOptionPane.showMessageDialog(this, "Username minimal 5 karakter");
            return false;
        }
        
        if (!isStrongPassword(pass)) {
            JOptionPane.showMessageDialog(this, "Password minimal 8 karakter, mengandung huruf besar dan angka");
            return false;
        }

        if (!doPasswordsMatch(pass, cfpass)) {
            JOptionPane.showMessageDialog(this, "Konfirmasi password tidak cocok");
            return false;
        }


        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email tidak valid");
            return false;
        }

        if (!isRFIDValid(rfid)) {
            JOptionPane.showMessageDialog(this, "RFID harus terdiri dari 10 digit angka.");
            return false;
        }
        return true;
    }
}
