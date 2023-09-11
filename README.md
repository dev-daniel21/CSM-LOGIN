# Construction Site Management

## This application is a part of multi-microservice project of my own idea and design.
## Its purpose is for managing civil engineering construction sites.

# It is still in development.

Use to build docker image:
    $ docker build -f ./Dockerfile -t csm-login .

Use to run container from image:

    $ docker run --name csm-login -p 8300:8300 -d csm-login
