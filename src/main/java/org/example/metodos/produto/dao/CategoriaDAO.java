package org.example.metodos.produto.dao;

import org.example.metodos.domain.Produto;
import org.example.metodos.domanin.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT id, nome FROM categoria";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rst.getInt(1));
                    categoria.setNome(rst.getString(2));
                    categorias.add(categoria);
                }
            }

        }
        return categorias;
    }

    public List<Categoria> listarComProdutos() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        Categoria ultima = null;

        String sql = "SELECT c.id, c.nome, p.id, p.nome, p.descricao " +
                "FROM categoria c " +
                "INNER JOIN produto p " +
                "ON c.id = p.categoria_id ";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
                        Categoria categoria = new Categoria();
                        categoria.setId(rst.getInt(1));
                        categoria.setNome(rst.getString(2));
                        ultima = categoria;
                        categorias.add(categoria);
                    }
                    Produto produto = new Produto();
                    produto.setId(rst.getInt(3));
                    produto.setNome(rst.getString(4));
                    produto.setDescricao(rst.getString(5));
                    ultima.adicionar(produto);
                }
            }

        }
        return categorias;
    }
}
