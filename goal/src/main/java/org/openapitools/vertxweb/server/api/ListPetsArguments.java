package org.openapitools.vertxweb.server.api;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.RoutingContext;

@VertxGen
public interface ListPetsArguments {
    // always present (maybe derive from a base class?)
    RoutingContext context();
    // dynamic
    Long limit();
}
