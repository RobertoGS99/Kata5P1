/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
    
    public void createNewTable(){
        Connection conn = null;
        String sql = "CREATE TABLE \"EMAIL\" (\n" +
                        "\"Id\"	INTEGER,\n" +
                        "\"Name\"	TEXT NOT NULL,\n" +
                        "PRIMARY KEY(\"Id\" AUTOINCREMENT)\n" +
                                                ");";
        
        conn = this.connect();
        Statement stmt;
        try {
            stmt = (Statement) conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void insertData (List<String> email){
        Connection conn = null;
        String sql = "INSERT INTO EMAIL(Name) VALUES (?)";
        try {
            conn = this.connect();
            for (String string : email) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, string);
                pstmt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(conn);
        }
    }
}
