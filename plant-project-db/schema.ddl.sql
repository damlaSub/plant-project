DROP TABLE IF EXISTS plants;
DROP TABLE IF EXISTS hydration_levels;
DROP TABLE IF EXISTS sunlight_levels;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;


CREATE TABLE roles (
    id SERIAL,
    role_name VARCHAR(100) NOT NULL,
    constraint pk_role_id primary key(id),
    UNIQUE (role_name)
);

CREATE TABLE users (
	id SERIAL,
	first_name VARCHAR(250) NOT NULL,
	last_name VARCHAR(250) NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(250) NOT NULL,
	constraint pk_users_id primary key(id),
	role_id INTEGER REFERENCES roles(id) NOT NULL,
	UNIQUE (email)
);

CREATE TABLE hydration_levels (
	id SERIAL PRIMARY KEY,
	hydration_code VARCHAR(5) NOT NULL,
	hydration_name VARCHAR(40) UNIQUE NOT NULL,
	logical_order SMALLINT NOT NULL,
	UNIQUE(hydration_name, hydration_code)
);

CREATE TABLE sunlight_levels (
	id SERIAL PRIMARY KEY,
	sunlight_code VARCHAR(5) NOT NULL,
	sunlight_name VARCHAR(40) UNIQUE NOT NULL,
	logical_order SMALLINT NOT NULL,
	UNIQUE(sunlight_code, sunlight_name)
);

CREATE TABLE plants (
	id SERIAL PRIMARY KEY,
	common_name VARCHAR(100) UNIQUE NOT NULL,
	latin_name VARCHAR(200) UNIQUE NOT NULL,
	description VARCHAR(1000) NOT NULL,
	image VARCHAR(300) UNIQUE NOT NULL,
	hydration_id INTEGER REFERENCES hydration_levels(id) NOT NULL,
	sunlight_id INTEGER REFERENCES sunlight_levels(id) NOT NULL,
    added_at DATE NOT NULL
);
