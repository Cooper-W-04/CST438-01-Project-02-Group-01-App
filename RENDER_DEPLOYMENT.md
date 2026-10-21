# Render Deployment Guide

This guide explains how to deploy the Spring Boot API to Render and update the Android app to use the hosted API.

The Android app itself is not hosted on Render. Render hosts the API as a web service. The Android app is built and distributed separately, but its production build points to the API's public HTTPS URL.

## 1. Prepare the API repository

Before deploying, confirm that the API repository contains:

- `Dockerfile`
- `docker-compose.yml` (useful locally, but Render will use the Dockerfile)
- `build.gradle.kts`
- `src/main/`
- The Supabase migration under `supabase/migrations/`

The API already has a Dockerfile that builds and runs the Spring Boot application on port `8080`.

Recommended API configuration:

```yaml
server:
  port: ${PORT:8080}
```

This allows Render's `PORT` environment variable to control the server port while keeping `8080` as the local default. If Render successfully detects the existing exposed port, this may not be required, but it is a safer configuration for deployment.

## 2. Create the database

Choose one database provider:

### Option A: Keep using Supabase

1. Open the Supabase project.
2. Run the migration file in the Supabase SQL editor:

   `supabase/migrations/20261006120000_initial_schema.sql`

3. Copy the PostgreSQL connection URL and database password.

### Option B: Use Render PostgreSQL

1. Create a Render PostgreSQL database.
2. Copy its internal connection URL for use by the Render web service.
3. Run the schema migration against that database.
4. Keep the database and web service in the same Render region when possible.

Do not commit database passwords or connection strings to Git.

## 3. Create the Render web service

1. Push the API repository to GitHub.
2. In Render, create a new **Web Service**.
3. Connect the API GitHub repository.
4. Select the Docker runtime so Render uses the repository's `Dockerfile`.
5. Select a service name and region.
6. Choose the desired instance plan.
7. Create the service, but configure the environment variables before relying on the first deploy.

Render supports deploying a web service from a connected Git repository and configuring environment variables and health checks from the service settings. See the [Render Web Services documentation](https://render.com/docs/web-services).

## 4. Add API environment variables in Render

In the Render service's **Environment** settings, add:

| Variable | Value |
|---|---|
| `SUPABASE_DB_URL` | PostgreSQL JDBC connection URL |
| `SUPABASE_DB_PASSWORD` | Database password |

The current API configuration already uses these names in `application.yml`. The database username is currently hardcoded as `postgres`.

If the API is later changed to read the username from an environment variable, also add:

```text
SUPABASE_DB_USERNAME=postgres
```

Save the environment variables and trigger a new deploy. Render injects service environment variables at runtime; do not place secrets in the Dockerfile. See [Render environment variables and secrets](https://render.com/docs/configure-environment-variables).

## 5. Verify the API deployment

Supabase is already configured and contains the application schema and seed data. Confirm that the Render service has these existing Supabase values configured as environment variables:

| Variable | Value |
|---|---|
| `SUPABASE_DB_URL` | Existing Supabase PostgreSQL JDBC connection URL |
| `SUPABASE_DB_PASSWORD` | Existing Supabase database password |

After the Render deployment finishes, Render provides a URL similar to:

```text
https://schedulr-api.onrender.com
```

Test the public time-slot endpoint:

```bash
curl https://schedulr-api.onrender.com/api/v1/time-slots
```

The endpoint should return JSON containing the weekly time slots. This verifies that the Render API can start and connect to the existing Supabase database.

The `/api/v1/time-slots` endpoint is currently public in the API security configuration, so it is the first deployment check. Protected endpoints will require authentication and a valid bearer token.

Optionally configure the Render health check path as:

```text
/api/v1/time-slots
```

## 6. Update the Android production API URL

The Android app currently defaults to the emulator's local API URL:

```text
http://10.0.2.2:8080/
```

For a Render build, pass the hosted HTTPS URL as the Gradle property:

```bash
./gradlew assembleRelease \
  -PapiBaseUrl=https://schedulr-api.onrender.com/
```

The app exposes this value through:

```kotlin
BuildConfig.API_BASE_URL
```

and [`ApiConfig.kt`](app/src/main/java/com/group1/cst438_01_project_02_group_01_app/data/api/ApiConfig.kt) provides it to the future Retrofit client.

Make sure the URL ends with `/`, because Retrofit base URLs require a trailing slash.

## 7. Remove local HTTP configuration for production

Before creating the production Android build, remove or disable this manifest setting:

```xml
android:usesCleartextTraffic="true"
```

It exists only for local development over HTTP. Render uses HTTPS, so the production app should not permit cleartext HTTP traffic.

The `INTERNET` permission should remain:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 8. Configure Google OAuth for production

When Google OAuth is implemented:

1. Keep the Android OAuth client configured with the app's package name and release SHA-1 certificate.
2. Add the release signing certificate fingerprint separately from the debug fingerprint.
3. Configure the API to validate the Google token's issuer and audience.
4. Use the Render HTTPS API URL for production API requests.
5. Never put a Google client secret in the Android application.

The Android app should send the resulting token to the hosted API using:

```http
Authorization: Bearer <token>
```

## 9. Final production checklist

- [ ] API deploys successfully on Render.
- [ ] Existing Supabase schema and seed data are available.
- [ ] `SUPABASE_DB_URL` is configured in Render.
- [ ] `SUPABASE_DB_PASSWORD` is configured in Render.
- [ ] API responds at `/api/v1/time-slots`.
- [ ] Android release build uses the Render HTTPS URL.
- [ ] `android:usesCleartextTraffic="true"` is removed for production.
- [ ] Retrofit uses `BuildConfig.API_BASE_URL` instead of a hardcoded URL.
- [ ] Google OAuth release SHA-1 is configured.
- [ ] Protected API endpoints reject missing or invalid tokens.
- [ ] No secrets are committed to either repository.

## Useful Render documentation

- [Web Services](https://render.com/docs/web-services)
- [Docker deployments](https://render.com/docs/docker)
- [Environment variables and secrets](https://render.com/docs/configure-environment-variables)
