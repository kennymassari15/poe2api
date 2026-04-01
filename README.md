# poe2api

## Java Requirement

- Runtime/JDK: Java 21
- Build: Maven wrapper via `backend/mvnw`

## CI

- A GitHub Actions workflow exists at `backend/.github/workflows/java-ci.yml`.
- It runs on Java 21 with `./mvnw clean test` (in `backend`).

