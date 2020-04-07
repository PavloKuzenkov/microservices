FROM java:8-jdk-alpine
COPY ./target/eureka.jar /usr/app/
WORKDIR /usr/app
#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "eureka.jar"]
