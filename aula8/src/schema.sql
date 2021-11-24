--CREATE DATABASE teste
--DROP TABLE contato
--DROP TABLE grupo
CREATE TABLE contato
(   id serial,
    nome character varying(100) ,
    email character varying(150) ,
    telefone character(15) ,
    datanasc date,
    CONSTRAINT contato_pkey PRIMARY KEY (id)
);


CREATE TABLE grupo(
	id serial PRIMARY KEY,
    nome varchar(50) UNIQUE NOT NULL
);
	
ALTER TABLE contato ADD COLUMN idgrupo int;

ALTER TABLE contato ADD CONSTRAINT "grupoFK" FOREIGN KEY (idgrupo) REFERENCES grupo(id) 
 ON DELETE SET NULL  ON UPDATE CASCADE;
