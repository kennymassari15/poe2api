# POE2 API Frontend

This frontend is an Angular application for the POE2 API project.

## Run the app

From the `frontend` directory:

```powershell
npm install
npm start
```

The Angular dev server runs on `http://localhost:4200`.

## Backend connection

This frontend expects the Spring Boot backend to be running on `http://localhost:8080`.

During development, Angular proxies API requests through [proxy.config.json](/C:/Users/hotle/poe2-project/frontend/proxy.config.json), so the app uses `/api/characters` instead of a hardcoded backend URL.

## Build

```powershell
npm run build
```

The production build output is written to `frontend/build`.
