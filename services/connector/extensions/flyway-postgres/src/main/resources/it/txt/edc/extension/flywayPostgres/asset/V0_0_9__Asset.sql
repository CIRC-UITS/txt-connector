ALTER TABLE edc_asset_property
    ADD property_is_private VARCHAR;

ALTER TABLE edc_asset_property
    ALTER COLUMN property_is_private SET NOT NULL;