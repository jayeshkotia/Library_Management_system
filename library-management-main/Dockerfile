# FROM openjdk:8
# EXPOSE 8080
# COPY target/spring-boot-first-web-application-git-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

# From tomcat:8-jre8
# COPY target/todo.jar /usr/local/tomcat/webapps
# RUN cp -R /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps

# FROM openjdk:17-oracle

# ARG JAR_FILE=target/librarymanagementsystem-0.0.1-SNAPSHOT.jar
# # Add the application's jar to the container
# ADD ${JAR_FILE} application.jar
# EXPOSE 9080
# CMD [ "java","-version" ]
# ENTRYPOINT ["java","-jar","application.jar"]

#if not workin with above one use this 
FROM openjdk:17-slim
COPY target/librarymanagementsystem-0.0.1-SNAPSHOT.jar .
EXPOSE 9080
ENTRYPOINT ["java","-jar","librarymanagementsystem-0.0.1-SNAPSHOT.jar"]

