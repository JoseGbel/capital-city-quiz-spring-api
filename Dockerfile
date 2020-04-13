#FROM jenkins/jenkins:lts
#
#COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
#
#RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
#
#ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
#EXPOSE 8080
FROM openjdk:8
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]