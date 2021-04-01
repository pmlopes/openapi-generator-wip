package org.openapitools.vertxweb.server.api.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.openapitools.vertxweb.server.api.ListPetsArguments;
import org.openapitools.vertxweb.server.api.PetsApi;

import java.util.function.Function;

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
    public <T> PetsApi listPets(Function<ListPetsArguments, Future<T>> function) {
        builder.operation("showPetById").handler(ctx -> {
            // TODO: extract the function to handler into a helper as it must be reused
            try {
                function
                        .apply(new ListPetsArguments() {
                            @Override
                            public RoutingContext context() {
                                return ctx;
                            }

                            @Override
                            public Long limit() {
                                return ctx.get("limit");
                            }
                        })
                        .onFailure(ctx::fail)
                        .onSuccess(body -> {
                            if (!ctx.response().headWritten()) {
                                if (body == null) {
                                    ctx
                                            .response()
                                            .setStatusCode(204)
                                            .end();
                                } else {
                                    final boolean hasContentType = ctx.response().headers().contains(HttpHeaders.CONTENT_TYPE);
                                    if (body instanceof Buffer) {
                                        if (!hasContentType) {
                                            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
                                        }
                                        ctx.end((Buffer) body);
                                    } else if (body instanceof String) {
                                        if (!hasContentType) {
                                            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html");
                                        }
                                        ctx.end((String) body);
                                    } else {
                                        ctx.json(body);
                                    }
                                }
                            } else {
                                if (body == null) {
                                    if (!ctx.response().ended()) {
                                        ctx.end();
                                    }
                                } else {
                                    ctx.fail(new HttpStatusException(500, "Response already written"));
                                }
                            }
                        });
            } catch (RuntimeException e) {
                ctx.fail(e);
            }
        });
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
