# Goals microservice

### Kotlin + Spring CRUD application.

#### You can find the following operations:
- GET /goals
- GET /goals/{id}
- POST /goals
- PUT /goals/{id}
- PATCH /goals/{id}
- DELETE /goals/{id}

### Clone the project:
```sh 
git clone https://github.com/gabrielbabler/ms-goals.git
``` 

Then go into the directory you just cloned:

```sh
cd ms-goals
```

### Run the following commands to build the project:

To download all dependencies and build it:

```sh 
mvn clean install
``` 

To build the postgres image through docker-compose:

```sh 
docker-compose -f /src/main/resources/docker-compose/docker-compose.yml up -d
``` 

#### To check if postgres image is running good (via terminal):

```sh 
docker ps
``` 

## Run ms-goals in a container:

Create a new image:
```sh 
docker image build .
``` 

Once it finishes, it will generate the image id, get it and then run:

```sh 
docker container run -e SPRING_PROFILES_ACTIVE=dev {id}
``` 

This way, we are initializing the microservice pointing to the dev environment. We are using the variables present on the application-dev.yml 