package org.example.metodos.repository;

import org.example.metodos.domain.Produto;

import java.sql.*;

public class ProdutoRepository {

    private Connection connection;

    public ProdutoRepository(Connection connection) {
        this.connection = connection;
    }

    public Produto salvarProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto(nome, descricao) VALUES(?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
        return produto;
    }

    public Produto listarUmProduto(Integer id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT id, nome, descricao FROM produto WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, id);
        stm.execute();
        ResultSet rst = stm.getResultSet();

        Produto produto = new Produto();

        while (rst.next()) {
            Integer id1 = rst.getInt("id");
            produto.setId(id1);
            String nome = rst.getString("nome");
            produto.setNome(nome);
            String descricao = rst.getString("descricao");
            produto.setDescricao(descricao);
            System.out.println(produto.toString());
        }
        return produto;
    }

    public Produto listarProduto() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT id, nome, descricao FROM produto", Statement.RETURN_GENERATED_KEYS);
        stm.execute();
        ResultSet rst = stm.getResultSet();

        Produto produto = new Produto();

        while (rst.next()) {
            Integer id1 = rst.getInt("id");
            produto.setId(id1);
            String nome = rst.getString("nome");
            produto.setNome(nome);
            String descricao = rst.getString("descricao");
            produto.setDescricao(descricao);
            System.out.println(produto.toString());
        }
        return produto;
    }
}
