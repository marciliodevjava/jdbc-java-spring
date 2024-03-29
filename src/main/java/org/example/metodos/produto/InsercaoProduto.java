package org.example.metodos.produto;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsercaoProduto {

    public static void main(String[] args) throws SQLException {

        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        String nome = "'Cadeira'";
        String descricao = "'Madeira nobre'";

        Statement stm = connection.createStatement();
        stm.execute("INSERT INTO produto(nome, descricao) VALUES(" + nome + ", " + descricao + ")", Statement.RETURN_GENERATED_KEYS);

        ResultSet rst = stm.getGeneratedKeys();

        while (rst.next()) {
            Produto produto = new Produto();
            Integer id = rst.getInt(1);
            produto.setId(id);
            String nomeInserido = rst.getString(1);
            produto.setNome(nomeInserido);
            String descricaoInserido = rst.getString(1);
            produto.setDescricao(descricaoInserido);
            System.out.println(produto.toString());
        }

        connection.close();
    }
}
