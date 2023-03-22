package org.example;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConectionFactory {

    private final String url = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "@Sa45781256";
    public DataSource dataSource;

    public ConectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setAcquireIncrement(10);
        comboPooledDataSource.setAcquireRetryAttempts(100);
        comboPooledDataSource.setAcquireRetryDelay(1000);
        comboPooledDataSource.setAutoCommitOnClose(false);

        this.dataSource = comboPooledDataSource;
    }

    public Connection criaConexao() throws SQLException {
        return this.dataSource.getConnection();
    }
}
