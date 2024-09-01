FROM gradle:8-jdk17 AS build

COPY . /app
WORKDIR /app/launchers/connector
RUN gradle build

FROM eclipse-temurin:17-jre-alpine AS jar

RUN adduser -D -H -s /sbin/nologin edc
USER root
RUN apk update && apk add whois
RUN apk update && apk add grep
USER edc:edc

# Copy the generated jar into the container
WORKDIR /app
COPY --from=build --chown=edc:edc /app/launchers/connector/build/libs/app.jar ./app.jar
COPY --from=build --chown=edc:edc /app/launchers/.production.env ./.env

COPY ./new-data-certs/connectorB.jks /connectorB.jks
COPY ./launchers/cert.pfx /cert.pfx
COPY ./launchers/provider-vault.properties /provider-vault.properties

RUN touch /app/emtpy-properties-file.properties

ENV JVM_ARGS=""

RUN sed -ri 's/^\s*(\S+)=(.*)$/\1=${\1:-"\2"}/' .env

COPY --from=build /app/banner.txt /etc/banner.txt

# Command that gets executed for this image
ENTRYPOINT cat /etc/banner.txt && sleep 2 && set -a && source .env && set +a && exec java -Djava.util.logging.config.file=/app/logging.properties $JVM_ARGS -jar app.jar
