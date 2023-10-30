FROM amazoncorretto:11-alpine3.17-jdk
ENV AWS_XRAY_CONTEXT_MISSING LOG_ERROR
ENV LC_ALL=C.UTF-8
ARG SPRING_PROFILE=dev
ARG SERVICE_NAME=cdp-portalbe
ARG APP_VERSION=0.0.1-SNAPSHOT
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILE}
ENV JAVA_HEAP_XMS 1024m
ENV JAVA_HEAP_XMX 2048m

RUN mkdir deploy
COPY build/libs/${SERVICE_NAME}-${APP_VERSION}.jar /source001/boot.jar
RUN chmod +x source001/boot.jar
COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]