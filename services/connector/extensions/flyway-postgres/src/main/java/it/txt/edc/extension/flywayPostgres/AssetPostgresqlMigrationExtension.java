package it.txt.edc.extension.flywayPostgres;

public class AssetPostgresqlMigrationExtension extends PostgresExtension {
    
    private static final String NAME_SUBSYSTEM = "asset";

    @Override
    public String getSubsystemName() {
        return NAME_SUBSYSTEM;
    }

}
