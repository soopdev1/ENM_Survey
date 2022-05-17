/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soop.engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static soop.engine.Action.conf;

/**
 *
 * @author Administrator
 */
public class Database {

    private Connection c = null;

    public Database(String host) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String user = conf.getString("db.user");
        String password = conf.getString("db.pass");

        try {
            Class.forName(driver).newInstance();
            Properties p = new Properties();
            p.put("user", user);
            p.put("password", password);
            p.put("characterEncoding", "UTF-8");
            p.put("passwordCharacterEncoding", "UTF-8");
            p.put("useSSL", "false");
            p.put("connectTimeout", "1000");
            p.put("useUnicode", "true");
            this.c = DriverManager.getConnection("jdbc:mysql://" + host, p);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (this.c != null) {
                try {
                    this.c.close();
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }
            this.c = null;
        }
    }

    public void closeDB() {
        try {
            if (this.c != null) {
                this.c.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

    public String getPath(String id) {
        String path = "-";
        try {
            String sql = "SELECT url FROM path WHERE id = ?";
            try ( PreparedStatement ps = this.c.prepareStatement(sql)) {
                ps.setString(1, id);
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        path = rs.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return path;
    }

}
