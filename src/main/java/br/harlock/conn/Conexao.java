/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author kai
 */
public class Conexao {
    

    private static Connection conexao = null;
    public static Connection getConexao() throws Exception{
        if (conexao != null){
            return conexao;
        }
        else{
            try{
                
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://31.170.166.50:3306/u465595781_bibli";
                String user = "u465595781_bibli";
                String password = "biblibibli";
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
                return conexao;
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
                throw new Exception("Erro ao conectar com DB");
            }
        }
    }   
}

