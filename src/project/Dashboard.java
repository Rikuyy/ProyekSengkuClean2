
package project;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends javax.swing.JPanel {
    
    private JPanel mainContent;
    private CardLayout cardLayout;
    
    public Dashboard() {
        initComponents();
        
        Cleaning.setIcon(new FlatSVGIcon("SVGDb/Cleaning.svg", 825, 345));
        Reglue.setIcon(new FlatSVGIcon("SVGDb/Reglue.svg", 825, 345));
        Repaint.setIcon(new FlatSVGIcon("SVGDb/repaint.svg", 825, 345));
        Bag.setIcon(new FlatSVGIcon("SVGDb/Bag.svg", 825, 345));
        Cap.setIcon(new FlatSVGIcon("SVGDb/Cap.svg", 825, 345));
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ContentPanel = new javax.swing.JPanel();
        txtDb = new javax.swing.JLabel();
        Cleaning = new javax.swing.JLabel();
        Reglue = new javax.swing.JLabel();
        Repaint = new javax.swing.JLabel();
        Bag = new javax.swing.JLabel();
        Cap = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1290, 1080));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportView(ContentPanel);

        ContentPanel.setBackground(new java.awt.Color(255, 255, 255));
        ContentPanel.setPreferredSize(new java.awt.Dimension(1100, 2200));
        ContentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDb.setIcon(new FlatSVGIcon("SVGDb/txtDashboard.svg"));
        ContentPanel.add(txtDb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 240, 37));

        Cleaning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cleaning.setIcon(new FlatSVGIcon("SVGDb/Cleaning.svg"));
        Cleaning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CleaningMouseClicked(evt);
            }
        });
        ContentPanel.add(Cleaning, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 825, 345));

        Reglue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reglue.setIcon(new FlatSVGIcon("SVGDb/Reglue.svg"));
        Reglue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReglueMouseClicked(evt);
            }
        });
        ContentPanel.add(Reglue, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 825, 345));

        Repaint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Repaint.setIcon(new FlatSVGIcon("SVGDb/Repaint.svg"));
        Repaint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RepaintMouseClicked(evt);
            }
        });
        ContentPanel.add(Repaint, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 860, 825, 345));

        Bag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bag.setIcon(new FlatSVGIcon("SVGDb/Bag.svg"));
        Bag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BagMouseClicked(evt);
            }
        });
        ContentPanel.add(Bag, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1240, 825, 345));

        Cap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cap.setIcon(new FlatSVGIcon("SVGDb/Cap.svg"));
        Cap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CapMouseClicked(evt);
            }
        });
        ContentPanel.add(Cap, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1620, 825, 345));

        jScrollPane1.setViewportView(ContentPanel);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void CleaningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CleaningMouseClicked
        ContentPanel.removeAll(); // Kosongkan panel sebelumnya
        ContentPanel.add(new CleaningPanel(this), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 730));
        ContentPanel.repaint();
        ContentPanel.revalidate();
    }//GEN-LAST:event_CleaningMouseClicked

    private void ReglueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReglueMouseClicked
        ContentPanel.removeAll(); // Kosongkan panel sebelumnya
        ContentPanel.add(new RegluePanel(this), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 730));
        ContentPanel.repaint();
        ContentPanel.revalidate();
    }//GEN-LAST:event_ReglueMouseClicked

    private void RepaintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RepaintMouseClicked
        ContentPanel.removeAll(); // Kosongkan panel sebelumnya
        ContentPanel.add(new RepaintPanel(this), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 730));
        ContentPanel.repaint();
        ContentPanel.revalidate();
    }//GEN-LAST:event_RepaintMouseClicked

    private void BagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BagMouseClicked
        ContentPanel.removeAll(); // Kosongkan panel sebelumnya
        ContentPanel.add(new BagPanel(this), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 730));
        ContentPanel.repaint();
        ContentPanel.revalidate();
    }//GEN-LAST:event_BagMouseClicked

    private void CapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CapMouseClicked
        ContentPanel.removeAll(); // Kosongkan panel sebelumnya
        ContentPanel.add(new CapPanel(this), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 730));
        ContentPanel.repaint();
        ContentPanel.revalidate();
    }//GEN-LAST:event_CapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bag;
    private javax.swing.JLabel Cap;
    private javax.swing.JLabel Cleaning;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JLabel Reglue;
    private javax.swing.JLabel Repaint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtDb;
    // End of variables declaration//GEN-END:variables

    void showDashboardContent() {
        ContentPanel.removeAll();

        // Tambahkan kembali semua icon menu dashboard
        ContentPanel.add(txtDb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 240, 37));
        ContentPanel.add(Cleaning, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 825, 345));
        ContentPanel.add(Reglue, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 825, 345));
        ContentPanel.add(Repaint, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 860, 825, 345));
        ContentPanel.add(Bag, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1240, 825, 345));
        ContentPanel.add(Cap, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1620, 825, 345));

        ContentPanel.repaint();
        ContentPanel.revalidate();
    }

}
