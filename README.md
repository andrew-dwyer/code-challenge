aligent-code-challenge
======================

Requirements
------------

JDK 5+
Maven 3

Build Instructions
------------------

# Build, Install & Test
mvn clean install

# Create a self contained JAR with demo client  
mvn compile assembly:single

Run Sample Client
-----------------

java -jar target/date-tool-1.0-jar-with-dependencies.jar

Build Javadocs
--------------

# Run command
mvn javadoc:javadoc
# Javadocs will be created in date-tool\target\site\apidocs

Use Library
-----------

See javadocs
