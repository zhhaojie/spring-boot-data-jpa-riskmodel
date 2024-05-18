FROM openjdk:17-bullseye

MAINTAINER zhou

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PROFILES="default"

ADD /target/*.jar /opt/risk_app/riskModel.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /opt/risk_app/riskModel.jar --spring.profiles.active=$PROFILES"]

EXPOSE 8080