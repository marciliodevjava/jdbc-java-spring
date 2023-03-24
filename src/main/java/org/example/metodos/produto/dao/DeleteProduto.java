package org.example.metodos.produto.dao;

import org.example.ConectionFactory;

import java.sql.*;

public class DeleteProduto {

    public static void main(String[] args) throws SQLException {

        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        int id = 13;

        PreparedStatement stm = connection.prepareStatement("DELETE FROM produto WHERE ? = ", Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, id);
        stm.execute();

        int linhaModificadas = stm.getUpdateCount();

        System.out.println("Quantidade de linhas modificadas " + linhaModificadas);

        connection.close();
    }
}
