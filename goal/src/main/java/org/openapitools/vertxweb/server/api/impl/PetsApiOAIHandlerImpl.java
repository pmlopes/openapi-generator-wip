package org.openapitools.vertxweb.server.api.impl;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.openapitools.vertxweb.server.api.PetsApiOAIHandler;

public class PetsApiOAIHandlerImpl implements PetsApiOAIHandler {

    private static final Handler<RoutingContext> NOT_IMPLEMENTED = ctx -> ctx.fail(501);

    private Handler<RoutingContext> createPets = NOT_IMPLEMENTED;
    private Handler<RoutingContext> listPets = NOT_IMPLEMENTED;
    private Handler<RoutingContext> showPetById = NOT_IMPLEMENTED;

    @Override
    public PetsApiOAIHandler mount(RouterBuilder builder) {
        builder.operation("createPets").handler(this::createPets);
        builder.operation("listPets").handler(this::listPets);
        builder.operation("showPetById").handler(this::showPetById);
        return this;
    }

    @Override
    public PetsApiOAIHandler createPetsHandler(Handler<RoutingContext> handler) {
        this.createPets = handler;
        return this;
    }

    @Override
    public PetsApiOAIHandler listPetsHandler(Handler<RoutingContext> handler) {
        this.listPets = handler;
        return this;
    }

    @Override
    public PetsApiOAIHandler showPetsByIdHandler(Handler<RoutingContext> handler) {
        this.showPetById = handler;
        return this;
    }

    private void createPets(RoutingContext routingContext) {
        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        // delegate
        this.createPets.handle(routingContext);
    }

    private void listPets(RoutingContext routingContext) {
        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        Integer limit = requestParameters.queryParameter("limit") != null ? requestParameters.queryParameter("limit").getInteger() : null;
        routingContext.put("limit", limit);

        // delegate
        this.listPets.handle(routingContext);
    }

    private void showPetById(RoutingContext routingContext) {
        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String petId = requestParameters.pathParameter("petId") != null ? requestParameters.pathParameter("petId").getString() : null;
        routingContext.put("petId", petId);

        // delegate
        this.showPetById.handle(routingContext);
    }
}
