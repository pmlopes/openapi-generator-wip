package org.openapitools.vertxweb.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.openapi.RouterBuilderOptions;

import org.openapitools.vertxweb.server.api.PetsApiOAIHandler;

public class HttpServerVerticle extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(HttpServerVerticle.class);


    public static void main(String[] args) {
        Vertx.vertx()
                .deployVerticle(new HttpServerVerticle());
    }

    private static final String specFile = "src/main/resources/openapi.yaml";

    @Override
    public void start(Promise<Void> startPromise) {
        RouterBuilder.create(vertx, specFile)
                .map(builder -> {
                    builder.setOptions(new RouterBuilderOptions()
                            // For production use case, you need to enable this flag and provide the proper security handler
                            .setRequireSecurityHandlers(false)
                    );

                    PetsApiOAIHandler.create()
                            .createPetsHandler(ctx -> {
                                logger.info("createPets()");

                                // TODO: Implement the operation
                                ctx.fail(501);
//                                ctx
//                                        .response()
//                                        .setStatusCode(201)
//                                        .end();
                            })
                            .listPetsHandler(ctx -> {
                                logger.info("listPetsHandler()");
                                logger.debug("Parameter limit is " + ctx.get("limit"));

                                // TODO: Implement the operation
                                ctx.fail(501);
//                                ctx
//                                        .json(new JsonArray()
//                                                .add(new Pet(1L, "Tom", "cat"))
//                                                .add(new Pet(2L, "Jerry", "mouse")));
                            })
                            .showPetsByIdHandler(ctx -> {
                                logger.info("showPetById()");
                                logger.debug("Parameter petId is " + ctx.get("petId"));

                                // TODO: Implement the operation
                                ctx.fail(501);
//                                ctx
//                                        .json(new Pet(1L, "Tom", "cat"));
                            })
                            .mount(builder);

                    Router router = builder.createRouter();
                    router.errorHandler(400, this::validationFailureHandler);

                    return router;
                })
                .compose(router ->
                        vertx.createHttpServer()
                                .requestHandler(router)
                                .listen(8080)
                )
                .onSuccess(server -> System.out.println("Server listening at http://0.0.0.0:" + server.actualPort()))
                // Complete the start promise
                .<Void>mapEmpty().onComplete(startPromise);
    }

    private void validationFailureHandler(RoutingContext rc) {
        rc.response().setStatusCode(400)
                .end("Bad Request : " + rc.failure().getMessage());
    }
}
