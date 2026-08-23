FROM selenium/standalone-firefox:latest

USER root

RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app

COPY . .

CMD ["mvn", "test"]
