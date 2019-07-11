CREATE TABLE perfil (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nome CHARACTER VARYING(100) NOT NULL
);

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    email CHARACTER VARYING(250) NOT NULL,
    nome CHARACTER VARYING(200) NOT NULL,
    senha CHARACTER VARYING(100) NOT NULL,
    id_perfil BIGINT NOT NULL,
    CONSTRAINT fk_perfil_u FOREIGN KEY (id_perfil) REFERENCES perfil(id)
);

CREATE TABLE permissao (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    permissao CHARACTER VARYING(100) NOT NULL
);

CREATE TABLE perfil_permissao (
	id_perfil BIGINT NOT NULL,
	id_permissao BIGINT NOT NULL,
	CONSTRAINT pk_perfil_permissao PRIMARY KEY (id_perfil, id_permissao),
	CONSTRAINT fk_perfil_pp FOREIGN KEY (id_perfil) REFERENCES perfil(id),
	CONSTRAINT fk_permissao_pp FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);

INSERT INTO permissao (permissao) VALUES ('CATEGORIA_CONSULTAR');
INSERT INTO permissao (permissao) VALUES ('CATEGORIA_ALTERAR');
INSERT INTO permissao (permissao) VALUES ('USUARIO_CONSULTAR');
INSERT INTO permissao (permissao) VALUES ('USUARIO_ALTERAR');

INSERT INTO perfil (nome) VALUES ('USER');
INSERT INTO perfil (nome) VALUES ('ADMIN');

INSERT INTO perfil_permissao (id_perfil, id_permissao) VALUES (
	(SELECT id FROM perfil p WHERE p.nome = 'USER'),
	(SELECT id FROM permissao s WHERE s.permissao = 'CATEGORIA_CONSULTAR')
);

INSERT INTO perfil_permissao (id_perfil, id_permissao) VALUES (
	(SELECT id FROM perfil p WHERE p.nome = 'ADMIN'),
	(SELECT id FROM permissao s WHERE s.permissao = 'CATEGORIA_CONSULTAR')
);
INSERT INTO perfil_permissao (id_perfil, id_permissao) VALUES (
	(SELECT id FROM perfil p WHERE p.nome = 'ADMIN'),
	(SELECT id FROM permissao s WHERE s.permissao = 'CATEGORIA_ALTERAR')
);
INSERT INTO perfil_permissao (id_perfil, id_permissao) VALUES (
	(SELECT id FROM perfil p WHERE p.nome = 'ADMIN'),
	(SELECT id FROM permissao s WHERE s.permissao = 'USUARIO_CONSULTAR')
);
INSERT INTO perfil_permissao (id_perfil, id_permissao) VALUES (
	(SELECT id FROM perfil p WHERE p.nome = 'ADMIN'),
	(SELECT id FROM permissao s WHERE s.permissao = 'USUARIO_ALTERAR')
);


INSERT INTO usuario (email, senha, nome, id_perfil) VALUES ('usuario@email.com', /* usuario123 */ '$2a$10$vg7c5ezwykIr5lWG7Rly9uGQFNOtdaXrEZMz9s64mP.ivmalKwUuC', 'Usuário'
    , (SELECT id FROM perfil p WHERE p.nome = 'USER')
);

INSERT INTO usuario (email, senha, nome, id_perfil) VALUES ('admin@email.com', /* admin123 */ '$2a$10$hHr8kI3BRHpweNJdn0Mg.e83mmZKkbzUf9twUbJ4cKmBZRNdf8QFK', 'Administrador'
    , (SELECT id FROM perfil p WHERE p.nome = 'ADMIN')
);