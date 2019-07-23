CREATE TABLE security_user (
    id BIGSERIAL NOT NULL,
    email CHARACTER VARYING(250) NOT NULL,
    password CHARACTER VARYING(250) NOT NULL,
    name CHARACTER VARYING(150),

    telefone CHARACTER VARYING(25),

    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_security_user_id PRIMARY KEY (id),
    CONSTRAINT uk_security_user_email UNIQUE (email)
);

CREATE TABLE security_profile (
    id BIGSERIAL NOT NULL,
    name CHARACTER VARYING(100) NOT NULL,

    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_security_profile_id PRIMARY KEY (id),
    CONSTRAINT uk_security_profile_name UNIQUE (name)
);

CREATE TABLE security_authority (
    id BIGSERIAL NOT NULL,
    name CHARACTER VARYING(150) NOT NULL,

    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_security_authority_id PRIMARY KEY (id),
    CONSTRAINT uk_security_authority_name UNIQUE (name)
);

CREATE TABLE security_user_profile (
	id_user BIGINT NOT NULL,
	id_profile BIGINT NOT NULL,

	CONSTRAINT pk_security_user_profile PRIMARY KEY (id_user, id_profile),
	CONSTRAINT fk_security_user_sup FOREIGN KEY (id_user) REFERENCES security_user(id),
	CONSTRAINT fk_security_profile_sup FOREIGN KEY (id_profile) REFERENCES security_profile(id)
);

CREATE TABLE security_profile_authority (
	id_profile BIGINT NOT NULL,
	id_authority BIGINT NOT NULL,

	CONSTRAINT pk_security_profile_authority PRIMARY KEY (id_profile, id_authority),
	CONSTRAINT fk_security_profile_spa FOREIGN KEY (id_profile) REFERENCES security_profile(id),
	CONSTRAINT fk_security_authority_spa FOREIGN KEY (id_authority) REFERENCES security_authority(id)
);

INSERT INTO security_user (email, name, password) VALUES ('admin@domain.com', 'Administrador', /* admin123 */ '$2a$10$hHr8kI3BRHpweNJdn0Mg.e83mmZKkbzUf9twUbJ4cKmBZRNdf8QFK');
INSERT INTO security_user (email, name, password) VALUES ('usuario@domain.com', 'Usuário', /* usuario123 */ '$2a$10$vg7c5ezwykIr5lWG7Rly9uGQFNOtdaXrEZMz9s64mP.ivmalKwUuC');

INSERT INTO security_profile (name) VALUES ('USU');
INSERT INTO security_profile (name) VALUES ('ADM');

INSERT INTO security_authority (name) VALUES ('CATEGORIA_CONSULTAR');
INSERT INTO security_authority (name) VALUES ('CATEGORIA_ALTERAR');
INSERT INTO security_authority (name) VALUES ('USUARIO_CONSULTAR');
INSERT INTO security_authority (name) VALUES ('USUARIO_ALTERAR');

INSERT INTO security_user_profile (id_user, id_profile) VALUES (
	(SELECT id FROM security_user u WHERE u.email = 'usuario@domain.com'),
	(SELECT id FROM security_profile p WHERE p.name = 'USU')
);

INSERT INTO security_user_profile (id_user, id_profile) VALUES (
	(SELECT id FROM security_user u WHERE u.email = 'admin@domain.com'),
	(SELECT id FROM security_profile p WHERE p.name = 'USU')
);

INSERT INTO security_user_profile (id_user, id_profile) VALUES (
	(SELECT id FROM security_user u WHERE u.email = 'admin@domain.com'),
	(SELECT id FROM security_profile p WHERE p.name = 'ADM')
);

INSERT INTO security_profile_authority (id_profile, id_authority) VALUES (
	(SELECT id FROM security_profile p WHERE p.name = 'USU'),
	(SELECT id FROM security_authority s WHERE s.name = 'CATEGORIA_CONSULTAR')
);

INSERT INTO security_profile_authority (id_profile, id_authority) VALUES (
	(SELECT id FROM security_profile p WHERE p.name = 'ADM'),
	(SELECT id FROM security_authority s WHERE s.name = 'CATEGORIA_ALTERAR')
);
INSERT INTO security_profile_authority (id_profile, id_authority) VALUES (
	(SELECT id FROM security_profile p WHERE p.name = 'ADM'),
	(SELECT id FROM security_authority s WHERE s.name = 'USUARIO_CONSULTAR')
);
INSERT INTO security_profile_authority (id_profile, id_authority) VALUES (
	(SELECT id FROM security_profile p WHERE p.name = 'ADM'),
	(SELECT id FROM security_authority s WHERE s.name = 'USUARIO_ALTERAR')
);
