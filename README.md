# PlayReady API

## start database

> make sure docker is running

```bash
  docker compose up -d
```

## start application

> make sure the database is reachable

```bash
  ./mvnw spring-boot:run
```

## running tests

> make sure the database is reachable

```bash
  ./mvnw test
```