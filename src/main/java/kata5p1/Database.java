/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Database {
    private final String name;

    public Database(String name) {
        this.name = name;
    }
    
    private Connection connect(){
        Connection conn = null;
        String url = "jdbc:sqlite:"+name;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conneccion establecida");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  conn;
    }
    
    public void close(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Query(){
        Connection conn = null;
        String sql = "SELECT * FROM PEOPLE";
        try {
            conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
            System.out.println(rs.getInt("Id")+ "\t"
                                + rs.getString("Name")+"\t"
                                + rs.getString("Apellidos")+"\t"
                                + rs.getString("Departamento")+"\t");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
