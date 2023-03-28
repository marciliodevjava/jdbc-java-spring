package org.example.metodos.test;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;
import org.example.metodos.produto.dao.PersistenciaDAO;
import org.example.metodos.repository.ProdutoRepository;

import java.sql.*;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto produto = new Produto("Copo", "Vermelho");
        Integer id = 40;

        try (Connection connection = new ConectionFactory().criaConexao()) {
            PersistenciaDAO persistenciaDAO = new PersistenciaDAO(connection);
            persistenciaDAO.salvarProdutoDAO(produto);
            persistenciaDAO.listarProduto();
        }

        System.out.println(produto);
    }
}
