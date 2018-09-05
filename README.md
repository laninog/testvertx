# textvertx

This is a little test app using [vertx](https://vertx.io/)

## Run httpserver

``` bash
./mvnw compile exec:java
```

Open your browser [http://localhost:8080](http://localhost:8080)

## Run no rx feed app

``` bash
./mvnw compile exec:java -Pnorx
```

Open your browser [http://localhost:8081](http://localhost:8081)

## Run rx feed app

``` bash
./mvnw compile exec:java -Prx
```

Open your browser [http://localhost:8082](http://localhost:8082)

## Package

``` bash
./mvnw package
```

vertx.io project