package it.txt.edc.extension.flywayPostgres;

public class ContractNegotiationPostgresqlMigrationExtension  extends PostgresExtension{
    
    private static final String NAME_SUBSYSTEM = "contractnegotiation";

    @Override
    public String getSubsystemName() {
        return NAME_SUBSYSTEM;
    }

}
