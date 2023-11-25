DROP TABLE IF EXISTS plants;
DROP TABLE IF EXISTS hydration_levels;
DROP TABLE IF EXISTS sunlight_levels;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id SERIAL NOT NULL,
    role_code VARCHAR(15) NOT NULL,
    role_name VARCHAR(40) NOT NULL,
    CONSTRAINT roles_role_code UNIQUE (role_code),
    CONSTRAINT roles_role_name UNIQUE (role_name),
    CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE TABLE accounts (
	id SERIAL NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	role_id INTEGER NOT NULL,
	CONSTRAINT accounts_email UNIQUE (email),
	CONSTRAINT accounts_role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id),
	CONSTRAINT accounts_pk PRIMARY KEY (id)
);

CREATE TABLE hydration_levels (
	id SERIAL NOT NULL,
	hydration_code VARCHAR(4) NOT NULL,
	hydration_name VARCHAR(40) NOT NULL,
	logical_order SMALLINT NOT NULL,
	CONSTRAINT hydration_levels_hydration_code UNIQUE (hydration_code),
	CONSTRAINT hydration_levels_hydration_name UNIQUE (hydration_name),
	CONSTRAINT hydration_levels_pk PRIMARY KEY (id)
);

CREATE TABLE sunlight_levels (
	id SERIAL NOT NULL,
	sunlight_code VARCHAR(4) NOT NULL,
	sunlight_name VARCHAR(40) NOT NULL,
	logical_order SMALLINT NOT NULL,
	CONSTRAINT sunlight_levels_sunlight_code UNIQUE (sunlight_code),
	CONSTRAINT sunlight_levels_sunlight_name UNIQUE (sunlight_name),
	CONSTRAINT sunlight_levels_pk PRIMARY KEY (id)
);

CREATE TABLE plants (
	id SERIAL NOT NULL,
	common_name VARCHAR(100) UNIQUE NOT NULL,
	latin_name VARCHAR(200) UNIQUE NOT NULL,
	description VARCHAR(1000) NOT NULL,
	image VARCHAR(300) UNIQUE NOT NULL,
	hydration_id INTEGER NOT NULL,
	sunlight_id INTEGER NOT NULL,
    added_at DATE NOT NULL,
	CONSTRAINT hydration_levels_hydration_id_fk FOREIGN KEY (hydration_id) REFERENCES hydration_levels(id),
	CONSTRAINT sunlight_levels_sunlight_id_fk FOREIGN KEY (sunlight_id) REFERENCES sunlight_levels(id),
    CONSTRAINT plants_pk PRIMARY KEY (id)
);
