# Memento

## Workstation

Prerequesites :
* Maven 3
* JDK 1.7
* Node.js
* NPM

Get the sources :
> git clone git@github.com:AgileSpirit/Memento.git

Build & run API server side (memento-api):
> cd memento-api
> mvn clean install jetty:run

Build & run presentation side (memento-ui):
> cd memento-ui
> grunt serve

## Delivery

### Memento-API

> cp -rf ~/Memento/memento-api/* ~/tmp/heroku/memento-api
> git add --all
> git ci -a -m
> git push


### Memento-UI

## Product Management

See the features board at https://trello.com/b/Ti8eNjr8/memento

## URLs

http://memento-api.herokuapp.com/api/documents/search?q=&o=0&s=100
http://memento-api.herokuapp.com/monitoring
http://memento-api.herokuapp.com/monitoring/metrics