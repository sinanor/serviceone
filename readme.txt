System requirements: Java 8 JRE, Maven

Running The Application:

to run the application from maven

$mvn spring-boot:run

to create single runnable jar file

$mvn clean package   (this command will create a jar file named  target/serviceone-appointment-0.0.1-SNAPSHOT.jar)

after creating the fat jar the application can be run without maven as below

$java -jar target/serviceone-appointment-0.0.1-SNAPSHOT.jar

After you run the application embedded tomcat will open the 8080 port and ready to accept rest calls.

rest endpoint address: http://localhost:8080/api/optimizework

rest method is : POST

Example input

{ "rooms": [35, 21, 17], "seniorSpeed": 10, "juniorSpeed": 6 }

Example output

[
    {
        "seniorCount": 3,
        "juniorCount": 1
    },
    {
        "seniorCount": 1,
        "juniorCount": 2
    },
    {
        "seniorCount": 2,
        "juniorCount": 0
    }
]

To run Automated tests

$mvn test
