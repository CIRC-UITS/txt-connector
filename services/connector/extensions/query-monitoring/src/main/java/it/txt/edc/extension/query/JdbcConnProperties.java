package it.txt.edc.extension.query;

import org.eclipse.edc.runtime.metamodel.annotation.Setting;
import org.eclipse.edc.spi.system.configuration.Config;

public class JdbcConnProperties {

    @Setting(required = true)
    private static final String DATASOURCE_SETTING_JDBC_URL = "edc.datasource.%s.url";
    @Setting(required = true)
    private static final String DATASOURCE_SETTING_USER = "edc.datasource.%s.user";
    @Setting(required = true)
    private static final String DATASOURCE_SETTING_PASSWORD = "edc.datasource.%s.password";

    private final String jdbcUrl;
    private final String user;
    private final String password;

    public JdbcConnProperties(Config config, String entityName) {
        jdbcUrl = config.getString(String.format(System.getenv("MY_EDC_JDBC_URL"), entityName));
        user = config.getString(String.format(System.getenv("MY_EDC_JDBC_USER"), entityName));
        password = config.getString(String.format(System.getenv("MY_EDC_JDBC_PASSWORD"), entityName));
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

