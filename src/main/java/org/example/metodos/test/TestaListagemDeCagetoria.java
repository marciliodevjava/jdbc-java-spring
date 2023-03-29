package org.example.metodos.test;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;
import org.example.metodos.produto.dao.CategoriaDAO;
import org.example.metodos.domanin.Categoria;
import org.example.metodos.produto.dao.ProdutoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class TestaListagemDeCagetoria {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConectionFactory().criaConexao()) {
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaCategoria = categoriaDAO.listar();

            listaCategoria.stream().forEach(ct -> {
                System.out.println(ct.toString());
                try {
                    for (Produto produto : new ProdutoDAO(connection).buscarPorCategoria(ct)) {
                        System.out.println("Categoria: " + ct.getNome() +
                                " Nome: " + produto.getNome() +
                                " Descrição: " + produto.getDescricao());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
