package org.openapitools.vertxweb.server.api.impl;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.openapitools.vertxweb.server.api.operation.CreatePetsHandler;
import org.openapitools.vertxweb.server.api.operation.ListPetsHandler;
import org.openapitools.vertxweb.server.api.PetsApi;
import org.openapitools.vertxweb.server.api.operation.ShowPetByIdHandler;

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
    public PetsApi createPetsHandler(CreatePetsHandler handler) {
        builder.operation("createPets")
                .handler(ctx -> handler.handle(ctx));
        return this;
    }

    @Override
    public PetsApi listPets(ListPetsHandler handler) {
        builder.operation("showPetById")
                .handler(ctx -> handler.handle(ctx, ctx.get("limit")));
        return this;
    }

    @Override
    public PetsApi showPetByIdHandler(ShowPetByIdHandler handler) {
        builder.operation("showPetById")
                .handler(ctx -> handler.handle(ctx, ctx.get("petId")));
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
