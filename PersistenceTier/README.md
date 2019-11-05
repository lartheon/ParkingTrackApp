#The Persistence Tier (database)
The database is MySQL server which has all the SQL queries that creates the tables and values inside the tables.

##The Database
The database is made up of three different tables:
1. employees
2. vehicles
3. employees_vehicles

We need three tables as the relationship between the tables are many to many.
The table that joins the employees table and the vehicle table is the join table called employees_vehicles.

###How To Use This Database
First the backend (serverside) will need to make SQL Requests. Then the SQL from the database will return the data/values requested.
The backend will serve up JSON to the frontend.

Now the user will receive the output they've requested.
All this starts with the user input their request.

