-- V1__TEST_CREATE_TABLE.sql

-- DROP TABLE IF EXISTS public.person;

CREATE TABLE IF NOT EXISTS public.person
(
    id SERIAL PRIMARY KEY,  -- Using SERIAL for auto-increment
    email VARCHAR(255),
    name VARCHAR(255),
    address VARCHAR(255),
    updated_time TIMESTAMP,
    created_time TIMESTAMP,
    is_active BOOLEAN
);
