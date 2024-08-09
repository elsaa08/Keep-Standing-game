/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author elsan
 */

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DB {
   public static Statement stmt;
   public static Connection conn;
   private ResultSet rs = null;
public DB(){ //koneksi database 
        String ConAddress = "jdbc:mysql://localhost/tmd_dpbo"; //nama database 
        String user = "root";
        String pass = "";
        connect(ConAddress, user, pass);
}

private void connect(String ConAddress, String username, String pass){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = (Connection) DriverManager.getConnection(ConAddress, username, pass);
        stmt = (Statement) conn.createStatement();         
    }catch(ClassNotFoundException | SQLException ex){
        System.out.println(ex.getMessage()); 
    }
}

public ResultSet selectQuery(String sql){ //memasukan hasil dari query ketika dipanggil 
    try{
        stmt.executeQuery(sql);
        return stmt.getResultSet();
    }catch (SQLException ex){
        System.out.println(ex.getMessage());
    }
    return null;
}
 public ResultSet getResult() throws Exception { //memanggil hasil data 
        ResultSet Temp = null;
        try {
            return rs;
        } catch (Exception e) {
            return Temp;
        }
    }
public int updateQuery(String sql){
    try{
        return stmt.executeUpdate(sql);
    } catch (SQLException ex){
        System.out.println(ex.getMessage());
        
    }
    return 0;
}

public Statement getStatement(){
    return stmt;
}
}