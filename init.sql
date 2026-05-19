CREATE TABLE roles (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50)
);

INSERT INTO roles(name)
VALUES ('ADMIN'),
       ('USER');