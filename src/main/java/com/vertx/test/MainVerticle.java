package com.vertx.test;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {

        vertx.deployVerticle(new MainFeedApplication());

        vertx.deployVerticle(new RXMainFeedApplication());

        vertx.deployVerticle(new HttpServerVerticle());

    }

}
