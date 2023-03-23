package org.example.metodos.test;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;
import org.example.metodos.repository.ProdutoRepository;

import java.sql.*;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto produto = new Produto("Caminão", "Truck");
        Integer id = 40;

        try (Connection connection = new ConectionFactory().criaConexao()) {
            ProdutoRepository repository = new ProdutoRepository(connection);
            System.out.println("========== ADICIONANDO UM PRODUTO ========== ");
            repository.salvarPrdoduto(produto);
            System.out.println(produto);
            System.out.println("========== LISTANDO UM PRODUTO ========== ");
            repository.listarUmProduto(id);
            System.out.println("========== LISTANDO TODOS OS PRODUTO ========== ");
            repository.listarProduto();
        }
    }
}
