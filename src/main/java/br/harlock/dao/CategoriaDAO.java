/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.dao;

import java.sql.Connection;
import br.harlock.conn.Conexao;
import br.harlock.model.Categoriaitemacervo;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoriaDAO {

    private Connection connection = null;

    public CategoriaDAO() throws Exception {
        connection = Conexao.getConexao();
    }

    public void Inserir(Categoriaitemacervo categoria) throws SQLException {
       
        try {
                
            
            String sql;
        
        sql = "INSERT INTO categoria_item_acervo(NomeCategoria,Descricao)VALUES (?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 1;
        ps.setString(i++, categoria.getNomeCategoria());
        ps.setString(i++, categoria.getDescricao());
        ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void Remover(Categoriaitemacervo categoria) throws SQLException {
        try{
        String sql;
        sql = "DELETE FROM categoria_item_acervo WHERE ID_CAT = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, categoria.getIdCat());
        ps.executeUpdate();
        } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void Update(Categoriaitemacervo categoria) throws SQLException {
        try{
        String sql;
        sql = "UPDATE categoria_item_acervo SET ID_CAT = ?, NomeCategoria = ?, Descricao = ? WHERE ID_CAT = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 1;
        ps.setInt(i++, categoria.getIdCat());
        ps.setString(i++, categoria.getNomeCategoria());
        ps.setString(i++, categoria.getDescricao());
        ps.setInt(i++, categoria.getIdCat());
        ps.executeUpdate();
        } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public Categoriaitemacervo Pesquisar(Categoriaitemacervo categoria) throws SQLException, Exception {

        try {
            String sql;
            sql = "SELECT ID_CAT, NomeCategoria,Descricao "
                    + "FROM categoria_item_acervo WHERE ID_CAT = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoria.getIdCat());
            ResultSet rs = ps.executeQuery();
            rs.next();
            categoria.setNomeCategoria(rs.getString("NomeCategoria"));
            categoria.setDescricao(rs.getString("Descricao"));
            return categoria;
        } catch (Exception e) {
            throw new Exception("java.sql.SQLException");
        }
    }
 public Categoriaitemacervo Pesquisar(int idcategoria) throws SQLException, Exception {

     Categoriaitemacervo categoria = new Categoriaitemacervo();
        try {
            String sql;
            sql = "SELECT ID_CAT, NomeCategoria,Descricao "
                    + "FROM categoria_item_acervo WHERE ID_CAT = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idcategoria);
            ResultSet rs = ps.executeQuery();
            rs.next();
            categoria.setNomeCategoria(rs.getString("NomeCategoria"));
            categoria.setDescricao(rs.getString("Descricao"));
            return categoria;
        } catch (Exception e) {
            throw new Exception("java.sql.SQLException");
        }
    }
    public Iterator<Categoriaitemacervo> ConsultarTodos() throws SQLException, Exception {
        try{
        List lista = new ArrayList();
        String sql;
        sql = "SELECT * FROM categoria_item_acervo";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Categoriaitemacervo categoria = new Categoriaitemacervo();
            categoria.setIdCat(rs.getInt("ID_CAT"));
            categoria.setNomeCategoria(rs.getString("NomeCategoria"));
            categoria.setDescricao(rs.getString("Descricao"));
            lista.add(categoria);
        }

        return lista.iterator();
        }catch(Exception e){
            e.printStackTrace();
             throw new Exception("java.sql.SQLException");
        }
    }
}
