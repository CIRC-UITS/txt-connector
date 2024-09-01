CREATE TABLE IF NOT EXISTS edc_data_plane_instance
(
    id                   VARCHAR NOT NULL PRIMARY KEY,
    data                 JSON,
    lease_id             VARCHAR
        CONSTRAINT data_plane_instance_lease_id_fk
                    REFERENCES edc_lease
                    ON DELETE SET NULL
);