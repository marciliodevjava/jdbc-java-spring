package org.example.metodos;

import org.example.ConectionFactory;

import java.sql.*;

public class TestaDelete {

    public static void main(String[] args) throws SQLException {

        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        int id = 13;

        PreparedStatement stm = connection.prepareStatement("DELETE FROM produto WHERE id = " + id, Statement.RETURN_GENERATED_KEYS);
        stm.execute("DELETE FROM produto WHERE id = " + id);

        int linhaModificadas = stm.getUpdateCount();

        System.out.println("Quantidade de linhas modificadas " + linhaModificadas);

        connection.close();
    }
}
