FROM tomcat:9.0.41-jdk8

# ENV spring.profiles.active=local
WORKDIR /usr/local/tomcat
RUN rm -rf ./webapps/*
COPY ./target/*.war ./webapps/ROOT.war

EXPOSE 8080
CMD $CATALINA_HOME/bin/startup.sh && tail -f $CATALINA_HOME/logs/catalina.out