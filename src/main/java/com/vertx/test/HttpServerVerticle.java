package com.vertx.test;

import io.vertx.core.AbstractVerticle;

public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        vertx.createHttpServer().requestHandler(req -> {

              req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x! " + Thread.currentThread().getName());

            }).listen(8080);

        System.out.println("HttpServerVerticle Server 8080");
    }
}
