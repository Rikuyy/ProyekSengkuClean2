/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Connector3 {
    private static Connection koneksi;
    
    public static Connection getkoneksi3() {
        if (koneksi == null) {
            try{
                String url = "jdbc:mysql://localhost:3306/sengkuclean2";
                String user = "root";  
                String password = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Connection Succes");
            } catch (SQLException e) {
                System.out.println("Error");
            }
        }
        return koneksi;
    }
    public static void main(String[] args) {
        getkoneksi3();
    }
}
