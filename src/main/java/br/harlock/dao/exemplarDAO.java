/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.harlock.dao;

import java.sql.Connection;
import br.harlock.conn.Conexao;
/**
 *
 * @author kai
 */
public class exemplarDAO {
    private Connection connection = null;

	public exemplarDAO() {
		connection = Conexao.getConexao();
	}
        
        public static void main(String[] args) {
        Conexao Conexao = new Conexao();
    }
}