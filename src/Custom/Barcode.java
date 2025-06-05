/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Barcode {
    private static final Logger logger = Logger.getLogger(Barcode.class.getName());

    public static String generateKode() throws Exception {
        String prefix = "SC";
        String timestamp = new SimpleDateFormat("yyMMdd").format(new Date());
        String barcode = prefix + timestamp + "0001";

        Connection conn = null;
        try {
            conn = Connector.getkoneksi();
            conn.setAutoCommit(false);
            
            // Gunakan FOR UPDATE untuk locking
            try (PreparedStatement st = conn.prepareStatement(
                "SELECT Barcode FROM transaksipemesanan WHERE Barcode LIKE ? " +
                "ORDER BY LENGTH(Barcode) DESC, Barcode DESC LIMIT 1 FOR UPDATE")) {
                
                st.setString(1, prefix + "%");
                
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        String lastBarcode = rs.getString("Barcode");
                        if (lastBarcode != null && lastBarcode.matches("^SC\\d{10}$")) {
                            String lastPart = lastBarcode.substring(prefix.length());
                            String lastDate = lastPart.substring(0, 6);
                            int lastNumber = Integer.parseInt(lastPart.substring(6));
                            barcode = lastDate.equals(timestamp) 
                                ? prefix + timestamp + String.format("%04d", lastNumber + 1)
                                : prefix + timestamp + "0001";
                        }
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, "Gagal rollback transaksi", ex);
                }
            }
            logger.log(Level.SEVERE, "Error generate barcode", e);
            throw new Exception("Gagal generate barcode: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Gagal menutup koneksi", e);
                }
            }
        }
        
        return barcode;
    }
   
    public static String generateGambar(String barcodeText, String saveDir) throws Exception {
        String safeBarcode = barcodeText.replaceAll("[^a-zA-Z0-9-]", "_");
        File dir = new File(saveDir);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new Exception("Gagal membuat direktori untuk barcode: " + dir.getAbsolutePath());
            }
        }
        
        String filePath = Paths.get(saveDir, safeBarcode + ".png").toString();
        int width = Math.max(300, barcodeText.length() * 10);
        BitMatrix matrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, width, 100);
        MatrixToImageWriter.writeToPath(matrix, "PNG", Paths.get(filePath));
        
        return filePath;
    }
}
