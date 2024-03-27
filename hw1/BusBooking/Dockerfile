FROM maven:3.8.5-openjdk-17

WORKDIR /BusBooking

COPY . .

RUN mvn install -DskipTests

CMD mvn spring-boot:run