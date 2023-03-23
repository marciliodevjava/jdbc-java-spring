package org.example.metodos.test;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;
import org.example.metodos.repository.ProdutoRepository;

import java.sql.*;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto produto = new Produto("Cama Casal", "1.80m x 1.20m");

        try (Connection connection = new ConectionFactory().criaConexao()) {
            ProdutoRepository repository = new ProdutoRepository(connection);
            repository.salvarPrdoduto(produto);
        }
        System.out.println(produto);
    }
}
