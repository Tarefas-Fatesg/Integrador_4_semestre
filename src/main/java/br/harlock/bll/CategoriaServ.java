/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.bll;

import br.harlock.dao.CategoriaDAO;
import br.harlock.model.Categoriaitemacervo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author minerthal
 */
@WebServlet(name = "Categoria.do", urlPatterns = {"/CategoriaServ"})
public class CategoriaServ extends HttpServlet {
    private CategoriaDAO categoriaDAO;
    private String acao = "";
    public CategoriaServ() throws Exception{
        categoriaDAO = new CategoriaDAO();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        acao = request.getParameter("acao");
        if (acao.equals("listarTodos")) {
            request.setAttribute("categorias", listarCategorias());
        }else if(acao.equals("pesquisar")){
            Categoriaitemacervo c = new Categoriaitemacervo();
            c.setIdCat(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("categoria", pesquisar(c));
        }
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
        } catch (Exception ex) {
            Logger.getLogger(CategoriaServ.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(CategoriaServ.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public Iterator listarCategorias() throws SQLException{
        Iterator e = categoriaDAO.ConsultarTodos();
        return e;
    }
    public Categoriaitemacervo pesquisar(Categoriaitemacervo c) throws Exception{
        c = categoriaDAO.Pesquisar(c);
        return c;
    }
}
