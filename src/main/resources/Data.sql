USE budget_app;
DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
                          id int AUTO_INCREMENT primary key,
                          name varchar(255),
                          password varchar(255),
                          created_at date,
                          created_by varchar(32)
);
INSERT INTO employee (name,password, created_at, created_by) VALUES ("Emma","KJ3tC7", now(), "CONSOLE");
INSERT INTO employee (name,password, created_at, created_by) VALUES ("Ulrika","waX4Yf", now(), "CONSOLE");
INSERT INTO employee (name,password, created_at, created_by) VALUES ("John","8tTHQC", now(), "CONSOLE");
INSERT INTO employee (name,password, created_at, created_by) VALUES ("James","RwWs4m", now(), "CONSOLE");

CREATE TABLE invoice (
                         id int AUTO_INCREMENT primary key,
                         title varchar(255),
                         category varchar(255),
                         invoicedate date,
                         description varchar(255),
                         amount int,
                         employeeid int,
                         INDEX par_ind (employeeid),
                         CONSTRAINT fk_employee FOREIGN KEY (employeeid)
                             REFERENCES employee(ID)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
);
INSERT INTO invoice (title,category, invoicedate, description,amount,employeeid) VALUES ("Title001","Travel", now(), "Traveled to Stockholm",1000,1);
INSERT INTO invoice (title,category, invoicedate, description,amount,employeeid) VALUES ("Title002","Others", now(), "Traveled to Stockholm",2000,1);
INSERT INTO invoice (title,category, invoicedate, description,amount,employeeid) VALUES ("Title003","Travel", now(), "Traveled to Stockholm",5000,1);