# PlayReady API

## Start the Database

> Ensure Docker is running on your machine.

```bash
  docker compose up -d
```

## Start the Application

> Make sure the database is accessible before starting.

```bash
  ./mvnw spring-boot:run
```

By default, the application runs with the `dev` profile active, which auto-populates the database with sample data and three default users (`gebruiker`, `admin`, and `huurder`).

### Switching Profiles

To run the application with a different profile (e.g., `prod`), specify the profile as a JVM argument:

```bash
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

Or by passing it as a program argument:

```bash
  ./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

Make sure you have a corresponding `application-prod.properties` file with the appropriate configuration.

## Run Tests

> Ensure the database is running and reachable.

```bash
  ./mvnw test
```

Tests will run using the active profile configuration.

## Configuration

The active profile is set in `src/main/resources/application.properties`:

```properties
spring.profiles.active=dev
```

- The **dev** profile uses `application-dev.properties` to configure a local database and automatically load test data.
- For **production** or other environments, define additional profile-specific property files (e.g., `application-prod.properties`) and set `spring.profiles.active` accordingly.

## Default Users

When running with the `dev` profile, the database is initialized with three users:

| Username   | Password  |
|------------|-----------|
| gebruiker  | gebruiker |
| admin      | admin     |
| huurder    | huurder   |

Passwords are set in the database initialization scripts (`data-dev.sql`). Refer to these scripts to view or change them.
