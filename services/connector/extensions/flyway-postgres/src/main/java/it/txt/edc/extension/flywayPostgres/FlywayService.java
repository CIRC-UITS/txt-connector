package it.txt.edc.extension.flywayPostgres;

import java.sql.Connection;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;

public class FlywayService {

    private static final String MIGRATION_LOCATION = String.format("classpath:%s", FlywayService.class.getPackageName().replace(".", "/"));

    static Connection connection = null;

    public static boolean migrate(String subSystemName, String defaultSchema, MigrationVersion version){
        var flyway = Flyway.configure()
                .baselineVersion(MigrationVersion.fromVersion("0.0.0"))
                .dataSource(System.getenv("MY_EDC_JDBC_URL"), System.getenv("MY_EDC_JDBC_USER"),System.getenv("MY_EDC_JDBC_PASSWORD"))
                .table("flyway_schema_history_%s".formatted(subSystemName))
                .locations("%s/%s".formatted(MIGRATION_LOCATION, subSystemName))
                .defaultSchema(defaultSchema)
                .target(version)
                .outOfOrder(true)
                .load();

        flyway.baseline();

        DataSource dataSource = flyway.getConfiguration().getDataSource();
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            flyway.migrate();
            connection.commit();
            return true;
        }catch(Exception e){
            System.err.println("Error during migration: " + e.getMessage());
            //flyway.undo();
            try{
                if(connection != null){
                    connection.rollback();
                }
            }catch(Exception f){
                f.printStackTrace();
            }
            return false;
        }finally{
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }


    }
}
