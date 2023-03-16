package org.example.metodos;

import org.example.ConectionFactory;
import org.example.metodos.domain.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConectionFactory conectionFactory = new ConectionFactory();
        Connection connection = conectionFactory.criaConexao();

        String nomeEnviou = "select * from produto;";
        String descricaoEnvivou = "Samsung S20";

        nomeEnviou = verifcaSqlInj(nomeEnviou);
        String nome = String.format("'%s'", nomeEnviou);
        String descricao = String.format("'%s'", descricaoEnvivou);

        System.out.println(nome);
        System.out.println(descricao);

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

    private static String verifcaSqlInj(String nomeEnviou) {
        String nomeRecebido;
        nomeRecebido = String.format(nomeEnviou).replace(";","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("delete","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("from","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("database","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("table","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("for","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("and","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("values","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("*","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("select","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("drop","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("DROP","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("DATABASE","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("TABLE","").trim();
        nomeRecebido = String.format(nomeRecebido).replace("USE","").trim();


        return nomeRecebido;
    }
}
