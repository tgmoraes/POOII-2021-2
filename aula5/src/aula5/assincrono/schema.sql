--CREATE DATABASE teste
--DROP TABLE empregrado
CREATE TABLE empregado(
    id SERIAL,
    nome varchar(100) NOT NULL,
    email varchar(100)  NOT NULL,
    salario numeric(8,2),
    datanasc date,
    PRIMARY KEY (id),
    UNIQUE (email)
)

