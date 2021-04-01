package org.openapitools.vertxweb.server.api.impl;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.openapitools.vertxweb.server.api.PetsApi;

public class PetsApiImpl implements PetsApi {

    private final RouterBuilder builder;

    public PetsApiImpl(RouterBuilder builder) {
        this.builder = builder;

        // default param parsing
        builder.operation("createPets")
                .handler(this::preCreatePets);
        builder.operation("listPets")
                .handler(this::preListPets);
        builder.operation("showPetById")
                .handler(this::preShowPetById);
    }

    @Override
    public PetsApi createPetsHandler(Handler<RoutingContext> handler) {
        builder.operation("createPets").handler(handler);
        return this;
    }

    @Override
    public PetsApi listPetsHandler(Handler<RoutingContext> handler) {
        builder.operation("listPets").handler(handler);
        return this;
    }

    @Override
    public PetsApi showPetsByIdHandler(Handler<RoutingContext> handler) {
        builder.operation("showPetById").handler(handler);
        return this;
    }

    private void preCreatePets(RoutingContext routingContext) {
        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        // delegate
        routingContext.next();
    }

    private void preListPets(RoutingContext routingContext) {
        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        Integer limit = requestParameters.queryParameter("limit") != null ? requestParameters.queryParameter("limit").getInteger() : null;
        routingContext.put("limit", limit);

        // delegate
        routingContext.next();
    }

    private void preShowPetById(RoutingContext routingContext) {
        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String petId = requestParameters.pathParameter("petId") != null ? requestParameters.pathParameter("petId").getString() : null;
        routingContext.put("petId", petId);

        // delegate
        routingContext.next();
    }
}
