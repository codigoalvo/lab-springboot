create table categoria (
    id BIGSERIAL NOT NULL,
    nome CHARACTER VARYING(60) NOT NULL,

    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_categoria_id PRIMARY KEY (id)
);

INSERT INTO categoria (nome) VALUES ('Transporte');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Suprimentos');
INSERT INTO categoria (nome) VALUES ('Outros');