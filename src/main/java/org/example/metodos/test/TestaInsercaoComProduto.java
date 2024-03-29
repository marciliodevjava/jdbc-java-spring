package org.example.metodos.test;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;
import org.example.metodos.produto.dao.ProdutoDAO;

import java.sql.*;
import java.util.List;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto produto = new Produto("Esqueiro", "BIC Verde");
        Integer id = 40;

        try (Connection connection = new ConectionFactory().criaConexao()) {
            ProdutoDAO persistenciaDAO = new ProdutoDAO(connection);
            persistenciaDAO.salvarProdutoDAO(produto);
            List<Produto> listar = persistenciaDAO.listarProduto();

            listar.forEach(System.out::println);
        }

        System.out.println(produto);
    }
}
