DROP TABLE IF EXISTS account_plants CASCADE;
DROP TABLE IF EXISTS plants CASCADE;
DROP TABLE IF EXISTS hydration_levels CASCADE;
DROP TABLE IF EXISTS sunlight_levels CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;

CREATE TABLE accounts (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL,
	role VARCHAR(11) NOT NULL
);

CREATE TABLE hydration_levels (
	id SERIAL PRIMARY KEY,
	hydration_code VARCHAR(4) UNIQUE,
	hydration_name VARCHAR(40) UNIQUE,
	logical_order SMALLINT NOT NULL
);

CREATE TABLE sunlight_levels (
	id SERIAL PRIMARY KEY,
	sunlight_code VARCHAR(4) UNIQUE,
	sunlight_name VARCHAR(40) UNIQUE,
	logical_order SMALLINT NOT NULL
);

CREATE TABLE plants (
	id SERIAL PRIMARY KEY,
	plant_code VARCHAR UNIQUE NOT NULL,
	common_name VARCHAR(100) UNIQUE NOT NULL,
	latin_name VARCHAR(200) UNIQUE NOT NULL,
	description VARCHAR(1000),
	image VARCHAR(300) UNIQUE NOT NULL,
	hydration_id INTEGER NOT NULL REFERENCES hydration_levels(id),
	sunlight_id INTEGER NOT NULL REFERENCES sunlight_levels(id),
    added_at DATE NOT NULL
);

CREATE TABLE account_plants (
	id SERIAL PRIMARY KEY,
	account_id INTEGER NOT NULL REFERENCES accounts(id),
	plant_id INTEGER NOT NULL REFERENCES plants(id),
	UNIQUE(account_id, plant_id)
);

CREATE SEQUENCE IF NOT EXISTS plants_plant_code_seq
    START WITH 9
    OWNED BY plants.plant_code;
