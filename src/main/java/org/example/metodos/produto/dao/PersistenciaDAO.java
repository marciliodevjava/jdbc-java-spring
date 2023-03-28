package org.example.metodos.produto.dao;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;
import org.example.metodos.repository.ProdutoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDAO {

    private Connection connection;

    public PersistenciaDAO(Connection connection){
        this.connection = connection;
    }

    public void salvarProdutoDAO(Produto produto) throws SQLException {

        String sql = "INSERT INTO produto(nome, descricao) VALUES(?, ?)";
        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try(ResultSet rst = pstm.getGeneratedKeys()){

                while(rst.next()){
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public List<Produto> listarProduto() throws SQLException{

        String sql = "SELECT id, nome, descricao FROM produto";

        List<Produto> produtoList = new ArrayList<>();
        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.NO_GENERATED_KEYS)){

            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()){
                while (rst.next()){
                    Produto produto = new Produto();
                    Integer id = rst.getInt("id");
                    produto.setId(id);
                    String nome = rst.getString("nome");
                    produto.setNome(nome);
                    String descricao = rst.getString("descricao");
                    produto.setDescricao(descricao);

                    produtoList.add(produto);
                }
            }
        }
        return produtoList;
    }
}
