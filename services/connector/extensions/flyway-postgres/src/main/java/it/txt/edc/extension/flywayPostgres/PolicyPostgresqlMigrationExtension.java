package it.txt.edc.extension.flywayPostgres;

public class PolicyPostgresqlMigrationExtension  extends PostgresExtension{
    
    private static final String NAME_SUBSYSTEM = "policy";

    @Override
    public String getSubsystemName() {
        return NAME_SUBSYSTEM;
    }

}
