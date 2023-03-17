package org.example.metodos;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        PreparedStatement stm = connection.prepareStatement("SELECT id, nome, descricao FROM produto", Statement.RETURN_GENERATED_KEYS);
        stm.execute();
        ResultSet rst = stm.getResultSet();


        while (rst.next()) {
            Produto produto = new Produto();
            Integer id = rst.getInt("id");
            produto.setId(id);
            String nome = rst.getString("nome");
            produto.setNome(nome);
            String descricao = rst.getString("descricao");
            produto.setDescricao(descricao);
            System.out.println(produto.toString());
        }

        System.out.println("Conexão falhou.");


        connection.close();
    }
}
