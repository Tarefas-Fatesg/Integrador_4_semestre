/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.dao;

import br.harlock.model.Exemplar;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kai
 */
public class ExemplarDAOTest {
    
    public ExemplarDAOTest() {
    }
    
    

    /**
     * Test of Inserir method, of class ExemplarDAO.
     */
    @Test
    public void testInserir() {
        System.out.println("Teste Inserir Exemplar");
        Exemplar exemplar = new Exemplar(0, 0, "asd", Boolean.TRUE, "asdas", "asd");
        
        ExemplarDAO instance = new ExemplarDAO();
        instance.Inserir(exemplar);
        
        
    }

    /**
     * Test of Remover method, of class ExemplarDAO.
     */
    @Test
    public void testRemover() {
        System.out.println("Remover");
        Exemplar exemplar = null;
        ExemplarDAO instance = new ExemplarDAO();
        instance.Remover(exemplar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Update method, of class ExemplarDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update");
        Exemplar exemplar = null;
        ExemplarDAO instance = new ExemplarDAO();
        instance.Update(exemplar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ConsultarTodos method, of class ExemplarDAO.
     */
    @Test
    public void testConsultarTodos() {
        System.out.println("ConsultarTodos");
        ExemplarDAO instance = new ExemplarDAO();
        Iterator<Exemplar> expResult = null;
        Iterator<Exemplar> result = instance.ConsultarTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Pesquisar method, of class ExemplarDAO.
     */
    @Test
    public void testPesquisar() {
        System.out.println("Pesquisar");
        String ISBN = "";
        ExemplarDAO instance = new ExemplarDAO();
        Exemplar expResult = null;
        Exemplar result = instance.Pesquisar(ISBN);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}