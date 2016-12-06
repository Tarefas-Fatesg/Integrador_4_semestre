/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.dao;

import br.harlock.model.Titulo;
import br.harlock.conn.Conexao;
import br.harlock.model.dataParseToSQL;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author kai
 */
public class TituloDAO {

    Connection connection = null;
    
    public TituloDAO() throws Exception {
        connection = Conexao.getConexao();
    }

    public void Inserir(Titulo titulo) {
        dataParseToSQL parse = new dataParseToSQL();
        try {
            String sql;
                sql = "INSERT INTO titulo(ISBN, ISSN, obra, Descricao, DataDePublicacao, CidadePublicacao, EstadoPublicacao, Edicao, Idioma, Traducao, Capa, FK_ITEM_PDC,FK_CAT_ARCE,duracao,volume,quatidadepaginas,tipoDeObra)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, titulo.getIsbn());
            ps.setString(2, titulo.getIssn());
            ps.setString(3, titulo.getObra());
            ps.setString(4, titulo.getDescricao());
            
            ps.setDate(5, parse.convertJavaDateToSqlDate(titulo.getDataDePublicacao()));
            ps.setString(6, titulo.getCidadePublicacao());
            ps.setString(7, titulo.getEstadoPublicacao());
            ps.setString(8, titulo.getEdicao());
            ps.setString(9, titulo.getIdioma());
            ps.setString(10, titulo.getTraducao());
            ps.setString(11, titulo.getCapa());
            ps.setInt(12, titulo.getFkItemPdc());
            ps.setInt(13, titulo.getFkItemAcervo());
            ps.setFloat(14, titulo.getDuracao());
            ps.setString(15, titulo.getVolume());
            ps.setInt(16, (int) titulo.getQuantidadePaginas());
            ps.setInt(17, Integer.parseInt(titulo.getTipoDeObra()));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Remover(Titulo titulo) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM titulo WHERE ID_TITU = ?");
            // Parameters start with 1
            preparedStatement.setInt(1, titulo.getIdTitu());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Update(Titulo titulo) {
        dataParseToSQL parse = new dataParseToSQL();
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE"
                            + "  titulo"
                            + " SET"
                            + "  ID_TITU = ?,"
                            + "  ISBN = ?,"
                            + "  ISSN = ?,"
                            + "  obra = ?,"
                            + "  Descricao = ?,"
                            + "  DataDePublicacao = ?,"
                            + "  CidadePublicacao = ?,"
                            + "  EstadoPublicacao = ?,"
                            + "  Edicao = ?,"
                            + "  Idioma = ?,"
                            + "  Traducao = ?,"
                            + "  Capa = ?,"
                            + "  FK_ITEM_PDC = ?,"
                            + "  FK_CAT_ARCE = ?,"
                            + "  tipoDeObra = ?,"
                            + "  duracao = ?,"
                            + "  volume = ?,"
                            + "  quatidadepaginas = ?"
                            + " WHERE"
                            + "   ID_TITU = ?");
            // Parameters start with 1
            ps.setInt(1, titulo.getIdTitu());
            ps.setString(2, titulo.getIsbn());
            ps.setString(3, titulo.getIssn());
            ps.setString(4, titulo.getObra());
            ps.setString(5, titulo.getDescricao());
            ps.setDate(6, parse.convertJavaDateToSqlDate(titulo.getDataDePublicacao()));
            ps.setString(7, titulo.getCidadePublicacao());
            ps.setString(8, titulo.getEstadoPublicacao());
            ps.setString(9, titulo.getEdicao());
            ps.setString(10, titulo.getIdioma());
            ps.setString(11, titulo.getTraducao());
            ps.setString(12, titulo.getCapa());
            ps.setInt(13, titulo.getFkItemPdc());
            ps.setInt(14, titulo.getFkItemAcervo());
            ps.setInt(15, Integer.parseInt(titulo.getTipoDeObra()));
            ps.setFloat(16, titulo.getDuracao());
            ps.setString(17, titulo.getVolume());
            ps.setInt(18, (int) titulo.getQuantidadePaginas());
            ps.setInt(19, titulo.getIdTitu());
            ps.executeUpdate();
//            titulo.getTipoDeObra()
//            titulo.getDuracao()
//            itulo.getVolume()
//            titulo.getQuantidadePaginas()
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public Iterator<Titulo> consutlarporTitulo(String termo) throws Exception{
        try {
            ArrayList lista = new ArrayList();
            String sql = "SELECT * from titulo where obra REGEXP '?'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, termo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                boolean next = rs.next();
                Titulo titulo = new Titulo();
                titulo.setIdTitu(rs.getInt("ID_TITU"));
                titulo.setIsbn(rs.getString("ISBN"));
                titulo.setIssn(rs.getString("ISSN"));
                titulo.setObra(rs.getString("obra"));
                titulo.setDescricao(rs.getString("Descricao"));
                titulo.setDataDePublicacao(rs.getString("DataDePublicacao"));
                titulo.setCidadePublicacao(rs.getString("CidadePublicacao"));
                titulo.setEstadoPublicacao(rs.getString("EstadoPublicacao"));
                titulo.setEdicao(rs.getString("Edicao"));
                titulo.setIdioma(rs.getString("Idioma"));
                titulo.setTraducao(rs.getString("Traducao"));
                //titulo.setCapa(rs.getBlob("Capa"));
                titulo.setFkItemPdc(rs.getInt("FK_ITEM_PDC"));
                titulo.setCategoriaitemacervo(new CategoriaDAO().Pesquisar(rs.getInt("FK_CAT_ARCE")));
                titulo.setFkItemAcervo(rs.getInt("FK_CAT_ARCE"));
                titulo.setTipoDeObra(rs.getString("tipoDeObra"));
                titulo.setDuracao(rs.getFloat("duracao"));
                titulo.setVolume(rs.getString("volume"));
                titulo.setQuantidadePaginas(rs.getInt("quatidadepaginas"));
                lista.add(titulo);
            }
            return lista.iterator();
            
        } catch (Exception e) {
             throw new Exception(" r"+e);

        }
    }
      public Iterator<Titulo> ConsultarTodos() throws ParseException, Exception {
        
        try {
            List<Titulo> titulos = new ArrayList<Titulo>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM titulo");
            while (rs.next()) {
                Titulo titulo = new Titulo();
                titulo.setIdTitu(rs.getInt("ID_TITU"));
                titulo.setIsbn(rs.getString("ISBN"));
                titulo.setIssn(rs.getString("ISSN"));
                titulo.setObra(rs.getString("obra"));
                titulo.setDescricao(rs.getString("Descricao"));
                titulo.setDataDePublicacao(rs.getString("DataDePublicacao"));
                titulo.setCidadePublicacao(rs.getString("CidadePublicacao"));
                titulo.setEstadoPublicacao(rs.getString("EstadoPublicacao"));
                titulo.setEdicao(rs.getString("Edicao"));
                titulo.setIdioma(rs.getString("Idioma"));
                titulo.setTraducao(rs.getString("Traducao"));
                //titulo.setCapa(rs.getBlob("Capa"));
                titulo.setFkItemPdc(rs.getInt("FK_ITEM_PDC"));
                
                titulo.setCategoriaitemacervo(new CategoriaDAO().Pesquisar(rs.getInt("FK_CAT_ARCE")));
                
                
                
                titulo.setFkItemAcervo(rs.getInt("FK_CAT_ARCE"));
                titulo.setTipoDeObra(rs.getString("tipoDeObra"));
                titulo.setDuracao(rs.getFloat("duracao"));
                titulo.setVolume(rs.getString("volume"));
                titulo.setQuantidadePaginas(rs.getInt("quatidadepaginas"));
                titulos.add(titulo);
                
            }
            return titulos.iterator();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(" r"+e);
        }
    }

    public Titulo Pesquisar(Titulo titulo) throws Exception {
        try {
            
            String sql = "";
            PreparedStatement ps = connection.prepareStatement(sql);
            String where="";
            if (titulo.getIdTitu() != 0) {    
                sql = "SELECT ID_TITU, ISBN, ISSN, obra, Descricao, DataDePublicacao, CidadePublicacao, EstadoPublicacao, Edicao, Idioma, Traducao, Capa, FK_ITEM_PDC, FK_CAT_ARCE ,tipoDeObra, duracao, volume, quatidadepaginas FROM titulo WHERE ID_TITU = ? ";
                ps =connection.prepareStatement(sql);
                ps.setInt(1, titulo.getIdTitu());
            }else if (!titulo.getIdioma().equals(null)) {
                sql = "SELECT ID_TITU, ISBN, ISSN, obra, Descricao, DataDePublicacao, CidadePublicacao, EstadoPublicacao, Edicao, Idioma, Traducao, Capa, FK_ITEM_PDC, FK_CAT_ARCE ,tipoDeObra, duracao, volume, quatidadepaginas  FROM titulo WHERE obra = ? ";
                ps =connection.prepareStatement(sql);
                ps.setString(1, titulo.getObra());
            }
            
            ResultSet rs = ps.executeQuery();
            rs.next();
           
            
                titulo.setIdTitu(rs.getInt("ID_TITU"));
                titulo.setIsbn(rs.getString("ISBN"));
                titulo.setIssn(rs.getString("ISSN"));
                titulo.setObra(rs.getString("obra"));
                titulo.setDescricao(rs.getString("Descricao"));
                titulo.setDataDePublicacao(rs.getString("DataDePublicacao"));
                titulo.setCidadePublicacao(rs.getString("CidadePublicacao"));
                titulo.setEstadoPublicacao(rs.getString("EstadoPublicacao"));
                titulo.setEdicao(rs.getString("Edicao"));
                titulo.setIdioma(rs.getString("Idioma"));
                titulo.setTraducao(rs.getString("Traducao"));
                //titulo.setCapa(rs.getBlob("Capa"));
                titulo.setFkItemPdc(rs.getInt("FK_ITEM_PDC"));
                titulo.setFkItemAcervo(rs.getInt("FK_CAT_ARCE"));
                titulo.setTipoDeObra(rs.getString("tipoDeObra"));
                titulo.setDuracao(rs.getFloat("duracao"));
                titulo.setVolume(rs.getString("volume"));
                titulo.setQuantidadePaginas(rs.getInt("quatidadepaginas"));

                return titulo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(" r"+e);
        }
        
    }
}
