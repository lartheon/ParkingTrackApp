-- To create vehicles table with PRIMARY KEY that auto increments 

CREATE TABLE vehicles (
    vehicle_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT
    , reg_number VARCHAR (11)
    , make TEXT
    , model TEXT
    , colour TEXT
);

