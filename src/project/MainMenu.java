 package project;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.sql.Connection;
import Custom.Connector;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainMenu extends javax.swing.JFrame {
    
    private String currentMenu = "Dashboard";
    private JLabel activeLabel;
    private CardLayout cardLayout;
    private int menuWidth = 250; // Ukuran awal panel
    private boolean isResizing = false;
    private int prevX;
    private boolean isMinimized = false;
    private JLayeredPane layeredPane; 
    private JPanel sidebarContainer;
    private Drop PanelDrop = new Drop();
    
    public MainMenu() throws SQLException {
        initComponents();
        getRootPane().setBackground(Color.WHITE);
        getRootPane().setOpaque(true);
        
               
        setIconImage(new FlatSVGIcon("SVGLogin/lgSCG.svg").getImage());    
      
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        // 1. Buat sidebarContainer dan tambahkan MainMenu ke dalamnya
        sidebarContainer = new JPanel(null); 
        sidebarContainer.setOpaque(false); 
        sidebarContainer.setBounds(0, 0, menuWidth, getHeight());

        MainMenu.setBounds(0, 0, 250, getHeight()); 
        sidebarContainer.add(MainMenu);

        // 2. Tambahkan panel-panel ke layeredPane
        layeredPane.add(sidebarContainer, JLayeredPane.DEFAULT_LAYER); // ganti dari MainMenu langsung
        layeredPane.add(Min, JLayeredPane.POPUP_LAYER);
        layeredPane.add(PanelUtama, JLayeredPane.DEFAULT_LAYER);
        Min.setBounds(menuWidth - 18, 47, 30, 30);
        PanelUtama.setBounds(250, 0, getWidth() - 250, getHeight());
        getContentPane().add(layeredPane);
       
        cardLayout = (CardLayout) Menu.getLayout();
        Menu.add(new Dashboard(), "Dashboard");
        Menu.add(PanelDrop, "Drop");
        
        Connection conn = Connector.getkoneksi();
        if (conn != null) {
            Menu.add(new Karyawan(conn), "Karyawan"); // ✅ kirim Connection
        } else {
            System.err.println("Gagal koneksi ke database.");
        }
        
        Menu.add(new Laporan(), "Laporan");
        
      
        Max.setVisible(false);
        Min.setVisible(true);
        lgDb.setVisible(false);
        txDb.setVisible(true);
        lgDrop.setVisible(false);
        txDrop.setVisible(true);
        lgEmploy.setVisible(false);
        txEmploy.setVisible(true);
        lgRep.setVisible(false);
        txRep.setVisible(true);
   
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainMenu = new Custom.PanelShadow();
        SC = new javax.swing.JLabel();
        Min = new javax.swing.JLabel();
        Max = new javax.swing.JLabel();
        lgDb = new javax.swing.JLabel();
        txDb = new javax.swing.JLabel();
        txDrop = new javax.swing.JLabel();
        lgDrop = new javax.swing.JLabel();
        txEmploy = new javax.swing.JLabel();
        lgEmploy = new javax.swing.JLabel();
        lgRep = new javax.swing.JLabel();
        txRep = new javax.swing.JLabel();
        PanelUtama = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelCustom1 = new Custom.PanelCustom();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        MainMenu.setBackground(new java.awt.Color(255, 255, 255));
        MainMenu.setMinimumSize(new java.awt.Dimension(260, 0));
        MainMenu.setOpaque(true);
        MainMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC.setIcon(new FlatSVGIcon("SVGMm/SC.svg"));
        SC.setPreferredSize(new java.awt.Dimension(95, 105));
        MainMenu.add(SC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 105, 95));

        Min.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Min.setIcon(new FlatSVGIcon("SVGMm/Min.svg"));
        Min.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinMouseClicked(evt);
            }
        });
        MainMenu.add(Min, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 47, 30, 30));

        Max.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Max.setIcon(new FlatSVGIcon("SVGMm/Max.svg"));
        Max.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Max.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaxMouseClicked(evt);
            }
        });
        MainMenu.add(Max, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 49, 30, 30));

        lgDb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgDb.setIcon(new FlatSVGIcon("SVGMm/lDash.svg"));
        lgDb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lgDb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lgDbMouseClicked(evt);
            }
        });
        MainMenu.add(lgDb, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 200, 25, 25));

        txDb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txDb.setIcon(new FlatSVGIcon("SVGMm/DB.svg"));
        txDb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txDb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txDbMouseClicked(evt);
            }
        });
        MainMenu.add(txDb, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 200, 140, 25));

        txDrop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txDrop.setIcon(new FlatSVGIcon("SVGMm/Drop.svg"));
        txDrop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txDrop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txDropMouseClicked(evt);
            }
        });
        MainMenu.add(txDrop, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 250, 90, 25));

        lgDrop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgDrop.setIcon(new FlatSVGIcon("SVGMm/Dr.svg"));
        lgDrop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lgDrop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lgDropMouseClicked(evt);
            }
        });
        MainMenu.add(lgDrop, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 254, 27, 21));

        txEmploy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txEmploy.setIcon(new FlatSVGIcon("SVGMm/Employ.svg"));
        txEmploy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txEmploy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txEmployMouseClicked(evt);
            }
        });
        MainMenu.add(txEmploy, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 300, 129, 25));

        lgEmploy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgEmploy.setIcon(new FlatSVGIcon("SVGMm/Emplo.svg"));
        lgEmploy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lgEmploy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lgEmployMouseClicked(evt);
            }
        });
        MainMenu.add(lgEmploy, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 300, 25, 25));

        lgRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lgRep.setIcon(new FlatSVGIcon("SVGMm/Rep.svg"));
        lgRep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lgRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lgRepMouseClicked(evt);
            }
        });
        MainMenu.add(lgRep, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 350, 25, 25));

        txRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txRep.setIcon(new FlatSVGIcon("SVGMm/Report.svg"));
        txRep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txRepMouseClicked(evt);
            }
        });
        MainMenu.add(txRep, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 350, 103, 25));

        getContentPane().add(MainMenu, java.awt.BorderLayout.LINE_START);

        PanelUtama.setBackground(new java.awt.Color(255, 255, 255));
        PanelUtama.setPreferredSize(new java.awt.Dimension(1290, 1080));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 90));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new FlatSVGIcon("SVGMm/Notif.svg"));
        jLabel1.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout panelCustom1Layout = new javax.swing.GroupLayout(panelCustom1);
        panelCustom1.setLayout(panelCustom1Layout);
        panelCustom1Layout.setHorizontalGroup(
            panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1225, Short.MAX_VALUE))
        );
        panelCustom1Layout.setVerticalGroup(
            panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.add(panelCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 32, 1260, 50));

        PanelUtama.add(jPanel2);

        Menu.setBackground(new java.awt.Color(255, 255, 255));
        Menu.setPreferredSize(new java.awt.Dimension(1280, 840));
        Menu.setLayout(new java.awt.CardLayout());
        PanelUtama.add(Menu);

        getContentPane().add(PanelUtama, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxMouseClicked
        toggleMenu();
    }//GEN-LAST:event_MaxMouseClicked

    private void MinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinMouseClicked
        toggleMenu();
    }//GEN-LAST:event_MinMouseClicked

    private void txDropMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txDropMouseClicked
        setActiveMenu("Drop");
        cardLayout.show(Menu, "Drop");
            try {
                    PanelDrop.importdb(); 
                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Gagal memuat data Drop: " + ex.getMessage());
                }        
    }//GEN-LAST:event_txDropMouseClicked

    private void lgDropMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgDropMouseClicked
        setActiveMenu("Drop");
        cardLayout.show(Menu, "Drop");
        try {
                    PanelDrop.importdb(); 
                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Gagal memuat data Drop: " + ex.getMessage());
                }   
    }//GEN-LAST:event_lgDropMouseClicked

    private void txDbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txDbMouseClicked
        setActiveMenu("Dashboard");
        cardLayout.show(Menu, "Dashboard");
    }//GEN-LAST:event_txDbMouseClicked

    private void lgDbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgDbMouseClicked
        setActiveMenu("Dashboard");
        cardLayout.show(Menu, "Dashboard");
    }//GEN-LAST:event_lgDbMouseClicked

    private void txEmployMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txEmployMouseClicked
        setActiveMenu("Karyawan");
        cardLayout.show(Menu, "Karyawan");
    }//GEN-LAST:event_txEmployMouseClicked

    private void lgEmployMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgEmployMouseClicked
        setActiveMenu("Karyawan");
        cardLayout.show(Menu, "Karyawan");
    }//GEN-LAST:event_lgEmployMouseClicked

    private void txRepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txRepMouseClicked
        setActiveMenu("Laporan");
        cardLayout.show(Menu, "Laporan");
    }//GEN-LAST:event_txRepMouseClicked

    private void lgRepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgRepMouseClicked
        setActiveMenu("Laporan");
        cardLayout.show(Menu, "Laporan");
    }//GEN-LAST:event_lgRepMouseClicked

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int width = getWidth();
        int height = getHeight();
        layeredPane.setBounds(0, 0, width, height);
        MainMenu.setBounds(0, 0, menuWidth, height);
        PanelUtama.setBounds(250, 0, getWidth() - 250, getHeight());
    }//GEN-LAST:event_formComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainMenu().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.PanelShadow MainMenu;
    private javax.swing.JLabel Max;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel Min;
    private javax.swing.JPanel PanelUtama;
    private javax.swing.JLabel SC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lgDb;
    private javax.swing.JLabel lgDrop;
    private javax.swing.JLabel lgEmploy;
    private javax.swing.JLabel lgRep;
    private Custom.PanelCustom panelCustom1;
    private javax.swing.JLabel txDb;
    private javax.swing.JLabel txDrop;
    private javax.swing.JLabel txEmploy;
    private javax.swing.JLabel txRep;
    // End of variables declaration//GEN-END:variables

    private void toggleMenu() {
        isMinimized = !isMinimized;
        int newWidth = isMinimized ? 50 : 250;
        menuWidth = newWidth;

        // Ubah ukuran container, bukan langsung MainMenu
        sidebarContainer.setBounds(0, 0, newWidth, getHeight());
        MainMenu.setBounds(0, 0, 250, getHeight()); // tetap 250 agar tidak merusak layout internalnya

        PanelUtama.setBounds(newWidth, 0, getWidth() - newWidth, getHeight());
        Min.setBounds(newWidth - 18, 47, 30, 30);
        Max.setBounds(8, 49, 30, 30);

        setActiveMenu(currentMenu); // Atur icon/menu sesuai status

        Min.setVisible(!isMinimized);
        Max.setVisible(isMinimized);

        SwingUtilities.invokeLater(() -> {
            sidebarContainer.revalidate();
            sidebarContainer.repaint();
            PanelUtama.revalidate();
            PanelUtama.repaint();
        });
    }
    private void setActiveMenu(String menuName) {
        currentMenu = menuName;

        // Reset semua icon ke default
        txDb.setIcon(new FlatSVGIcon("SVGMm/Dash.svg"));
        lgDb.setIcon(new FlatSVGIcon("SVGMm/Dashh.svg"));
        txDrop.setIcon(new FlatSVGIcon("SVGMm/Drop.svg"));
        lgDrop.setIcon(new FlatSVGIcon("SVGMm/Dr.svg"));
        txEmploy.setIcon(new FlatSVGIcon("SVGMm/Employ.svg"));
        lgEmploy.setIcon(new FlatSVGIcon("SVGMm/Emplo.svg"));
        txRep.setIcon(new FlatSVGIcon("SVGMm/Report.svg"));
        lgRep.setIcon(new FlatSVGIcon("SVGMm/Rep.svg"));
        
        

        // Atur visibilitas ikon dan teks berdasarkan status minimisasi
        lgDb.setVisible(isMinimized);
        txDb.setVisible(!isMinimized);
        lgDrop.setVisible(isMinimized);
        txDrop.setVisible(!isMinimized);
        lgEmploy.setVisible(isMinimized);
        txEmploy.setVisible(!isMinimized);
        lgRep.setVisible(isMinimized);
        txRep.setVisible(!isMinimized);
      

        // Tambahkan highlight atau ikon aktif jika perlu
        switch (menuName) {
            case "Dashboard":
                txDb.setIcon(new FlatSVGIcon("SVGMm/DB.svg"));
                lgDb.setIcon(new FlatSVGIcon("SVGMm/lDash.svg"));
                break;
            case "Drop":
                txDrop.setIcon(new FlatSVGIcon("SVGMm/DropB.svg"));
                lgDrop.setIcon(new FlatSVGIcon("SVGMm/lShoe.svg"));
                break;
            case "Karyawan":
                txEmploy.setIcon(new FlatSVGIcon("SVGMm/EmployB.svg"));
                lgEmploy.setIcon(new FlatSVGIcon("SVGMm/lEmploy.svg"));
                break;
            case "Laporan":
                txRep.setIcon(new FlatSVGIcon("SVGMm/RepB.svg"));
                lgRep.setIcon(new FlatSVGIcon("SVGMm/lRep.svg"));
                break;
      

        }
        boolean showLabelIcons = isMinimized;

        lgDb.setVisible(showLabelIcons);
        txDb.setVisible(!showLabelIcons);
        lgDrop.setVisible(showLabelIcons);
        txDrop.setVisible(!showLabelIcons);
        lgEmploy.setVisible(showLabelIcons);
        txEmploy.setVisible(!showLabelIcons);
        lgRep.setVisible(showLabelIcons);
        txRep.setVisible(!showLabelIcons);
      

        // Set visibility icon sesuai minimized atau tidak
        if (isMinimized) {
            // Hanya tampilkan ikon kecil (lgXXX), sembunyikan txXXX
            txDb.setVisible(false);
            txDrop.setVisible(false);
            txEmploy.setVisible(false);
            txRep.setVisible(false);
            

            lgDb.setVisible(true);
            lgDrop.setVisible(true);
            lgEmploy.setVisible(true);
            lgRep.setVisible(true);
            
        } else {
            // Hanya tampilkan ikon teks (txXXX), sembunyikan lgXXX
            txDb.setVisible(true);
            txDrop.setVisible(true);
            txEmploy.setVisible(true);
            txRep.setVisible(true);
            

            lgDb.setVisible(false);
            lgDrop.setVisible(false);
            lgEmploy.setVisible(false);
            lgRep.setVisible(false);
            
        }
    }

    private void refreshActiveIconVisibility() {
        if (isMinimized) {
            txDb.setVisible(false);
            txDrop.setVisible(false);
            txEmploy.setVisible(false);
            txRep.setVisible(false);
            

            lgDb.setVisible(true);
            lgDrop.setVisible(true);
            lgEmploy.setVisible(true);
            lgRep.setVisible(true);
            
        } else {
            txDb.setVisible(true);
            txDrop.setVisible(true);
            txEmploy.setVisible(true);
            txRep.setVisible(true);
            

            lgDb.setVisible(false);
            lgDrop.setVisible(false);
            lgEmploy.setVisible(false);
            lgRep.setVisible(false);
            
        }
    }

    private void updateIconsVisibility(boolean minimized) {
        if (minimized) {
            txDb.setVisible(false);
            lgDb.setVisible(true);
            txDrop.setVisible(false);
            lgDrop.setVisible(true);
            txEmploy.setVisible(false);
            lgEmploy.setVisible(true);
            txRep.setVisible(false);
            lgRep.setVisible(true);
          

            Min.setVisible(false);
            Max.setVisible(true);
        } else {
            txDb.setVisible(true);
            lgDb.setVisible(false);
            txDrop.setVisible(true);
            lgDrop.setVisible(false);
            txEmploy.setVisible(true);
            lgEmploy.setVisible(false);
            txRep.setVisible(true);
            lgRep.setVisible(false);
      

            Min.setVisible(true);
            Max.setVisible(false);
        }
    }
}
