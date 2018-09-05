package com.vertx.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.util.Random;
import java.util.UUID;

public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        Random random = new Random();
        String tx = UUID.randomUUID().toString();

        Router router = Router.router(vertx);

        router.get("/").handler(context -> {

            HttpServerRequest req = context.request();
            HttpServerResponse res = context.response();

            JsonObject json = new JsonObject()
                    .put("userAgent", req.getHeader("User-Agent"))
                    .put("tx", tx)
                    .put("timestamp", System.currentTimeMillis());

            long randomDelay = random.nextInt(1000);

            System.out.println("Random delay: " + randomDelay);

            vertx.setTimer(randomDelay, id -> {

                if (random.nextInt(20) == 1) {

                    res.setStatusCode(500)
                            .end();

                } else {

                    res.putHeader("Content-Type", "application/json")
                            .end(json.encode());

                }

            });

        });

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

        System.out.println("HttpServerVerticle Server 8080");
    }
}
