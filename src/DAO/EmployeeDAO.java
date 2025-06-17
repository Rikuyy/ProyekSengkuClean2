  package DAO;

import Custom.Connector;
import EmployeeClass.EmployeeData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertEmployee(EmployeeData employee) throws SQLException {
        String sql = "INSERT INTO karyawan (nama, usia, alamat, no_hp, email, foto) "
                   + "VALUES (?, ?, ?, ?, ?, ?)"; 

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employee.getNama());
            stmt.setInt(2, employee.getUsia());
            stmt.setString(3, employee.getAlamat());
            stmt.setString(4, employee.getTelepon());
            stmt.setString(5, employee.getEmail());

            byte[] fotoBytes = employee.getFoto();
            if (fotoBytes != null) {
                stmt.setBytes(6, fotoBytes);
            } else {
                stmt.setNull(6, Types.BLOB);
            }

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        employee.setId(String.valueOf(generatedId));
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }
    
    public EmployeeData findById(String id_karyawan) throws SQLException {
        String sql = "SELECT id_karyawan, nama, usia, alamat, telepon, email, foto FROM karyawan WHERE id_karyawan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id_karyawan);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new EmployeeData(
                        rs.getString("id_karyawan"),
                        rs.getString("nama"),
                        rs.getInt("usia"),
                        rs.getString("alamat"),
                        rs.getString("telepon"),
                        rs.getString("email"),
                        null 
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public boolean updateEmployee(EmployeeData data) throws SQLException {
        String sql = "UPDATE karyawan SET nama = ?, usia = ?, alamat = ?, telepon = ?, email = ? "
                   + "WHERE id_karyawan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data.getNama());
            stmt.setInt(2, data.getUsia());
            stmt.setString(3, data.getAlamat());
            stmt.setString(4, data.getTelepon());
            stmt.setString(5, data.getEmail());
            stmt.setString(6, data.getId());

            return stmt.executeUpdate() == 1;
        }
    }
    
    public boolean hapusData(String id_karyawan) throws SQLException {        
        String sqlDeleteHistory = "DELETE FROM transaksipemesanan WHERE id_karyawan = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sqlDeleteHistory)) {
            stmt1.setString(1, id_karyawan);
            stmt1.executeUpdate();
        }
        String sqlDeleteEmp = "DELETE FROM karyawan WHERE id_karyawan = ?";
        try (PreparedStatement stmt2 = conn.prepareStatement(sqlDeleteEmp)) {
            stmt2.setString(1, id_karyawan);
            int affected = stmt2.executeUpdate();
            return affected == 1;
        }
    }

    public List<EmployeeData> getAllEmployees() throws SQLException {
        List<EmployeeData> list = new ArrayList<>();
        String sql = "SELECT id_karyawan , nama, usia, alamat, no_hp, email, foto FROM karyawan ORDER BY id_karyawan";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {                
                byte[] fotoBytes = null;
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    fotoBytes = blob.getBytes(1, (int) blob.length());
                }

                list.add(new EmployeeData(
                    rs.getString("id_karyawan"),
                    rs.getString("nama"),
                    rs.getInt("usia"),
                    rs.getString("alamat"),
                    rs.getString("no_hp"),
                    rs.getString("email"),
                    fotoBytes
                ));
            }
        }
        return list;
    }

    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM karyawan WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
