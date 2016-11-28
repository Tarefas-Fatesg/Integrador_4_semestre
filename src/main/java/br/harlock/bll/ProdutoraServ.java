/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.bll;

import br.harlock.dao.ProdutoraDAO;
import br.harlock.model.ProdutoraConteudo;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/Produtora.do")
public class ProdutoraServ extends HttpServlet {

    private String acao = "";
    private ProdutoraDAO DAO;

    public ProdutoraServ() throws Exception {
        DAO = new ProdutoraDAO();
        acao = "";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String pagina = "cadastroprodutora";

        ProdutoraConteudo p = new ProdutoraConteudo();

        acao = request.getParameter("acao");
        if (acao.equalsIgnoreCase("salvar")) {
            p.setNomeProdutora(request.getParameter("nome"));
            p.setDescricao(request.getParameter("desc"));
            p.setCnpj(request.getParameter("CNPJ"));
            p.setIdPdc(Integer.parseInt(request.getParameter("ID")));
            if (p.getIdPdc() != 0) {
                DAO.Update(p);
                
            } else {
                DAO.Inserir(p);
            }

            pagina = "Produtora.do?acao=produtoras";
        } else if (acao.equals("remover")) {
            p.setIdPdc(Integer.parseInt(request.getParameter("ID")));
            p.setIdPdc(Integer.parseInt(request.getParameter("ID")));
            DAO.Remover(p);
            pagina = "index.jsp?pagina=produtoresCTRL";
        } else if (acao.equals("produtoras")) {
            Iterator produtoras = DAO.ConsultarTodos();
            request.setAttribute("produtoras", produtoras);
            pagina = "index.jsp?pagina=produtoresCTRL";
        } else if (acao.equals("update")) {
            p.setIdPdc(Integer.parseInt(request.getParameter("ID")));
            ProdutoraConteudo pdc = DAO.Pesquisar(p);
            request.setAttribute("produtora", pdc);
            pagina = "index.jsp?pagina=produtoresui";
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
        } catch (Exception ex) {
            Logger.getLogger(ProdutoraServ.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProdutoraServ.class.getName()).log(Level.SEVERE, null, ex);
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
