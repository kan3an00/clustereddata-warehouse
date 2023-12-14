FROM openjdk:17-oracle

WORKDIR /app

COPY target/warehouse.jar /app/warehouse.jar

EXPOSE 8080

CMD ["java", "-jar", "warehouse.jar"]