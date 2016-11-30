/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.bll;

import br.harlock.conn.Conexao;
import br.harlock.dao.AutorDAO;
import br.harlock.dao.CategoriaDAO;
import br.harlock.dao.ProdutoraDAO;
import br.harlock.dao.TituloDAO;
import br.harlock.model.Autor;
import br.harlock.model.Categoriaitemacervo;
import br.harlock.model.ProdutoraConteudo;
import br.harlock.model.Titulo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author minerthal
 */
@WebServlet("/Titulo.do")
public class TituloServ extends HttpServlet {
    private Connection connection = null;
    private String acao = "";
    private TituloDAO tituloDAO;
    private ProdutoraDAO produtoraDAO;
    private AutorDAO autorDAO;
    private Autor autor;
    private CategoriaDAO categoriaDAO;
    public TituloServ() throws Exception{
        categoriaDAO = new CategoriaDAO();
        tituloDAO = new TituloDAO();
        produtoraDAO = new ProdutoraDAO();
        autorDAO = new AutorDAO();
        connection = Conexao.getConexao();
    }
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException, Exception {
            String pagina = "index.jsp";
            acao = request.getParameter("acao");
            if (acao.equals("tituloui")) {
                Iterator iteratorAutores = autorDAO.ConsultarTodos();
                Iterator iteratorProdutoraDeConteudo = produtoraDAO.ConsultarTodos();
                Iterator iteratorCategoria = categoriaDAO.ConsultarTodos();
                request.setAttribute("autores", iteratorAutores);
                request.setAttribute("prdoutoras", iteratorProdutoraDeConteudo);
                request.setAttribute("categorias", iteratorCategoria);
                pagina = "index.jsp?pagina=tituloui";
            }
            if (acao.equals("salvar")) {
                
                Titulo ti = new Titulo(" ");
                ProdutoraConteudo produtoraConteudo = new ProdutoraConteudo();
                produtoraConteudo.setIdPdc(Integer.parseInt(request.getParameter("produtora")));
                ti.setProdutoraConteudo(produtoraConteudo);
                ti.setObra(request.getParameter("obra"));
                ti.setIssn(request.getParameter("ISSN"));
                ti.setIsbn(request.getParameter("ISBN"));
                ti.setTipoDeObra(request.getParameter("tipoObra"));
                ti.setIdioma(request.getParameter("idiomaObra"));
                if (request.getParameter("idTitulo") != null ) {
                    ti.setIdTitu(Integer.parseInt(request.getParameter("idTitulo")));
                }
                
                ti.setVolume(request.getParameter("Volume"));
                ti.setFkItemPdc(Integer.parseInt(request.getParameter("produtora")));
//                ti.setEdicao(request.getParameter(""));
                String f = request.getParameter("duracao");
                if(f == null){
                    ti.setDuracao(0);
                }else{
                    ti.setDuracao(Float.parseFloat(request.getParameter("duracao")));
                }
                 String PG = request.getParameter("numeropaginas");
                if(PG == null){
                    ti.setQuantidadePaginas(0);
                }else{
                    ti.setQuantidadePaginas(Float.parseFloat(request.getParameter("numeropaginas")));
                }
                ti.setDescricao(request.getParameter("descricao"));
                ti.setDataDePublicacao(request.getParameter("datapublicacao"));
                ti.setEstadoPublicacao(request.getParameter("estado"));
                ti.setCidadePublicacao(request.getParameter("cidade"));
                Categoriaitemacervo categoriaitemacervo = new Categoriaitemacervo();
                categoriaitemacervo.setIdCat(Integer.parseInt(request.getParameter("categoria")));
                ti.setFkItemAcervo(categoriaitemacervo.getIdCat());
                ti.setCapa(request.getParameter("base64img"));
                ti.setTraducao(request.getParameter("traducao"));
                tituloDAO.Inserir(ti);
                ti = tituloDAO.Pesquisar(ti);
                String[] autres = request.getParameterValues("autores");
                String[] idAutores = request.getParameterValues("idAutor");
                String[] exemplares = request.getParameterValues("exemplar");
                String[] liberadoEmp = request.getParameterValues("liberadoParaEmprestimo");
                String nul = null;
//                for (String aut : autres) {
//                    autor = new Autor(0, aut, " ", " ");
//                   int ID = autorDAO.Pesquisar(autor).getIdAutor();
//                   String nA = autorDAO.Pesquisar(autor).getNome();
//                   String sql= "";
//                   sql="INSERT INTO titulo_tem_autor (Titulo_idTitulo, Autor_idAutor, TipoDeAutor) VALUES ("+ID+" , "+nA+" , ? )";
//                   PreparedStatement ps = connection.prepareStatement(sql);
//                   ps.execute();
//                }

                
                
                
            }
            request.getRequestDispatcher(pagina).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TituloServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TituloServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TituloServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TituloServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TituloServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TituloServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
