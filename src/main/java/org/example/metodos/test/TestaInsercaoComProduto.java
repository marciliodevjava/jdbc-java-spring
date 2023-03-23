package org.example.metodos.test;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto produto = new Produto("Cômoda", "Cômoda Vertical");

        try(Connection connection = new ConectionFactory().criaConexao()) {

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
        System.out.println(produto);
    }
}
