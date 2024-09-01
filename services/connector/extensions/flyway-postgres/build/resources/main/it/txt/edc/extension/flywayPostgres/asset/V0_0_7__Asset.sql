-- table: edc_asset_dataaddress
CREATE TABLE IF NOT EXISTS edc_asset_dataaddress
(
    asset_id_fk VARCHAR NOT NULL,
    properties  JSON    NOT NULL,
    PRIMARY KEY (asset_id_fk),
    FOREIGN KEY (asset_id_fk) REFERENCES edc_asset (asset_id) ON DELETE CASCADE
);
COMMENT ON COLUMN edc_asset_dataaddress.properties IS 'DataAddress properties serialized as JSON';