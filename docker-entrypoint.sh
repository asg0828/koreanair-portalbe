#!/bin/sh
### env 설정 ###
# for SECRET in $(ls /etc/secret/); do export $SECRET=$(cat /etc/secret/$SECRET); done
# 변경필요
### app 설정 ###
# # JAVA_OPTS="${JAVA_OPTS} ${JAVA_AGENT}"
JAVA_OPTS="${JAVA_OPTS} -XX:InitialRAMPercentage=70.0 -XX:MaxRAMPercentage=70.0"
JAVA_OPTS="${JAVA_OPTS} -XX:MaxMetaspaceSize=256m"
JAVA_OPTS="${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom"
JAVA_OPTS="${JAVA_OPTS} -Dspring.backgroundpreinitializer.ignore=true"
JAVA_OPTS="${JAVA_OPTS} -Xlog:gc*=debug:file=/var/log/gc.log:time,level,tags"

### app 실행 ###
echo "JAVA_OPTS=${JAVA_OPTS}"
exec java ${JAVA_OPTS} -jar /source001/boot.jar