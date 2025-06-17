/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author OZ 
 */
public class ConnectorH {
  private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/sengkuclean2");
        config.setUsername("root");
        config.setPassword("");
        
        
        config.setMaximumPoolSize(15);          
        config.setMinimumIdle(5);                
        config.setConnectionTimeout(30000);    
        config.setIdleTimeout(600000);         
        config.setMaxLifetime(1800000);
        
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection(); 
    }
   public static void main(String[] args) throws SQLException {
        getConnection();
    }
    }

