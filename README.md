### 로컬 셋팅시 DB 설정방법
로컬에서 DB셋팅시 Docker를 이용하여 구동이 가능합니다.

* DB1번
```
docker run --name postgres12 -t \
      -e POSTGRES_USER="dbuser" \
      -e POSTGRES_PASSWORD="qwer1234@" \
      -e POSTGRES_DB="ap" \
      -p 5432:5432 \
      -d postgres:12
```

* DB2번
```
docker run --name postgres12_2 -t \
      -e POSTGRES_USER="dbuser" \
      -e POSTGRES_PASSWORD="qwer1234@" \
      -e POSTGRES_DB="ap2" \
      -p 5433:5432 \
      -d postgres:12
```

위와 같이 명령실행 후에 서버를 기동하면 페이지가 정상 동작됩니다.
물론 위에 명령 실행 전에 PC에 Docker가 설치되어 있어야 합니다.