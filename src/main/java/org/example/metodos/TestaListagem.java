package org.example.metodos;

import org.example.ConectionFactory;
import org.example.Produto;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        Statement stm = connection.createStatement();
        boolean resultadoTrue = stm.execute("SELECT id, nome, descricao FROM produto");
        ResultSet rst = stm.getResultSet();

        if(resultadoTrue){
            while (rst.next()){
                Produto produto = new Produto();
                Integer id = rst.getInt("id");
                produto.setId(id);
                String nome = rst.getString("nome");
                produto.setNome(nome);
                String descricao = rst.getString("descricao");
                produto.setDescricao(descricao);
                System.out.println(produto.toString());
            }
        } else {
            System.out.println("Conexão falhou.");
        }

        connection.close();
    }
}
