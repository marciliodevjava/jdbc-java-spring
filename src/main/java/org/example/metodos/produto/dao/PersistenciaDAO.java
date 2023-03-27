package org.example.metodos.produto.dao;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;
import org.example.metodos.repository.ProdutoRepository;

import java.sql.*;

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
}
