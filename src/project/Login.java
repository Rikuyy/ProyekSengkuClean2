package project;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
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
import java.sql.DriverManager;

public class Login extends JFrame {
    
    private ImageIcon originalIcon;
    private Timer resizeTimer;
    private int targetWidth, targetHeight;
    private static final int ANIMATION_DURATION = 100;
    private static final int FRAME_RATE = 60;
    
    public Login() {
        initComponents();
        setSize(800, 600); 
        setLocationRelativeTo(null);
        
        setIconImage(new FlatSVGIcon("SVGLogin/lgSCG.svg").getImage());
        
        txUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        
        txUser.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                checkRFID();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {}
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}

            private void checkRFID() {
                String input = txUser.getText().trim();            
                if (input.matches("^\\d{10,12}$")) {
                    try {             
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sengkuclean2", "root", ""
                        );
                        String query = "SELECT Username, Password FROM users WHERE rfid = ?";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, input);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {        
                            String foundUsername = rs.getString("Username");
                            String foundPassword = rs.getString("Password");
                            
                            System.out.println("RFID cocok. Mengambil Username & Password dari database.");
                            System.out.println("Username ditemukan: " + foundUsername);

                            SwingUtilities.invokeLater(() -> {
                                txUser.setText(foundUsername);
                                txPass.setText(foundPassword);
                            });
                        } else {
                            System.out.println("RFID tidak ditemukan di database.");
                        }

                        con.close();
                    } catch (Exception ex) {
                        System.err.println("RFID lookup error: " + ex.getMessage());
                    }
                }
            }
        });
                
        jpLogin.setOpaque(false); 
        jpLogin.setBackground(new java.awt.Color(0, 0, 0, 0));
        
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

        jpLogin = new javax.swing.JPanel();
        btSignup = new javax.swing.JLabel();
        txPass = new javax.swing.JPasswordField();
        txUser = new javax.swing.JTextField();
        btLogin = new javax.swing.JLabel();
        Login = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        Login1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jpLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btSignup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btSignup.setIcon(new FlatSVGIcon("SVGLogin/btSignup.svg"));
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
        jpLogin.add(btSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 8, 65, 20));

        txPass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txPass.setBorder(null);
        jpLogin.add(txPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 205, 150, 30));

        txUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txUser.setBorder(null);
        jpLogin.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 152, 150, 30));

        btLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btLogin.setIcon(new FlatSVGIcon("SVGLogin/btLogin.svg"));
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
        jpLogin.add(btLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 127, 33));

        Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Login.setIcon(new FlatSVGIcon("SVGLogin/Login.svg"));
        Login.setPreferredSize(new java.awt.Dimension(700, 400));
        jpLogin.add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 400));

        getContentPane().add(jpLogin);
        jpLogin.setBounds(430, 250, 700, 400);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Desain/1.jpg"))); // NOI18N
        Background.setMaximumSize(new java.awt.Dimension(1594, 1080));
        Background.setPreferredSize(new java.awt.Dimension(1594, 1040));
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1580, 1080);

        Login1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Login1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Desain/Login.png"))); // NOI18N
        getContentPane().add(Login1);
        Login1.setBounds(0, 0, 700, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        originalIcon = (ImageIcon) Background.getIcon();
        centerImage();
    }//GEN-LAST:event_formWindowOpened

    private void btLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLoginMouseExited
        btLogin.setIcon(new FlatSVGIcon("SVGLogin/btLogin.svg"));
    }//GEN-LAST:event_btLoginMouseExited

    private void btLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLoginMouseEntered
        btLogin.setIcon(new FlatSVGIcon("SVGLogin/btLoginEnter.svg"));
    }//GEN-LAST:event_btLoginMouseEntered

    private void btSignupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSignupMouseExited
        btSignup.setIcon(new FlatSVGIcon("SVGLogin/btSignup.svg"));
    }//GEN-LAST:event_btSignupMouseExited

    private void btSignupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSignupMouseEntered
        btSignup.setIcon(new FlatSVGIcon("SVGLogin/btSignupEnter.svg"));
    }//GEN-LAST:event_btSignupMouseEntered

    private void btLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLoginMouseClicked
        String userInput = txUser.getText().trim();
        String password  = new String(txPass.getPassword()).trim();
                
        if (userInput.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Silakan masukkan Username atau RFID!", "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (userInput.length() < 4 || password.length() < 6) {
            JOptionPane.showMessageDialog(this,
                "Username minimal 4 karakter & Password minimal 6 karakter.", 
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");           
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sengkuclean2","root","");                        
            String sql = "SELECT * FROM users WHERE Username = ? AND Password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userInput);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {                
                this.dispose();
                new MainMenu().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Username atau Password salah!", 
                    "Gagal Login", JOptionPane.ERROR_MESSAGE);
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Driver JDBC tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Koneksi/Query ke database gagal!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btLoginMouseClicked

    private void btSignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSignupMouseClicked
        this.dispose();
        new Signup().setVisible(true);
    }//GEN-LAST:event_btSignupMouseClicked
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Login;
    private javax.swing.JLabel Login1;
    private javax.swing.JLabel btLogin;
    private javax.swing.JLabel btSignup;
    private javax.swing.JPanel jpLogin;
    private javax.swing.JPasswordField txPass;
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
        int panelWidth = jpLogin.getWidth();
        int panelHeight = jpLogin.getHeight();

        int x = (frameWidth - panelWidth) / 2;
        int y = (frameHeight - panelHeight) / 2;

        jpLogin.setBounds(x, y, panelWidth, panelHeight);
    }
}
