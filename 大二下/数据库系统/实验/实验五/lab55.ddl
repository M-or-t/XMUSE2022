-- 生成者Oracle SQL Developer Data Modeler 17.2.0.188.1059
--   时间:        2024-05-15 19:26:16 CST
--   站点:      Oracle Database 12c
--   类型:      Oracle Database 12c



CREATE TABLE actor (
    name                   VARCHAR2(50) NOT NULL,
    date_of_birth          DATE NOT NULL,
    film_title             VARCHAR2(100) NOT NULL,
    film_year_of_release   DATE NOT NULL,
    role                   VARCHAR2(50) NOT NULL
);

ALTER TABLE actor ADD CONSTRAINT actor_pk PRIMARY KEY ( name );

CREATE TABLE director (
    name            VARCHAR2(50) NOT NULL,
    date_of_birth   DATE NOT NULL
);

ALTER TABLE director ADD CONSTRAINT director_pk PRIMARY KEY ( name );

CREATE TABLE film (
    title                    VARCHAR2(100) NOT NULL,
    year_of_release          DATE NOT NULL,
    length                   NUMBER NOT NULL,
    plot                     VARCHAR2(1000) NOT NULL,
    productioncompany_name   VARCHAR2(50) NOT NULL
);

ALTER TABLE film ADD CONSTRAINT film_pk PRIMARY KEY ( title,year_of_release );

CREATE TABLE film_actor (
    film_title             VARCHAR2(100) NOT NULL,
    film_year_of_release   DATE NOT NULL,
    actor_name             VARCHAR2(50) NOT NULL,
    role                   VARCHAR2(50) NOT NULL
);

ALTER TABLE film_actor
    ADD CONSTRAINT film_actor_pk PRIMARY KEY ( film_title,film_year_of_release,actor_name );

CREATE TABLE film_director (
    film_title             VARCHAR2(100) NOT NULL,
    film_year_of_release   DATE NOT NULL,
    director_name          VARCHAR2(50) NOT NULL
);

ALTER TABLE film_director
    ADD CONSTRAINT film_director_pk PRIMARY KEY ( film_title,film_year_of_release,director_name );

CREATE TABLE filmgenre (
    film_title             VARCHAR2(100) NOT NULL,
    film_year_of_release   DATE NOT NULL,
    genre_genreid          INTEGER NOT NULL
);

ALTER TABLE filmgenre
    ADD CONSTRAINT filmgenre_pk PRIMARY KEY ( film_title,film_year_of_release,genre_genreid );

CREATE TABLE genre (
    genreid     INTEGER NOT NULL,
    genrename   VARCHAR2(4000) NOT NULL
);

ALTER TABLE genre ADD CONSTRAINT genre_pk PRIMARY KEY ( genreid );

CREATE TABLE productioncompany (
    name      VARCHAR2(50) NOT NULL,
    address   VARCHAR2(100) NOT NULL
);

ALTER TABLE productioncompany ADD CONSTRAINT productioncompany_pk PRIMARY KEY ( name );

CREATE TABLE quote (
    quoteid                VARCHAR2(100) NOT NULL,
    content                VARCHAR2(500) NOT NULL,
    film_title             VARCHAR2(100) NOT NULL,
    film_year_of_release   DATE NOT NULL,
    actor_name             VARCHAR2(50) NOT NULL
);

ALTER TABLE quote ADD CONSTRAINT quote_pk PRIMARY KEY ( quoteid );

ALTER TABLE actor
    ADD CONSTRAINT actor_film_fk FOREIGN KEY ( film_title,film_year_of_release )
        REFERENCES film ( title,year_of_release );

ALTER TABLE film_actor
    ADD CONSTRAINT film_actor_actor_fk FOREIGN KEY ( actor_name )
        REFERENCES actor ( name );

ALTER TABLE film_actor
    ADD CONSTRAINT film_actor_film_fk FOREIGN KEY ( film_title,film_year_of_release )
        REFERENCES film ( title,year_of_release );

ALTER TABLE film_director
    ADD CONSTRAINT film_director_director_fk FOREIGN KEY ( director_name )
        REFERENCES director ( name );

ALTER TABLE film_director
    ADD CONSTRAINT film_director_film_fk FOREIGN KEY ( film_title,film_year_of_release )
        REFERENCES film ( title,year_of_release );

ALTER TABLE film
    ADD CONSTRAINT film_productioncompany_fk FOREIGN KEY ( productioncompany_name )
        REFERENCES productioncompany ( name );

ALTER TABLE filmgenre
    ADD CONSTRAINT filmgenre_film_fk FOREIGN KEY ( film_title,film_year_of_release )
        REFERENCES film ( title,year_of_release );

ALTER TABLE filmgenre
    ADD CONSTRAINT filmgenre_genre_fk FOREIGN KEY ( genre_genreid )
        REFERENCES genre ( genreid );

ALTER TABLE quote
    ADD CONSTRAINT quote_actor_fk FOREIGN KEY ( actor_name )
        REFERENCES actor ( name );

ALTER TABLE quote
    ADD CONSTRAINT quote_film_fk FOREIGN KEY ( film_title,film_year_of_release )
        REFERENCES film ( title,year_of_release );



-- Oracle SQL Developer Data Modeler 概要报告: 
-- 
-- CREATE TABLE                             9
-- CREATE INDEX                             0
-- ALTER TABLE                             19
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
