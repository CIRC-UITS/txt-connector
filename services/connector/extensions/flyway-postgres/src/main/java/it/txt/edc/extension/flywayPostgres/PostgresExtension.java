package it.txt.edc.extension.flywayPostgres;

import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.spi.monitor.Monitor;
import org.eclipse.edc.runtime.metamodel.annotation.Setting;
import java.util.Objects;

import static org.flywaydb.core.api.MigrationVersion.LATEST;

public class PostgresExtension implements ServiceExtension {

    private Monitor monitor;

    private static final String DEFAULT_MIGRATION_SCHEMA = "public";
    @Setting(value = "Schema used for the migration", defaultValue = DEFAULT_MIGRATION_SCHEMA)
    private static final String MIGRATION_SCHEMA = "it.txt.edc.extension.flywayPostgres.schema";

    @Override
    public void initialize(ServiceExtensionContext context){
        var config = context.getConfig();
        monitor = context.getMonitor();
        monitor.info("Initialized Postgres Flyway extension"); 
        var subSystemName = Objects.requireNonNull(getSubsystemName());

        var defaultSchema = config.getString(MIGRATION_SCHEMA, DEFAULT_MIGRATION_SCHEMA);
        boolean migrationSuccess = FlywayService.migrate(subSystemName, defaultSchema, LATEST );
        if(migrationSuccess){
            monitor.info("Migration completed successfully");
        }else{
            monitor.info("Error during migration, rollback executed");
        }
    }

    @Override
    public void shutdown(){
        monitor.info("Shutdown Postgres Flyway extension");
    }


    public String getSubsystemName(){
        return "";
    }

}