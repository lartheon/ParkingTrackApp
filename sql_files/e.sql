CREATE TABLE employees (
      employee_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT
    , permit_number INT NOT NULL
    , first_name TEXT
    , last_name TEXT
    , skype_id VARCHAR (256)
    , email VARCHAR (254)
    , dept TEXT
);