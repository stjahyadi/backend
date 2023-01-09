CREATE TABLE IF NOT EXISTS product
(
    id                      BIGINT PRIMARY KEY generated always as identity (START WITH 300000001 INCREMENT BY 1),
    name                    VARCHAR(100) NOT NULL,
    qty                     NUMERIC(10, 0) NOT NULL,
    price                   NUMERIC(10, 0),
    image_url               VARCHAR(255),
    creation_timestamp      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_timestamp  TIMESTAMPTZ
);
