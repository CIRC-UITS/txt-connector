package it.txt.edc.extension.flywayPostgres;

public class TransferProcessPostgresqlMigrationExtension extends PostgresExtension {

    private static final String NAME_SUBSYSTEM = "transferprocess";

    @Override
    public String getSubsystemName() {
        return NAME_SUBSYSTEM;
    }
    
}
