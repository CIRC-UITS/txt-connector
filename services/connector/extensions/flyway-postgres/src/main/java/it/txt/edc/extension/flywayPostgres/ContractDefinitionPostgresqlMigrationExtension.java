package it.txt.edc.extension.flywayPostgres;

public class ContractDefinitionPostgresqlMigrationExtension extends PostgresExtension {
    
    private static final String NAME_SUBSYSTEM = "contractdefinition";

    @Override
    public String getSubsystemName() {
        return NAME_SUBSYSTEM;
    }

}
