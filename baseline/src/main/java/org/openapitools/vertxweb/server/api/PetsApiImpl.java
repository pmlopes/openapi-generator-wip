package org.openapitools.vertxweb.server.api;

import org.openapitools.vertxweb.server.model.Error;
import org.openapitools.vertxweb.server.model.Pet;

import org.openapitools.vertxweb.server.ApiResponse;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.impl.HttpStatusException;

import java.util.List;
import java.util.Map;

// Implement this class

public class PetsApiImpl implements PetsApi {
    public Future<ApiResponse<Void>> createPets() {
        return Future.failedFuture(new HttpStatusException(501));
    }

    public Future<ApiResponse<List<Pet>>> listPets(Integer limit) {
        return Future.failedFuture(new HttpStatusException(501));
    }

    public Future<ApiResponse<Pet>> showPetById(String petId) {
        return Future.failedFuture(new HttpStatusException(501));
    }

}
