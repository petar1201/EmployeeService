# EmployeeService
Employee is a MicroService for admins in some company, that is used to search data about employees, vacations, and used vacations and try to add new record of used vacation, this service will be used by employees.

## Getting Started
To use this service, you need to set up a PostgreSQL database with the name VacationTracker. The port should be 5432, the user should be postgres, and the password is "admin". Once the database is set up, the project is ready to be started.

## Starting the Application
This Service works on localhost:8082 and it is written in SpringBoot. You can run the application just by starting it in whichever working environment you are using.

Docker files are also provided, and this service can also be started on Docker.

## Endpoints
Services endpoints are:

1. /api/employee/usedVacations/new - Trying to add new record of UsedDays, request must be provided with Form parameters startDate and endDate(Strings in format dd yy mmmm).
2. /api/employee/usedVacations/get - returns a list of POJO objects representing usedVacations in provided Form parameters startDate and endDate
3. /api/employee/availableVacations - returns a POJO object representing how many days(total,used and available) Employee who sent the request has in each year
   All of the endpoints are protected with Basic Authenticatoin. So, when an employee makes a request, he need to send his credentials, and if they are good, he will access any endpoint in this Service.
## Author
petar3747@gmail.com