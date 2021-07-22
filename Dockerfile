FROM maven:3.6.0-jdk-11
RUN mkdir -p /code
ADD . /code/
WORKDIR /code/
RUN mvn clean install
EXPOSE 6080
ENTRYPOINT [ "java", "-jar", "/code/target/banking-0.0.1-SNAPSHOT.jar" ]
