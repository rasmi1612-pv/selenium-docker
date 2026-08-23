FROM selenium/standalone-firefox:latest

USER root

RUN sudo apt-get update && \
    sudo apt-get install -y maven

WORKDIR /app

COPY . .

CMD ["sh", "-c", "mvn test; RESULT=$?; echo '===== TEST OUTPUT ====='; ls -la /app/test-output 2>/dev/null || true; echo '===== REPORT FILES ====='; find /app -type f \\( -name '*.html' -o -name '*.xml' \\) 2>/dev/null; exit $RESULT"]
