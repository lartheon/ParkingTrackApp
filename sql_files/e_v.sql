CREATE TABLE employees_vehicles (
      employee_id INT NOT NULL REFERENCES employees(employee_id)
    , vehicle_id INT NOT NULL REFERENCES vehicles(vehicle_id)
    , PRIMARY KEY (employee_id, vehicle_id)
);