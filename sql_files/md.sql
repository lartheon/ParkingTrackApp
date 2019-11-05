
INSERT INTO employees (
    permit_number, first_name, last_name, skype_id, email, dept
) VALUES
(244, "Kara", "Brown", "live.kara.brown", "kara@raytheon.co.uk", "Engineering"),
(252, "Vanessa", "Tsang", "live.vanessa.tsang", "vanessa@raytheon.co.uk", "Engineering"),
(252, "Paul", "White", "live.paul.white", "paul@raytheon.co.uk", "Finance"),
(363, "Jess", "Kit", "live.jess.kit", "jess@raytheon.co.uk", "HR");




INSERT INTO vehicles (
    reg_number, make, model, colour
) VALUES
("BV10 SEP", "Honda", "Jazz", "Red"),
("DB51 SMR", "BMW", "Z3", "Silver"),
("OP65 AWR", "Ford", "Focus", "Black"),
("GR22 SMX", "VW", "Polo", "Blue");



INSERT INTO employees_vehicles (
    employee_id, vehicle_id
) VALUES
(
    (SELECT employee_id FROM employees WHERE permit_number = 244 LIMIT 1)
    , (SELECT vehicle_id FROM vehicles WHERE make = "Honda" LIMIT 1)
),
(
    (SELECT employee_id FROM employees WHERE permit_number = 244 LIMIT 1)
    , (SELECT vehicle_id FROM vehicles WHERE make = "Ford" LIMIT 1)
),
(
    (SELECT employee_id FROM employees WHERE permit_number = 252 AND first_name = "Vanessa" LIMIT 1)
    , (SELECT vehicle_id FROM vehicles WHERE make = "BMW" LIMIT 1)
),
(
    (SELECT employee_id FROM employees WHERE permit_number = 252 AND first_name = "Paul" LIMIT 1)
    , (SELECT vehicle_id FROM vehicles WHERE make = "BMW" LIMIT 1)
),
(
    (SELECT employee_id FROM employees WHERE permit_number = 363 LIMIT 1)
    , (SELECT vehicle_id FROM vehicles WHERE make = "VW" LIMIT 1)
);