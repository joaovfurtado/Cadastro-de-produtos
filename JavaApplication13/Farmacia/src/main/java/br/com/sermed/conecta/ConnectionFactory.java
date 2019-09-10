/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.conecta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joão.furtado
 */
public class ConnectionFactory {

    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String url = "jdbc:derby://127.0.0.1:1527/novoteste";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection getConection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro conexao", ex);
        }
        
    }
    public static void closeConnection(Connection con){
        if(con !=null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro"+ ex);
            }
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt){
        if(stmt !=null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                 System.err.println("Erro"+ ex);
            }
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con, PreparedStatement stmt,ResultSet rs){
        if(rs !=null){
            try {
                rs.close();
            } catch (SQLException ex) {
                 System.err.println("Erro"+ ex);
            }
        }
        closeConnection(con, stmt);
    }
}
