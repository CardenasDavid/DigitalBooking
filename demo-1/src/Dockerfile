FROM maven
COPY pom.xml /user/local/services/pom.xml
COPY src /user/local/services/src
WORKDIR /user/local/services
RUN mvn package
CMD ["java","-cp","target/nombredeljar.jar","com.nombreproy.app.app"]
