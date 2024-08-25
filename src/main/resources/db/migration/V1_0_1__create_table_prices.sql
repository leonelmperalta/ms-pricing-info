CREATE SCHEMA IF NOT EXISTS ecommerce;

CREATE TABLE IF NOT EXISTS ecommerce.PRICES (
     id BIGINT PRIMARY KEY,
     application_end_date TIMESTAMP,
     application_start_date TIMESTAMP,
     brand_id BIGINT,
     currency VARCHAR(255),
     fee_id BIGINT,
     final_price DECIMAL(19, 2),
     priority INT,
     product_id BIGINT
);