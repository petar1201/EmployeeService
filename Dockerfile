FROM openjdk:17
EXPOSE 8082
ADD target/EmployeeService-0.0.1-SNAPSHOT.jar employee-service.jar
ENTRYPOINT ["java","-jar","/employee-service.jar"]