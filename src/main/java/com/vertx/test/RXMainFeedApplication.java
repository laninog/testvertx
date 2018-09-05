package com.vertx.test;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;


public class RXMainFeedApplication extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        WebClient client = WebClient.create(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestStream().toFlowable()
                .flatMapCompletable(req ->

                    client.getAbs("https://twitter.com/vertx_project")
                            .rxSend()
                            .map(HttpResponse::bodyAsString)
                            .onErrorReturn(t ->
                                "Can't access the twitter feed: " + t.getMessage()
                            )
                            .doOnSuccess(res -> req.response().end(res))
                            .toCompletable()

                ).subscribe();

        server.listen(8082);

        System.out.println("RXMainFeedApplication Server 8082");

    }

}
