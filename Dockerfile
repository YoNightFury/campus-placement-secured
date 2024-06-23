FROM node:16-buster-slim as react-build

WORKDIR /reactapp
COPY placement-portal-front/package.json ./
COPY placement-portal-front/package-lock.json ./
RUN npm i
COPY placement-portal-front/src ./src
COPY placement-portal-front/public ./public/
RUN npm run build

FROM eclipse-temurin:11-jdk-focal as spring-build
WORKDIR /springapp
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN sh ./mvnw dependency:go-offline
COPY src ./src
COPY --from=react-build /reactapp/build/ ./src/main/resources/static/
RUN  sh ./mvnw -e package -DskipTests=true

# # Create a custom Java runtime
# RUN $JAVA_HOME/bin/jlink \
#     --add-modules java.base \
#     --strip-debug \
#     --no-man-pages \
#     --no-header-files \
#     --compress=2 \
#     --output /javaruntime

# # Define your base image
# FROM debian:buster-slim
# ENV JAVA_HOME=/opt/java/openjdk
# ENV PATH "${JAVA_HOME}/bin:${PATH}"
# COPY --from=spring-build /javaruntime $JAVA_HOME
# COPY --from=spring-build /springapp/target/ /app

# WORKDIR /app
FROM eclipse-temurin:11-jdk-focal as app
WORKDIR /app
COPY --from=spring-build /springapp/target/ ./

CMD java -jar CampusPlacementPortal*.jar
