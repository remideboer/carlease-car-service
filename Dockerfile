FROM adoptopenjdk/openjdk11:latest as BUILDER
# copy stuff from project into container
COPY . /build
RUN cd build/ && ./mvnw clean package

FROM adoptopenjdk/openjdk11:latest as carlease-car-service
EXPOSE 9091
COPY --from=BUILDER build/target/car-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

