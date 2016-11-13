# socobo-spring
A socobo backend implementation using Spring Boot

## Run the app 

Just enter the following command on your console:

`./gradlew bootRun`

## Api

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
