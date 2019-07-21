create table categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome CHARACTER VARYING(60) NOT NULL
);

INSERT INTO categoria (nome) VALUES ('Transporte');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Suprimentos');
INSERT INTO categoria (nome) VALUES ('Outros');