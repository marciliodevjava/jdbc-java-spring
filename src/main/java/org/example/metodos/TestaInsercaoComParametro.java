package org.example.metodos;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();
        connection.setAutoCommit(false);

        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO produto(nome, descricao) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

            adicionarVariavel(stm, "Pinsel Fabcastel", "Vermelho");
            adicionarVariavel(stm, "Pinsel Fabcastel", "Preto");
            adicionarVariavel(stm, "Pinsel Fabcastel", "Azul");

            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ROLLBACK EXECUTTE : " + e.getMessage());
            connection.rollback();
        }
    }

    private static void adicionarVariavel(PreparedStatement stm, String nome, String descricao) throws SQLException {


        stm.setString(1, nome);
        stm.setString(2, descricao);
        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        
        if(nome.equals("Pinsel Fabcastel")){
            throw new RuntimeException("Não foi possivel adicionar o produto");
        }

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

    }

    private static String verifcaSqlInj(String nomeEnviou) {
        String nomeRecebido;
        nomeRecebido = String.format(nomeEnviou).replace(";", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("delete", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("from", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("database", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("table", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("for", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("and", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("values", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("*", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("select", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("drop", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("DROP", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("DATABASE", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("TABLE", "").trim();
        nomeRecebido = String.format(nomeRecebido).replace("USE", "").trim();

        return nomeRecebido;
    }
}
