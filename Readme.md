# Notes API

REST API for creating and managing notes. Spring Boot 4, Java 21, PostgreSQL.

**Live:** http://92.4.93.229:8080/notes

## Features
- CRUD endpoints for notes
- Input validation with per-field error messages
- Global exception handling (404, 400)
- Dockerized, deployed on Oracle Cloud
- CI/CD with GitHub Actions (tests on push, auto-deploy)

## Endpoints
| Method | Path | Result |
|---|---|---|
| GET | /notes | list all |
| GET | /notes/{id} | one note, 404 if missing |
| POST | /notes | create, 201 |
| PUT | /notes/{id} | update |
| DELETE | /notes/{id} | delete, 204 |

## Run locally
(steps: clone, create .env, docker compose up)

## Design decisions
## Design decisions
- **DTO for requests:** fields not in the DTO are ignored, so clients can't set `id` or `createdAt`, and only valid input reaches the entity.
- **Global exception handler:** exceptions are caught in one place and returned as clean, consistent JSON (404 for missing notes, 400 with per-field validation messages).
- **Multi-stage Dockerfile:** Maven builds the jar in the first stage; the final image contains only a JRE and the jar, so it's smaller and has fewer tools an attacker could use.
- **Secrets in environment variables:** passwords never enter the code or Git history; `.env` is gitignored.
- **Database not exposed:** the DB port isn't published, so only the app container can reach Postgres over Docker's internal network.