# Car Sales System - CRUD

### <i> Details </i>
Car sales system that follows the following specifications:

#### User
* A user can be registered on the system but there cannot be two with the same cpf
* A user can be deleted from the system.
* A user can be changed in the system.
* A user can be searched on the system.

#### Car

* A car can be searched in the system
* When running the system, the list of cars needs to be automatically entered into the database
* A car can be sold to one or more users.
* A car can be sold to the same user as many times as the user wants.

 ### <i> Frameworks and languages: </i>
- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Spring Boot v2.5.6](https://start.spring.io/)

### <i> Start the Application </i>

In the terminal navigate to the project directory and execute the command:


```bash
$ mvn clean install
```

After run the command:

```bash
$ mvn spring-boot:run
```

Obs: To run the application it is necessary for [Java](https://www.oracle.com/java/technologies/downloads/) and Maven installed on your machine

### <i>Routes</i>

```CUSTOMER: ```
- <b>POST</b> : ```/customer ```

``` 
{
  "name": "name",
  "cpf": "17973787089",
  "birthDate": "1990-12-15"
}
   ```

- <b>GET</b> : ```/customer/{cpf} ``` 
<br />
<br />

- <b>GET</b> : ```/customer ```
<br />
<br />

- <b>PUT</b> : ```/customer/{cpf} ```

``` 
{
  "name": "newName",
  "cpf": "17973787089",
  "birthDate": "1990-12-15"
}
   ```

- <b>DELETE</b> : ```/customer/{cpf} ```

<br />

----------------------------------------------------

``` CAR: ```


- <b>GET</b> : ```/car ```
  <br />
  <br />

- <b>GET</b> : ```/car/{code} ```
  <br />
  <br />

- <b>POST</b> : ```/car/sell ```
``` 
{
  "customerCpf": "17973787089",
  "carCode": 133520
}
   ```



