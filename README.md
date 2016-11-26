[![Build Status](https://travis-ci.org/socobo/socobo-spring.svg?branch=master)](https://travis-ci.org/socobo/socobo-spring)

# socobo-spring
A socobo backend implementation using Spring Boot


## Execute the application 
### Provide a database
Start a Postgres database:
```bash
docker run --name socobo-postgres -e POSTGRES_PASSWORD=socobo -d -p 5432:5432 postgres
```
(Or set up your own local postgres instance with the settings located in the applicaiton.properties file)

Connect via psql (or any other tool):
```bash
docker run -it --rm --link socobo-postgres:postgres postgres psql -h postgres -U postgres
```

Add the database:
```bash
create database socobo
\connect socobo
```

### Run the *frontend* and *backend* (w/o watch file changes)
Just enter the following command on your console:
```bash
./gradlew clean build bootRun
```

_backend available under localhost:8181_
_frontend available under localhost:3000_

### Run the *backend* only (w/o watch file changes)
```bash
./gradlew backend:clean backend:build backend:bootRun
```

_backend available under localhost:8181_

### Run the *frontend* only (w/o watch file changes)
```bash
./gradlew frontend:npmClean frontend:npm_install frontend:npm_run_start
```

_frontend available under localhost:3000_

### Run the *frontend* only (w/ watch file changes)
```bash
./gradlew frontend:npmClean frontend:npm_install frontend:npm_run_start-w
```

_frontend available under localhost:3000_

## Api

**Basic Auth**

Each endpoint is secured via basic authentifaction by default
user: user
password: socobo

**Register user**

POST /register
```json
{
    "username":"username",
    "email":"email",
    "password":"password",
    "repeatedPassword":"password confirmation"
}
```
