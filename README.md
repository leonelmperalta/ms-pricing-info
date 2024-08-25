
# ms-pricing-info

**Pricing info** is a versatile *Spring Boot* application that offers a comprehensive API for efficient price queries. Developed using the Hexagonal (Ports & Adapters) architecture, it ensures scalability, adaptability, and ease of transitioning between different adapters (e.g., switching from MongoDB to PostgreSQL).

## Stack

This project leverages Java 17 and Maven. The primary framework is Spring Boot, accompanied by Spring Data, Spring Web, Spring Validation, Spring Test, and SpringDoc. Additionally, it utilizes an in-memory H2 database and Flyway for seamless database migrations. Unit and Integration tests are implemented using JUnit and Mockito.

## How to Run

To run this project, follow these steps:

1. Install Java 17 or a later version from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

2. Install Maven if not already installed. Download it from [Maven](https://maven.apache.org/download.cgi) and follow the installation instructions [here](https://maven.apache.org/install.html).

3. Clone this repository.

   ```bash
   git clone https://github.com/leonelmperalta/ms-pricing-info.git
   ```

4. Customize database configuration by navigating to:

   ```
   ms-pricing-info\src\main\resources\application.properties
   ```

   Modify the following properties as needed:

   ```properties
   spring.datasource.url=jdbc:h2:mem:database  # Database URL
   spring.datasource.username=user               # Database username
   spring.datasource.password=password           # Database password
   spring.h2.console.enabled=true                 # Enable H2 console in the browser
   ```

   Ensure that any modifications to the datasource options are reflected in the `pom.xml` file under:

   ```
   ms-pricing-info\pom.xml
   ```

   Adjust the URL, user, password, and schema properties accordingly:

   ```xml
   <plugin>
       <groupId>org.flywaydb</groupId>
       <artifactId>flyway-maven-plugin</artifactId>
       <version>10.8.1</version>
       <configuration>
           <url>jdbc:h2:mem:database</url>
           <user>user</user>
           <password>password</password>
           <schemas>
               <schema>ecommerce</schema>
           </schemas>
       </configuration>
   </plugin>
   ```

5. In the project root folder, open a terminal and run:

   ```bash
   mvn spring-boot:run
   ```

6. The application is now ready to use.

## How to Use the Project

Once the application is running, the server will be accessible on port 8080. The OpenAPI 3.0 documentation can be found at:

```
/swagger-ui/index.html
```
Additionally, the database console allows direct operations in the browser. Access it at:

```
/h2-console
```