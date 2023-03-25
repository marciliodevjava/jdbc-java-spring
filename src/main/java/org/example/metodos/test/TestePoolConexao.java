package org.example.metodos.test;

import org.example.ConectionFactory;

import java.sql.SQLException;

public class TestePoolConexao {

    private static ConectionFactory conectionFactory;

    static {
        try {
            conectionFactory = new ConectionFactory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        for(int i = 0; i < 20; i++){
            conectionFactory.criaConexao();
            System.out.println("Conexão Nº: " + i );
        }

        System.out.println("Fechando Conexão!");
        conectionFactory.criaConexao();
    }
}
