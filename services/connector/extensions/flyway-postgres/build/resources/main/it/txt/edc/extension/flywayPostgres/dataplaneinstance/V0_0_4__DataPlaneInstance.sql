CREATE TABLE IF NOT EXISTS edc_data_plane_instance
(
    id   VARCHAR NOT NULL,
    data JSON    NOT NULL,
    PRIMARY KEY (id)
);
