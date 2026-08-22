FROM selenium/standalone-firefox:latest

USER root

RUN sudo apt-get update && \
    sudo apt-get install -y maven

WORKDIR /app

COPY . .

CMD ["mvn", "test"]