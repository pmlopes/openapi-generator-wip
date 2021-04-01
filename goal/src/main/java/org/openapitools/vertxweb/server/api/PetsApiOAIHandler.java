package org.openapitools.vertxweb.server.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import org.openapitools.vertxweb.server.api.impl.PetsApiOAIHandlerImpl;

@VertxGen
public interface PetsApiOAIHandler {

    static PetsApiOAIHandler create() {
        return new PetsApiOAIHandlerImpl();
    }

    @Fluent
    PetsApiOAIHandler mount(RouterBuilder builder);

    @Fluent
    PetsApiOAIHandler createPetsHandler(Handler<RoutingContext> handler);

    @Fluent
    PetsApiOAIHandler listPetsHandler(Handler<RoutingContext> handler);

    @Fluent
    PetsApiOAIHandler showPetsByIdHandler(Handler<RoutingContext> handler);
}
