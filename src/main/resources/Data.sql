CREATE DATABASE budget_app;
USE budget_app;
DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
                       id int AUTO_INCREMENT primary key,
                       name varchar(255),
                       created_at date,
                       created_by varchar(32)
);
INSERT INTO employee (name, created_at, created_by) VALUES ("Emma", now(), "CONSOLE");
INSERT INTO employee (name, created_at, created_by) VALUES ("Ulrika", now(), "CONSOLE");
INSERT INTO employee (name, created_at, created_by) VALUES ("John", now(), "CONSOLE");
INSERT INTO employee (name, created_at, created_by) VALUES ("James", now(), "CONSOLE");