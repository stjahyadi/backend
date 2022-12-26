CREATE TABLE IF NOT EXISTS product
(
    id                      NUMERIC(10, 0) PRIMARY KEY,
    name                    VARCHAR(100) NOT NULL,
    qty                     NUMERIC(10, 0) NOT NULL,
    price                   NUMERIC(10, 0),
    image_url               VARCHAR(255),
    creation_timestamp      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_timestamp  TIMESTAMPTZ
);
