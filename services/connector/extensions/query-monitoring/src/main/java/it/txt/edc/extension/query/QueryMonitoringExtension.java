package it.txt.edc.extension.query;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.edc.runtime.metamodel.annotation.Setting;
import org.eclipse.edc.spi.monitor.Monitor;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;


public class QueryMonitoringExtension implements ServiceExtension{
    
    private Monitor monitor;
    private static final String EXTENSION_NAME = "Query Monitoring";
     @Setting(required = true)
    private static final String DATASOURCE_SETTING_JDBC_URL = "my.edc.jdbc.url";
    @Setting(required = true)
    private static final String DATASOURCE_SETTING_USER = "my.edc.jdbc.user";
    @Setting(required = true)
    private static final String DATASOURCE_SETTING_PASSWORD = "my.edc.jdbc.password";

    public static String getExtensionName() {
        return EXTENSION_NAME;
    }

    @Override
    public void initialize(ServiceExtensionContext context){

        monitor = context.getMonitor();
        monitor.info("Initialized Query Monitoring extension"); 

        try (Connection connection = DriverManager.getConnection(System.getenv("MY_EDC_JDBC_URL"), System.getenv("MY_EDC_JDBC_USER"),System.getenv("MY_EDC_JDBC_PASSWORD"))){
            try(Statement statement = connection.createStatement()) {
                String sql = "CREATE EXTENSION IF NOT EXISTS pg_stat_statements";
                statement.executeUpdate(sql);
            }

            String outputFile = "QueryMonitoring.txt";

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String sql = "SELECT query, calls FROM pg_stat_statements";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                try(ResultSet resultSet = statement.executeQuery()){
                    while (resultSet.next()) {
                        // String query = resultSet.getString("query");
                        // //double totalTime = resultSet.getDouble("total_time");
                        // long calls = resultSet.getLong("calls");
                        // monitor.info("Query: " + query + ", Calls: " + calls);
                        writer.write("Query: " + resultSet.getString("query") + "\n\n");
                        writer.write("Calls: " + resultSet.getLong("calls"));
                    }
                writer.close();
                resultSet.close();
                statement.close();
                connection.close();
                }
            }
    
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown(){
        monitor.info("Shutdown Query Monitoring extension");
    }
}
