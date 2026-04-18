# POE2 API Backend

## Java Requirement

- Runtime/JDK: Java 25
- Build: Maven wrapper via `.\mvnw.cmd clean package`

## Start the backend

From the `backend` directory, run:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

The backend starts on `http://localhost:8080`.

## CI

GitHub Actions runs backend CI on pushes and pull requests to `main`. See `backend/.github/workflows/java-ci.yml` for the exact steps and Java version.

# POE2 API Frontend

This frontend is an Angular application for the POE2 API project.

## Start the frontend

After a fresh clone, install the frontend dependencies first. Then start the dev server from the `frontend` directory:

```powershell
cd frontend
npm install
npm start
```

The Angular dev server runs on `http://localhost:4200`.


## Backend connection

This frontend expects the Spring Boot backend to be running on `http://localhost:8080`.

During development, Angular proxies API requests through `frontend/proxy.config.json`, so the app uses `/api/characters` instead of a hardcoded backend URL.
