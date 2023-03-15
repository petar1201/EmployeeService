# EmployeeService
Employee is a MicroService for admins in some company, that is used to search data about employees, vacations, and used vacations and try to add new record of used vacation, this service will be used by employees.

## Getting Started
To use this service, you need to set up a PostgreSQL database with the name VacationTracker. The port should be 5432, the user should be "postgres", and the password is "admin". Once the database is set up, the project is ready to be started.

## Starting the Application
This Service works on localhost:8082 and it is written in SpringBoot. You can run the application just by starting it in whichever working environment you are using.since project is using JDK17 it is needed to have it installed. For full functionalities of the application AdminService should also be started(localhost:8080).

For easier testing Docker files are also provided, and this service(applocation) can also be started on Docker. And there are instructions for that:
1. As i already said it is nedeed to make database in a way described in previous section.
2. Here is the link ${link} to download target folder with jar file needed to start docker and add that folder to project folder (git refuesed to push files becaouse of their size), same needs to be done in AdminService and link for that is provided in AdminService/readme.md, if target folder and jar in it are available to you without downloading it in here, you do not need to download it in this step and can just skip it
3. Projects AdminService and EmployeeService need to be in a same directory in order to simplify running it on Docker(this can also be resolved for example with Environment Variables but in terms of making running it on Docker as simple as it can be, just put both projects in a same directory)
4. Download and open DockerDesktop app
5. Navigate to AdminService(docker-compose.yml is here) and run this command: docker-compose up
6. That should create Docker Images and Container
7. Go to your DockerDesktop app where you can now see both container and images and you can start or stop services.
8. That should be all and you shall go to for example Postman and use the application with endpoints described


## Endpoints
Services endpoints are:

1. /api/employee/usedVacations/new - Trying to add new record of UsedDays, request must be provided with Form parameters startDate and endDate(Strings in format dd yy mmmm).
2. /api/employee/usedVacations/get - returns a list of POJO objects representing usedVacations in provided Form parameters startDate and endDate
3. /api/employee/availableVacations - returns a POJO object representing how many days(total,used and available) Employee who sent the request has in each year
   All of the endpoints are protected with Basic Authenticatoin. So, when an employee makes a request, he need to send his credentials, and if they are good, he will access any endpoint in this Service.
## Author
petar3747@gmail.com
