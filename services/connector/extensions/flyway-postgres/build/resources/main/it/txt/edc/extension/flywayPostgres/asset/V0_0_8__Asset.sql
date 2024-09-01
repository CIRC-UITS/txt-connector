-- table: edc_asset_property
CREATE TABLE IF NOT EXISTS edc_asset_property
(
    asset_id_fk    VARCHAR NOT NULL,
    property_name  VARCHAR NOT NULL,
    property_value VARCHAR NOT NULL,
    property_type  VARCHAR NOT NULL,
    PRIMARY KEY (asset_id_fk, property_name),
    FOREIGN KEY (asset_id_fk) REFERENCES edc_asset (asset_id) ON DELETE CASCADE
);

COMMENT ON COLUMN edc_asset_property.property_name IS
    'Asset property key';
COMMENT ON COLUMN edc_asset_property.property_value IS
    'Asset property value';
COMMENT ON COLUMN edc_asset_property.property_type IS
    'Asset property class name';

CREATE INDEX IF NOT EXISTS idx_edc_asset_property_value
    ON edc_asset_property (property_name, property_value);