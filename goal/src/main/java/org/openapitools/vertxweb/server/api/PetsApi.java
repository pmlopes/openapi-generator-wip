package org.openapitools.vertxweb.server.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import org.openapitools.vertxweb.server.api.impl.PetsApiImpl;

@VertxGen
public interface PetsApi {

    static PetsApi create(RouterBuilder builder) {
        return new PetsApiImpl(builder);
    }

    /**
     * Generate generic javadoc, stating that the API params are parsed into the expected type in the context data
     */
    @Fluent
    PetsApi createPetsHandler(Handler<RoutingContext> handler);

    /**
     * Generate generic javadoc, stating that the API params are parsed into the expected type in the context data
     */
    @Fluent
    PetsApi listPetsHandler(Handler<RoutingContext> handler);

    /**
     * Generate generic javadoc, stating that the API params are parsed into the expected type in the context data
     */
    @Fluent
    PetsApi showPetsByIdHandler(Handler<RoutingContext> handler);
}
