create table usuario (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    email CHARACTER VARYING(250) NOT NULL,
    nome CHARACTER VARYING(200) NOT NULL,
    senha CHARACTER VARYING(100) NOT NULL
);

INSERT INTO usuario (email, senha, nome) VALUES ('usuario@email.com', '$2a$10$ZiqZlo2WYftsg/1R/J77HuGV5eBhqzg3.kbJlnp1z2UMHgkMIxcwO', 'usuario');
INSERT INTO usuario (email, senha, nome) VALUES ('admin@email.com', '$2a$10$hHr8kI3BRHpweNJdn0Mg.e83mmZKkbzUf9twUbJ4cKmBZRNdf8QFK', 'admin');