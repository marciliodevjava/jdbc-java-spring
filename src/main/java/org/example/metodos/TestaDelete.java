package org.example.metodos;

import org.example.ConectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaDelete {

    public static void main(String[] args) throws SQLException {

        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        int id = 13;

        Statement stm = connection.createStatement();
        stm.execute("DELETE FROM produto WHERE id = " + id);

        int linhaModificadas = stm.getUpdateCount();

        System.out.println("Quantidade de linhas modificadas " + linhaModificadas);

        connection.close();
    }
}
