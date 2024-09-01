package it.txt.edc.extension.flywayPostgres;

public class DataPlaneInstancePostgresqlMigrationExtension  extends PostgresExtension{
    
    private static final String NAME_SUBSYSTEM = "dataplaneinstance";

    @Override
    public String getSubsystemName() {
        return NAME_SUBSYSTEM;
    }

}
