# Engineering Test
Spring Boot Microservice for Catalytic

Instructions for Running (requires Docker to be installed)

Run the following commands in terminal from root directory of  the "CatalyticEngineeringTest-master" folder

   1.  Start the docker machine  
     *  ```docker-machine start ```  
   2.  Ensure terminal window is refreshed and has the docker-machine env variables  
     *  ```eval $(docker-machine env) ```  
   3.  Pull an existing mysql docker image from docker hub and configures database information the app will need  
     *  ```docker run -d -e MYSQL_ROOT_PASSWORD=catalytic -e MYSQL_DATABASE=pyouells_test -p 3306:3306 --name mysql_test mysql:latest```  
   4.  Check the logs for the MySql docker image to make sure it's running  
     *  ```docker logs mysql_test```
   5.  Build the gradle project (including junit and integration tests) and builds the docker image  
     *  ```./gradlew buildDocker```  
   6.  Run the docker container on port 8080 of the docker machine and link the mysql docker container 
     *  ```docker run -p 8080:8080 --name engineering_test_microservice --link mysql_test:mysql -d pyouells/engineering-test /bin/bash```  
   7.  Check the logs for the Spring Boot docker image to make sure it's running  
     *  ```docker logs engineering_test_microservice```
   8.  Verify the docker machine's IP address is 192.168.99.100
     *  ```docker-machine ip```
   9.  Navigate to the Swagger UI Console to test the different controllers in the Microservice (If the IP address from the previous command is different than 192.168.99.100, replace the host name with your docker-machine's IP address)
     *  http://192.168.99.100:8080/swagger-ui.html
