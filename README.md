[![Build Status](https://travis-ci.org/socobo/socobo-spring.svg?branch=master)](https://travis-ci.org/socobo/socobo-spring)

# socobo-spring
A socobo backend implementation using Spring Boot


## Execute the application 
### Provide a database
Start a Postgres database:
`docker run --name socobo-postgres -e POSTGRES_PASSWORD=socobo -d -p 5432:5432 postgres`
(Or set up your own local postgres instance with the settings located in the applicaiton.properties file)

Connect via psql (or any other tool):
`docker run -it --rm --link socobo-postgres:postgres postgres psql -h postgres -U postgres`

Add the database:
`create database socobo`
`\connect socobo`

### Run the app
Just enter the following command on your console:

`./gradlew bootRun`

## Api

**Basic Auth**

Each endpoint is secured via basic authentifaction by default
user: user
password: socobo

**Register user**

POST /register
```javascript
{
"username":"username",
"email":"email",
"password":"password",
"repeatedPassword":"password confirmation"
}
```
