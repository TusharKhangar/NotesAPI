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
(your 5 sentences)