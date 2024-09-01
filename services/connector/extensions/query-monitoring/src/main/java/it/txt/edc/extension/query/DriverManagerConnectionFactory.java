package it.txt.edc.extension.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.eclipse.edc.sql.ConnectionFactory;

import org.eclipse.edc.spi.persistence.EdcPersistenceException;

public class DriverManagerConnectionFactory implements ConnectionFactory {
    

    private final JdbcConnProperties jdbcProperties;

    public DriverManagerConnectionFactory(JdbcConnProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }


    @Override
    public Connection create(String jdbcUrl, Properties properties) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    
}
