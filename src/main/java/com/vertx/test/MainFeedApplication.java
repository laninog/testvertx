package com.vertx.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.client.WebClient;


public class MainFeedApplication extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        WebClient client = WebClient.create(vertx);

        vertx.createHttpServer().requestHandler(req -> {

            client.getAbs("https://twitter.com/vertx_project")
                    .send(res -> {

                        if(res.failed()) {
                            req.response().end("Can't access the twitter feed: " + res.cause().getMessage());
                        } else {
                            req.response().end(res.result().bodyAsString());
                        }

                    });

        }).listen(8081);

        System.out.println("MainFeedApplication Server 8081");

    }

}
