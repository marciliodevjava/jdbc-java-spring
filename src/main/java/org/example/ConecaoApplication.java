package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class ConecaoApplication {
    public static void main(String[] args) throws SQLException {
        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        System.out.println("Fechando conexão!");
        connection.close();
    }

}