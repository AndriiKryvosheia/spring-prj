CREATE TABLE users (
    id bigint NOT NULL AUTO_INCREMENT,
    login varchar(255) NOT NULL unique,
    email varchar(255) NOT NULL unique,
    password varchar(255) NOT NULL,
    registered_on datetime default NOW(),
    PRIMARY KEY (id)
);
