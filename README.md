## 도커 실행 방법
```shell
./gradlew bootJar
```

```shell
docker build -t domino:latest .  
```

```shell
docker run --rm -it -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=local" domino:latest
```

## swagger ui
```http request
http://localhost:8080/swagger-ui/index.html#
```

## API docs
```http request
http://localhost:8080/v3/api-docs
```
