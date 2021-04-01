package org.openapitools.vertxweb.server.api;

import org.openapitools.vertxweb.server.model.Error;
import org.openapitools.vertxweb.server.model.Pet;

import org.openapitools.vertxweb.server.ApiResponse;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;

public interface PetsApi  {
    Future<ApiResponse<Void>> createPets();
    Future<ApiResponse<List<Pet>>> listPets(Integer limit);
    Future<ApiResponse<Pet>> showPetById(String petId);
}
