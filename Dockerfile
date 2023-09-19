FROM openjdk:17.0.1
COPY build/libs/decommerce-1.0.0.jar  /app/ecommerce-1.0.0.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app/ecommerce-1.0.0.jar"]
EXPOSE 8081
HEALTHCHECK CMD curl -f http://localhost:8081/actuator/health || exit 1