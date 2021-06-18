# Car Service for Carlease assignment

## Functionality

RESTFull webservice facilitating CRUD operations on a Customer datastore using Spring REST repositories for this
service. Endpoints require a valid JWT issued by an API-gateway sharing the same secret

### URL mapping

- [x] Phase 1: Basic URL mapping docs
- [ ] Phase 2: Swagger docs / REST Docs

```
- [x] GET:    {domain}/cars       - returns al cars
- [x] GET:    {domain}/cars/{id}  - returns specific cars
- [x] POST    {domain}/cars       - create new cars
- [x] DELETE: {domain}/cars/{id}  - delete specific cars
- [x] PUT:    {domain}/cars/{id}  - update specific cars
- [x] GET:    {domain}/cars/{id}/leaserate?{mileage,interest,duration} - calculculates leaserate for given car. required parameters
```

Sample POST/PUT Body

```json
{
  "make": "volkswagen",
  "model": "golf",
  "version": "gte",
  "doorCount": 4,
  "co2Emission": 123.45,
  "grossPrice": 42000,
  "nettPrice": 35000
}
```

## Run application

This service currently runs on port 9092

In development use dev profile:

```shell
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Authentication

This service requires a valid JWT to use any endpoint, the JWT must contain a expiration, ```exp```, claim

When not using an authentication service to create a valid token got to [jwt.io](https://jwt.io/) set the
expiration ```"exp"``` claim to later than current UTC and use a secret in the dev profile:

JWT payload example

```json
{
  "sub": "1234567890",
  "name": "Jaap Test",
  "exp": 1824003711
}
```
