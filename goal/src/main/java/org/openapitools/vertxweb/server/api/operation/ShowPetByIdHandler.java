package org.openapitools.vertxweb.server.api.operation;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.RoutingContext;

@VertxGen
@FunctionalInterface
public interface ShowPetByIdHandler {
    void handle(RoutingContext ctx, Long limit);
}
